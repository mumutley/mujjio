var renderer = require('../../shared/services/renderer').Renderer;
var pages = require('./config').Pages;
var form = require('connect-form');

module.exports = function(app){    
	renderer = new Renderer();
	
	app.get('/upload.htm', function(req, res){
        var values = { options: {} };
        var path = pages.base + pages.upload.main;        
    	renderer.generate(path, 200, 'text/html', values, res);				
	});
	
	app.post('/upload.htm', function(req, res, next){
		console.log(req.form);
	// connect-form adds the req.form object
	// we can (optionally) define onComplete, passing
	// the exception (if any) fields parsed, and files parsed
	  req.form.complete(function(err, fields, files){
		if (err) {
		  next(err);
		} else {
		  console.log('\nuploaded %s to %s'
			,  files.image.filename
			, files.image.path);
		  res.redirect('back');
		}
	  });

	  // We can add listeners for several form
	  // events such as "progress"
	  req.form.on('progress', function(bytesReceived, bytesExpected){
		var percent = (bytesReceived / bytesExpected * 100) | 0;
		process.stdout.write('Uploading: %' + percent + '\r');
	  });
  	});
	
}