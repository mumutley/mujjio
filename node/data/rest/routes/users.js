var Account = require('../model/account').Account;
var Person = require('../model/person').Person;
var config = require('../../../config.js').Configuration;
var codes = config.codes;
var mime = config.mime;

var session = require('../../../shared/services/session').Session;

module.exports = function(app){
    var db = new Storage();       
    session = new Session();

    //Get the user profile
    app.get('/me/:uid', function(req, res){
        console.log(req.params.uid);    
        db.find(req.params.uid, 'people', function(error, perso){ 
            
            if(perso === null || error) {
                session.write(codes.NOTFOUND, mime.JSON, 
                    {'status' : 'person with id ' + req.params.uid + ' not found'}, res);
                return;
            }
            session.write(codes.OK, mime.JSON, perso, res);
        });                                     
    });
    
    //get a list of groups for a specific user.
    app.get('/me/:uid/ls', function(req, res) {

    });
    
    //add a list to the collection of lists
    app.put('me/:uid/ls', function(req, res) {
    });
        
};
