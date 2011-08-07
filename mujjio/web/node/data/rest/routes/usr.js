var Account = require('../model/account').Account;
var Person = require('../model/person').Person;
//BSON = require('mongodb').BSONNative;
Q = require("q");
msg = require('../services/mq').MQ
BSON = require('mongodb').BSONPure;


//define two outcomes that are to run in parallel with Q
var outcomes = {}
outcomes.write = function(payload, res) {
    res.writeHead(200, {'Content-Type': 'application/json'})
    res.end(JSON.stringify(payload)); 
    return this;
};

outcomes.message = function(exch, que, name, message) {
    var msgr = new MQ();
    //send person out to the queue
    msgr.send(exch, que, name, message);                                                    
    return this;
};


module.exports = function(app){
    var db = new Storage();        
    //http://localhost:8800/rest/people/signup
    //{"email":"suhailski@gmail.com","password":"password","givenName":"Suhail","familyName":"Manzoor","gender":"male","language":"english","dd":"10","mm":"11","yyyy":"1968","noage":"true", "primary":"true"}

    app.post('/people/signup', function(req, res){
                                                           
        try{
            var account = new Account(req.body);
            //it looks strange but its used for Q
            account = account.validate();           
            
            var person = new Person(req.body);
            //it looks strange but its used for Q
            person = person.validate();
            
            Q.join(account, person, function (account, person) {
                db.save(person.data, person.className, function(error, perso){                
                    // save a reference to the default profile to the account.
                    account.data.profiles = [];
                    account.data.profiles.push(new BSON.DBRef(person.className, perso._id));
                    db.save(account.data, account.className, function(error, acco){
                        
                        //execute the http response and message to the search
                        //queue in parallel
                        var render = outcomes.write(acco, res);
                        var message = outcomes.message('rest', 'search', person.className, perso);                        
                        Q.join(render, message);//, function (aValue, bValue) {
                            //console.log('account id ' + acco._id);
                        //});                                                
                    });
                });                                       
            });                       
        }catch(err){
           console.log(err);
            res.writeHead(412, {'Content-Type': 'application/json'})
            res.end(JSON.stringify(err));
        }        
    });

    app.get('/people/:id', function(req, res){
        
        db.fetch(null, 'people', function(error, perso){ 
            res.writeHead(200, {'Content-Type': 'application/json'})
            var objToJson = { };          
            res.end(JSON.stringify(objToJson));
        });                                     
    });
};
