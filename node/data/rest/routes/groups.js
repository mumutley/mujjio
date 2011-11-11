var Group = require('../../data/rest/model/group').Group;

module.exports = function(app){
    
    
    //get a specific group
    app.get('/gr/:id', function(req, res){
        res.send('group ' + req.params.id);
    });
};
