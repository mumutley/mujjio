var enrollment = require('../../../shared/services/registration').Registration;
var users = require('../../../shared/services/user').User;

module.exports = function(app) {  

	enrollment = new Registration();
   	users = new User();

   	//submit new user data
	app.post('/p/jn', function(req, res) {
		enrollment.register(req, res, function(data){
            return;
        });
	});

	//check activation code for correctness.
	app.get('/p/ac', function(req, res){
		users.getInitial(req, res, function(err, data) {
			if(data === null) {
				res.writeHead('404', {'Content-Type': 'application/json'});
				res.end(JSON.stringify({'status' : "error"}));
			} else {
				res.writeHead('200', {'Content-Type': 'application/json'});
				res.end(JSON.stringify({'status' : data.status}));
			}
		});
	});

	app.post('/p/ac', function(req, res){
		users.activate(req, res, function(err, doc) {
			if(err) {
				res.writeHead(402, {'Content-Type': 'text/json'}) ;	
				res.end(JSON.stringify(err));    
				return;        				
			}				
			res.writeHead(200, {'Content-Type': 'text/json'}) ;
			res.end(JSON.stringify({'status' : 'ok'}));            				
		});	
	});

	app.get('/p/jn', function(req, res){
		res.end(JSON.stringify("hello world"));
	});

}