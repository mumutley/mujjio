var Base = require('./base').Base;

var Person = exports.Person = function (request) {  
    
    this.data = {};    
    this.data.gender = request.gender;
    this.data.firstName = request.firstName;
    this.data.lastName = request.lastName;
    this.data.displayName = request.firstName + " " + request.lastName;
    this.data.joined = new Date();
    this.data.language = request.language;     
    var idex = request.email.indexOf("@");
    this.data.nickName = request.email.substring(0, idex);
    this.data.relationships = {};
    
    this.firstName = {
        value : request.firstName,
        type : String,
        validations : ['notNull','notEmpty']        
    };    
    
    this.lastName = {
        value : request.lastName,
        type : String,
        validations : ['notNull','notEmpty']        
    }        
    
    this.gender = {
        value : request.gender,
        type : String,
        validations : ['contains'],
        collect : ['male','female']
    };
    
    this.language = {
        value : request.language,
        type : String,
        validations : ['contains'],
        collect : ['english','french','german','hindi']
    };
}

Person.prototype.__proto__ = Base.prototype;
Person.prototype.constructor = Person
Person.prototype.className = "people";