var Base = require('./base').Base;

var Status = {};
Status = {
    registered  : 'registered'
    , 
    valid     : 'valid'
    , 
    expired   : 'expired'
    , 
    blocked   : 'blocked'
    , 
    initial   : 'initial'
}

/**
 * Account constructor that takes a request body
 * and converts it to its internal state.
 */
var Account = exports.Account = function (request) {
    this.email = request.email;
    this.password = request.password;
    this.status = Status.initial;
    this.created = new Date();    
    console.log("constructor called");
    return this;    
}

Account.prototype.__proto__ = Base.prototype;
Account.prototype.constructor = Account


/**
 * Validates the state.
 * Email must be correct
 * Password must be correct
 */
exports.Account.prototype.validate = function() {
    console.log("account validation called");
    /*var error = {};
    error.message = "Account data is invalid";
    error.code = "10001";
    throw error;*/
    return this;
}


