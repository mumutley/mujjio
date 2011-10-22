
$(document).ready(function(){
	//global vars
	var form = $("#loginForm");
	var email = $("#email");
	var emailHint = $("#emailHint");
	var password = $("#password");
	var passwordHint = $("#passwordHint");

	
	//On blur

	email.blur(validateEmail);
	password.blur(validatePassword);
	
	//On key press
	email.keyup(validateEmail);
	password.keyup(validatePassword);
	
	//On Submitting
	
	form.submit(function(){
		if(validateEmail() & validatePassword())
			return true
		else
			return false;
	});
	
	//validation functions
	function validateEmail(){
		//testing regular expression
		var a = $("#email").val();
		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
		
		//if it's valid email
		if(filter.test(a)){
			emailHint.removeClass("muj-container-login-form-error");
			emailHint.text("");
			emailHint.addClass("muj-container-login-form-valid");
			
			return true;
		}
		
		//if it's NOT valid
		else{
			
			emailHint.addClass("muj-container-login-form-error");
			emailHint.text("Valid email address please");
			emailHint.addClass("muj-container-login-form-error");
			$('this').stopPropagation();
			$("#email").focus();
			return false;
		}
	}
	
	function validatePassword(){
		
		//it's NOT valid
		if(password.val().length <5){
			passwordHint.addClass("muj-container-login-form-error");
			passwordHint.text("Must be at least 6 characters");
			passwordHint.addClass("muj-container-login-form-error");
			return false;
		}
		
		//it's valid
		else{			
			passwordHint.removeClass("muj-container-login-form-error");
			passwordHint.text("");
			passwordHint.addClass("muj-container-login-form-valid");
		
		
			return true;
		}
	}
	
	
});