
var session = require('../../../shared/services/session').Session;
var config = require('../../../config').Configuration;
var codes = config.codes;
var mime = config.mime;
var mongo = require('mongodb'),
  BSONPure = mongo.BSONPure,
  ObjectId = mongo.ObjectId;


module.exports = function(app) { 
	storage = new Storage();
	session = new Session();
		       
    //get a specific group
    app.get('/gr/:id', function(req, res){    	
    	var query = {"owners" : BSONPure.ObjectID(req.params.id)};	
    	storage.fetchAll(query, "groups", function(err, collection) {
    		session.write(codes.OK, mime.JSON, collection, res);        	
    	});
    	
    });
};

exports.Registration = Registration;