var Mongo = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var BSON = require('mongodb').BSON;
var ObjectID = require('mongodb').ObjectID;

var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  

Storage = function() {  
    //fetch = true causes problems with mongo
    this.mongo = new Mongo('social', new Server(host, port, {}), {native_parser:false});
    this.mongo.open(function(){});
}

Storage.prototype.getCollection= function(name,callback) {
  this.db.collection(name, function(error, article_collection) {
    if( error ) callback(error);
    else callback(null, article_collection);
  });
};

Storage.prototype.save = function(row, name, callback) {        
           
    this.mongo.collection(name, function(err, collection) { 
        if(err) throw err;
        
        row.created = new Date();
        row.updated = new Date();
        
        collection.insert(row, function(){            
            callback(null, row);
        });            
    });                 
}

Storage.prototype.fetch = function(row, name, callback) { 
    console.log("fetch called");
    callback(null, row);
}

exports.Storage = Storage;