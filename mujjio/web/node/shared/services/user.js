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

User.prototype.activate = function(req, res, callback) {

	if(req.body.email.length <= 0 || req.body.password.length <= 0) {
		callback({"error" : "validations failed"}, null);
		return;
	}

	var query = {'_id' : BSONPure.ObjectID(req.body.uid), 'status' : 'initial', 'email' : req.body.email, 'password' : req.body.password};	
	store.fetch(query, 'account', function(err, doc) {
		var outcomes = {
			write : function(err, doc) {
				console.log("write called");
				callback(err, doc); //return to the view layer
				return this;
			},
			message : function(que, exch, name, message) {
				console.log("message called");
				return this;
				//save object in search
			},
			update : function() {
				if(doc) {
					store.update('account',doc._id, {'status':'registered'}, function(err){
						console.log('document updated');
						callback(null, doc);
					});
				} else {
					callback({"error" : "document not found"}, null);
				}
				return this;
			}			
		}
		//var email =  outcomes.message('data', 'email', null, null);
		//var write = outcomes.write(err, doc);
		var update = outcomes.update();
		
		Q.join(update);		
//		Q.join(email, write, update);		
	});	
}

//login the user
User.prototype.login = function(req, res, callback) {
	var query = {'email' : req.body.email, 'password' : req.body.password};	
	store.fetch(query, 'account', function(err, doc) {
		callback(err, doc);
	});
}

User.prototype.find = function(req, res, callback) {
	var query = {'nickName' : req.params.person };
	store.fetch(query, 'people', function(err, doc) {
		console.log(doc);
		callback(err, doc);
	});
}

exports.User = User;
