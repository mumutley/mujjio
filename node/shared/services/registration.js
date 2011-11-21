var scramble = require('./crypto').Crypto;
var store = require('./db').Storage;
var msg = require('./mq').MQ;
var config = require('../../config').Configuration;
var codes = config.codes;
var mime = config.mime;

var Account = require('../../data/rest/model/account').Account;
var Person = require('../../data/rest/model/person').Person;
var session = require('./session').Session;

var mongodb = require('mongodb');
var BSON = mongodb.BSON;
var REF = mongodb.DBRef;
var OID = mongodb.ObjectID;
var BSON = mongodb.BSONPure;

var Q = require("q");

Registration = function() {
    
    session = new Session();
}

Registration.prototype.register = function(req, res, callback) {    
    var account = new Account(req.body);
    var person = new Person(req.body);

    try {
        //it looks strange but its used for Q
        account = account.validate();           
    } catch(err){
        console.log(err);
        session.write(codes.INVALID, mime.JSON, err, res);
        return;
    }
    
    try {
        //it looks strange but its used for Q
        person = person.validate();
    } catch(err){
        console.log(err);
        session.write(codes.INVALID, mime.JSON, err, res);
        return;
    }       
     
     
    var outcomes = {
		write : function(payload, res) {
            session.write(codes.OK, mime.JSON, payload, res);
			return this;
		},
		message : function(que, exch, name, message) {
			var msgr = new MQ();
			//send person out to the queue
			msgr.send(exch, que, name, message);                                                    
			return this;
		}
    }
    
    var db = new Storage();    

	db.save(person.data, person.className, function(error, perso) {

		// save a reference to the default profile to the account.
		account.data.profiles = [];		
        var ref = new BSON.DBRef(person.className, perso._id);
		account.data.profiles.push(ref);
        
		db.save(account.data, account.className, function(error, acco) { 
            //get the default groups
			var groups = person.getDefaultRelations();
		    for(var i = 0; i < groups.length; i++) {                                
                groups[i].owners.push(perso._id);
				db.save(groups[i], 'groups', function(error, grp) {	
                    //no operations
				});
			}        

            //execute the http response and message to the search
			var render = outcomes.write(acco, res);            

			var search = outcomes.message('rest', 'search', person.className, perso);
			var payload = {
				to : acco.email,
				type : 'initial',
				name : perso.displayName,
				uuid : perso._id,
				locale : 'en',
				template : 'welcome',
				url : 'http://localhost:8080/login.htm' + '?uid=' + acco._id
			};                     
			//var email =  outcomes.message('data', 'email', account.className, payload);
            //session.write(codes.OK, mime.JSON, res.end(JSON.stringify(payload)), res);            
			callback(error, person);
	   });     	  
});    
    
    /*scramble =  new Crypto();
    
    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var email = req.body.emailCopy;
    var password = req.body.password;
    var day = req.body.birthday;
    var month = req.body.birthmonth;
    var year = req.body.birthyear;
    var gender = req.body.gender;
    var language = req.body.language;
    
    var person = {};
    person.firstName = req.body.firstName;
    person.lastName = req.body.lastName;
    person.email = req.body.email;
    person.birthday = new Date(req.body.birthyear, req.body.birthmonth, req.body.birthday);
    person.gender = req.body.gender;
    person.language = req.body.language; 
    
    scramble.scramble(req.body.password, function(data) {
        person.password = data;
    });
    //do a rest call and then go back
*/
}

exports.Registration = Registration;
