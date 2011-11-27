var session = require('../../../shared/services/session').Session;
var config = require('../../../config').Configuration;
var Group = require('../model/group').Group;
var codes = config.codes;
var mime = config.mime;
var mongo = require('mongodb'),
  BSONPure = mongo.BSONPure,
  ObjectId = mongo.ObjectId;

module.exports = function(app) { 

	db = new Storage();
	session = new Session();

	//add a person to a group creating a relationship. The body has a relationship
	//document that needs to be added to the system
	app.put('/rel/:grid', function(req, res) {
		console.log(req.params.grid);
		console.log(req.body);
		var row = {"members" : req.body};
		db.push("groups", req.params.grid, row, function(err, doc){
			session.write(codes.OK, mime.JSON, req.body, res);	
		});		
	});

	//remove a person to a group creating a relationship
	app.delete('/rel/:grid/:pid', function(req, res) {
	});

	//update a relationship
	app.put('/rel/:grid/:pid', function(req, res) {
	});

	//get all relationship a user has
	app.get('/rel/:pid', function(req, res) { 
	});
}