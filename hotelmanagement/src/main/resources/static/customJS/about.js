$(document).ready(function() {
	setHeaderFile();
	populateReviews();
	$.session.remove("roomSearch");
});

function populateReviews() {
	$
			.ajax({
				url : "/feedback/all",
				type : "GET",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(result) {
					var noOfReviews = result.length;
					if (result.length > 3) {
						noOfReviews = 3;
					}
					for (var i = 0; i < noOfReviews; i++) {
						if (i == 0) {
							var firstDiv = $('#divFirstReview');
							var htmlString = "<p>"+ result[i].comment +"</p><strong class='text-uppercase'>"+ result[i].userName +"</strong>";
							var content = $(htmlString);
							firstDiv.append(content);
						}
						if (i == 1) {
							var secondDiv = $('#divSecondReview');
							htmlString = "<p>"+ result[i].comment +"</p><strong class='text-uppercase'>"+ result[i].userName +"</strong>";
							content = $(htmlString);
							secondDiv.append(content);
						}
						/*if (i == 2) {
							var thirdDiv = $('#divThirdReview');
							htmlString = "<p>"+ result[i].comment +"</p><strong class='text-uppercase'>"+ result[i].userName +"</strong>";
							content = $(htmlString);
							thirdDiv.append(content);
						}*/
					}
				},
				error : function(e) {
					
				},
			});
}