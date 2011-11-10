var check = require('validator').check,
Validator = require('validator').Validator;
sanitize = require('validator').sanitize,
    Db = require('../services/db').Db;

var Base = exports.Base = function() {};
//Base.prototype.__proto__  = Db.prototype;

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}

exports.Base.prototype.print = function() {    
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            console.log("property " + prop + " value is " + this[prop]);
        }
    }
}

/**
 * Validates the state.
 * Email must be correct
 * Password must be correct
 */
exports.Base.prototype.validate = function() {    
    
    var truth = [];
    
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            if(this[prop].hasOwnProperty('validations') && !this[prop].hasOwnProperty('collect') ){
                var valids = this[prop].validations;
                var value = this[prop].value;  
                //for every validation in the group
                for(var i = 0; i < valids.length; i++){                    
                    try{
                        check(value)[valids[i]]();
                        truth.push(true);   
                    }catch(err){
                        truth.push(false);   
                    }                         
                }                
            } else if(this[prop].hasOwnProperty('validations') && this[prop].hasOwnProperty('collect')) {                
                var alloweds = this[prop].collect;
                var val = this[prop].value;
                if(alloweds.contains(val)){
                    truth.push(true);   
                } else {
                    truth.push(false);    
                }                
            }
        }
    }
    
    if(truth.contains(false)) {
        var error = {};
        error.message = this.className +  " data is invalid";
        error.code = "412";
        throw error;
    }    
    return this;    
}