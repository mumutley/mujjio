var Base = require('./base').Base;
var config = require('../../../config').Configuration;

var Group = exports.Group = function (name, visibility) {
	this.data = {};
 	//the name of the group
 	this.data.name = name;
 	this.data.status = visibility;
 	this.data.owners = [];
 	this.data.members = [];

 	this.name = {
    	value : name,
        type : String,
        validations : ['notNull','notEmpty'] 		
 	}
 	
    return this; 
}

Group.prototype.__proto__ = Base.prototype;
Group.prototype.constructor = Group
Group.prototype.className = "group";