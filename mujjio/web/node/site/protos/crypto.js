var crypto = require('crypto');

var data = "I am the clear text data";
console.log('Original cleartext: ' + data);
var algorithm = 'aes-128-cbc';
var key = 'mysecretkey';
var clearEncoding = 'utf8';
var cipherEncoding = 'hex';
//If the next line is uncommented, the final cleartext is wrong.
//cipherEncoding = 'base64' hex;
var cipher = crypto.createCipher(algorithm, key);
var cipherChunks = [];
cipherChunks.push(cipher.update(data, clearEncoding, cipherEncoding));
cipherChunks.push(cipher.final(cipherEncoding));
console.log(cipherEncoding + ' ciphertext: ' + cipherChunks.join(''));
var decipher = crypto.createDecipher(algorithm, key);
var plainChunks = [];
for (var i = 0;i < cipherChunks.length;i++) {
  plainChunks.push(decipher.update(cipherChunks[i], cipherEncoding, clearEncoding));

}
plainChunks.push(decipher.final(clearEncoding));
console.log("UTF8 plaintext deciphered: " + plainChunks.join(''));