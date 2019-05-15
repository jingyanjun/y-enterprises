/*created by Yanjun*/

$(function(){
	
	$('.confirmation').on('click', function () {
		
        return confirm('Delete this entry?');
    });	
		
	$("#loginLnkId").on('click',function(event){
		event.preventDefault();
		window.location = 'login';
	});
	
	$("#logoutLnkId").on('click',function(event){
		event.preventDefault();
		document.cookie = "userId=; expires= Thu, 01 Jan 1970 00:00:00 UTC" ;
		window.location = 'main';
	});
});