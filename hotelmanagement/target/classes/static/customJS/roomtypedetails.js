$(document).ready(function() {
	var roomTypeId = JSON.parse($.session.get("adminRoomTypeId"));
	if (roomTypeId != null && roomTypeId != '' && roomTypeId != 'null') {
		populateRoomTypeDetails(roomTypeId);
		populateRoomImages(roomTypeId);
	} else {
		setTimeout(function() {
			window.location.href = "/logout";
		}, 500);
	}
});

function populateRoomTypeDetails(roomTypeId) {
	$.ajax({
		url : "/roomtype/" + roomTypeId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			$('#txtRoomTypeName').val(result.roomTypeName);
			$('#txtNoOfRooms').val(result.noOfRooms);
			$('#txtNoOfMaxAdults').val(result.noOfMaxAdults);
			$('#txtNoOfMaxChildren').val(result.noOfMaxChildren);
			$('#txtRoomCharge').val(result.roomCharge);
			$('#txtDescription').val(result.description);
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = "/logout";
			}, 500);
		},
	});
}

function populateRoomImages(roomTypeId) {
	$.ajax({
		url : "/roomtype/images/" + roomTypeId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			for (var i = 0; i < result.length; i++) {
				if (result[i].image1 != null) {
					result[i].image1 = "<img src='/img/rooms/" + result[i].image1 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId1 + "' />";
				}
				if (result[i].image2 != null) {
					result[i].image2 = "<img src='/img/rooms/" + result[i].image2 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId2 + "' />";
				}
				if (result[i].image3 != null) {
					result[i].image3 = "<img src='/img/rooms/" + result[i].image3 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId3 + "' />";
				}
				if (result[i].image4 != null) {
					result[i].image4 = "<img src='/img/rooms/" + result[i].image4 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId4 + "' />";
				}
				if (result[i].image5 != null) {
					result[i].image5 = "<img src='/img/rooms/" + result[i].image5 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId5 + "' />";
				}
				if (result[i].image6 != null) {
					result[i].image6 = "<img src='/img/rooms/" + result[i].image6 + "' class='img-responsive col-sm-12' alt='" + result[i].imageId6 + "' />";
				}
			}
			createDataTableforImages(result);
		},
		error : function(e) {
			setTimeout(function() {
				window.location.href = "/logout";
			}, 500);
		},
	});
}

var createDataTableforImages = function(result) {
	$('.fixed-table-loading').hide();
	$('#tblImages').bootstrapTable('destroy');
	$('#tblImages').bootstrapTable({
		method : 'get',
		striped : false,
		pagination : true,
		pageSize : 100,
		pageList : [ 5, 10, 25, 50, 100, 200 ],
		columns : [ {
			field : 'image1',
			title : '',
			align : 'center'
		}, {
			field : 'image2',
			title : '',
			align : 'center'
		}, {
			field : 'image3',
			title : '',
			align : 'center'
		}, {
			field : 'image4',
			title : '',
			align : 'center'
		}, {
			field : 'image5',
			title : '',
			align : 'center'
		}, {
			field : 'image6',
			title : '',
			align : 'center'
		} ],
		data : result
	});

	$('.fixed-table-loading').hide();

	$("#tblImages tbody tr td img").on('click', '', function() {
		openImagePopup($(this).attr("alt"), $(this)[0].outerHTML);
	});
};

$('#btnSave').click(
		function() {
			var roomTypeId = JSON.parse($.session.get("adminRoomTypeId"));
			var roomtype = {
				"roomTypeId" : roomTypeId,
				"roomTypeName" : $('#txtRoomTypeName').val(),
				"noOfRooms" : $('#txtNoOfRooms').val(),
				"noOfMaxAdults" : $('#txtNoOfMaxAdults').val(),
				"noOfMaxChildren" : $('#txtNoOfMaxChildren').val(),
				"roomCharge" : $('#txtRoomCharge').val(),
				"description" : $('#txtDescription').val()
			}
			$.ajax({
				url : "/roomtype/save",
				type : "POST",
				data : JSON.stringify(roomtype),
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					setTimeout(function() {
						window.location.href = "/admin/roommngt";
					}, 500);
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

var openImagePopup = function(imageId, imgPath) {
	$('#hotelModal').removeAttr('style');
	$('#hotelModal').modal('show');
	$(".modal .modal-title").html($('#txtRoomTypeName').val());
	$("#actionBtnModalBody").html(imgPath);
	$('#btnModalDelete').attr('value', imageId);
};

$('#btnModalCancel').click(function() {
	$('#hotelModal').modal('hide');
});

$('#btnModalDelete').click(
		function() {
			$.ajax({
				url : "/roomtype/image/delete",
				type : "POST",
				data : $('#btnModalDelete').val(),
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					$('#hotelModal').modal('hide');
					toastr.success('Image is deleted successfully', '', 
							{
								closeButton : true,
								progressBar : true,
								positionClass : "toast-top-center",
								timeOut : "2000",
							});
					var roomTypeId = JSON.parse($.session
							.get("adminRoomTypeId"));
					if (roomTypeId != null && roomTypeId != ''
							&& roomTypeId != 'null') {
						populateRoomImages(roomTypeId);
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
