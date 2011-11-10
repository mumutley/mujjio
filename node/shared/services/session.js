
Session = function() {    
}

Session.prototype.set = function(req) {
	
	if(req.session.principle) {
	} else {
		req.session.principle = 'anonymous';
		req.session.authenticated = false
	}
}

exports.Session = Session;