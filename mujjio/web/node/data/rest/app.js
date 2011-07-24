var express = require('express')
  , messages = require('express-messages');

var mongoose = require('mongoose');
var db = mongoose.connect('mongodb://localhost/social');
var app = module.exports = express.createServer();

app.mounted(function(other){
  console.log('ive been mounted!');
});

//http://localhost:8800/rest/people/signup
//{"email":"suhailski@gmail.com","password":"password","givenName":"Suhail","familyName":"Manzoor","gender":"male","language":"english","dd":"10","mm":"11","yyyy":"1968","noage":"true"}

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

require('./routes/usr')(app);
require('./routes/grp')(app);

if (!module.parent) {
  app.listen(3000);
  console.log('Express started on port 3000');
}