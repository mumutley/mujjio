var email   = require("emailjs/email");
var jade = require('jade');

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
                 
    var options = {
        locals : payload
    };    
    var template =  config.templates.base + config.templates[payload.locale][payload.template];
    
    //render the message and send email
    jade.renderFile(template, options , function(err, html){
        if(err) {
            consol.log(err);
            return;
        }
        
        var headers = {
            text:       "Welcome to Mujjio " + payload.name, 
            from:       "Mutley Mu Mu <mumutleymu@gmail.com>", 
            to:         payload.to,
            subject:    "Welcome to Mujjio " + payload.name
        };
        var message = email.message.create(headers);
        message.attach_alternative(html);        
        server.send(message, function(err, message) {/*nop for now*/});
    });        
}

exports.Emailer = Emailer;