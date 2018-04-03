$(document).ready(
		function() {
			setHeaderFile();
			var eventRoomSearch = JSON.parse($.session.get("eventRoomSearch"));
			$('#lblSpanOfStay').text(
					eventRoomSearch.startDate + ' to ' + eventRoomSearch.endDate);
			$('#lblEventType').text(eventRoomSearch.eventTypeName + " with " + eventRoomSearch.mealOptionName);
			$('#lblNoOfGuests').text('No of Guests: ' + eventRoomSearch.noOfGuests);
			searchRooms(eventRoomSearch);
		});

$('#btnModifySearch').click(function() {
	setTimeout(function() {
		window.location.href = "/user/events";
	}, 500);
});

function searchRooms(eventRoomSearch) {
	$.ajax({
		url : "/event/search",
		type : "POST",
		data : JSON.stringify(eventRoomSearch),
		contentType : "application/json; charset=utf-8",
		success : function(result) {
			createTable(result);
			$('.fixed-table-loading').hide();
		},
		error : function(e) {
			toastr.error('Event Halls are not found', '', {
				closeButton : true,
				progressBar : true,
				positionClass : "toast-top-center",
				timeOut : "2000",
			});
		},
	});
}

var createDataTableforRooms = function(result) {
	$('#tblRoomTypes').bootstrapTable('destroy');
	for (var i = 0; i < result.length; i++) {
		result[i].buttonHtml = "<button type='button' id='btnViewDetails' value='"+ result[i].roomTypeId + "' class='tm-yellow-btn viewButtonClass'>View</button>";
		result[i].imagePath = "<img src='/img/rooms/" + result[i].imagePath +"' width='100' height='100' />";
	}
	$('#tblRoomTypes').bootstrapTable({
		method : 'get',
		striped : false,
		pagination : true,
		pageSize : 100,
		pageList : [ 5, 10, 25, 50, 100, 200 ],
		columns : [ {
			field : 'imagePath',
			title : '',
			align : 'center'
		}, {
			field : 'roomTypeName',
			title : 'Room Type',
			align : 'left',
			formatter : 'roomTypeFormatter'
		}, {
			field : 'roomCharge',
			title : 'Room Charge',
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

	$("#tblRoomTypes tbody tr td button").on('click', '', function() {
		var roomSearch = JSON.parse($.session.get("roomSearch"));
		roomSearch.roomTypeId = $(this).val();
		$.session.remove("roomSearch");
		$.session.set("roomSearch", JSON.stringify(roomSearch));
		$.ajax({
			url : "/user/checkauthentication",
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(result) {
				setTimeout(function() {
					window.location.href = '/user/eventbooking';
				}, 500);
			},
			error : function(e) {
				setTimeout(function() {
					window.location.href = '/login';
				}, 500);
			},
		});
	});
};

/*function createTable(result){
	var table = $('#tblEventHalls');
	for (var i = 0; i < result.length; i++) {
		var htmlString = "<tr><td><img onclick='openImagePopup(this)' class='img-responsive' value='"+ result[i].roomTypeId +"' src='/img/rooms/" + result[i].imagePath +"' width='100' height='100' /></td>" +
			"<td width='60%'><div id='divRoomTypeName' style='font-weight: bold;font-size: medium;'>" + result[i].roomTypeName + "</div>" +
			"<div id='divDescription'>" + result[i].description + "</div> " +
			"<div id='divAmenities' value='amenity"+ result[i].roomTypeId + "'></div>" +
			"<div id='divReadMore'><a onclick='viewAmenities(this)' id='lnkReadMore' value='"+ result[i].roomTypeId +"'>Read More...</a></div></td>" +
			"<td><div id='divMaxNoOfGuests' style='font-weight: bold;font-size: medium;'>" + result[i].noOfMaxAdults + "</div></td>" +
			"<td><div id='divRoomCharge' style='font-weight: bold;font-size: medium;'>₹" + result[i].roomCharge + "</div></td>" +
			"<td><button type='button' onclick='viewDetails(this)' id='btnViewDetails' value='"+ result[i].roomTypeId + "' class='tm-yellow-btn viewButtonClass'>Book Rooms</button></td>" +
			"</tr>";
		var tableHead = $(htmlString);
        table.append(tableHead);
	}
}*/

function createTable(result){
	var section = $('#more');
	for (var i = 0; i < result.length; i++) {
		var htmlString = "<div class='row form-group'><div class='col-sm-2'><img onclick='openImagePopup(this)' class='img-responsive' value='"+ result[i].roomTypeId +"' src='/img/rooms/" + result[i].imagePath +"' width='100' height='100' /></div>" +
			"<div class='col-sm-8'><p style='font-weight: bold;font-size: medium;'>" + result[i].roomTypeName + "</p> " +
			"<p>"+result[i].description+"</p><div class='table-responsive col-md-12' id='divAmenities' value='amenity"+ result[i].roomTypeId + "'></div> " +
			"<div class='table-responsive col-md-12' id='divPrice' style='font-weight: bold;'>Max No Of Guests: "+result[i].noOfMaxAdults+"</div>" +
			"<div class='table-responsive col-md-12' id='divPrice' style='font-weight: bold;'>Hall Charge: ₹"+result[i].roomCharge+"</div>" +
			"<div class='table-responsive col-md-12' id='divReadMore'><a onclick='viewAmenities(this)' id='lnkReadMore' value='"+ result[i].roomTypeId +"'>Read More...</a></div></div>" +
			"<div class='col-sm-2'><button type='button' onclick='viewDetails(this)' id='btnViewDetails' value='"+ result[i].roomTypeId + "' class='tm-yellow-btn viewButtonClass'>Book Rooms</button></div>" +
			"</div>";
		var tableHead = $(htmlString);
		section.append(tableHead);
	}
}

function openImagePopup(roomImage) {
	var roomTypeId = $(roomImage).attr("value");
	$.ajax({
		url : "/roomtype/imagesforpopup/" + roomTypeId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$.session.remove("roomImages");
			$.session.set("roomImages", JSON.stringify(result));
			var imgPath = "<img class='img-responsive' src='/img/rooms/" + result[0].imagePath +"' />";
			$('#hotelModal').removeAttr('style');
			$('#hotelModal').modal('show');
			$(".modal .modal-title").html('OPAL Rooms');
			$("#actionBtnModalBody").html(imgPath);
			$('#btnModalPrev').attr('value', 0);
			$('#btnModalNext').attr('value', 0);
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
};

$('#btnModalPrev').click(function() {
	var index = $(this).val();
	var currentImageIndex = parseInt(index);
	if(currentImageIndex > 0){
		if($.session.get("roomImages") != null){
			var roomImages = JSON.parse($.session.get("roomImages"));
			currentImageIndex = currentImageIndex - 1;
			var imgPath = "<img class='img-responsive' src='/img/rooms/" + roomImages[currentImageIndex].imagePath +"' />";
			$("#actionBtnModalBody").html(imgPath);
			$('#btnModalPrev').attr('value', currentImageIndex);
			$('#btnModalNext').attr('value', currentImageIndex);
			if(currentImageIndex < roomImages.length - 1){
				$("#btnModalNext").removeAttr('disabled');
			}
		}
	}
	if(currentImageIndex <= 0){
		$('#btnModalPrev').attr('disabled', 'disabled');
	}
});

$('#btnModalNext').click(function() {
	var index = $(this).val();
	var currentImageIndex = parseInt(index);
	if($.session.get("roomImages") != null){
		var roomImages = JSON.parse($.session.get("roomImages"));
		if(currentImageIndex < roomImages.length){
			currentImageIndex = currentImageIndex + 1;
			var imgPath = "<img class='img-responsive' src='/img/rooms/" + roomImages[currentImageIndex].imagePath +"' />";
			$("#actionBtnModalBody").html(imgPath);
			$('#btnModalPrev').attr('value', currentImageIndex);
			$('#btnModalNext').attr('value', currentImageIndex);
		}
		if(currentImageIndex == roomImages.length){
			$('#btnModalNext').attr('disabled', 'disabled');
		}
		if(currentImageIndex > 0){
			$('#btnModalPrev').removeAttr('disabled');
		}
	}
	
});

$('#btnModalCancel').click(function() {
	$.session.remove("roomImages");
	$('#hotelModal').modal('hide');
});

function viewDetails(btnViewDetails){
	var eventRoomSearch = JSON.parse($.session.get("eventRoomSearch"));
	eventRoomSearch.roomTypeId = $(btnViewDetails).val();
	$.ajax({
		url : "/user/checkauthentication",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$.session.remove("eventRoomSearch");
			$.session.set("eventRoomSearch", JSON.stringify(eventRoomSearch));
			setTimeout(function() {
				window.location.href = '/user/eventbooking';
			}, 500);
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = '/login';
			}, 500);
		},
	});
}

function viewAmenities(lnkViewAmenities){
	var roomTypeId = $(lnkViewAmenities).attr("value");
	if($(lnkViewAmenities)[0].innerHTML == 'Read More...'){
		$.ajax({
			url : "/roomtype/amenities/" + roomTypeId,
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(result) {
				populateAmenities(result, roomTypeId);
				$(lnkViewAmenities).html('Read Less...');
			},
			error : function(e) {
				setTimeout(function() {
					window.location.href = "/logout";
				}, 500);
			},
		});
	}
	else{
		var divAmenity = $("[value=amenity"+ roomTypeId +"]");
		$(divAmenity).html('');
		$(lnkViewAmenities).html('Read More...');
	}
}

function populateAmenities(result, roomTypeId){
	var divAmenity = $("[value=amenity"+ roomTypeId +"]");
	var htmlString = "<ul>";
	for (var i = 0; i < result.length; i++) {
		htmlString = htmlString + "<li>" + result[i].amenity + "</li>";
	}
	htmlString = htmlString + "</ul>";
	var amenities = $(htmlString);
	$(divAmenity).append(amenities);
	$(divAmenity).removeAttr('style');
}