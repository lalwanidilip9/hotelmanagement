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
	var roomSearch = JSON.parse($.session.get("eventRoomSearch"));
	var eventRoomSearch = JSON.parse($.session.get("eventRoomSearch"));
	$('#lblSpanOfStay').text(
			eventRoomSearch.startDate + ' to ' + eventRoomSearch.endDate);
	$('#lblEventType').text(eventRoomSearch.eventTypeName + " with " + eventRoomSearch.mealOptionName);
	$('#lblNoOfGuests').text('No of Guests: ' + eventRoomSearch.noOfGuests);
	searchRooms(eventRoomSearch);
}

$('#btnConfirmBooking')
		.click(
				function() {
					var roomSearch = JSON.parse($.session.get("eventRoomSearch"));
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
						"noOfRooms" : 1,
						"noOfAdults" : roomSearch.noOfGuests,
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
						"eventType" : roomSearch.eventType,
						"mealOption" : roomSearch.mealOption
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
											$.session.remove("eventRoomSearch");
											$.session.set("booking", JSON
													.stringify(result));
											setTimeout(
													function() {
														window.location.href = "/user/eventbookingconfirmation";
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
		window.location.href = "/user/events";
	}, 500);
});