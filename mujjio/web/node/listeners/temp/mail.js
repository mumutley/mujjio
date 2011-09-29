var msg = require('./services/mq').MQ
var amqp = require('amqp');

var msgr = new MQ();
var connection = amqp.createConnection({host: 'io'});     
msgr.recieve(connection, 'rest', 'email');


msgr.recieve(connection, 'data', 'search');


console.log('email server started');