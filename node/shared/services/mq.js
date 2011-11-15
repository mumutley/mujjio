var sys =  require('util');
var amqp = require('amqp');

MQ = function() {}

MQ.prototype.send = function(exch, que, name, msg) {     
    var connection = amqp.createConnection({host: 'io'});     
    connection.addListener('ready', function () {
        connection.exchange(exch, {type: 'fanout'}, function(exchange) {
            connection.queue(que, function(q) {
                q.bind(exchange, "*");                
                sys.puts("publishing " + name);    
                exchange.publish(name, msg);
                connection.end();                
            });
        });                   
    });
};

exports.MQ = MQ;
