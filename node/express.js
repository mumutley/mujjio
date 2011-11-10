var express = require('express'),
    app = express.createServer(),
    usr = require('data/server');
    
app.use(express.cookieParser());
app.use(express.session({ secret: 'keyboard cat' }));    

app.use('/usr', usr);

app.get('/', function(req, res){
    res.send('Visit <a href="/usr">/usr</a>');
});

app.listen();
console.log('Express server started on port %s', app.address().port);