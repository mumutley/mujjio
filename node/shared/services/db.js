var mongo = require('mongodb'),
  Db = mongo.Db,
  Connection = mongo.Connection,
  Server = mongo.Server,
  BSON = mongo.BSON,
  ObjectId = mongo.ObjectId,
  GridStore = mongo.GridStore,
  BSONPure = mongo.BSONPure;


var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  

Storage = function() { }

Storage.prototype.getCollection = function(name,callback) {
  this.db.collection(name, function(error, article_collection) {
    if( error ) callback(error);
    else callback(null, article_collection);
  });
};

Storage.prototype.save = function(row, name, callback) {       
	var db = new Db('social', new Server(host, port, {}), {native_parser:false});	
	db.open(function(err, db) {   
		db.collection(name, function(err, collection) {
	        if(err) throw err;	
			row.created = new Date();
			row.updated = new Date();        
			console.log("collection name " + row);
			collection.insert(row, function(){            
    	        callback(null, row);
	        }); 
		});
	});
}

Storage.prototype.update = function(coll, id, data, callback){  
	var db = new Db('social', new Server(host, port, {}), {native_parser:false});		
	db.open(function(err, db) {   
		db.collection(coll, function(err, collection) {
	        if(err) {            
				console.log(err);
				callback(err, null);
			} else {
				collection.update({_id : id}, {"$set" : data}, function(error, doc) {
					if( error ) {
						console.log(error);
						callback(error);
					}
					else callback(null, doc);
				});
			}
		});
	});
}

//find a document by id, from a collection named
Storage.prototype.find = function(id, name, callback) {
	var db = new Db('social', new Server(host, port, {}), {native_parser:false});
	var query = {"_id" : BSONPure.ObjectID(id)};	
	db.open(function(err, db){
		db.collection(name, function(err, collection) {
			collection.findOne(query, function(err, doc) {
           	 	if(err) console.log(err);
            	callback(err, doc);
	        });
		});
	});
}

//find a collection
Storage.prototype.fetchAll = function(query, name, callback) { 
	var db = new Db('social', new Server(host, port, {}), {native_parser:false});
	db.open(function(err, db){
		db.collection(name, function(err, collection) {
			collection.find(query, function(err, cursor) {
				cursor.toArray(function(err, items) {
					if(err) console.log(err);
					callback(err, items);
				});
	        });
		});
	})
}

//find a document
Storage.prototype.fetch = function(query, name, callback) { 
	var db = new Db('social', new Server(host, port, {}), {native_parser:false});	
	db.open(function(err, db){
		db.collection(name, function(err, collection) {
			collection.findOne(query, function(err, doc) {
           	 	if(err) console.log(err);
            	callback(err, doc);
	        });
		});
	})
}

exports.Storage = Storage;