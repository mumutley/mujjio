var sys =  require('sys');
MQ = function() {}

MQ.prototype.recieve = function(connection, command, queueName, exchangeName) {
    connection.on('ready', function () {
        var exchange = connection.exchange(exchangeName, {type: 'fanout'});
        var q = connection.queue(queueName, function() {
            q.bind(exchange, "*");
            q.subscribe(function (json, headers, deliveryInfo) {
                console.log('message recieved for ' + queueName);
                command.execute(json);
            });
        });
    });
}   

exports.MQ = MQ;
