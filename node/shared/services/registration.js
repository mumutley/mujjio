var scramble = require('./crypto').Crypto;
var store = require('./db').Storage;
var msg = require('./mq').MQ;
var config = require('../../config');

var Account = require('../../data/rest/model/account').Account;
var Person = require('../../data/rest/model/person').Person;

var mongodb = require('mongodb');
var BSON = mongodb.BSON;
var REF = mongodb.DBRef;
var OID = mongodb.ObjectID;
var BSON = mongodb.BSONPure;

var Q = require("q");

Registration = function() {      
}

Registration.prototype.register = function(req, res, callback) {

    var account = new Account(req.body);
    var person = new Person(req.body);

    try {
        //it looks strange but its used for Q
        account = account.validate();           
    } catch(err){
        console.log(err);
        res.writeHead(412, {
            'Content-Type': 'application/json'
        })
        res.end(JSON.stringify(err));
        return;
    }
    
    try {
        //it looks strange but its used for Q
        person = person.validate();
        person.relate();        
    } catch(err){
        console.log(err);
        res.writeHead(412, {
            'Content-Type': 'application/json'
        })
        res.end(JSON.stringify(err));
        return;
    }       
     
     
    var outcomes = {
		write : function(payload, res) {
			res.writeHead(200, {
				'Content-Type': 'application/json'
			})
			res.end(JSON.stringify(payload)); 
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
    Q.join(account, person, function (account, person) {
		db.save(person.data, person.className, function(error, perso) {
                        
			// save a reference to the default profile to the account.
			account.data.profiles = [];		
            var ref = new BSON.DBRef(person.className, perso._id);
			account.data.profiles.push(ref);				
			db.save(account.data, account.className, function(error, acco) { 
                //get the default relationships
    			var groups = person.relate();
    		    for(var i = 0; i < groups.length; i++) {
    				db.save(groups[i], 'groups', function(error, grp) {	
                        //no operations
    				});
    			}
               
                //execute the http response and message to the search
				//queue in parallel
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
				console.log(payload);                        
				var email =  outcomes.message('data', 'email', account.className, payload);
				Q.join(render, search, email);				
				callback(person);
		});     
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
