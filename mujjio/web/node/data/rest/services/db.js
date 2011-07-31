var EventEmitter = require("events").EventEmitter;
var util = require("util");

var Mongo = require('mongodb').Db,
  Connection = require('mongodb').Connection,
  Server = require('mongodb').Server,
  BSON = require('mongodb').BSONNative;
  
var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  
var db = new Mongo('social', new Server(host, port, {}), {native_parser:true});

var Db = exports.Db = function() {
    this.name = "name";
}

util.inherits(Db, EventEmitter);

exports.Db.prototype.save = function() {    
    console.log("save called");
    for(var prop in this) {
        if(this.propertyIsEnumerable(prop)) {
            console.log("property " + prop + " value is " + this[prop]);
        }
    }
}