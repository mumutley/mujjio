var Base = require('./base').Base;
var config = require('../../../config').Configuration;

/**
 * Account constructor that takes a request body
 * and converts it to its internal state.
 */
var Account = exports.Account = function (request) {
    
    this.data = {};
    this.data.email = request.email;
    this.data.password = request.password;
    
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
    
    this.data.status = config.Status.initial;
    this.data.created = new Date();
    
    return this;    
}

Account.prototype.__proto__ = Base.prototype;
Account.prototype.constructor = Account
Account.prototype.className = "account";