$(document).ready(function() {
	getFeedbacks();
});

function getFeedbacks(){
	$.ajax({
		url : "/feedback/all",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(result) {
			createDataTableforFeedback(result);
			$('.fixed-table-loading').hide();
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

function createDataTableforFeedback(result) {
	$('#tblFeedback').bootstrapTable('destroy');
	$('.fixed-table-loading').hide();
	$('#tblFeedback').bootstrapTable({
		method : 'get',
		striped : false,
		pagination : true,
		pageSize : 100,
		pageList : [ 5, 10, 25, 50, 100, 200 ],
		columns : [ {
			field : 'feedbackDate',
			title : 'Booking Date',
			align : 'left'
		}, {
			field : 'userName',
			title : 'Name of User',
			align : 'left',
			sortable : true
		}, {
			field : 'emailID',
			title : 'Email ID',
			align : 'left',
			sortable : true
		}, {
			field : 'mobileNumber',
			title : 'Mobile Number',
			align : 'left',
			sortable : true
		}, {
			field : 'comment',
			title : 'Comment',
			align : 'left'
		}],
		data : result
	});
};
