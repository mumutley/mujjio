var https = require('https');
var fs = require('fs');

var options = {
    key: fs.readFileSync('privatekey.pem'),
    cert: fs.readFileSync('certificate.pem')
};

https.createServer(options, function (req, res) {
    res.writeHead(200);
    res.end("hello worlds\n");
}).listen(443);