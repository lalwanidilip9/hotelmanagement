$(document).ready(function() {
	setHeaderFile();
	checkAuthentication();
});

function checkAuthentication() {
	$.ajax({
		url : "/user/checkauthentication",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			populateRoomSearchParams();
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = '/login';
			}, 500);
		},
	});
}

function populateRoomSearchParams() {
	var roomSearch = JSON.parse($.session.get("roomSearch"));
	$('#lblSpanOfStay')
			.text(roomSearch.startDate + ' to ' + roomSearch.endDate);
	var adultString = '', childrenString = '';
	$('#lblNoOfRooms').text('No of Rooms: ' + roomSearch.noOfRooms);
	if (roomSearch.noOfAdults == '0' || roomSearch.noOfAdults == '1') {
		adultString = roomSearch.noOfAdults + ' adult';
	} else {
		adultString = roomSearch.noOfAdults + ' adults';
	}
	if (roomSearch.noOfChildren == '0' || roomSearch.noOfChildren == '1') {
		childrenString = roomSearch.noOfChildren + ' child';
	} else {
		childrenString = roomSearch.noOfChildren + ' children';
	}
	$('#lblNoOfMembers').text(adultString + ' & ' + childrenString);
}

$('#btnConfirmBooking')
		.click(
				function() {
					var roomSearch = JSON.parse($.session.get("roomSearch"));
					if ($('#ddlTypeOfCard').val() == ''
							|| $('#txtCardNumber').val().trim() == ''
							|| $('#ddlExpMonth').val() == ''
							|| $('#ddlExpYear').val() == ''
							|| $('#txtGuestName').val().trim() == ''
							|| $('#txtEmailID').val().trim() == ''
							|| $('#txtMobileNumber').val().trim() == ''
							|| $('#txtAddress').val().trim() == ''
							|| $('#txtCity').val().trim() == ''
							|| $('#txtState').val().trim() == ''
							|| $('#txtPinCode').val().trim() == '') {
						toastr.error('Please enter all the details', '', {
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
						return;
					}
					if (!validateEmailID($('#txtEmailID').val())) {
						toastr.error('Invalid Email ID', '', {
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
						return;
					}
					if (!validateMobileNumber($('#txtMobileNumber').val())) {
						toastr.error('Invalid Mobile Number', '', {
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
						return;
					}
					if (!validatePinCode($('#txtPinCode').val())) {
						toastr.error('Invalid Pin Code', '', {
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
						return;
					}
					$('#lblMessage').text('');
					var booking = {
						"roomTypeId" : roomSearch.roomTypeId,
						"startDateString" : roomSearch.startDate,
						"endDateString" : roomSearch.endDate,
						"noOfRooms" : roomSearch.noOfRooms,
						"noOfAdults" : roomSearch.noOfAdults,
						"noOfChildren" : roomSearch.noOfChildren,
						"paymentModeId" : $('#ddlTypeOfCard').val(),
						"cardNumber" : $('#txtCardNumber').val(),
						"cardExpMonth" : $('#ddlExpMonth').val(),
						"cardExpYear" : $('#ddlExpYear').val(),
						"guestName" : $('#txtGuestName').val(),
						"emailID" : $('#txtEmailID').val(),
						"mobileNumber" : $('#txtMobileNumber').val(),
						"address" : $('#txtAddress').val(),
						"city" : $('#txtCity').val(),
						"state" : $('#txtState').val(),
						"pinCode" : $('#txtPinCode').val(),
					};
					$
							.ajax({
								url : "/booking/save",
								type : "POST",
								data : JSON.stringify(booking),
								contentType : "application/json; charset=utf-8",
								success : function(result) {
									if (result != null) {
										if (result.message == '') {
											$.session.remove("roomSearch");
											$.session.set("booking", JSON
													.stringify(result));
											setTimeout(
													function() {
														window.location.href = "/user/bookingconfirmation";
													}, 500);
										} else {
											toastr
													.error(
															result.message,
															'',
															{
																closeButton : true,
																progressBar : true,
																positionClass : "toast-top-center",
																timeOut : "2000",
															});
										}
									} else {
										toastr
												.error(
														'Error occured during confirmation',
														'',
														{
															closeButton : true,
															progressBar : true,
															positionClass : "toast-top-center",
															timeOut : "2000",
														});
									}
								},
								error : function(e) {
									toastr
											.error(
													'Error occured during confirmation',
													'',
													{
														closeButton : true,
														progressBar : true,
														positionClass : "toast-top-center",
														timeOut : "2000",
													});
								},
							});
				});

$('#btnModifySearch').click(function() {
	setTimeout(function() {
		window.location.href = "/user";
	}, 500);
});