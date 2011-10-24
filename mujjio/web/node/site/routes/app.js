var express = require('express')
  , messages = require('express-messages')
  , form = require('connect-form');

//var mongoose = require('mongoose');
//var db = mongoose.connect('mongodb://localhost/social');
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

form({ keepExtensions: true })

require('./start')(app); //routes to display the postings
require('./person')(app); //route to display person management
require('./enrollment')(app); //routes to enroll and login
require('./relations')(app); //route to display relationship management
require('./photos')(app); //route to display relationship management


if (!module.parent) {
  app.listen(3000);
  console.log('Express started on port 3000');
}