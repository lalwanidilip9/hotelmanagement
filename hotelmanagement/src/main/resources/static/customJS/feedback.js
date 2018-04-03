$(document).ready(function() {
	setHeaderFile();
	$.session.remove("roomSearch");
	$.ajax({
		url : "/currentuser",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$('#divUsername').hide();
			$('#divEmailID').hide();
			$('#divMobileNumber').hide();
		},
		error : function(e) {
			
		},
	});
});

$('#btnSave').click(function() {
	if($('#divUsername').attr('style') != 'display: none;'){
		if($('#txtUserName').val().trim() == ''){
			toastr.error('Please enter the name', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
			return;
		}
		else if($('#txtEmailID').val().trim() == '' && $('#txtMobileNumber').val().trim() == ''){
			toastr.error('Please enter Email ID or Mobile Number', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
			return;
		}
		else if($('#txtEmailID').val().trim() != '' && !validateEmailID($('#txtEmailID').val())){
			toastr.error('Invalid Email ID', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
			return;
		}
		else if($('#txtMobileNumber').val().trim() != '' && !validateMobileNumber($('#txtMobileNumber').val())){
			toastr.error('Invalid Mobile Number', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
			return;
		}
	}
	if($('#txtComment').val() == ''){
		toastr.error('Please enter the comment', '', {
			closeButton : true,
			progressBar : true,
			positionClass : "toast-top-center",
			timeOut : "2000",
		});
		return;
	}
	var feedback = {
		"userName" : $('#txtUserName').val(),
		"emailID" : $('#txtEmailID').val(),
		"mobileNumber" : $('#txtMobileNumber').val(),
		"comment" : $('#txtComment').val()
	}
	$.ajax({
		url : "/feedback/save",
		type : "POST",
		data : JSON.stringify(feedback),
		contentType : "application/json; charset=utf-8",
		success : function(result) {
			if (result != null) {
				if (result.feedbackId != null && result.feedbackId != '') {
					clearFields();
					toastr.success('Feedback is submitted successfully', '', 
							{
								closeButton : true,
								progressBar : true,
								positionClass : "toast-top-center",
								timeOut : "2000",
							});
					if($('#divUsername').attr('style') != 'display: none;'){
						$('#txtUserName').focus();
					}
					else{
						$('#txtComment').focus();
					}
				}
				else{
					toastr.error('Error occured', '', {
						closeButton : true,
						progressBar : true,
						positionClass : "toast-top-center",
						timeOut : "2000",
					});
				}
			}
		},
		error : function(e) {
			toastr.error('Error occured', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
});

function clearFields(){
	$('#txtUserName').val('');
	$('#txtEmailID').val('');
	$('#txtMobileNumber').val('');
	$('#txtComment').val('');
}