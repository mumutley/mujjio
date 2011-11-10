var Base = require('./base').Base;

var Content = exports.Content = function (request) {
    
    this.date = {}
}


Content.prototype.__proto__ = Base.prototype;
Content.prototype.constructor = Content
Content.prototype.className = "content";