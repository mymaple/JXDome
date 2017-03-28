//header 菜单
$(function () {
    var banner = new Swiper('.banner',{
        autoplay: 5000,
        pagination : '.swiper-pagination',
        paginationClickable: true,
        lazyLoading : true,
        loop:true
    });

     $('a.slide-menu').on('click', function(e){
		var wh = $('div.wrapper').height();
		$('div.slide-mask').css('height', wh).show();
		$('aside.slide-wrapper').css('height', wh).addClass('moved');
	});
	
	$('div.slide-mask').on('click', function(){
		$('div.slide-mask').hide();
		$('aside.slide-wrapper').removeClass('moved');
	});
    

});
