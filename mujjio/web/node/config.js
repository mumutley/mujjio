Configuration = {
    
    search : {
        exchange : 'rest',
        queue    : 'search'
    },
    
    email : {
        exchange : 'data',
        queue    : 'email'
    },
    
    Status : {
        registered  : 'registered', 
        valid     : 'valid', 
        expired   : 'expired', 
        blocked   : 'blocked', 
        initial   : 'initial'
    },
    
    smtp : {
        user      :"mumutleymu@gmail.com", 
        password  :"password1968", 
        host      :"smtp.gmail.com", 
        ssl       :true
    },
    
    templates : {        
        base : 'templates/',
        en : {
            welcome : 'welcome.jade'
        }
    },
    
    endpoints : {
        activate : 'http://localhost:8800/rest/people/activate'
    }
};

exports.Configuration = Configuration;

