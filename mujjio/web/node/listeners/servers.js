var msg = require('./services/mq').MQ,
amqp = require('amqp'),
config = require('../config').Configuration,
emailer = require('./services/emailer').Emailer,
solr = require('./services/solr').Solr;

var msgr = new MQ();
var connection = amqp.createConnection({host: 'io'});     
var emailCommand = new Emailer(config.smtp);
var solrCommand = new Solr(config.smtp);

msgr.recieve(connection, emailCommand, config.email.queue, config.email.exchange);
msgr.recieve(connection, solrCommand, config.search.queue, config.search.queue);
console.log('[search and email] servers started');