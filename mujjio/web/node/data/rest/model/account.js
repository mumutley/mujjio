var Db = require('../services/db').Db;
var util = require("util");

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

//var F = function() {}
var Base = exports.Base = function() {};
Base.prototype.__proto__  = Db.prototype;
exports.Base.prototype.print = function() {    
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            console.log("property " + prop + " value is " + this[prop]);
        }
    }
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
    console.log("validation called");
    var error = {};
    error.message = "Account data is invalid";
    error.code = "10001";
    throw error;
    return this;
}


