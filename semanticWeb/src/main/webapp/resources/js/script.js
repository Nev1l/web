/*$('#collapsePerformers').on('shown', function(){
	$('#performers').addClass('active');
	$("#collapsePerformers").slideUp();
	$("#collapseAlbums").slideDown();
	$("#collapseTracks").slideDown();
});

$('#collapseAlbums').on('shown', function(){
	$('#albums').removeClass('active');
	$("#collapsePerformers").slideDown();
	$("#collapseAlbums").slideUp();
	$("#collapseTracks").slideDown();
});

$('#collapseTracks').on('shown', function(){
	$('#tracks').addClass('active');
	$("#collapsePerformers").slideDown();
	$("#collapseAlbums").slideDown();
	$("#collapseTracks").slideUp();
});*/
var activateTab = function(n) {
	disableAll();
	if (n === 1) {
		$('#performers').addClass('active');
		document.getElementById('collapsePerformers').style.display = "block";
		//$('#collapsePerformers').show();//slideUp();
	} else if (n === 2) {
		$('#albums').addClass('active');
		document.getElementById('collapseAlbums').style.display = "block";
		//$('#collapseAlbums').slideUp();
	} else {
		$('#tracks').addClass('active');
		document.getElementById('collapseTracks').style.display = "block";
		//$('#collapseTracks').slideUp();
	}
}

var disableAll = function() {
	if ($('#tracks').hasClass('active')) {
		$('#tracks').removeClass('active');
		document.getElementById('collapseTracks').style.display = "none";
		//$('#collapseTracks').slideDown();
	}
	if ($('#albums').hasClass('active')) {
		$('#albums').removeClass('active');
		document.getElementById('collapseAlbums').style.display = "none";
		//$('#collapseAlbums').slideDown();
	}
	if ($('#performers').hasClass('active')) {
		$('#performers').removeClass('active');
		document.getElementById('collapsePerformers').style.display = "none";
		//$('#collapsePerformers').slideDown();
	}
}
