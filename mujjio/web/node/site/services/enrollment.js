
var scramble = require('./crypto').Crypto

Enrollment = function() {      
}

Enrollment.prototype.register = function(req, callback) {
    
    console.log(req.body);
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
    callback(person);
}

exports.Enrollment = Enrollment;