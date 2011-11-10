var express = require('express')
  , form = require('connect-form')
  , mongodb = require('mongodb').native();

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

var store = require('../../shared/services/db').Storage;
var fstore = require('../../shared/services/imagedb').Storage;
store = new Storage();
fstore = new ImageStorage();

var app = express.createServer(
  // connect-form (http://github.com/visionmedia/connect-form)
  // middleware uses the formidable middleware to parse urlencoded
  // and multipart form data
  form({ keepExtensions: true })
);

app.get('/', function(req, res){
  res.send('<form method="post" enctype="multipart/form-data">'
    + '<p>Image: <input type="file" name="image" /></p>'
    + '<p><input type="submit" value="Upload" /></p>'
    + '</form>');
});

app.get('/list', function(req, res, next) {
	var query = {"metadata.author" : "joe mutley"};		
	store.fetchAll(query, 'fs.files', function(err, doc) {
		var html = "<p> listing </p> <ul>";
		for(var i = 0; i < doc.length; i++) {
			html += '<li> <a href=/image/' + doc[i].filename  + '>' + doc[i]._id + '</a></li>';
		}		
		html += '</ul>';
		res.send(html);
	});
});

app.get('/image/:image', function(req, res, next) {
	fstore.streamResource(req.params.image, res, function() {
	});
});

app.post('/', function(req, res, next){
	req.form.complete(function(err, fields, files){
		if (err) {
		  next(err);
		} else {
			res.writeHead(200, {'Content-Type': 'application/json' });
			if (err) res.write(JSON.stringify(err.message));
				
			fstore.save(files, function() {
				console.log("image saved");
				res.write(JSON.stringify(fields));
				res.write(JSON.stringify(files.image.mime));
				res.end();
			});							
		}
	});

	// We can add listeners for several form
	// events such as "progress"
	req.form.on('progress', function(bytesReceived, bytesExpected){
		var percent = (bytesReceived / bytesExpected * 100) | 0;
		process.stdout.write('Uploading: %' + percent + '\r');
	});
});

app.listen(3000);
console.log('Express app started on port 3000');