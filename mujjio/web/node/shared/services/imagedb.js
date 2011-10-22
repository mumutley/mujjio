var mongodb = require('mongodb').native();

var Db = mongodb.Db,
    Cursor = mongodb.Cursor,
    Collection = mongodb.Collection,
    GridStore = mongodb.GridStore,
    Chunk = mongodb.Chunk,
	Connection = mongodb.Connection,    
    Server = mongodb.Server;

var fs = require('fs');

var host = process.env['MONGO_NODE_DRIVER_HOST'] != null ? process.env['MONGO_NODE_DRIVER_HOST'] : 'localhost';
var port = process.env['MONGO_NODE_DRIVER_PORT'] != null ? process.env['MONGO_NODE_DRIVER_PORT'] : Connection.DEFAULT_PORT;  
var db = new Db('social', new Server(host, port, {auto_reconnect: true, poolSize: 4}), {native_parser: true});

ImageStorage = function() { }

ImageStorage.prototype.save = function(files, callback) {
	db.open(function(err, db) {
	
		var gs = new GridStore(db, files.image.filename, "w", {
			"content_type": files.image.mime,
			"chunk_size": 1024*4					
		});						
					
		gs.open(function(err, store){
			console.log('\nuploaded %s to %s', files.image.filename, files.image.path);
			store.metadata = {
				'a': 1,
				'author' : 'joe mutley',
				'owner' : 'baynor mutley',
				'tags' : ['one','two','three'],
				'privacy' : ['private', 'general electric']
			};
			fs.readFile(files.image.path, function(err, data){
				store.write(data, function(err, store){
					store.close(function(err, result) {								
						fs.unlink(files.image.path, function (err) {
							if (err) throw err;
							console.log('successfully deleted ' + files.image.path);
							callback();							
						});													
					});							
				});
			});
		});					
	});	
}

ImageStorage.prototype.streamResource = function(name, res, callback) {
	var db = new Db('social', new Server(host, port, {}), {native_parser:true});
	
	db.open(function(err, db){
		var gs = new GridStore(db, name, "r");
		gs.open(function(err, gs) {
			var stream = gs.stream(true);			
			var offset = 0;
			var response_content_length = parseInt(gs.length);
			var response_body = new Buffer(response_content_length);
			res.contentType(gs.contentType);			
			stream.on("data", function(chunk) {			
				res.write(chunk);
			});
			
			stream.on("end", function() {
				res.end();			
				callback();
			});
		});
	});
}


exports.ImageStorage = ImageStorage;