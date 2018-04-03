$(document).ready(
		function() {
			populateRoomTypes();
		});

function populateRoomTypes() {
	$.ajax({
		url : "/roomtype/all",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			createDataTableforRoomTypes(result);
		},
		error : function(e) {
			$('#tblRoomTypes').bootstrapTable('destroy');
		},
	});
}

var createDataTableforRoomTypes = function(result) {
	$('.fixed-table-loading').hide();
	$('#tblRoomTypes').bootstrapTable('destroy');
	for (var i = 0; i < result.length; i++) {
		result[i].buttonHtml = "<button type='button' id='btnViewDetails' value='"+ result[i].roomTypeId + "' class='tm-yellow-btn viewButtonClass'>View</button>"
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
			field : 'buttonHtml',
			title : '',
			align : 'left',
			sortable : true
		} ],
		data : result
	});

	$("#tblRoomTypes tbody tr td button").on('click', '', function() {
		$.session.remove("adminRoomTypeId");
		$.session.set("adminRoomTypeId", JSON.stringify($(this).val()));
		$.ajax({
			url : "/user/checkauthentication",
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(result) {
				setTimeout(function() {
					window.location.href = '/admin/roomtypedetails';
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
