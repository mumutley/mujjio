var sys =  require('sys');
var amqp = require('amqp');

var connection = amqp.createConnection({
    host: 'io'
});

connection.addListener('error', function (e) {
    throw e;
})

connection.addListener('close', function (e) {
    sys.puts('connection closed.');
});


var origMessage1 = {
    two:2, 
    one:1
},
origMessage2 = {
    foo:'email', 
    hello: 'world'
},
origMessage3 = {
    coffee:'caf\u00E9', 
    tea: 'th\u00E9'
};


connection.addListener('ready', function () {
    sys.puts("connected to " + connection.serverProperties.product);              
    connection.exchange('data', {type: 'fanout'}, function(exchange) {
        sys.puts("connected to " + connection.serverProperties.product);   
        connection.queue('search', function(q) {
            q.bind(exchange, "*");            
            sys.puts("publishing 3 json messages");    
            exchange.publish('message.json1', origMessage1);
            exchange.publish('message.json2', origMessage2, {contentType: 'application/json'});
            exchange.publish('message.json3', origMessage3, {contentType: 'application/json'});                 
            connection.end();
        });
    });                   
});