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
			populateBookingDetails();
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = '/login';
			}, 500);
		},
	});
}

function populateBookingDetails(){
	var booking = JSON.parse($.session.get("booking"));
	$('#lblGuestName').text(booking.guestName);
	$('#lblMobileNumber').text(booking.mobileNumber);
	$('#lblEmailID').text(booking.emailID);
	$('#lblNoOfAdults').text(booking.noOfAdults);
	$('#lblNoOfChildren').text(booking.noOfChildren);
	
	var roomNumbers = '';	
	if(booking.rooms.length == 1){
		roomNumbers = booking.rooms[0].roomNumber;
	}
	else{
		for (var i = 0; i < booking.rooms.length; i++) {
			roomNumbers = roomNumbers + booking.rooms[i].roomNumber + ', ';
		}
		roomNumbers = roomNumbers.substring(0, roomNumbers.length - 2);
	}
	$('#lblRoomNumbers').text(roomNumbers);
}

$("#btnPrint").click(function () {
    var contents = $("#divDetails").html();
    var frame1 = $('<iframe />');
    frame1[0].name = "frame1";
    frame1.css({ "position": "absolute", "top": "-1000000px" });
    $("body").append(frame1);
    var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
    frameDoc.document.open();
    //Create a new HTML document.
    frameDoc.document.write('<html><head><title>OPAL Booking Confirmation</title>');
    frameDoc.document.write('</head><body>');
    //Append the external CSS file.
    frameDoc.document.write('<link href="/css/templatemo-style.css" rel="stylesheet" />');
    //Append the DIV contents.
    frameDoc.document.write(contents);
    frameDoc.document.write('</body></html>');
    frameDoc.document.close();
    setTimeout(function () {
        window.frames["frame1"].focus();
        window.frames["frame1"].print();
        frame1.remove();
    }, 500);
});

$('#btnBookAnotherRoom').click(function() {
	setTimeout(function(){ window.location.href = "/user";}, 500);
});
