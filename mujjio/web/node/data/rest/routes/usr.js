var validations = require('../services/db').Db;
var Account = require('../model/account').Account;
var Person = require('../model/person').Person;
   
module.exports = function(app){

    //http://localhost:8800/rest/people/signup
    //{"email":"suhailski@gmail.com","password":"password","givenName":"Suhail","familyName":"Manzoor","gender":"male","language":"english","dd":"10","mm":"11","yyyy":"1968","noage":"true", "primary":"true"}

    app.post('/people/signup', function(req, res){
                                                           
        try{
            var account = new Account(req.body);
            account.validate();           
            var db = new Db();
            db.save(account.data, account.className);
            
            var person = new Person(req.body);
            person.validate();
            db.save(person.data, person.className);
            
            res.writeHead(200, {'Content-Type': 'application/json'})
            res.end(JSON.stringify("person"));                        
        }catch(err){
           console.log(err);
            res.writeHead(412, {'Content-Type': 'application/json'})
            res.end(JSON.stringify(err));
        }        
    });

    app.get('/people/:id', function(req, res){
        
        Account.findOne({'email': req.params.id}, function(err, doc){
            if (err) throw err;
            
            console.log('doc ' + doc.profiles.pop().toString());
            console.log('err ' + err);
            //res.writeHead(200, { 'Content-Type': 'application/json' })
            
            var objToJson = { };
            objToJson.response = res;
            
            res.send(JSON.stringify(doc.profiles.pop()));
            //res.end();
        });
        
        //res.send('user ' + req.params.id);
    });
};
