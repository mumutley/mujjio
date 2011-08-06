var sys = require('sys');
var amqp = require('amqp');

var connection = amqp.createConnection({host: 'io'});

// Wait for connection to become established.
connection.on('ready', function () {
    var exchange = connection.exchange('search', {type: 'fanout'});

    var q = connection.queue('solr', function() {
        q.bind(exchange, "*");
        sys.puts("Listening to " + connection.serverProperties.product);  
        q.subscribe(function (json, headers, deliveryInfo) {
            console.log(json);
        });

    });
});