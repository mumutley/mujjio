var sys =  require('sys');
MQ = function() {}

MQ.prototype.recieve = function(connection, exchangeName, queueName) {
    connection.on('ready', function () {
        var exchange = connection.exchange(exchangeName, {type: 'fanout'});
        var q = connection.queue(queueName, function() {
            q.bind(exchange, "*");
            sys.puts("Listening to " + connection.serverProperties.product + ' for ' + queueName);  
            q.subscribe(function (json, headers, deliveryInfo) {
                console.log("recieved on channel for " + queueName);
                console.log(json);
            });
        });
    });
}   

exports.MQ = MQ;
