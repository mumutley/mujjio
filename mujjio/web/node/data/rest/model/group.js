var Base = require('./base').Base;
var config = require('../../../config').Configuration;

var Group = exports.Group = function (request) {

	this.data = {};
 	//the name of the group
 	this.name = {
    	value : request.name,
        type : String,
        validations : ['notNull','notEmpty'] 		
 	},

 	//the owner of group
 	this.owner - {
 		value : request.uid,
 		type : String,
 		validations : []
 	}

 	this.data.status = config.Access.private;
    this.data.created = new Date();
}

var Group = exports.Group = function (name, owner) {
	this.data = {};
 	//the name of the group
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

 	this.data.status = config.Access.private;
    this.data.created = new Date();	
}

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