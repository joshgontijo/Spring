$(document).ready(function() {
	$('#mySubmitButton').click(function() {
		$.getJSON("/getMessage", {
			tags : "marine",
			format : "json"
		}, function(data) {
			alert('');
		});
	});
});

function myFunction() {
	$.getJSON("/springws/getMessage", function(data) {
		alert(JSON.stringify(data));
		$('#mySubmitButton').html(data['textMessage']);	
		
	});
}
