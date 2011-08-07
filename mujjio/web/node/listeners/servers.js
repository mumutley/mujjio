var msg = require('./services/mq').MQ
var amqp = require('amqp');

var msgr = new MQ();
var connection = amqp.createConnection({host: 'io'});     

msgr.recieve(connection, 'data', 'email');
msgr.recieve(connection, 'rest', 'search');


console.log('[search and email] servers started');