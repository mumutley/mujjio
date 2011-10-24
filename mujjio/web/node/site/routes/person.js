var jade = require('jade');
var pages = require('./config').Pages;

module.exports = function(app) {

	app.get('/:person', function(req, res){
    	console.log("in profile");
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: {} }));
	});
	
    app.get('/:person/profile.htm', function(req, res){
    	console.log("in profile");
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: {} }));
	});

	app.get('/:person/:page', function(req, res){
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: {} }));
	});
}