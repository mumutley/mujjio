var users = require('../../../shared/services/user').User;
var session = require('../../../shared/services/session').Session;
var codes = require('../../../config.js').Configuration.codes;

module.exports = function(app) {
  	users = new User();
   	session = new Session();

    //login
	app.post('/ac/auth', function(req, res) {
		users.login(req, res, function(err, doc) {
			if(err){
				session.write(codes.FORBIDDEN, mime.JSON, {'status' : 'error in creating authentication token'}, res);
				return;
			}
			session.write(codes.OK, mime.JSON, doc, res);			
		});
	});        
    
    //log out
	app.get('/ac/dauth', function(req, res) {
		session.write(codes.FORBIDDEN, mime.JSON, {'status' : 'To be implemented'}, res);		
	});
    
    //reset password.
    app.post('/ac/:uid/pwd', function(req, res) {
    	users.setPassword(req, res, function(err, doc) {
			if(err){
				session.write(codes.FORBIDDEN, mime.JSON, {'status' : 'error in creating authentication token'}, res);				
				return;
			}
			session.write(codes.OK, mime.JSON, doc, res);			
    	});
    });
    
    //disable the account
    app.post('/ac/:uid/off', function(req, res) {
		users.disable(req, res, function(err, doc) {
			if(err){
				session.write(codes.FORBIDDEN, mime.JSON, {'status' : 'error in creating authentication token'}, res);
				return;
			}
			session.write(codes.OK, mime.JSON, doc, res);			
    	});
    });
}