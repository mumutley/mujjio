
var scramble = require('./crypto').Crypto
var Account = require('../../data/rest/model/account').Account;
var Person = require('../../data/rest/model/person').Person;

Enrollment = function() {      
}

Enrollment.prototype.register = function(req, res, callback) {
    
    try {
        var account = new Account(req.body);
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
        var person = new Person(req.body);
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