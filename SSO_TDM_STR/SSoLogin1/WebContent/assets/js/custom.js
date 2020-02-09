 

(function ($) {
    "use strict";
    var mainApp = {

        metisMenu: function () {

            /*====================================
            METIS MENU 
            ======================================*/

            $('#main-menu').metisMenu();

        },


        loadMenu: function () {

            /*====================================
            LOAD APPROPRIATE MENU BAR
         ======================================*/

            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
        },
        slide_show: function () {

            /*====================================
           SLIDESHOW SCRIPTS
        ======================================*/

            $('#carousel-example').carousel({
                interval: 3000 // THIS TIME IS IN MILLI SECONDS
            })
        },
        reviews_fun: function () {
            /*====================================
         REWIEW SLIDE SCRIPTS
      ======================================*/
            $('#reviews').carousel({
                interval: 2000 //TIME IN MILLI SECONDS
            })
        },
        
       
        
    };
    $(document).ready(function () {
        mainApp.metisMenu();
        mainApp.loadMenu();
        mainApp.slide_show();
        mainApp.reviews_fun();
       
    });
}(jQuery));


//Left Nav Highlight function
function menu_highlight(targetItem){
	var targetItemObj= $('#'+targetItem);
	targetItemObj.addClass('active-menu');
}
function menu_highlight_top(targetItem){
	var targetItemObj= $('#'+targetItem);
	targetItemObj.addClass('active-menu-top');
}

function collapse_in(targetItem){
	var targetItemObj= $('#'+targetItem);
	targetItemObj.addClass('collapse in');
}



function createUser(){
	var formObj = $("#remsUserDtlDTOForm");
	var buttonObj = formObj.find('.btn-info');
	 

	//form validation rules
	formObj.validate({
		 errorClass: "has-error",
	     validClass: "has-success",
	     rules: {
	   	
	    	 userId:{
				required:true
			} 
	     },
	     
	     submitHandler: function(form) {
	    	  
	    	 form.submit();
	     },
	});
}

