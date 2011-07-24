var express = require('express'),
    app = express.createServer(),
    rest = require('./rest/app');
    
app.use(express.cookieParser());
app.use(express.session({ secret: 'keyboard cat' }));    

app.use('/rest', rest);


app.get('/', function(req, res){
    res.send('Visit <a href="/usr">/usr</a>');
});

app.listen(8800);
console.log('Express server started on port %s', app.address().port);