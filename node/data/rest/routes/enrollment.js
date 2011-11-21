var enrollment = require('../../../shared/services/registration').Registration;
var users = require('../../../shared/services/user').User;
var codes = require('../../../config.js').Configuration.codes;
var mime = require('../../../config.js').Configuration.mime;
var session = require('../../../shared/services/session').Session;

module.exports = function(app) {  

	enrollment = new Registration();
   	users = new User();
   	session = new Session();

   	//submit new user data for join
	app.post('/p/jn', function(req, res) {
		enrollment.register(req, res, function(err, person) {
			console.log(person.data._id + " is saved.");
			//session is rended in the registration function
			return;
        });
	});

	//check activation code for correctness.
	app.get('/p/ac', function(req, res){
		users.getInitial(req, res, function(err, data) {
			if(data === null) {
				session.write(codes.NOTFOUND, mime.JSON, {'status' : "error"}, res);
			} else {
				session.write(codes.OK, mime.JSON, {'status' : data.status}, res);
			}
		});
	});

	//activate the user
	app.post('/p/ac', function(req, res){
		users.activate(req, res, function(err, doc) {
			if(err) {
				session.write(codes.FORBIDDEN, mime.JSON, err, res);
				return;        				
			}
			session.write(codes.OK, mime.JSON, {'status' : 'ok'}, res);			
		});	
	});
}