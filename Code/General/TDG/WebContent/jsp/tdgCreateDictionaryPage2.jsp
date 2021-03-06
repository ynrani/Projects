<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
  <title>TDG | Create Master Dictionary</title>
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
<style type="text/css">
table{
        width: 100%;
        margin-bottom: 20px;
		border-collapse: collapse;
    }
   /*  table, th, td{
        border: 1px solid #cdcdcd;
    } */
    table th, table td{
        padding: 10px;
        text-align: left;
    }
</style>
<script type="text/javascript" src="js/script.js"></script>
  <script>
  $(document).ready(function() {
	  var str = "${tdgMasterDictionaryDTO.conSelected}";
	  //alert("${tdgMasterDictionaryDTO.conSelected}");
	  str = str.replace("TDGCON","#");
	  $('#connectId').text(str);
	  $('#errors').html('');
		$('#errors').hide();
		$('#success').html('');
		$('#success').hide();
		
	})
	function showAjaxLoad(){
	  $(".modal").show();
  }
  
  $(document).ready(function(){
	  if("${tdgMasterDictionaryDTO.editFlag} == true"){
		  var str = "${tdgMasterDictionaryDTO.editSchemeDetails}";
		  if(str != '')
		  if(str.toLowerCase().indexOf("$") >= 0){
			  var strArraymain = str.split('$');
			  for(var j=0;j<strArraymain.length;j++){
				  var strArray = strArraymain[j].split('#');
				  if(strArray.length != 4)
					  strArray[3]='';
				  var markup = "<tr><td style='border: 1px solid #cdcdcd;' align='left'><input type='checkbox' name='record' title='"+strArray[0]+"'></td><td style='border: 1px solid #cdcdcd;' align='left'>" + strArray[0] + "</td><td style='border: 1px solid #cdcdcd;' align='left'>" + strArray[1] + "</td><td style='border: 1px solid #cdcdcd;' align='left'>"+strArray[2]+"</td><td style='border: 1px solid #cdcdcd;' align='left' title='"+strArray[0]+"#"+strArray[1]+"#"+strArray[2]+"#"+strArray[3]+"'>"+strArray[3]+"</td></tr>";
		          //$("table tbody").append(markup);
		          $('#fileTable > tbody:last-child').append(markup);
		          $("#columnName option[value='"+name+"']").remove();  
			  }		 
		  }else{
			  var strArray = str.split('#');
			  if(strArray.length != 4)
				  strArray[3]='';
			  var markup = "<tr><td style='border: 1px solid #cdcdcd;' align='left'><input type='checkbox' name='record' title='"+strArray[0]+"'></td><td style='border: 1px solid #cdcdcd;' align='left'>" + strArray[0] + "</td><td style='border: 1px solid #cdcdcd;' align='left'>" + strArray[1] + "</td><td style='border: 1px solid #cdcdcd;' align='left'>"+strArray[2]+"</td><td style='border: 1px solid #cdcdcd;' align='left' title='"+strArray[0]+"#"+strArray[1]+"#"+strArray[2]+"#"+strArray[3]+"'>"+strArray[3]+"</td></tr>";
	          //$("table tbody").append(markup);
	          $('#fileTable > tbody:last-child').append(markup);
	          $("#columnName option[value='"+name+"']").remove();
		  }
	  }
      $("#add-row").click(function(){
          var name = $("#columnName").val();
          var displaylabel = $("#displayLabel").val();
          if(displaylabel == '' || displaylabel == null){
        	  alert("Enter Display Label for master dictionary");
        	  $("#displayLabel").focus();
        	  return;
          }
          var type = $("#type").val();
          var values = $("#values").val();
          if(type == 'SELECTBOX' || type == 'CHECKBOX' || type == 'MULTISELECTBOX' || type == 'RADIOBOX'){
        	  if(values == null || values == ''){
        		  alert("Enter possible values for "+type);
        		  $("#values").focus();
            	  return;
        	  }
          }
          var markup = "<tr><td style='border: 1px solid #cdcdcd;' align='left'><input type='checkbox' name='record' title='"+name+"'></td><td style='border: 1px solid #cdcdcd;' align='left'>" + name + "</td><td style='border: 1px solid #cdcdcd;' align='left'>" + displaylabel + "</td><td style='border: 1px solid #cdcdcd;' align='left'>"+type+"</td><td style='border: 1px solid #cdcdcd;' align='left' title='"+name+"#"+displaylabel+"#"+type+"#"+values+"'>"+values+"</td></tr>";
          //$("table tbody").append(markup);
          $('#fileTable > tbody:last-child').append(markup);
          $("#columnName option[value='"+name+"']").remove();
          $("#displayLabel").val('');
          $("#values").val('');
      });
      
      // Find and remove selected table rows
      $("#deleterow").click(function(){
    	  var x =0;
          $("table tbody").find('input[name="record"]').each(function(){
          	if($(this).is(":checked")){
          		$("#columnName").append("<option value='"+$(this).attr("title")+"'>"+$(this).attr("title")+"</option>");
                  $(this).parents("tr").remove();
                  x++;
              }
          });
          if(x ==0){
        	  alert("Please select atleast one record to delete");
        	  return;
          }
      });
      
      $("#createDictionary").click(function(){
    	  var count =0;
    	  var finalParam ='';
    	  $("#fileTable tbody tr").each(function() {
    		  if(finalParam != '')
    			  finalParam += '$';
    		  finalParam += $(this).find("td:last-child").attr("title");
    		});
    	  if(finalParam == ''){
    		  alert("Atleast one column should be there for GUI creation");
    		  return;
    	  }
    	 /*  $.ajaxSetup({
  			global : false,
  			type : "GET",
  			url : './tdgaNextCreateMasterDictionary',
  			beforeSend : function() {
  				$(".modal").show();
  			},
  		//complete: function () {
  		//$(".modal").hide();
  		//}
  		}); */
  	  $.ajaxSetup({
  		global : false,
  		type : "GET",
  		url : './tdgaFinalCreateMasterDictionary',
  		beforeSend : function() {
  			$(".modal").show();
  		},
  	//complete: function () {
  	//$(".modal").hide();
  	//}
  	});
  		$.ajax({
  			data : {
  				reqVals : finalParam
  			},
  			success : function(responseText) {
  				//alert(responseText);
  				if (responseText.toLowerCase().indexOf("created") >= 0){
  					alert(responseText);
  					document.location.href="./tdgMasterDictionaryDashboard";
  				}else{
  					alert(responseText);
  				}
  		}
    });
      });
  });  
  function clickEvent() {
	  $.ajaxSetup({
			global : false,
			type : "POST",
			url : './tdgaNextCreateMasterDictionary',
			beforeSend : function() {
				$(".modal").show();
			},
		//complete: function () {
		//$(".modal").hide();
		//}
		});
		$.ajax({
			data : {
				reqVals : ''
			},
			success : function(responseText) {
		}
  });
  }
  
  function finalCreation() {
  $.ajaxSetup({
		global : false,
		type : "GET",
		url : './tdgaFinalCreateMasterDictionary',
		beforeSend : function() {
			$(".modal").show();
		},
	//complete: function () {
	//$(".modal").hide();
	//}
	});
	$.ajax({
		data : {
			reqVals : vals
		},
		success : function(responseText) {
			$(".modal").hide();
			if("${tdgMasterDictionaryDTO.messageConstant}" == 'SUCCESS'){
				 $('#success').html('${uploadDTO.message}');
					$('#success').show(); 
			 }else if("${uploadDTO.messageConstant}" == 'FAILED'){
				 $('#success').html('');
					$('#success').hide();
					$('#errors').html('${uploadDTO.message}');
					$('#errors').show();
			 }
			if (responseText.indexOf("#") > -1) {
				var res = responseText.split("#");
				$('#errors').html(res[1]);
				$('#errors').show();
			} else {
				$('#errors').html('');
				$('#errors').hide();
				alert(responseText);
				var resetButton = val.split('-');
				$("#Reset" + resetButton[1]).click();
			}
		}
	});
	}
  
  </script>

</head>
<body>
   <div id="main" class="wrapper">
    <!-- <script src="include/indexHeader.js"></script> -->
    <jsp:include page="indexHeader.jsp"></jsp:include>
      <div id="tabs-1" class="container">
		<form:form name="flatfilesupload" action="${pageContext.request.contextPath}/tdgaNextCreateMasterDictionary" modelAttribute="tdgMasterDictionaryDTO" >
		<div id="errors" class="errorblock" style = "display:none" ></div>
		<div id="success" class="successblock" style = "display:none" ></div>
		
		<!-- <div class="input_fields_wrap">
		<div><input type="file" name="files[0]" /></div> -->
		<table style="width:100%; border:0; font-size: 13px; cellpadding:2;">
              <tbody>
               <tr style="border-bottom: 1px solid black;">
               <td style="font-size: 15px"> <b> Connections selected : </b></td>
               <form:input type="hidden" path="conSelected"/>
               <td><b><label id="connectId"></label>   </b></td>
               </tr>
               <tr>
               <td>Required Tables</td>
               <%-- <td><form:input path="passedTabs" class="form-control-mst" ondblclick="popup('./tdgaNextCreateMasterDictionary?passedTabs=true','Un-Reserve Request','popup','popupOverlay','550');"/></td> --%>
               <td><form:input path="passedTabs" class="form-control-mst" ondblclick="popup('./addPassedTabs?selectedTabs=TDG_PASSED_TABS','Add Required Tables','popup','popupOverlay','700');" readonly="true"/>&nbsp;
               <button class="btn-primary btn-cell" onclick="clickEvent()">Fetch Columns</button></td>
               </tr>
               <tr>
               <td>Master Tables</td>
               <td><form:input path="masterTabs" class="form-control-mst" ondblclick="popup('./addPassedTabs?selectedTabs=TDG_MASTER_TABS','Add Required Tables','popup','popupOverlay','700');" readonly="true"/></td>
               </tr>
               <tr>
               <td>Required Columns</td>
               <td><form:input path="requiredCols" class="form-control-mst" ondblclick="popup('./addPassedTabs?selectedTabs=TDG_REQUIRED_COLS','Add Required Tables','popup','popupOverlay','700');" readonly="true"/></td>
               </tr>
               <tr>
               <td>DB sequences</td>
               <td><form:input path="sequencePrefixTabs" class="form-control-mst" onclick=""  /></td>
               </tr>
               <tr>
               <td>Business Rules</td>
               <td><form:input path="businessRules" class="form-control-mst" onclick=""/></td>
               </tr>
               <tr>
               <td>Dependent DB Columns</td>
               <td><form:input path="dependentDbs" class="form-control-mst" onclick=""/></td>
               </tr>
               <tr>
               <td>DB Date Formats</td>
               <td><form:input path="dateFormates" class="form-control-mst" onclick=""/></td>
               </tr>
               </tbody>
               </table>
		<%-- Connection selected &nbsp; <label>${tdgMasterDictionaryDTO.conSelected}</label>
		<div style="display: block;width=90%;">
		Required Tables <form:input path="passedTabs" class="form-control" onclick=""/>&nbsp;
		Master Tables: <form:input path="masterTabs" class="form-control" onclick=""/>
		</div>
		<div style="display: block;width=90%;">
		Required Columns :<form:input path="requiredCols" class="form-control"/>&nbsp;
		DB sequences : <form:input path="sequencePrefixTabs" class="form-control"/>
		</div>
		<div style="display: block;width=90%;">
		Business Rules :<form:input path="businessRules" class="form-control"/>&nbsp;
		Dependent DB Columns : <form:input path="dependentDbs" class="form-control"/>
		</div> --%>
		<%-- <div style="display: block;width=90%;">
		DB Date Formats : <form:input path="dateFormates" class="form-control"/>
		</div> --%>
		 <c:if test="${tdgMasterDictionaryDTO.listColumns ne null &&  not empty tdgMasterDictionaryDTO.listColumns}">
		<div id="basedOnSelect" >
		<div style="display: block;width=90%;">Coumn Name<!-- <input type="text" id="columnName" required="required" placeholder="Column Name" class="form-control-half-dictionary"> --> 
		<select id="columnName" class="form-control-half-dictionary">
		<c:forEach items="${tdgMasterDictionaryDTO.listColumns}" var="columnName1" varStatus="status">
		<option value="${columnName1}">${columnName1}</option>
		</c:forEach>	
		</select>&nbsp;
		Display Label <input type="text" id="displayLabel" placeholder="Display Label"  class="form-control-half-dictionary"> &nbsp;
		Type <select id="type" class="form-control-half-dictionary">		  
		  <option value="CHECKBOX">CHECKBOX</option>		  
		  <option value="DATEBOX">DATEBOX</option>		  
		  <option value="MULTISELECTBOX">MULTISELECTBOX</option>
		  <option value="RADIOBOX">RADIOBOX</option>
		  <option value="SELECTBOX">SELECTBOX</option>
		  <option value="TEXTBOX" selected="selected">TEXTBOX</option>
		</select> &nbsp;
		Values <input type="text" id="values" placeholder="Display Values" class="form-control-half-dictionary" > &nbsp;
		<input type="button" class="btn-primary btn-cell" id="add-row" value="Add Row">
		</div>
		<!-- 
        <input type="text" id="email" placeholder="Email Address" class="form-control">
    	<input type="button" class="btn-primary btn-cell" id="add-row" value="Add Row"> -->
			<table id="fileTable" style="width:100%;font-size: 13px;border:0; cellpadding:0; cellspacing:1">
        <thead>
            <tr>
                <th style="border: 1px solid #cdcdcd;" bgcolor="#E3EFFB" scope="col" class="whitefont" align="center">Select</th>
                <th style="border: 1px solid #cdcdcd;" bgcolor="#E3EFFB" scope="col" class="whitefont" align="center">Column Name</th>
                <th style="border: 1px solid #cdcdcd;" bgcolor="#E3EFFB" scope="col" class="whitefont" align="center">Display Lable(GUI)</th>
                <th style="border: 1px solid #cdcdcd;" bgcolor="#E3EFFB" scope="col" class="whitefont" align="center">Type(GUI)</th>
                <th style="border: 1px solid #cdcdcd;" bgcolor="#E3EFFB" scope="col" class="whitefont" align="center">Display Values(GUI)</th>
            </tr>
        </thead>
        <tbody>
          <!--   <tr>
                <td align="left"><input type="checkbox" name="record"></td>
                <td align="left">Peter Parker</td>
                <td align="left">peterparker@mail.com</td>
            </tr>
 -->        </tbody>
    </table>
    <input type="button" id="deleterow" class="btn-primary btn-cell" value="Delete Row"/> &nbsp;
    <input type="button" id="createDictionary" class="btn-primary btn-cell" value="Create/Update Dictionary" />
    </div>
    </c:if>
		</form:form>
	  </div>
	  <script src="include/footer.js"></script>
  </div>
  <script>
 	menu_highlight('tdm_command_center1');
  </script>  
</body>
</html>