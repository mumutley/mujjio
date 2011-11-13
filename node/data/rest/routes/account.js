var users = require('../../../shared/services/user').User;

module.exports = function(app) {
  	users = new User();

    //login
	app.post('/ac/auth', function(req, res) {
		users.login(req, res, function(err, doc) {
			if(err){
				res.writeHead('403', {'Content-Type': 'application/json'});
				res.end(JSON.stringify({'status' : 'error in creating authentication token'}));
				return;
			}
			res.writeHead('200', {'Content-Type': 'application/json'});
			res.end(JSON.stringify({'status' : doc}));
		});
	});        
    
    //log out
	app.get('/ac/dauth', function(req, res) {
		console.log("logging out");
		res.writeHead('200', {'Content-Type': 'application/json'});
		res.end(JSON.stringify({'status' : "ok"}));
	});
    
    //reset password.
    app.get('/ac/:uid/pwd', function(req, res) {
    });
    
    //disable the account
    app.get('/ac/:uid/off', function(req, res) {

    });
    
    //disable and delete the account
    app.get('/ac/:uid/die', function(req, res) {
    });
    
    //update the account
    app.post('/ac/:uid', function(req, res) {
           
    });
}