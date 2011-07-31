//var mongoose = require('mongoose');
var schema = require('../model/schema');
var validations = require('../services/validations');
var Account = require('../model/account').Account;
    

// Access the mongoose-dbref module
//var dbref = require("mongoose-dbref");
//var utils = dbref.utils;

// Install everything
//var loaded = dbref.install(mongoose);

module.exports = function(app){

    //http://localhost:8800/rest/people/signup
    //{"email":"suhailski@gmail.com","password":"password","givenName":"Suhail","familyName":"Manzoor","gender":"male","language":"english","dd":"10","mm":"11","yyyy":"1968","noage":"true"}

    app.post('/people/signup', function(req, res){
                                                             
        try{
            var account = new Account(req.body);
            account.validate().save();
            res.writeHead(200, {'Content-Type': 'application/json'})
            res.end(JSON.stringify(account)); 
        }catch(err){
            res.writeHead(412, {'Content-Type': 'application/json'})
            res.end(JSON.stringify(error));
        }
        
        return;
        
        var account = validations.account(req.body);
        if(account) {
            console.log(account);
            res.writeHead(200, {'Content-Type': 'application/json'})
            res.end(JSON.stringify(account));
            return;
        }
        
        res.writeHead(412, {'Content-Type': 'application/json'})
        var error = {};
        error.message = "Account data is invalid";
        error.code = "10001";
        res.end(JSON.stringify(error));
        
        return;
        
        var profile = new Person();
                        
        profile.primary = 'true'; 
        profile.gender = req.body.gender;
        profile.givenName = req.body.givenName;
        profile.familyName = req.body.familyName;
        profile.displayName = req.body.givenName + " " + req.body.familyName;
        profile.joined = new Date();
        profile.language = req.body.language;        
        var idex = req.body.email.indexOf("@");
        profile.nickName = req.body.email.substring(0, idex);
                
        profile.save(function (err) {
            if (!err) {
                console.log('saved profile instance!');            
                var signup = new Account();                   
                signup.email = req.body.email;
                signup.password = req.body.password;
                signup.created = new Date();
                signup.status = 'registered';
                
                signup.profiles.push({"$ref" : profile.collection.name, "$id": profile._id});                
                signup.save(function (err) {
                    if (!err) {
                        console.log('saved account instance!');
                    } else {
                        console.log('not saved account instance!' + err);         
                    }
                });             
            } else {
               console.log('not saved profile instance!' + err);             
            } 
        });
        

        res.send('user ' + profile);
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
