$(document).ready(function() {
	$.session.remove("roomSearch");
	$.ajax({
		url : "/currentuser",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$('#txtUserName').val(result.userName);
			$('#txtEmailID').val(result.emailId);
			$('#txtMobileNumber').val(result.mobileNumber);
			$('#txtPassword').val(result.password);
			$('#txtUserName').focus();
		},
		error : function(e) {
			
		},
	});
});

$('#btnSave').click(
		function() {
			if ($('#txtUserName').val().trim() == ''
					|| $('#txtEmailID').val().trim() == ''
					|| $('#txtMobileNumber').val().trim() == ''
					|| $('#txtPassword').val() == '') {
				toastr.error('Please enter all the details', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtUserName').focus();
				return;
			}
			if (!validateEmailID($('#txtEmailID').val())) {
				toastr.error('Please enter valid Email ID', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtEmailID').focus();
				return;
			}
			if (!validateMobileNumber($('#txtMobileNumber').val())) {
				toastr.error('Please enter valid Mobile Number', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtMobileNumber').focus();
				return;
			}
			$('#lblMessage').text('');
			var user = {
				"userName" : $('#txtUserName').val(),
				"emailId" : $('#txtEmailID').val(),
				"mobileNumber" : $('#txtMobileNumber').val(),
				"password" : $('#txtPassword').val(),
				"userType" : 1,
				"isActive" : "true",
				"message" : ""
			}
			$.ajax({
				url : "/user/save",
				type : "POST",
				data : JSON.stringify(user),
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					if (result != null) {
						if (result.message == '') {
							toastr.success('User details are saved successfully', '', 
									{
										closeButton : true,
										progressBar : true,
										positionClass : "toast-top-center",
										timeOut : "2000",
									});
						} else {
							toastr.error(result.message, '', {
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

$('#btnMyBookings').click(function() {
	setTimeout(function() {
		window.location.href = "/user/mybookings";
	}, 500);
});