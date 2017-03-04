function QueryString(item){ 
	var sValue=location.search.match(new RegExp("[\?\&]"+item+"=([^\&]*)(\&?)","i"));
	return sValue?sValue[1]:sValue; 
}

//$(document).ready(
//	function() { 
//		// 搜索分页功能监听
//		var $page_class = $("input[name='toNumPage']");
//		$page_class.keydown(
//			function(event){
//				// enter button
//				if(event.keyCode == 13){
//					toPage();
//				}
//			}
//		); 
//	});
$(function(){			
//	$(".searchtext").focus(
//		function(){	
//			this.select();
//			var valuemm=$(this).val();
//			if(valuemm==this.defaultValue){
//				$(this).css("color","#333");
//				$(this).val("");
//			}
//	});
//	
//	$(".searchtext").blur(
//		function(){
//			var valuemm=$(this).val();
//			if(valuemm==""){
//				$(this).css("color","#aaa");
//				$(this).val(this.defaultValue);
//				
//				}			
//     });
	
	
	var pageCache = {
		custometId:null,
		productInventory:0
	};
	if($('.to-top').length>0){
		$(window).scroll(function(){
			var scrTop=0;
			if(document.documentElement && document.documentElement.scrollTop){
				scrTop=document.documentElement.scrollTop;
			}else if(document.body){
				scrTop=document.body.scrollTop;
			}
			//console.log(scrTop);
			if(scrTop>=150){
				$('.to-top').fadeIn(300);
			}else{
				$('.to-top').fadeOut(200);
			}
		});
		$('.to-top').click(function(){
			$('html,body').animate({ scrollTop: "0"}, 200);
			return false;
		});
	}
	
});




	