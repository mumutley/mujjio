var Account = require('../model/account').Account;
var Person = require('../model/person').Person;
Q = require("q");
msg = require('../services/mq').MQ
BSON = require('mongodb').BSONPure,
ObjectID = require('mongodb').BSONPure.ObjectID,
config = require('../../../config').Configuration;

module.exports = function(app){
    var db = new Storage();       
    
    //Get the user profile
    app.get('/me/:uid', function(req, res){        
        db.fetch(null, 'people', function(error, perso){ 
            res.writeHead(200, {'Content-Type': 'application/json'});       
            res.end(JSON.stringify(perso));
        });                                     
    });
    
    app.post('/me/:uid/disable', function(req, res) {
    });
    
    //update a profile
    app.post('/me/:uid', function(req, res) {
    });
    
    //get a list of groups for a specific user.
    app.get('/me/:uid/ls', function(req, res) {
    });
    
    //add a list to the collection of lists
    app.put('me/:uid/ls', function(req, res) {
    });
        
};
