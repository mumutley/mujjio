var Base = require('./base').Base;

var Status = {};
Status = {
    registered  : 'registered', 
    valid     : 'valid', 
    expired   : 'expired', 
    blocked   : 'blocked', 
    initial   : 'initial'
}

/**
 * Account constructor that takes a request body
 * and converts it to its internal state.
 */
var Account = exports.Account = function (request) {
    this.data = request
    
    this.email = {
        value : request.email,
        type : String,
        validations : ['notNull','notEmpty','isEmail']
    };        

    
    this.password = {
        value : request.password,
        type : String,
        validations : ['notNull','notEmpty']
    }
    
    this.data.status = Status.initial;
    this.data.created = new Date();
    
    return this;    
}

Account.prototype.__proto__ = Base.prototype;
Account.prototype.constructor = Account
Account.prototype.className = "Account";



