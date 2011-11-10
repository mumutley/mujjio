Pages = {
    base : 'templates/',
    
    root : {
    	layout : '',
    	main : 'root.jade'
    },
    
    join : {
        layout : '',
        main : 'enroll.jade'        
    },
    
    upload : {
    	layout : '',
    	main : 'upload.jade'
    },
    
    login : {
        layout : '',
        main : 'login.jade',
        activate : 'activate.jade',
        activatenotfound : 'activate404.jade' 
    },
    
    profile : {
    	layout : '',
    	main : 'profile.jade'
    }
}

exports.Pages = Pages;