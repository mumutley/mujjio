var Base = require('./base').Base;
var Group = require('./group').Group;
var config = require('../../../config').Configuration;


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

    this.firstName = {
        value : request.firstName,
        type : String,
        validations : ['notNull','notEmpty'],
        update : false    
    };
    
    this.lastName = {
        value : request.lastName,
        type : String,
        validations : ['notNull','notEmpty'],
        update : false    
    };   
    
    this.gender = {
        value : request.gender,
        type : String,
        validations : ['contains'],
        collect : ['male','female'],
        update : false
    };
    
    this.language = {
        value : request.language,
        type : String,
        validations : ['contains'],
        collect : ['english','french','german','hindi'],
        update : false
    };

    exports.Person.prototype.getDefaultRelations = function() { 
	var groups = []
        for(var i = 0; i < config.relationships.en.elements.length; i++) {
            var rel = config.relationships.en.elements[i];
            var group = new Group(rel.name, rel.visibility);
            groups.push(group.data);
        }   
	return groups;
    };
};

Person.prototype.__proto__ = Base.prototype;
Person.prototype.constructor = Person;
Person.prototype.className = "people";
