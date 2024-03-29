var Setup = require('../services/system').Setup

module.exports = function(app){
    
    app.post('/sys/setup', function(req, res){
        var setup = new Setup();
        setup.setupIndex('people','nickName', true, function(err, indexName){
            //nop          
        });
        setup.setupIndex('people','gender', false, function(err, indexName){
            //nop
        });
        
        setup.setupIndex('people','_id', false, function(err, indexName){
            //nop
        });
        
        setup.setupIndex('account','email', true, function(err, indexName){
            //nop
        });
        setup.setupIndex('account','password', false, function(err, indexName){
            //nop
        });
        
        res.writeHead(200, { 'Content-Type': 'application/json' })
        var objToJson = { };          
        res.end(JSON.stringify(objToJson));
    });
}