$(document).ready(function() {
	$('#txtUsername').focus();
});

$('#btnLogin').click(function() {
	if ($('#txtUsername').val().trim() == '' || $('#txtPassword').val() == '') {
		toastr.error('Please enter Username and Password', '', {
			closeButton : true,
			progressBar : true,
			positionClass : "toast-top-center",
			timeOut : "2000",
		});
		return;
	}
	authenticateUser($('#txtUsername').val(), $('#txtPassword').val());
});

function authenticateUser(username, password) {
	$('#lblMessage').text('');
	$.ajax({
		url : "/user/authenticate/" + username + "/" + password,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			if (result.userType == 1) {
				if($.session.get("roomSearch") != null){
					setTimeout(function() {
						window.location.href = "/user/roomtypes";
					}, 500);
				}
				else{
					setTimeout(function() {
						window.location.href = '/aboutus';
					}, 500);
				}
			} else if (result.userType == 2) {
				setTimeout(function() {
					window.location.href = '/admin';
				}, 500);
			}
		},
		error : function(e) {
			toastr.error('Invalid Username or Password', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
}

function loginLinkClicked() {
	$('#lnkLogin').addClass("active");
	$('#lnkRooms').removeClass("active");
	$.session.remove("roomSearch");
}

function roomsLinkClicked() {
	$('#lnkLogin').removeClass("active");
	$('#lnkRooms').addClass("active");
	$.session.remove("roomSearch");
}

$('#btnSubmit').click(
		function() {
			if ($('#txtNewUserName').val().trim() == ''
					|| $('#txtNewUserEmailID').val().trim() == ''
					|| $('#txtNewUserMobileNumber').val().trim() == ''
					|| $('#txtNewUserPassword').val() == '') {
				toastr.error('Please enter all the details', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtNewUserName').focus();
				return;
			}
			if (!validateEmailID($('#txtNewUserEmailID').val())) {
				toastr.error('Please enter valid Email ID', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtNewUserEmailID').focus();
				return;
			}
			if (!validateMobileNumber($('#txtNewUserMobileNumber').val())) {
				toastr.error('Please enter valid Mobile Number', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				$('#txtNewUserMobileNumber').focus();
				return;
			}
			$('#lblNewUserMessage').text('');
			var user = {
				"userName" : $('#txtNewUserName').val(),
				"emailId" : $('#txtNewUserEmailID').val(),
				"mobileNumber" : $('#txtNewUserMobileNumber').val(),
				"password" : $('#txtNewUserPassword').val(),
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
						if (result.userId != null) {
							if (result.message == '') {
								setTimeout(function() {
									window.location.href = '/aboutus';
								}, 500);
							} else {
								toastr.error(result.message, '', {
									closeButton : true,
									progressBar : true,
									positionClass : "toast-top-center",
									timeOut : "2000",
								});
							}
						} else {
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