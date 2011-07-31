var check = require('validator').check,
    sanitize = require('validator').sanitize
    

var Registration = {};

Registration.Status = {
    registered : 'registered'
    , valid : 'valid'
    , expired : 'expired'
    , blocked : 'blocked'
}

var validator = {};


var isValid = function(value, type) {
    
    try {
        check(value)[type]();
        return value;
    } catch(err) {
        return false;
    }
}

validator.account = function(request) {
  /*{ email: 'suhailski@gmail.com',
  /*password: 'password',
  givenName: 'Suhail',
  familyName: 'Manzoor',
  gender: 'male',
  language: 'english',
  dd: '10',
  mm: '11',
  yyyy: '1968',
  noage: 'true' }*/

    
    var email = isValid(request.email,'isEmail')
    var password = isValid(request.password, 'notNull');
    var emptyPassword = isValid(request.password, 'notEmpty');
   
    if(email && password && emptyPassword) {
        var account = {};
        account.email = request.email;
        account.password = request.password;
        account.status = Registration.Status.registered;
        return account;    
    }
    
    return false;
};

validator.profile = function(request) {
    var firstName = isValid(request.email)
}

module.exports = validator;