var Mongo = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var BSON = require('mongodb').BSON;
var ObjectID = require('mongodb').ObjectID;

var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  

Setup = function() {  
    //fetch = true causes problems with mongo
    this.mongo = new Mongo('social', new Server(host, port, {auto_reconnect: true, poolSize: 1}), {native_parser:false});
    this.mongo.open(function(){});
}

Setup.prototype.setupIndex = function(name, index, uniq, callback) {
  
  this.mongo.createIndex(name, index, {unique:uniq}, function(err, indexName) {
      if(err) {
          callback(err, null);
      } else {
          callback(null, indexName);
      }
  });  
};

exports.Setup = Setup;
