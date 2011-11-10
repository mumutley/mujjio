var jade = require('jade');
var rbytes = require('rbytes');

var rbuff = rbytes.randomBytes(16);

var options = { 
    locals: { 
        name: 'Suhail Manzoor',
        url: 'http://localhost:8800/rest/'
    }
};

// Render a file
jade.renderFile('../templates/welcome.jade', options , function(err, html){
    // options are optional,
    // the callback can be the second arg
    console.log(html);
    console.log(rbuff.toHex());
});
