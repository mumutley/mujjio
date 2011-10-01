
var scramble = require('./crypto').Crypto
var db = require('./db').Storage
var Account = require('../../data/rest/model/account').Account;
var Person = require('../../data/rest/model/person').Person;

Q = require("q");

Enrollment = function() {      
}

Enrollment.prototype.register = function(req, res, callback) {

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
    } catch(err){
        console.log(err);
        res.writeHead(412, {
            'Content-Type': 'application/json'
        })
        res.end(JSON.stringify(err));
        return;
    }       
    
    
    
    Q.join(account, person, function (account, person) {
        var db = new Storage();        
        db.save(person.data, person.className, function(error, perso){                
            // save a reference to the default profile to the account.
            
            account.data.profiles = [];
            account.data.profiles.push(new BSON.DBRef(person.className, perso._id));
            
            console.log("saving " + db);
            
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
    
    scramble =  new Crypto();
    
    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var email = req.body.emailCopy;
    var password = req.body.password;
    var day = req.body.birthday;
    var month = req.body.birthmonth;
    var year = req.body.birthyear;
    var gender = req.body.gender;
    var language = req.body.language;
    
    /*var person = {};
    person.firstName = req.body.firstName;
    person.lastName = req.body.lastName;
    person.email = req.body.email;
    person.birthday = new Date(req.body.birthyear, req.body.birthmonth, req.body.birthday);
    person.gender = req.body.gender;
    person.language = req.body.language;*/    
    
    scramble.scramble(req.body.password, function(data) {
        person.password = data;
    });
    //do a rest call and then go back
    callback(person);
}

exports.Enrollment = Enrollment;