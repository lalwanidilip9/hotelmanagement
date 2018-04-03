$(document).ready(function() {
	var booking = JSON.parse($.session.get("booking"));
	$('#lblGuestName').text(booking.guestName);
	$('#lblMobileNumber').text(booking.mobileNumber);
	$('#lblEmailID').text(booking.emailID);
	$('#lblNoOfAdults').text(booking.noOfAdults);
	$('#lblNoOfChildren').text(booking.noOfChildren);
	$('#lblBookingStatus').text(booking.bookingStatusName);

	var roomNumbers = '';
	if (booking.rooms.length == 1) {
		roomNumbers = booking.rooms[0].roomNumber;
	} else {
		for (var i = 0; i < booking.rooms.length; i++) {
			roomNumbers = roomNumbers + booking.rooms[i].roomNumber + ', ';
		}
		roomNumbers = roomNumbers.substring(0, roomNumbers.length - 2);
	}
	$('#lblRoomNumbers').text(roomNumbers);
	if(booking.bookingStatus == 1){
		showButtons();
	}
	else{
		hideButtons();
	}
});

$("#btnPrint")
		.click(
				function() {
					var contents = $("#divDetails").html();
					var frame1 = $('<iframe />');
					frame1[0].name = "frame1";
					frame1.css({
						"position" : "absolute",
						"top" : "-1000000px"
					});
					$("body").append(frame1);
					var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow
							: frame1[0].contentDocument.document ? frame1[0].contentDocument.document
									: frame1[0].contentDocument;
					frameDoc.document.open();
					// Create a new HTML document.
					frameDoc.document
							.write('<html><head><title>DIV Contents</title>');
					frameDoc.document.write('</head><body>');
					// Append the external CSS file.
					frameDoc.document
							.write('<link href="/css/templatemo-style.css" rel="stylesheet" />');
					// Append the DIV contents.
					frameDoc.document.write(contents);
					frameDoc.document.write('</body></html>');
					frameDoc.document.close();
					setTimeout(function() {
						window.frames["frame1"].focus();
						window.frames["frame1"].print();
						frame1.remove();
					}, 500);
				});

$('#btnReleaseRooms').click(function() {
	openModalPopup('Release Rooms', 'Do you want to release these rooms?', 'release');
});

$('#btnPreClosure').click(function() {
	openPreclosureModalPopup('Preclosure');
});

$('#btnCancelBooking').click(function() {
	openModalPopup('Release Rooms', 'Do you want to cancel this booking?', 'cancel');
});

var openModalPopup = function(title, message, status){
	$('#hotelModal').removeAttr('style');
	$('#hotelModal').modal('show');
	$(".modal .modal-title").html(title);
    $("#actionBtnModalBody").html(message);
    $('#btnModalOK').attr('value', status);
};

var openPreclosureModalPopup = function(title){
	$('#preclosureModal').removeAttr('style');
	$('#preclosureModal').modal('show');
	$(".modal .modal-title").html(title);
};

$('#btnModalOK').click(function(){
	var status = $('#btnModalOK').val();
	if(status == 'release'){
		releaseRoom();
	}
	if(status == 'cancel'){
		cancelBooking();
	}
});

$('#btnPreclosureModalOK').click(function(){
	roomPreClosure();
});

$('#btnModalCancel').click(function() {
	$('#hotelModal').modal('hide');
});

$('#btnPreclosureModalCancel').click(function() {
	$('#preclosureModal').modal('hide');
});

function releaseRoom(){
	var booking = JSON.parse($.session.get("booking"));
	$.ajax({
		url : "/booking/close",
		type : "POST",
		data : JSON.stringify(booking.bookingId),
		contentType : "application/json; charset=utf-8",
		success : function(result) {
			$('#hotelModal').modal('hide');
			if(result.message != null){
				toastr.error(result.message, '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
			}
			else{
				toastr.success('Rooms are released', '', 
						{
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
			}
			$('#lblBookingStatus').text(result.bookingStatusName);
			hideButtons();
		},
		error : function(e) {
			$('#hotelModal').modal('hide');
			toastr.error('Rooms are not found', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
}

function roomPreClosure(){
	var booking = JSON.parse($.session.get("booking"));
	var preClosure = {
			"bookingId" : booking.bookingId,
			"preClosureDate" : $('#txtPreclosureDate').val()
	}
	$.ajax({
		url : "/booking/preclosure",
		type : "POST",
		data : JSON.stringify(preClosure),
		contentType : "application/json; charset=utf-8",
		success : function(result) {
			if(result.message != null){
				toastr.error(result.message, '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
			}
			else{
				toastr.success('Rooms are Preclosed', '', 
						{
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
			}
			$('#preclosureModal').modal('hide');
			$('#lblBookingStatus').text(result.bookingStatusName);
			hideButtons();
		},
		error : function(e) {
			$('#preclosureModal').modal('hide');
			toastr.error('Rooms are not found', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
}

function cancelBooking(){
	var booking = JSON.parse($.session.get("booking"));
	$.ajax({
		url : "/booking/cancel",
		type : "POST",
		data : JSON.stringify(booking.bookingId),
		contentType : "application/json; charset=utf-8",
		success : function(result) {
			if(result.message != null){
				toastr.error(result.message, '', {
					closeButton : true,
					progressBar : true,
					positionClass : "toast-top-center",
					timeOut : "2000",
				});
			}
			else{
				toastr.success('Rooms are released', '', 
						{
							closeButton : true,
							progressBar : true,
							positionClass : "toast-top-center",
							timeOut : "2000",
						});
			}
			$('#hotelModal').modal('hide');
			$('#lblBookingStatus').text(result.bookingStatusName);
			hideButtons();
		},
		error : function(e) {
			$('#hotelModal').modal('hide');
			toastr.error('Rooms are not found', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
}

function hideButtons(){
	$('#btnReleaseRooms').hide();
	$('#btnPreClosure').hide();
	$('#btnCancelBooking').hide();
}

function showButtons(){
	$('#btnReleaseRooms').show();
	$('#btnPreClosure').show();
	$('#btnCancelBooking').show();
}