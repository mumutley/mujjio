var email   = require("emailjs/email");
    var server  = email.server.connect({
        user:       "suhailski@gmail.com", 
        password:"sw+d7d+1", 
        host:       "smtp.gmail.com", 
        ssl:        true
    });

    var headers = {
        text:       "i hope this works", 
        from:       "you <suhalski@gmail.com>", 
        to:         "someone <avramlevinski@gmail.com>",
        subject:    "testing emailjs"
    };

//        to:     "someone <avramlevinski@gmail.com>, another <another@gmail.com>",
//        cc:     "else <else@gmail.com>",

    // create the message
    var message = email.message.create(headers);

    // attach an alternative html email for those with advanced email clients
    message.attach_alternative("<html>i <i>hope</i> this works!</html>");

    // attach attachments because you can!
    //message.attach("path/to/file.zip", "application/zip", "renamed.zip");

    // send the message and get a callback with an error or details of the message that was sent
    server.send(message, function(err, message) { console.log(err || message); });