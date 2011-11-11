var Base = require('./base').Base;
var config = require('../../../config').Configuration;

var Group = exports.Group = function (name, owner, visibility) {
	this.data = {};
 	//the name of the group
 	this.data.name = name;
 	this.data.owner = owner;
 	this.data.status = visibility;
 	
 	this.name = {
    	value : name,
        type : String,
        validations : ['notNull','notEmpty'] 		
 	},

 	//the owner of group
 	this.owner - {
 		value : owner,
 		type : String,
 		validations : []
 	}
	
    return this; 
}

Group.prototype.__proto__ = Base.prototype;
Group.prototype.constructor = Group
Group.prototype.className = "group";

/*
A Group is made of zero or more members. A member has the following structure:
member : {
	oid : 'suhailski',
	liked : 'false' //signifies if the relationship has been accepted.
	incept : date //date when sent
	link : date // date when the link was made symettrical
	visibility : 'private' //the relationship is private by default.
	//relationships have a visibility by default. If a member specifies
	//that a relationship should remain private, then the other members
	//of the relationship group cannot see the member in the relationship
	transferable : {
		mode : none // if a group was shared or 'sold' the membership is 
		//transferred automatically. The different types of modes are 
		//automatic, monitised and none. 
		value : 0.0 // its possible for a relationship to be monitised. value
		//tells you how much the transfer will cost.
	}
The member document will be embedded in the group
*/