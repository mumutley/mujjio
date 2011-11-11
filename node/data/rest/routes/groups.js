module.exports = function(app){
  app.get('/group/:id', function(req, res){
    res.send('group ' + req.params.id);
  });
};
