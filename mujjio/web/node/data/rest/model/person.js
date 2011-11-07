var Base = require('./base').Base;
var config = require('../../../config').Configuration;
var group = require('./group').Group;

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
    this.data.relationships = [];
    
    for(var i = 0; i < config.relationships.en.elements.length; i++) {
        var rel = config.relationships.en.elements[i];
        group = new Group(rel.name, this.data.nickName, rel.visibility);
        this.data.relationships.push(group);
    }

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