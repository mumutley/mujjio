var config = require('../../config').Configuration;
var pages = require('./config').Pages;
var users = require('../../shared/services/user').User;
var jade = require('jade');

module.exports = function(app){    
	users = new User();
	
	app.get('/:person/befriend/:me', function(req, res) {
		users.find(req, res, function(err, doc) {
			//add this relationship between person and me
			//the relationship is the default one, mainly public.
			//this relationship can then be fine tuned at the time
			//of the user's choosing.
			var path = pages.base + pages.profile.main
			  , str = require('fs').readFileSync(path, 'utf8')
			  , fn = jade.compile(str, { filename: path, pretty: true });
			
			res.writeHead(200, {'Content-Type': 'text/html'});             
			res.end(fn({ options: {} }));
		});
	});
}