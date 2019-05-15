/*created by Yanjun*/

$(function(){
	$("#deleteBtnId").on('click',function(event){
		event.preventDefault();
		    var r = confirm("Delete this entry?");
	        if (r == true) {
	            window.location = "delete";
	        } 
	        else{	        	
	        }	   
	});	
	
});