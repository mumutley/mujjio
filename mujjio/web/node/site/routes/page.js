var config = require('../../config').Configuration;
var meta = require('../services/metatemplate').JadeTemplate;
var jade = require('jade');

module.exports = function(app){    
    ///Users/suhail/NetBeansProjects/Herql/mujjio/web/node/site/servicesdefaults.jazz
    app.get('/', function(req, res){                        
        var layout = config.web.templates.base + config.web.templates.layout;
                
        var options = {
            debug : false,
            locals : {
                name : 'Suhail Manzoor',
                url : 'http://localhost:8080/suhail',
                container : 'main',
                head : 'include includes/head',
                includes : [
                    'include includes/head',
                    'include includes/mast',
                    'include includes/footer'
                ]
            }            
        };
        
        jade.renderFile(layout, options , function(err, html){
            if(err) throw err;
            res.writeHead(200, {'Content-Type': 'text/html'});             
            res.end(html);            
        });        
    });
    
    app.post('/login', function(req, res){            
        //res.writeHead(200, {'Content-Type': 'text/html'});             
        //res.end('login');                    
        res.writeHead(200, {'Content-Type': 'text/plain'})
        var name = req.body.username;
        console.log(name);
        res.end(req.body.username);
            
    });
    
    app.get('/:person', function(req, res){
        res.writeHead(200, {'Content-Type': 'text/plain'})                
        res.end('req.body.username');

    });
}