var config = require('../../config').Configuration;
var meta = require('../services/metatemplate').JadeTemplate;
var enrollment = require('../../shared/services/enrollment').Enrollment;
var jade = require('jade');
var pages = require('./config').Pages;

module.exports = function(app){    
    ///Users/suhail/NetBeansProjects/Herql/mujjio/web/node/site/servicesdefaults.jazz  
    enrollment = new Enrollment();
    
    app.get('/', function(req, res){ 
        var page = config.web.templates.base + config.web.templates.layout;
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
        
        jade.renderFile(page, options , function(err, html){
            if(err) throw err;
            res.writeHead(200, {'Content-Type': 'text/html'});             
            res.end(html);            
        });        
    });
    
    app.get('/join.html', function(req, res){
        var test = pages.base + pages.join.main;        
        
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
        
        var page = config.web.templates.base + config.web.templates.layout;
        jade.renderFile(test, options , function(err, html){
            if(err) throw err;
            res.writeHead(200, {'Content-Type': 'text/html'});             
            res.end(html);            
        });        
    });
        
    
    app.post('/join', function(req, res){        
        enrollment.register(req, res, function(data){
            console.log(data);
            res.writeHead(200, {'Content-Type': 'application/json'});       
            res.end(JSON.stringify(data));
            
            //res.writeHead(200, {'Content-Type': 'text/plain'});
            //res.end(data);            
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