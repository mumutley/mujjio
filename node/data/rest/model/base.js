var check = require('validator').check,
Validator = require('validator').Validator;
sanitize = require('validator').sanitize;

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
    var error = {};
    
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            if(this[prop].hasOwnProperty('validations') && !(this[prop].hasOwnProperty('collect')) ){
                var valids = this[prop].validations;
                var value = this[prop].value;  
                //for every validation in the group
                for(var i = 0; i < valids.length; i++){                    
                    try{
                        //using validations to check
                        check(value)[valids[i]]();
                        truth.push(true);   
                    }catch(err){
                        error.cause = valids + " is incorrect " + value;
                        truth.push(false);   
                    }                         
                }                
            } else if(this[prop].hasOwnProperty('validations') && this[prop].hasOwnProperty('collect')) { 
                
                
                var alloweds = this[prop].collect;
                var val = this[prop].value;
                if(alloweds.contains(val)){
                    truth.push(true);   
                } else {
                    error.cause = alloweds + " is not correct " + val;
                    truth.push(false);    
                }                
            }
        }
    }
        
    if(truth.contains(false)) {        
        error.message = this.className +  " data is invalid";
        error.code = "412";       
        throw error;
    }

    
    
    return this;    
}