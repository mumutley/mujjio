var Mongo = require('mongodb').Db,
Connection = require('mongodb').Connection,
Server = require('mongodb').Server,
BSON = require('mongodb').BSONNative;

var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  

var Db = exports.Db = function() {}

exports.Db.prototype.save = function(row, name) {        
           
    mongo = new Mongo('social', new Server(host, port, {}), {native_parser:true});
    
    mongo.open(function(err, mongo) {
        mongo.collection(name, function(err, collection) { 
            if(err) throw err;
            collection.insert(row);    
            console.log(row)
        });         
        mongo.close();
    });        
}