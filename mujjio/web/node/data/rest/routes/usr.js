var Account = require('../model/account').Account;
var Person = require('../model/person').Person;
Q = require("q");
msg = require('../services/mq').MQ
BSON = require('mongodb').BSONPure,
ObjectID = require('mongodb').BSONPure.ObjectID,
config = require('../../../config').Configuration;

//define two outcomes that are to run in parallel with Q
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

module.exports = function(app){
    var db = new Storage();       
    //http://localhost:8800/rest/people
    //{"email":"suhailski@gmail.com","password":"password","givenName":"Suhail","familyName":"Manzoor","gender":"male","language":"english","dd":"10","mm":"11","yyyy":"1968","noage":"true", "primary":"true"}

    app.post('/people', function(req, res){
                                                           
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
                        var search = outcomes.message(config.search.queue, config.search.exchange, person.className, perso);
                        var emalMessage = {
                            to : acco.email,
                            type : config.Status.initial,
                            name : perso.displayName,
                            uuid : perso._id,
                            locale : 'en',
                            template : 'welcome',
                            url : config.endpoints.activate + '?uid=' + perso._id
                        };
                        console.log(emalMessage);                        
                        var email =  outcomes.message(config.email.queue, config.email.exchange, account.className, emalMessage);
                        Q.join(render, search, email);
                    });
                });                                       
            });                       
        }catch(err){
            console.log(err);
            res.writeHead(412, {
                'Content-Type': 'application/json'
            })
            res.end(JSON.stringify(err));
        }        
    });

    //{"email":"suhailski@gmail.com","password":"password"}
    //http://localhost:8800/rest/people/initial
    app.post('/people/initial', function(req, res){    
        db.fetch(req.body, 'account', function(err, account){    
            if(err) {
                res.writeHead(404, {'Content-Type': 'application/json'})            
                res.end(JSON.stringify(err));
            } else {
                var data = {
                    status : config.Status.registered
                }
                
                db.update('account', account._id, data, function(err, result){
                    if(err) {
                        res.writeHead(500, {'Content-Type': 'application/json'})            
                        res.end(JSON.stringify({}));                                        
                    }
                    res.writeHead(200, {'Content-Type': 'application/json'})
                    res.end(JSON.stringify(err));
                });                
            }
        });        
    });    
    
    app.get('/people/:id/:profile', function(req, res){        
        db.fetch(null, 'people', function(error, perso){ 
            res.writeHead(200, {'Content-Type': 'application/json'});       
            res.end(JSON.stringify(perso));
        });                                     
    });
    
    /**
     * Checks the activation status based on the uid and status that is 'initial'
     * This link is used when the user clicks on the the link given in the email
     */
    app.get('/people/activate', function(req, res){
        
        var query = {
            _id : ObjectID.createFromHexString(req.query.uid),
            status : config.Status.initial
        }
        
        db.fetch(query, 'account', function(error, account){    
            if(account) {
                res.writeHead(200, {'Content-Type': 'application/json'});            
                res.end(JSON.stringify(account));
            } else {
                res.writeHead(404, {'Content-Type': 'application/json'});
                res.end(JSON.stringify({}));                
            }
        });                                     
    });          
};
