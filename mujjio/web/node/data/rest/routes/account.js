var users = require('../../../shared/services/user').User;

module.exports = function(app) {
  	users = new User();

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
}