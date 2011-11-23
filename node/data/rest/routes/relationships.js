var session = require('../../../shared/services/session').Session;
var config = require('../../../config').Configuration;
var Group = require('../model/group').Group;
var codes = config.codes;
var mime = config.mime;
var mongo = require('mongodb'),
  BSONPure = mongo.BSONPure,
  ObjectId = mongo.ObjectId;

module.exports = function(app) { 

	storage = new Storage();
	session = new Session();

	//add a person to a group creating a relationship
	app.post('/rel/:grid/:pid', function(req, res) {

	});

	//remove a person to a group creating a relationship
	app.delete('/rel/:grid/:pid', function(req, res) {

	});

	//update a relationship
	app.put('/rel/:grid/:pid', function(req, res) {

	});



}