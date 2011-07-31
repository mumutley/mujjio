var Base = require('./base').Base;

var Person = exports.Person = function (request) {    
    this.primary = request.primary; 
    this.gender = request.gender;
    this.givenName = request.givenName;
    this.familyName = request.familyName;
    this.displayName = request.givenName + " " + request.familyName;
    this.joined = new Date();
    this.language = request.language;        
    var idex = request.email.indexOf("@");
    this.nickName = request.email.substring(0, idex);
}

Person.prototype.__proto__ = Base.prototype;
Person.prototype.constructor = Person

exports.Person.prototype.validate = function() {
    console.log("person validation called");
    return this;
}
