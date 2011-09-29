var config = require('../config').Configuration,
msg = require('./services/mq').MQ,
amqp = require('amqp'),
emailer = require('./services/emailer').Emailer,
solr = require('./services/solr').Solr;

var msgr = new MQ();
var connection = amqp.createConnection({host: 'io'});     
var emailCommand = new Emailer(config.email);
var solrCommand = new Solr(config.search);

msgr.recieve(connection, emailCommand, config.email.queue, config.email.exchange);
msgr.recieve(connection, solrCommand, config.search.queue, config.search.queue);
console.log('[search and email] servers started');