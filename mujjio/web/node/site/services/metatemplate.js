var jazz = require("jazz");
var fs = require("fs");

JadeTemplate = function() {      
}

JadeTemplate.prototype.getTemplate= function(locals, callback) {
    
    var data = fs.readFileSync(locals.metatemplate , locals.locale);
    var template = jazz.compile(data);
    template.eval({}, function(data) { 
        callback(null, data);
    });
    
}

exports.JadeTemplate = JadeTemplate;

