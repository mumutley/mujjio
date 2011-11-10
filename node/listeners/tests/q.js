var Q = require("q");

msg = require('../../data/rest/services/mq').MQ

var test = {};

test.foo = function(){
    var msgr = new MQ();
    msgr.send(null);
    //return this;
};

test.bar = function(){
    var msgr = new MQ();
    msgr.send(null);
    //return this;
};

var a = test.foo();

var b = test.bar();

Q.join(a, b, function (aValue, bValue) {
    console.log("sssx");
    return cValue;
});

console.log("sss");