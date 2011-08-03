var Base = require('./base').Base;

var Person = exports.Person = function (request) {  
    
    this.data = {};
    this.data.primary = request.primary;
    this.data.gender = request.gender;
    this.data.givenName = request.givenName;
    this.data.familyName = request.familyName;
    this.data.displayName = request.givenName + " " + request.familyName;
    this.data.joined = new Date();
    this.data.language = request.language;        
    var idex = request.email.indexOf("@");
    this.data.nickName = request.email.substring(0, idex);

    this.gender = {
        value : request.gender,
        type : String,
        validations : ['contains'],
        collect : ['male','female']
    };
    this.givenName = {
        value : request.givenName,
        type : String,
        validations : ['notNull','notEmpty']
        
    };
    
    this.familyName = {
        value : request.familyName,
        type : String,
        validations : ['notNull','notEmpty']        
    }
    
    this.language = {
        value : request.language,
        type : String,
        validations : ['contains'],
        collect : ['english','french', 'german', 'malayalam']
    };
}

Person.prototype.__proto__ = Base.prototype;
Person.prototype.constructor = Person
Person.prototype.className = "people";