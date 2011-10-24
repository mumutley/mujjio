var config = require('../../config').Configuration;
var meta = require('../services/metatemplate').JadeTemplate;
var enrollment = require('../../shared/services/registration').Registration;
var jade = require('jade');
var pages = require('./config').Pages;
var users = require('../../shared/services/user').User;
var renderer = require('../../shared/services/renderer').Renderer;

module.exports = function(app){    
    enrollment = new Registration();
    renderer = new Renderer()
    users = new User();
    
    app.get('/join.htm', function(req, res){
        
        var days = ['Day'];
        
        for(var i=0; i < 31; i++){ 
            days.push(i++);
        }
        
        var options = {
            debug : false,
            locals : {
                name : 'Suhail Manzoor',
                day : days
            }            
        };
        
        var path = pages.base + pages.join.main;        
    	renderer.generate(path, 200, 'text/html', options, res);
    });
        
    
    app.post('/join.htm', function(req, res){        
        registration.register(req, res, function(data){
            return;
        });
    });
    
    app.get('/login.htm', function(req, res) {    
        var name = req.query.uid;
        var values = { options: {} };

        if(typeof(req.query.uid) === 'undefined') {
        	//normal login page
			var path = pages.base + pages.login.main;			
			renderer.generate(path, 200, 'text/html', values, res);
        } else {
        	//activation page encountered
        	users.getInitial(req, res, function(err, data){
        		if(err || err === 'undefined' || data === 'undefined' || data === null) {
        			var path = pages.base + pages.login.activatenotfound;
					values.options.error = {'message' : 'Account and email is not found', 'title' : 'Account not found'};		
					renderer.generate(path, 404, 'text/html', values, res);
        		} else {
        			//containts activation request
        			values.options.data = {'uid' : data._id};
					var path = pages.base + pages.login.activate;
					renderer.generate(path, 200, 'text/html', values, res);
        		}				            
        	});			  
        }   
    });
    
    app.post('/activate.htm', function(req, res) {
   
		//data in invalid
		if(req.body.email.length <= 0 || req.body.password.length <= 0) {
			res.writeHead(420, {'Content-Type': 'text/plain'}) ;
			//TODO load error page here
			res.end("<p>bad data</p>");     
			return;
		} else {
			users.activate(req, res, function(err, doc) {
				res.writeHead(420, {'Content-Type': 'text/plain'}) ;
				if(doc) {
					console.log(doc);
					var options = {};
					res.end("activated");            				
				} else {
					res.end("not found or activated");            								
				}
			});
		}
    });
    
    app.post('/login.htm', function(req, res) {
        	
    	if(!req.session.authenticated) {
			users.login(req, res, function(err, data) {
				var code = 200;        	
				if(err) { code = 404; }
				
				req.session.regenerate(function(err) {
					req.session.principle = data.email;
					req.session.authenticated = true;
					var idex = data.email.indexOf("@");
					nickName = data.email.substring(0, idex);
					res.redirect('/' + nickName + '/home.htm');
				});
				
			});    	
    	} else {
			var idex = req.session.principle.indexOf("@");
			nickName = req.session.principle.substring(0, idex);
    		res.redirect('/' + nickName + '/home.htm');
    	}     
    });
}