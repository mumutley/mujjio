var Db = require('../services/db').Db;

var Base = exports.Base = function() {};
Base.prototype.__proto__  = Db.prototype;
exports.Base.prototype.print = function() {    
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            console.log("property " + prop + " value is " + this[prop]);
        }
    }
}
