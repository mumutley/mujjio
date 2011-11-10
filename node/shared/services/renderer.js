var jade = require('jade');

Renderer = function() {}

/*
Utility method to use jade to render the page
*/
Renderer.prototype.generate = function(path, code, type, opts, res) {

	  var str = require('fs').readFileSync(path, 'utf8')
	  , fn = jade.compile(str, { filename: path, pretty: true });
	  
	res.writeHead(code, {'Content-Type': type});             
	res.end(fn({ options: opts }));            		        

};

exports.Renderer = Renderer;