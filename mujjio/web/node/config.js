Configuration = {
    
    search : {
        exchange : 'rest',
        queue    : 'search'
    },
        
    Status : {
        registered  : 'active', 
        valid       : 'valid', 
        expired     : 'expired', 
        blocked     : 'blocked', 
        initial     : 'initial'
    },        
    
    email : {
        exchange : 'data',
        queue    : 'email',

        templates : {        
            base : 'templates/',
            en : {
                welcome : 'welcome.jade'
            }
        },
        smtp : {
            user      :"mumutleymu@gmail.com", 
            password  :"password1968", 
            host      :"smtp.gmail.com", 
            ssl       :true
        }
    },
    
    web : {
        templates : {
            base : 'templates/',
            layout : 'basic.jade'            
        },
        
        meta : {
            base : __dirname + "/site/templates/meta/",
            layout : 'default.jazz'
        },
    
        navigation : { //default navigation
            en : {
                elements : [
                    {name : 'Updates'},
                    {name : 'Friends'},
                    {name : 'Wall'},
                    {name : 'Photographs'},
                    {name : 'Video'},
                    {name : 'Music'}
                ]
            }
        },    
        subscriptions : { //default subscriptions
            en : {
                elements : [
                    {name : "wall"},
                    {name : "neworktags"},
                    {name : "apps"}
                ]             
            }
        }
    },
    
    endpoints : {
        activate : 'http://localhost:8800/rest/people/activate'
    }
};

exports.Configuration = Configuration;

