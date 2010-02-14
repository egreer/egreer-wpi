/** validate.js
 * 
 * @author Eric Greer
 * @author Matt Runkle
 * @author Jason Codding
 * 
 * This file contains functions to validate
 * form information.
 */

/**
 * This validates the login form on the client-side
 */
function checkLogin(thisform) {
	
	with(thisform) {
		
		if(isEmpty(username) != false) {
			username.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter your username';
			return false;
		}
		
		if(isEmpty(password) != false) {
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a password';
			return false;
		}
		
		//Encrypt password and replace text
		var hash = hex_sha512(password.value);
		password.value = hash;
	}
	return true;
}

/**
 * This validates the registration form on the client-side
 */
function checkRegister(thisform) {
	
	with(thisform) {
		
		if(isEmpty(firstname) != false) {
			firstname.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter your first name';
			return false;
		}
		
		if(isEmpty(lastname) != false) {
			lastname.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter your last name';
			return false;
		}
		
		if(isEmpty(username) != false) {
			username.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a username';
			return false;
		}
		
		if(isEmpty(password) != false) {
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a password';
			return false;
		}
		
		var passwordCheckFailed = checkPassword(password.value);
		if (!passwordCheckFailed){
			set_border(password, "error");
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a valid password.  Passwords must be at least eight characters long and have at least one letter, one number, and one special character (-, +, _, %, @, *, or &).';
			return false;
		}
			
		if(isEmpty(pwconfirm) != false) {
			pwconfirm.focus();
			document.getElementById('errorMsg').innerHTML = 'Please retype your password to confirm';
			return false;
		}
		
		if(pwconfirm.value != password.value) {
			set_border(password, "error");
			set_border(pwconfirm, "error")
			pwconfirm.focus();
			document.getElementById('errorMsg').innerHTML = 'Your passwords do not match';
			return false;
		}
		
		//Encrypt password and replace text
		var hash = hex_sha512(password.value);
		password.value = hash;
		
		//Encrypt password and replace text
		hash = hex_sha512(pwconfirm.value);
		pwconfirm.value = hash;
	}
	return true;
}

/**
 * This validates the user story form on the client-side
 */
function checkUserStory(thisform) {
	
	with(thisform) {
		
		if(isEmpty(title) != false) {
			title.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a user story title';
			return false;
		}
		
		if(isEmpty(description) != false) {
			description.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a user story description';
			return false;
		}
		
	}
	return true;
}

/**
 * This validates the user story estimation form on the client-side
 */
function checkEstimate(thisform) {
	
	with(thisform) {
		
		// Check for empty
		if(isEmpty(estimate) != false) {
			estimate.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter an estimate';
			return false;
		}
		
		// Test for a numeric value
		if( isNAN(estimate.value) ) {
			estimate.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a valid number';
			return false;
		}
		
		// Disallow negative numbers
		if(estimate.value < 0) {
			estimate.focus();
			document.getElementById('errorMsg').innerHTML = 'Pleas enter a number greater than 0';
			return false;
		}
		
	}
	return true;
}

/**
 * This validates the change password form on the client-side
 */
function checkChangePassword(thisform) {
	
	with(thisform) {
		
		// Test if old password is empty
		if(isEmpty(oldpassword) != false) {
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter your current password';
			return false;
		}
		
		// Test if new password is empty
		if(isEmpty(password) != false) {
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a new password';
			return false;
		}
		
		// Test if the new password meets the criteria
		var passwordCheckFailed = checkPassword(password.value);
		if (!passwordCheckFailed){
			set_border(password, "error");
			password.focus();
			document.getElementById('errorMsg').innerHTML = 'Please enter a valid password. Passwords must be at least eight characters long and have at least one letter, one number, and one special character (-, +, _, %, @, *, or &).';
			return false;
		}
		
		// Test if the confirm new password is empty
		if(isEmpty(pwconfirm) != false) {
			pwconfirm.focus();
			document.getElementById('errorMsg').innerHTML = 'Please retype your password to confirm';
			return false;
		}
		
		if(pwconfirm.value != password.value) {
			set_border(password, "error");
			set_border(pwconfirm, "error")
			pwconfirm.focus();
			document.getElementById('errorMsg').innerHTML = 'Your new passwords do not match';
			return false;
		}
		
		//All is good, encrypt:
		
		//Encrypt password and replace text
		var hash = hex_sha512(oldpassword.value);
		oldpassword.value = hash;
		
		//Encrypt password and replace text
		hash = hex_sha512(password.value);
		password.value = hash;
		
		//Encrypt password and replace text
		hash = hex_sha512(pwconfirm.value);
		pwconfirm.value = hash;
	}
	return true;
}


/** Helper functions */

/**
 * This validates that the password meets the given security requirements
 */
function checkPassword(pw){
	
	if (pw.length < 8)return false;
	var regx = /[0-9]/;
	if(!regx.test(pw)) return false;
	regx = /[a-z]/i;
	if(!regx.test(pw)) return false;
	regx = /[-+_%@*&]/;
	if(!regx.test(pw)) return false;
	return true;
}

/**
 * This method sets the border color to red if control='error'
 */
function set_border(field, control) {

	with(field) {
		if(control == "error") {
			style.borderColor = '#B30800';
		} else {
			style.border = 'none';
		}
	}	
}


/**
 * This method will determine if the input field is empty.
 */
function isEmpty(field) {
	with(field) {
		
		if (value == "" || value == null) {
			set_border(field, "error");
			return true;
		}
	}
	set_border(field, "");
	return false;
}