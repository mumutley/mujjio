BSON = require('mongodb').BSONPure,
    ObjectID = require('mongodb').BSONPure.ObjectID,
    config = require('../../../config').Configuration;

var data = {    
    'entry' : {        
        'owner' : 'Ahmed Suhail Manzoor',
        'published' : '13:15',
        'visibility' : ['Friends', 'Jerry McGuire'],
        'body' : {
            'mimetype' : 'text/html',
            'body' : '<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>'
        },
        'actions' : [
            'Like' , 'Dislike', 'Share', 'Buy', 'Bib'
        ]
    }
}

module.exports = function(app){    
    //http://localhost:8800/rest/feeds/suhail/home
    app.get('/feeds/:id/:page', function(req, res){         
        res.writeHead(200, {'Content-Type': 'application/json'});       
        res.end(JSON.stringify(data));                                            
    });
}