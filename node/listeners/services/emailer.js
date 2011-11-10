var email   = require("emailjs/email");
var jade = require('jade');
var config = require('../../config').Configuration;

var server = {}

Emailer = function(config) {       
    server  = email.server.connect({
        user        : config.smtp.user, 
        password    : config.smtp.password, 
        host        : config.smtp.host, 
        ssl         : true
    });
}
    
Emailer.prototype.execute = function(payload) {
	var path = config.email.templates.base + config.email.templates[payload.locale][payload.template]
	  , str = require('fs').readFileSync(path, 'utf8')
	  , fn = jade.compile(str, { filename: path, pretty: true });

	var options = {
		name : payload.name,
		url : payload.url
	}	  
	var headers = {
		text:       "Welcome to Mujjio " + payload.name, 
		from:       "Mutley Mu Mu <mumutleymu@gmail.com>", 
		to:         payload.to,
		subject:    "Welcome to Mujjio " + payload.name
	};
	var message = email.message.create(headers);
	message.attach_alternative(fn(options));        
	//server.send(message, function(err, message) { console.log(err || message); });		
}

exports.Emailer = Emailer;