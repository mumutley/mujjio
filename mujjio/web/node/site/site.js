var config = require('../config').Configuration;
    express = require('express'),
    site = require('./routes/app'),    
    app = express.createServer();
    
    
app.configure(function(){
    app.use(express.methodOverride());
    app.use(express.bodyParser());
	app.use(express.cookieParser());
	app.use(express.session({ secret: 'keyboard cat', cookie: { maxAge: 60000 }}));
    app.use(express.favicon());
	app.use(express.session({ secret: "keyboard cat"}));    
    app.use(app.router);
    app.use(express.static(__dirname + '/'));
    app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
    app.use(express.cookieParser());
    app.use(express.session({ secret: 'keyboard cat' }));    
});    

app.use('/', site);

app.listen(8080);
console.log('Express server started on port %s', app.address().port);
