$(document).ready(function() {
	setHeaderFile();
});

$('#btnSearchRooms').click(
		function() {
			if ($('#txtStartDate').val().trim() == ''
					|| $('#txtEndDate').val().trim() == ''
					|| $('#ddlNoOfRooms').val() == ''
					|| $('#ddlNoOfAdults').val() == ''
					|| $('#ddlNoOfChildren').val() == '') {
				toastr.error('Please enter all the details', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				return;
			}
			else if(!validateDate($('#txtStartDate').val())){
				toastr.error('Invalid Start Date', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				return;
			}
			else if(!validateDate($('#txtEndDate').val())){
				toastr.error('Invalid End Date', '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
				return;
			}
			var roomSearch = {
				"startDate" : $('#txtStartDate').val(),
				"endDate" : $('#txtEndDate').val(),
				"noOfRooms" : $('#ddlNoOfRooms').val(),
				"noOfAdults" : $('#ddlNoOfAdults').val(),
				"noOfChildren" : $('#ddlNoOfChildren').val()
			};
			$.session.set("roomSearch", JSON.stringify(roomSearch));
			setTimeout(function() {
				window.location.href = "/user/roomtypes";
			}, 500);
		});
