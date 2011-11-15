var mongo = require('mongodb'), 
	Q = require("q");
	BSONPure = mongo.BSONPure;
	  
var store = require('./db').Storage;

User = function() {    
	store = new Storage();  
}

//get registration with initial state
User.prototype.getInitial = function(req, res, callback) {
	var query = {'_id' : BSONPure.ObjectID(req.query.uid), 'status' : 'initial'};	
	store.fetch(query, 'account', function(err, doc) {
		callback(err, doc);
	});
}

//update user password
User.prototype.setPassword = function(req, res, callback) {
	store.update("account", req.body.uid, { "password" : req.body.password }, function(err, doc) {
		callback(err, doc);
	});
}

//update user password
User.prototype.disable = function(req, res, callback) {
	store.update("account", req.body.uid, { "status" : "disable" }, function(err, doc) {
		callback(err, doc);
	});
}

//activate user account
User.prototype.activate = function(req, res, callback) {

	if(req.body.email.length <= 0 || req.body.password.length <= 0) {
		callback({"error" : "validations failed"}, null);
		return;
	}

	var query = {'_id' : BSONPure.ObjectID(req.body.uid), 'status' : 'initial', 'email' : req.body.email, 'password' : req.body.password};	
	
	store.fetch(query, 'account', function(err, doc) {
		if(doc) {
			store.update('account', doc._id.toString(), {'status':'registered'}, function(err){
				callback(null, doc);
			});
		} else {
			callback({"error" : "document not found"}, null);
		}
	});	
}

//login the user
User.prototype.login = function(req, res, callback) {
	var query = {'email' : req.body.email, 'password' : req.body.password};	
	store.fetch(query, 'account', function(err, doc) {
		callback(err, doc);
	});
}

//find a user by nickname
User.prototype.find = function(req, res, callback) {
	var query = {'nickName' : req.params.person };
	store.fetch(query, 'people', function(err, doc) {
		console.log(doc);
		callback(err, doc);
	});
}

exports.User = User;
