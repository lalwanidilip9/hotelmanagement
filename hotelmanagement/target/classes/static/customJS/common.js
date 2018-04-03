function setHeaderFile() {
	$.ajax({
		url : "/user/checkauthentication",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			if (result.userType == 1) {
				setUserHeader();
			} else if (result.userType == 2) {
				setAdminHeader();
			} else {
				setGuestHeader();
			}
		},
		error : function(e) {
			setGuestHeader();
		},
	});
}

function setAdminHeader() {
	$('#divGuestHeader').attr('style', 'display:none');
	$('#divUserHeader').attr('style', 'display:none');
	$('#divAdminHeader').removeAttr('style');
}

function setUserHeader() {
	$('#divGuestHeader').attr('style', 'display:none');
	$('#divAdminHeader').attr('style', 'display:none');
	$('#divUserHeader').removeAttr('style');
}

function setGuestHeader() {
	$('#divUserHeader').attr('style', 'display:none');
	$('#divAdminHeader').attr('style', 'display:none');
	$('#divGuestHeader').removeAttr('style');
}

function validateEmailID(emailID) {
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (filter.test(emailID)) {
		return true;
	} else {
		return false;
	}
}

function validateDate(txtDate) {
	var currVal = txtDate;
	  if(currVal == '')
	    return false;
	  
	  var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; 
	  var dtArray = currVal.match(rxDatePattern); // is format OK?

	  if (dtArray == null)
	     return false;
	 
	  //Checks for mm/dd/yyyy format.
	  var dtMonth = dtArray[1];
	  var dtDay= dtArray[3];
	  var dtYear = dtArray[5];

	  if (dtMonth < 1 || dtMonth > 12)
	      return false;
	  else if (dtDay < 1 || dtDay> 31)
	      return false;
	  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
	      return false;
	  else if (dtMonth == 2)
	  {
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap))
	          return false;
	  }
	  return true;
}

function validatePinCode(pinCode) {
	var filter = /^\d*(?:\.\d{1,2})?$/;
	if (filter.test(pinCode)) {
		if (pinCode.length == 6) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

function validateMobileNumber(mobileNumber) {
	var filter = /^\d*(?:\.\d{1,2})?$/;
	if (filter.test(mobileNumber)) {
		if (mobileNumber.length == 10) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

function validateCreditDebitNumber(cardNumber) {
	var filter = /^\d*(?:\.\d{1,2})?$/;
	if (filter.test(cardNumber)) {
		if (cardNumber.length == 16) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}