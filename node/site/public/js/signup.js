  
      jQuery("#birthday_day").focus(function() {
        if( this.value == this.defaultValue ) {
          this.value = "";
        }
      }).blur(function() {
        if( !this.value.length ) {
          this.value = this.defaultValue;
        }
      });
  
      jQuery("#birthday_month").focus(function() {
        if( this.value == this.defaultValue ) {
          this.value = "";
        }
      }).blur(function() {
        if( !this.value.length ) {
          this.value = this.defaultValue;
        }
      });
  
      jQuery("#birthday_year").focus(function() {
        if( this.value == this.defaultValue ) {
          this.value = "";
        }
      }).blur(function() {
        if( !this.value.length ) {
          this.value = this.defaultValue;
        }
      });
  
   
  
      jQuery.validator.setDefaults({      
        //if form validates contstuct JSON
        submitHandler: function() {
          var form = JSON.stringify(jQuery('#signup_form').serializeObject());
          jQuery.ajax({
            type : "POST",
            url : "http://localhost:8080/social/rest/signup",
            data : form,
            dataType : "json",
            contentType : "application/json",          
            success : function(data) {
              console.log(data);
            }
          });            
          return false;   
 
        }     

      });
  
      //construct JSON
      jQuery.fn.serializeObject = function()
      {
        var o = {};
        var a = this.serializeArray();
        jQuery.each(a, function() {
          if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
              o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
          } else {
            o[this.name] = this.value || '';
          }
        });
        return o;
      };
  

      jQuery().ready(function() {
        var validator = jQuery("#signup_form").bind("invalid-form.validate", function() {
          jQuery("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
        }).validate({
          debug: true,
          errorElement: "em",
          errorContainer: jQuery("#warning, #summary"),
          errorPlacement: function(error, element) {
            error.appendTo( element.parent("div").next("div") );
          },
          success: function(label) {
            label.text("").addClass("success");
          },
          rules: {
            givenName: {
              required:true,
              minlength:3,
              maxlength:25
		
            },
            familyName: {
              required:true,
              minlength:3,
              maxlength:25	
            },
            email2: {
              required: true,
              email: true,
              remote : "http://localhost:8080/social/rest/validator/email"
            },
            confirmEmail: {
              required: true,
              email2: true,
              equalTo: "#email"
            },
            password: {
              required:true,
              minlength:5
            },
            gender: {
              required:true       
            },
            language: {
              required:true
            },
            messages: {
              email: { 
                remote: "Email address already in use",
                email : "A valid email address is required",
                required : "Please enter a valid email address"
              }
            }
          }
        });
		
      });
