$('li > a').click(function() {
    $('li').removeClass();
    $(this).parent().addClass('active');
});

$('.login').click(function(){
	$('#signup-li').removeClass();
	$('#login-li').addClass('active');
	$('#modal-login').css('display', 'block');
	$('#modal-signup').css('display','none');
});

$('.signup').click(function(){
	$('#login-li').removeClass();
	$('#signup-li').addClass('active');
	$('#modal-login').css("display","none");
	$('#modal-signup').css("display","block");
});
