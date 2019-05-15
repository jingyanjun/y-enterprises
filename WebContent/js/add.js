/*created by Yanjun
*/
$(function(){
	$("#saveBtnId").on('click',function(event){
		event.preventDefault();			
		var title = $("#titleId").val();
		var shortDescription = $("#shortDescriptionId").val();
		var msg = "";

        if (title.trim().length == 0) {
            msg += "Title field is required! ";
        }
        if (shortDescription.trim().length == 0) {
            msg += "Short Description is required! ";
        }
       
        if (msg.trim().length != 0) {
        	alert(msg);
            return;
        }
		
		$("#newPostingFormId").submit();
	});
		
	$("#cancelBtnId").on('click',function(event){
		event.preventDefault();
		window.location = 'main';
	});	
	
});