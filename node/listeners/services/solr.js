var config = require('../../config').Configuration;

Solr = function(config) {
    this.conf = config;
}

Solr.prototype.execute = function(payload) {
    console.log('payload for solr');
}

exports.Solr = Solr;