var store = require('./db').Storage;

Security = function() {    
	store = new Storage();  
};

//this changes the credentials of the user
Security.prototype.resetCredentials = function(req, res, callback) {
	
};

