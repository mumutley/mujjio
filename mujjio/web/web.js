var express = require('express'),
    app = express.createServer();
    
    
app.configure(function(){
    app.use(express.methodOverride());
    app.use(express.bodyParser());
    app.use(app.router);
    app.use(express.static(__dirname + '/'));
    app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
    app.use(express.cookieParser());
    app.use(express.session({ secret: 'keyboard cat' }));    
});

/*app.get('/:user', function(req, res){
    res.send('Visit <a href="/usr">/usr</a>');
});*/

app.listen(8081);
console.log('Express server started on port %s', app.address().port);
