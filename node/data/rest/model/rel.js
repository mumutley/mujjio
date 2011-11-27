var Base = require('./base').Base;
var config = require('../../../config').Configuration;

var Relationship = exports.Relationship = function (from, strenght, visibility, transfer_mode, transfer_price) {

	this.data = {};
	this.data.transferable = {};
	this.data.transferable.mode = transfer_mode || config.relationships.transferable.AUTOMATIC;
	this.data.transferable.price = transfer_price || 0.0;
	this.data.strength = strenght || config.relationships.strength.WEAK;
	this.data.access = visibility;
	this.data.to = from;	
	return this;
}

Relationship.prototype.__proto__ = Base.prototype;
Relationship.prototype.constructor = Relationship
Relationship.prototype.className = "relationship";


