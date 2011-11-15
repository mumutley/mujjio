
Session = function() {    
}

Session.prototype.set = function(req) {
	
	if(req.session.principle) {
	} else {
		req.session.principle = 'anonymous';
		req.session.authenticated = false
	}
}

//common function to write the return code to response
Session.prototype.write = function(code, mime, data, res) {
	res.writeHead(code, mime);
	res.end(JSON.stringify(data));
}

exports.Session = Session;