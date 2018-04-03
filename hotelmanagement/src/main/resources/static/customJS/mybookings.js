$(document).ready(function() {
	checkAuthentication();
});

function checkAuthentication() {
	$.ajax({
		url : "/user/checkauthentication",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			getMyBookings();
			$.session.remove("roomSearch");
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = '/login';
			}, 500);
		},
	});
}

function getMyBookings(){
	$.ajax({
		url : "/booking/mybookings",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			createDataTableforRooms(result);
			$('.fixed-table-loading').hide();
		},
		error : function(e) {
			
		},
	});
}

var createDataTableforRooms = function(result) {
	$('#tblRoomTypes').bootstrapTable('destroy');
	for (var i = 0; i < result.length; i++) {
		result[i].buttonHtml = "<button type='button' id='btnViewDetails' value='"
				+ result[i].bookingId
				+ "' class='tm-yellow-btn viewButtonClass'>View</button>"
	}
	$('#tblMyBookings').bootstrapTable({
		method : 'get',
		striped : false,
		pagination : true,
		pageSize : 100,
		pageList : [ 5, 10, 25, 50, 100, 200 ],
		columns : [ {
			field : 'bookingId',
			title : 'Booking ID',
			align : 'center'
		}, {
			field : 'noOfRooms',
			title : 'No Of Rooms',
			align : 'left',
			formatter : 'roomTypeFormatter'
		}, {
			field : 'startDate',
			title : 'Start Date',
			align : 'center',
			sortable : true
		}, {
			field : 'endDate',
			title : 'End Date',
			align : 'center',
			sortable : true
		}, {
			field : 'bookingStatusName',
			title : 'Booking Status',
			align : 'center',
			sortable : true
		}, {
			field : 'buttonHtml',
			title : '',
			align : 'left',
			sortable : true
		} ],
		data : result
	});

	$("#tblMyBookings tbody tr td button").on('click', '', function() {
		var bookingId = $(this).val();
		$.ajax({
			url : "/user/checkauthentication",
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(loggedInUser) {
				searchByBookingId(bookingId);
			},
			error : function(e) {
				setTimeout(function() {
					window.location.href = '/login';
				}, 500);
			},
		});
	});
};

function searchByBookingId(bookingId){
	$.ajax({
		url : "/booking/" + bookingId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$.session.set("booking", JSON.stringify(result));
			if(result.noOfAdults > 5){
				setTimeout(function(){ window.location.href = "/user/eventbookingconfirmation";}, 500);
			}
			else{
				setTimeout(function(){ window.location.href = "/user/bookingconfirmation";}, 500);
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
}