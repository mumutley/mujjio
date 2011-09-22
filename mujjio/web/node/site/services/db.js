var Mongo = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var BSON = require('mongodb').BSON;


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

Storage.prototype.update = function(name, id, data, callback){   
    this.mongo.collection(name, function(err, collection) {
        if(err) {            
            console.log(err);
            callback(err, null);
        } else {

            collection.update({_id : id}, {"$set" : data}, function(error, doc){
                    if( error ) {
                        console.log(error);
                        callback(error);
                    }
                    else callback(null, doc);
            });
        }
    });
}

Storage.prototype.fetch = function(query, name, callback) { 
    this.mongo.collection(name, function(err, collection) {        
        collection.findOne(query, function(err, doc) {
            if(err) console.log(err);
            callback(null, doc);
        });
    });
}

exports.Storage = Storage;