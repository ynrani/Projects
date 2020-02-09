<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<title>Manual Dictionary</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/theme.default.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
    
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
  <script>
  $(document).ready(function() {
	  $('#errors').html('');
		$('#errors').hide();
		$('#success').html('');
		$('#success').hide();
		if("${tdgDictionaryDTO.errors}" == null || typeof '${tdgDictionaryDTO.errors}' == '' ||  '${tdgDictionaryDTO.errors}' == ''){
			$('#errors').html('');
			$('#errors').hide();
			
		}else{
			 if('${tdgDictionaryDTO.getErrors().size()}' > 0){
				var iLength = '${tdgDictionaryDTO.getErrors().size()}';
				errorInfo = "";
		    	  for(i =0 ; i < iLength ; i++){
		    		  errorInfo += " \n "+"${tdgDictionaryDTO.getErrors().get(i)}";
		    	  }
			$('#errors').html('${tdgDictionaryDTO.errors}');
			$('#errors').show(); 
		}			 
		}
		
		if("${tdgDictionaryDTO.messageConstant}" == 'SUCCESS'){	
			$(".modal").hide();
				alert('${tdgDictionaryDTO.message}');
				window.close(this);
		 }else if("${tdgDictionaryDTO.messageConstant}" == 'FAILED'){
			 $(".modal").hide();
			 alert('${tdgDictionaryDTO.message}');
		 }
		
		
		$("#selectedTabsMulti").change(function() {
		    // get reference to display textarea
		    /* var display = document.getElementById('selectedTabs');
		    display.innerHTML = ''; // reset
		    
		    // callback fn handles selected options
		    getSelectedOptions(this, callback);
		    
		    // remove ', ' at end of string
		    var str = display.innerHTML.slice(0, -2);
		    display.innerHTML = str; */
		    $('option:selected', $(this)).each(function() {
		    	var str = $('#selectedTabs').val();
		    	if(str != ''){
		    		str += ","+$(this).val();
		    	}else
		    		str += $(this).val();
		    	//var str = display.innerHTML.slice(0, -2);
		        $('#selectedTabs').val(str);
		      });
		});
		
	})
	function showAjaxLoad(){
	  $(".modal").show();
  }
  
  function getSelectedOptions(sel, fn) {
	    var opts = [], opt;
	    
	    // loop through options in select list
	    for (var i=0, len=sel.options.length; i<len; i++) {
	        opt = sel.options[i];
	        
	        // check if selected
	        if ( opt.selected ) {
	            // add to array of option elements to return from this function
	            opts.push(opt);
	            
	            // invoke optional callback function if provided
	            if (fn) {
	                fn(opt);
	            }
	        }
	    }
	    
	    // return array containing references to selected option elements
	    return opts;
	}
  
  function callback(opt) {
	    // display in textarea for this example
	    var display = document.getElementById('selectedTabs');
	    display.innerHTML += opt.value + ', ';

	    // can access properties of opt, such as...
	    //alert( opt.value )
	    //alert( opt.text )
	    //alert( opt.form )
	}
  </script>
</head>
<body>
     <form:form action="${pageContext.request.contextPath}/addPassedTabs" modelAttribute="tdgMasterDictionaryDTO">
          <div align="center" style="text-align: center; padding: 5%; width: 80%;">
            <table style="text-align: center; padding: 1%; width: 80%;">               
                <tr>
                    <td>
                        <table style="width: 80%" >
                        <thead>
                        	<tr>
							<th>Tables</th> <th>Selected Tables</th>
						</tr>
						</thead>
                            <tr>
                                <td align="center">
                               		<select id="selectedTabsMulti"  class="form-control-half-dictionary" multiple="multiple" size="15">
		<c:forEach items="${tdgMasterDictionaryDTO.listTables}" var="dbConnectionsDTOs" varStatus="status">
		<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
		</c:forEach>	
		</select>
                                </td>
                                <td align="center">
                                <form:textarea id="selectedTabs" path="selectedTabs" cols="40" rows="15" />
                                </td>
                            </tr>
                            <tr>
                                <td align="center" colspan="2">
                                <button class="btn-primary btn-cell" value="Submit">Submit</button>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
          </div>  
      </form:form>
      <jsp:include page="ajaxfooter.jsp"></jsp:include>
 </body>
</html>