
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
		       
    //get all groups for the user. @id is the person id
    app.get('/gr/:id', function(req, res){    	
    	var query = {"owners" : BSONPure.ObjectID(req.params.id)};	
    	storage.fetchAll(query, "groups", function(err, collection) {
    		session.write(codes.OK, mime.JSON, collection, res);        	
    	});    	
    });

    //add a new group to the collection of groups, @id is the person id
    app.post('/gr/:id', function(req, res) {
        var query = {"owners" : BSONPure.ObjectID(req.params.id)};  
        var group = new Group(req.body.name, req.body.status);
        group.data.owners.push(req.params.id);
        storage.save(group.data, "groups", function(err, data) {
            session.write(codes.OK, mime.JSON, data, res);            
        });         	
    });

    //delete a group, @grid is the group id
    app.delete('/gr/:id/:grid', function(req, res) {        
    });

    //update a group, @grid is the group id 4ecc2a2c38ea968210000001
    app.put('/gr/:id/:grid', function(req, res) {
        storage.update('groups', req.params.grid, req.body, function(err, data){
            if(err) {
                session.write(codes.ERROR, mime.JSON, err, res);
                return;
            }
            session.write(codes.OK, mime.JSON, data, res);
        });    	
    });

    //get a group http://localhost:3000/gr/4ecad77ffd53034e5600000d/4ecad77ffd53034e56000011
    app.get('/gr/:id/:grid', function(req, res) { 
        storage.find(req.params.grid, 'groups', function(err, data) {
           if(err) {
                session.write(codes.NOTFOUND, mime.JSON, err, res);
                return;
            }
            session.write(codes.OK, mime.JSON, data, res);
        });
    });

};

exports.Registration = Registration;