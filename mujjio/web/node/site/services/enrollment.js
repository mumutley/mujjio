Enrollment = function() {      
}

Enrollment.prototype.register = function(req, callback) {
    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var email = req.body.emailCopy;
    var password = req.body.password;
    var day = req.body.birthday;
    var month = req.body.birthmonth;
    var year = req.body.birthyear;
    var gender = req.body.gender;
    var language = req.body.language;
    
    //do a rest call and then go back
    callback(firstName);
}

exports.Enrollment = Enrollment;