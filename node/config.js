Configuration = {
    
 
    //private > personal > shared > public
    access : {
        private : 'private', //this is only for me
        personal : 'personal', //this is only for bi directional relationships
        shared : 'shared', //this can be for community relationships
        public : 'public' //this is the universe
    }, 

   //default relationship groups
    relationships : {
        en : {
            elements : [
                { name : 'Family', visibility : 'personal' },
                { name : 'Friends' , visibility : 'personal' },
                { name : 'Collegues' , visibility : 'personal' },
                { name : 'Acquaintences', visibility : 'personal' }
            ]
        }
    },

    //the content type
    mime : {
        JSON : {'Content-Type': 'application/json'}
    },

    //status code
    codes : {
        INVALID : '412',
        FORBIDDEN : '403',
        OK : '200',
        NOTFOUND : '404',
        ERROR : '500'
    },

    status : {
        registered  : 'active', 
        valid       : 'valid', 
        expired     : 'expired', 
        blocked     : 'blocked', 
        initial     : 'initial'
    },  
    
    search : {
        exchange : 'rest',
        queue    : 'search'
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
            layout : 'yui.jade'            
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

