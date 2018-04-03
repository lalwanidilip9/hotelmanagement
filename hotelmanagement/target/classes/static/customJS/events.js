$(document).ready(function() {
	setHeaderFile();
});

$('#btnSearchEventRooms').click(
		function() {
			if ($('#txtStartDate').val().trim() == ''
					|| $('#txtEndDate').val().trim() == ''
					|| $('#ddlEventType').val() == ''
					|| $('#ddlNoOfGuests').val() == ''
					|| $('#ddlMealOption').val() == '') {
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
			var eventRoomSearch = {
				"startDate" : $('#txtStartDate').val(),
				"endDate" : $('#txtEndDate').val(),
				"noOfGuests" : $('#ddlNoOfGuests').val(),
				"eventType" : $('#ddlEventType').val(),
				"eventTypeName" : $('#ddlEventType option:selected').text(),
				"mealOption" : $('#ddlMealOption').val(),
				"mealOptionName" : $('#ddlMealOption option:selected').text()
			};
			$.session.set("eventRoomSearch", JSON.stringify(eventRoomSearch));
			setTimeout(function() {
				window.location.href = "/user/eventhalls";
			}, 500);
		});
