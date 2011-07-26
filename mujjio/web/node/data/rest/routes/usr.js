var mongoose = require('mongoose');
var schema = require('../model/schema');
var Account = mongoose.model('Account');
var Person = mongoose.model('Person');

module.exports = function(app){

    app.post('/people/signup', function(req, res){
        
        var profile = new Person();
        profile.primary = true; 
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
                signup.save(function (err) {
                    if (!err) console.log('saved account instance!');            
                });                
            } else {
               console.log('not saved profile instance!' + err);             
            } 
        });
        

        res.send('user ' + profile);
    });

    app.get('/people/:id', function(req, res){
        res.send('user ' + req.params.id);
    });
};
