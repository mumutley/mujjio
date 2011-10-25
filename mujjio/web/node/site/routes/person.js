var jade = require('jade');
var pages = require('./config').Pages;
var session = require('../../shared/services/session').Session;

module.exports = function(app) {
	session = new Session();

	app.get('/u/:person', function(req, res){
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});         
		session.set(req);   
		res.end(fn({data : req.session}));
	});
	
    app.get('/u/:person/profile.htm', function(req, res){
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: {} }));
	});

	app.get('/u/:person/:page', function(req, res){
		var path = pages.base + pages.profile.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: {data : req.session} }));
	});
}