var crypto = require('crypto');

var algorithm = 'aes-128-cbc';
var key = 'mysecretkey';
var clearEncoding = 'utf8';
var cipherEncoding = 'hex';

Crypto = function() {}

Crypto.prototype.scramble = function(data, callback) {    
    var cipher = crypto.createCipher(algorithm, key);
    var cipherChunks = [];
    cipherChunks.push(cipher.update(data, clearEncoding, cipherEncoding));
    cipherChunks.push(cipher.final(cipherEncoding));    
    callback(cipherChunks.join(''));
}

exports.Crypto = Crypto;
