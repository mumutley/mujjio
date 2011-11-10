var config = require('../../config').Configuration;
var jade = require('jade');
var pages = require('./config').Pages;

module.exports = function(app){    
    
    app.get('/', function(req, res){ 
		var path = pages.base + pages.root.main
		  , str = require('fs').readFileSync(path, 'utf8')
		  , fn = jade.compile(str, { filename: path, pretty: true });
        
        var options = {
            debug : false,
            locals : {
                name : 'Suhail Manzoor',
                url : 'http://localhost:8080/suhail',
                container : 'main',
                head : 'main',
                includes : [
                    'include includes/head',
                    'include includes/mast',
                    'include includes/footer'
                ]
            }            
        };
        
		res.writeHead(200, {'Content-Type': 'text/html'});             
		res.end(fn({ options: options }));            		                         
    });
        
}