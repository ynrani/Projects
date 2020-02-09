<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>TDG Central | Data Generation</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="js/html5.js"></script>
<link href="css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script>
	function clickEvent(val) {
		var vals = "";
		console.log($("#" + val));
		$("input[type='text']").each(
				function() {
					if ($("#" + val).find(this).val() != undefined) {
						vals += $("#" + val).find(this).val() + ":"
								+ $("#" + val).find(this).attr("name") + "*";
					}
				});

		$("input[type='hidden']").each(
				function() {
					if ($("#" + val).find(this).val() != undefined && $("#" + val).find(this).val().indexOf("=") < 0) {
						vals += $("#" + val).find(this).val() + ":"
								+ $("#" + val).find(this).attr("name") + "*";
					}
				});
		
		$("input[type='radio']").each(
				function() {
					if ($("#" + val)
							.find("input[type='radio']:checked").val() != undefined) {
						vals += $("#" + val)
								.find("input[type='radio']:checked").val()
								+ ":"
								+ $("#" + val).find(this).attr("name")
								+ "*";
					}
				});

		$(":checkbox:checked").each(
				function() {
					
					if ($("#" + val).find("input[type='checkbox']:checked").val() != undefined ) {
						vals += $("#" + val).find("input[type='checkbox']:checked").val() + ":"
								+ $("#" + val).find(this).attr("name") + "*";
					}
				});

		$(":selected")
				.each(
						function() {
							if ($("#" + val).find(this)
									.val() != undefined && $("#" + val).find(this)
									.val() != "-1") {
								vals += $("#" + val).find(this).val()
										+ ":"
										+ $("#" + val).find(this).attr(
												"title") + "*";
							}
						});

		$("input[type='textarea']").each(
				function() {
					if ($("#" + val).find(this).attr("name") != undefined) {
						vals += $("#" + val).find(this).val() + ":"
								+ $("#" + val).find(this).attr("name") + "*";
					}
				});

		if (vals != '') {
			$.ajax({
				type : "POST",
				url : './tdgOperationsDetails',
				data : {
					reqVals : vals
				},
				success : function(responseText) {
					if ( responseText.indexOf("#") > -1 ) {
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
	}
	
	
	$(function() {
		$("#tabs").tabs();
		var pickerOpts = {
				dateFormat:"d/M/yy"
			};	
		$("#datepicker").datepicker(pickerOpts);
	});
</script>

</head>
<body>
	<div id="main" class="wrapper">
		<!-- <script src="include/indexHeader.js"></script> -->
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div id="tabs-1" class="container">
			<ol class="breadcrumb">
				<li><a href="./index">Home</a></li>
				<li>&#x2f;</li>
				<li><a href="./index">TDG Life Cycle</a></li>
				<li>&#x2f;</li>
				<li class="active">Test Data Generation</li>
			</ol>

			<form:form name="tdgOperations"
				action="${pageContext.request.contextPath}/tdgOperationsDetails"
				modelAttribute="dynamicPageContent">
				<div id="errors" class="errorblock" style="display: none"></div>
				<div id="success" class="successblock" style="display: none"></div>
				<div id="tabs">
					<ul>
						<c:forEach items="${requestList}" var="dContent"
							varStatus="myIndex">
							<li><a href="#tabs-${myIndex.index+2}">Schema-<c:out
										value="${myIndex.index+1}" /></a></li>
						</c:forEach>
					</ul>
					<c:choose>
						<c:when test="${requestList ne null && !requestList.isEmpty()}">
							<c:forEach items="${requestList}" var="dContent"
								varStatus="myIndex">
								<div id="tabs-${myIndex.index+2}">
									<input type="hidden" name="tabs-${myIndex.index+2}SCHEMA_ID"
										value="${dContent.schemaId}" /> <input type="hidden"
										name="tabs-${myIndex.index+2}DEPENDS_ON"
										value="${dContent.dependevalues}" /> <br />
									<table style="width: 100%; border: 0; font-size: 13px;"
										cellpadding="4">
										<tr>
											<td class="lable-title" width="35%" align="left"
												valign="middle">Dictionary Name :</td>
											<td class="flied-title" width="20%" align="left"
												valign="middle">${dContent.schemaname}</td>
										</tr>
										<tr>
											<td class="lable-title" width="35%" align="left"
												valign="middle">Database Type :</td>
											<td class="flied-title" width="20%" align="left"
												valign="middle">${dContent.dbType}</td>
										</tr>
										<c:forEach items="${dContent.listDynamicPojo}" var="dValues">


											<c:if test="${dValues.columnType eq 'TEXTBOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><input type="text"
														name="${dValues.columnName}" value="" /><c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>


											<c:if test="${dValues.columnType eq 'TEXTAREABOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><textarea
															name='${dValues.columnName}'></textarea>
															<c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>


											<c:if test="${dValues.columnType eq 'RADIOBOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><c:forEach
															items="${dValues.mapValues}" var="entry">
															<input type='radio' name='${dValues.columnLabel}'
																value="${entry.key}">${entry.value}
						</c:forEach><c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>
											<c:if test="${dValues.columnType eq 'CHECKBOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><c:forEach
															items="${dValues.mapValues}" var="entry">
															<input type='checkbox' name='${dValues.columnName}'
																value="${entry.key}">${entry.value}
						</c:forEach><c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>
											<c:if test="${dValues.columnType eq 'SELECTBOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><select name='${dValues.columnName}'
														onchange="doAjaxCall('tabs-${myIndex.index+2}','${dValues.columnName}')">
															<option value='-1'>--Select--</option>
															<c:forEach items="${dValues.mapValues}" var="entry">
																<option title="${dValues.columnName}"
																	value='${entry.key}'>${entry.value}</option>
															</c:forEach>

													</select><c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>
											<c:if test="${dValues.columnType eq 'DATEBOX'}">
												<tr>
													<td class="lable-title" width="35%" align="left"
														valign="middle">${dValues.columnLabel}</td>
													<td class="flied-title" width="20%" align="left"
														valign="middle"><input type="text"
														name='${dValues.columnName}' id="datepicker" />
														<c:if
															test="${dValues.manualDictionaryContains == 'true'}">

															<input type='checkbox'
																name='${dValues.columnName}MANUAL_DICTIONARY' value="Y">
														</c:if></td>
												</tr>
											</c:if>
										</c:forEach>
										<tr>
											<td class="lable-title" width="35%" align="left"
												valign="middle">Generate Count</td>
											<td class="flied-title" width="20%" align="left"
												valign="middle"><input type="text"
												name="${myIndex.index+1}GenerateCount" /></td>
										</tr>
									</table>

									<table style="width: 100%; border: 0; font-size: 13px;"
										cellpadding="4">
										<tbody>
											<tr>
												<td colspan="4" align="center" valign="middle"><input
													type="button" name="${myIndex.index+2}"
													id="${myIndex.index+2}" class="btn-primary btn-cell"
													value="Submit"
													onclick="clickEvent('tabs-${myIndex.index+2}')" /> <input
													type="reset" name='Reset${myIndex.index+2}'
													id='Reset${myIndex.index+2}' value="Reset"
													class="btn-primary btn-cell"
													onclick="$('#errors').html('');$('#errors').hide();" /></td>
											</tr>
										</tbody>
									</table>

								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<hr>
							<center>
								<font size="+2" color="red"> No dictionary details are
									exist.Kindly contact Administrator. </font>
							</center>
							<hr>

						</c:otherwise>
					</c:choose>
				</div>
			</form:form>
		</div>
	</div>
	<script>
		menu_highlight('tdg_life_cycle1');
		
		function doAjaxCall(tabid,componentChanged){
			var selectedval = $('select[name="' + componentChanged + '"]')
					.val();
			var dependsVal = $("input[name=" + tabid + "DEPENDS_ON]").val();
			var vSchemaId= $("input[name=" + tabid + "SCHEMA_ID]").val();
			var valsDependent = '';
			var compentName;
			
			
			if (dependsVal.indexOf(",") >= 0) {
				
				var dependsArray = dependsVal.split(',');
				for (var ii = 0; ii < dependsArray.length; ii++) {
					
					var dependsArrays = dependsArray[ii].split(',');
					if(ii==0 && dependsArrays[1] == componentChanged){
						valsDependent += "SCHEMA_ID:"+vSchemaId+"*";
						valsDependent += "DEPENDS_ON:"+selectedval;
						valsDependent += ":COMPONENT_NAME:"+componentChanged;
					}
					if (dependsArrays.indexOf("=") > 0) {						
						compentName[ii] = dependsArrays[0];
					}
				}
			} else {
				if (dependsVal.indexOf("=") > 0) {					
					var dependsArrays = dependsVal.split('=');
					if(dependsArrays[1] == componentChanged){
						valsDependent += "SCHEMA_ID:"+vSchemaId+"*";
						valsDependent += "DEPENDS_ON:"+selectedval;
						valsDependent += ":COMPONENT_NAME:"+componentChanged;
					}
					compentName = [dependsArrays[0]];
				}
			}
			
			if (valsDependent != '' && selectedval != '-1') {
				$.ajax({
					type : "GET",
					url : './tdgDependentDetails',
					data : {
						reqvalsDependent : valsDependent
					},
					success : function(responseText) {
						var columnSplits;
						if(responseText.indexOf("*") > 0){
							columnSplits=  responseText.split("*");
						}else{
							columnSplits=[responseText];
						}
						for(var k=0;k<columnSplits.length;k++){
						if(columnSplits[k].indexOf("#") > 0){
							var finalselectBoxvals = columnSplits[k].split("#");
							
							for (var j = 0; j < compentName.length; j++) {
								if(compentName[j] == finalselectBoxvals[0]){
								if(finalselectBoxvals[1].indexOf(",") > 0){
									var arrays = finalselectBoxvals[1].split(",");
									for(var key in arrays){
										var rdField = arrays[key].split(":");
										$('select[name="' + compentName[j] + '"]').append("<option title='"+compentName[j]+"' value='" + rdField[0] + "'> " + rdField[1] + "</option>");
									}
								}else{
									if(finalselectBoxvals[1].indexOf(":") > 0){
										var rdField = finalselectBoxvals[1].split(":");
										$('select[name="' + compentName[j] + '"]').append("<option title='"+compentName[j]+"' value='" + rdField[0] + "'> " + rdField[1] + "</option>");
									}
								}
							}
							}
						}
						}
						
						
					}
				});
			}
		}
	</script>

</body>
</html>