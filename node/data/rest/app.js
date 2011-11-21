var express = require('express')
  , messages = require('express-messages');

var app = module.exports = express.createServer();

app.mounted(function(other){
  console.log('controllers mounted!');
});

app.configure(function(){
  app.use(express.logger('\x1b[33m:method\x1b[0m \x1b[32m:url\x1b[0m :response-time'));
  app.use(express.bodyParser());
  app.use(express.methodOverride());
  app.use(express.cookieParser());
  app.use(express.session({ secret: 'keyboard cat' }));
  app.use(app.router);
  app.use(express.static(__dirname + '/public'));
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

require('./routes/enrollment')(app);
require('./routes/account')(app);
require('./routes/users')(app);

//require('./routes/grp')(app);
//require('./routes/sys')(app);
//require('./routes/cntnt')(app);

if (!module.parent) {
  app.listen(3000);
  console.log('Express started on port ' + 3000);
}