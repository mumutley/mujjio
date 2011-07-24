

module.exports = function(app){

    app.post('/people/signup', function(req, res){
        console.log('body' + req.body);
        
        res.send('usser ' + req.body.email);
    });

    app.get('/people/:id', function(req, res){
        res.send('user ' + req.params.id);
    });
};
