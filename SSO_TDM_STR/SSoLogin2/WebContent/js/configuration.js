function saveButtonDisable(id) {
	var value = $('#' + id).val();
	if (id == "projName1") {
		if (value == "") {

			$('#saveBtn1').attr('disabled', true);

		} else {

			$('#saveBtn1').attr('disabled', false);

		}
	} else {
		if (value == "") {

			$('#saveBtn2').attr('disabled', true);

		} else {
			$('#saveBtn2').attr('disabled', false);

		}

	}
}

$('#enviorment').on('change', function() {
	if ($(this).val() == '') {
		$('#chkAvil').hide();

	}

	if ($(this).val() != '') {
		$('#chkAvil').show();
	}
})

$(document).ready(function() {
	$("#jobProjectErrorDiv").hide();

	$("#errorLable1").hide();
	$("#errorLable2").hide();
	$("#errorLable3").hide();
	if ($(this).val() == '') {
		$('#chkAvil').hide();

	}

	$('#bddSct').multiselect({
		nonSelectedText : '--Select Scripts--'
	});
	$('#apiTest').multiselect({
		nonSelectedText : '--Select Scripts--'
	});
	$("#plusClick").click(function() {
		$("#modalInput").val("");
		$("#myModal").modal('show');
	});
	$("#plusClick2").click(function() {
		$("#modalInput").val("");
		$("#myModal2").modal('show');
	});

	
	/*
	 * $('.testingToggle').click(function() { $("#funnctionsla").toggle();
	 * $("#funnctionslb").toggle(); $("#test2").toggle(); $("#test3").toggle();
	 * });
	 */
	 $("#repoUrl1").hide();
	$( "#source" ).change(function() {
	 	if ("" != $('#source').val()) {
			$("#repoUrl1").show();
		}else{
			 $("#repoUrl1").hide();
		}
	});
	 

});
function resetCLick() {
	$("#jobProjectErrorDiv").hide();
}




 

function checkDuplicateProjectJobName(projectName, jobName) {
	var status = false;
	var checkString = projectName + "!" + jobName;
	var saveData = $.ajax({
		type : "get",
		async : false,
		url : "projectJobCheck",
		data : checkString,
		dataType : "text",
		success : function(resultData) {
			console.log(resultData);
			status = resultData.trim();
		}
	});

	return status;
	/*
	 * $.ajax({ url : "porjectJobCheck?job=" + jobName, type : 'GET', dataType :
	 * 'json', success : function(data) { } });
	 */
}

function submitForm(buttonType) {

	// var pojectName1 = $("#projName1").val();

	var pojectName1 = $('#projName').find(":selected").text();
	var jobName = $('#jobName').find(":selected").text();
	if (pojectName1 == "" || jobName == "" || jobName == "- Select -"
			|| pojectName1 == "- Select -") {
		if (pojectName1 == "" || pojectName1 == "- Select -") {
			$("#errorLable1").css({
				"color" : "#FF0000"
			});

			$("#errorLable1").show();
		} else {
			$("#errorLable1").hide();
		}

		if (jobName == "" || jobName == "- Select -") {
			$("#errorLable2").show();
			$("#errorLable2").css({
				"color" : "#FF0000"
			});
		} else {
			$("#errorLable2").hide();
		}

		$("#jobProjectErrorDiv").show();

	} else {
		$("#jobProjectErrorDiv").hide();
		var status = checkDuplicateProjectJobName(pojectName1, jobName);
		if (status == "false") {

			var deviceCombo = "";
			var deviceComboOptions = document
					.getElementsByClassName("mobileDivClass");
			for (var counter = 0; counter < deviceComboOptions.length; counter++) {
				var deviceName = deviceComboOptions[counter]
						.getAttribute("data-devicename");
				var udid = deviceComboOptions[counter]
						.getAttribute("data-deviceudid");
				var thiscombo = deviceName + "," + udid + ";";
				deviceCombo = deviceCombo + thiscombo;
			}
			document.getElementById("combinedDeviceComboId").value = deviceCombo;

			var browOsCombo = "";
			var browserComboOptions = document
					.getElementsByClassName("browDivClass");
			for (var counter = 0; counter < browserComboOptions.length; counter++) {
				var browser = browserComboOptions[counter]
						.getAttribute("data-browser");
				var version = browserComboOptions[counter]
						.getAttribute("data-version");
				var os = browserComboOptions[counter].getAttribute("data-os");
				var combo = os + "," + browser + "," + version + ";";
				browOsCombo = browOsCombo + combo;
			}
			document.getElementById("combinedBrowserComboId").value = browOsCombo;

			if (buttonType == "save") {
				document.getElementById("toExecuteFlagId").value = "false";
			} else if (buttonType == "saveandexe") {
				document.getElementById("toExecuteFlagId").value = "true";
			} else if (buttonType == "unreserve") {
				document.getElementById("toExecuteFlagId").value = "unreserve";
			}

			localStorage.setItem("submitStatus", "submitDone");

			document.getElementById("itapConfigDTOForm").submit();
		} else {
			$("#errorLable1").hide();
			$("#errorLable2").hide();
			$("#errorLable3").show();
			$("#errorLable3").css({
				"color" : "#FF0000"
			});
			$("#jobProjectErrorDiv").show();

		}
	}
}

$(document).ready(
		function() {

			var submitStatus = localStorage.getItem("submitStatus");
			var saveStatus = "";
			if (submitStatus == "submitDone") {

				var saveData = $.ajax({
					type : "get",
					async : false,
					url : "checkStatus",
					dataType : "text",
					success : function(resultData) {
						console.log(resultData);
						saveStatus = resultData.trim();
						localStorage.setItem("submitStatus", "null");
						localStorage.setItem("saveStatus", saveStatus);
					}
				});
			} else {
				localStorage.setItem("saveStatus", "null");
			}
			// var saveStatus = $("#saveStatus")[0].textContent;
			saveStatus = saveStatus.trim();
			if (saveStatus == "null" || saveStatus == "") {
				/*
				 * $("#statusDiv").find("p").html = "amit";
				 * 
				 * $("#statusDiv").show();
				 */

			} else if (saveStatus == "success") {

				$("#statusLabelSuccess").css({
					"color" : "#00CC66"
				});
				$("#statusLabelFail").hide();
				$("#statusLabelSuccess").show();
				$("#statusDiv").show();

			} else {
				$("#statusLabelFail").css({
					"color" : "#FF0000"
				});
				$("#statusLabelSuccess").hide();
				$("#statusLabelFail").show();

				$("#statusDiv").show();
			}

			$('#projName').change(
					function() {

						var selValue = $('#projName').val();
						if (selValue != "" && selValue != "selected") {

							loadAjax();
							var selectedValue = $(this).val();
							var servletUrl = 'configurationAllJobs?projName='
									+ selectedValue;

							$.getJSON(servletUrl, function(options) {
								var provSpec = $('#jobName');
								var postBuild = $('#postBuildInvoke');
								$('>option', provSpec).remove(); // Clean old
								// options
								// first.
								$('>option', postBuild).remove();
								$("#ajaxloader").hide();
								if (options) {
									postBuild.append($('<option/>').text(
											"--Select--"));
									$.each(options, function(value, value) {
										provSpec.append($('<option/>').val(
												value).text(value));
										postBuild.append($('<option/>').val(
												value).text(value));
									});
								} else {
									provSpec.append($('<option/>').text(
											"Please select Project"));
									postBuild.append($('<option/>').text(
											"Please select Project"));
								}
							});
						} else {

						}
					});

		});

$("#saveBtn1").click(function() {
	options = $("#projName1").val();
	if (options)
		var provSpec = $('#projName');

	if (options) {
		provSpec.append($('<option/>').text(options));
		$("#projName option:last-child").attr('selected', 'selected');
		$('#jobName').empty()
	}

	$('#myModal').modal('hide');
	$("#statusDiv").hide();
	$("#jobProjectErrorDiv").hide();
});

$("#saveBtn2").click(function() {
	options = $("#jobName1").val();
	var existingOptions = [];
	$("#jobName option").each(function() {
		// Add $(this).val() to your list
		existingOptions.push($(this)[0].label);
	});
	// var existingOptions = $('#jobName').find("options");

	var provSpec = $('#jobName');
	if ($.isArray(existingOptions)) {
		// existingOptions.indexOf(searchElement[provSpec, fromIndex = 0]);
		if (jQuery.inArray(options, existingOptions) !== -1) {

		} else {
			if (options) {
				provSpec.append($('<option/>').text(options));
				$("#jobName option:last-child").attr('selected', 'selected');
			}
		}
	}

	$('#myModal2').modal('hide');
	$("#statusDiv").hide();
});

$(document).ready(function() {
	$("#jobName").change(function() {
		var jobName = $(this).val();
		$.ajax({
			url : "configurationEdit?serId=1&job=" + jobName,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				// SCM
				$('#source').val(data.source);
				$('#repoUrl').val(data.repoUrl);
				// CODE COVERAGE ANALYSIS
				$('#codeCvg').val(data.codeCvg);
				// UNIT TEST
				$('#testType').val(data.testType);
				// ENVIRONMENT SETUP
				$('#enviorment').val(data.enviorment);
				$('#sanity1').val(data.sanity);
				$('#docker1').val(data.docker);
				// BUILD & DEPLOY
				$('#pckage1').val(data.pckage);
				$('#deployment1').val(data.deployment);

				// TESTING BDD
				$('#bddSct').val(data.bddSct);
				// TESTING API TESTING
				$('#apiTest').val(data.apiTest);
				// TESTING CROSS BROWSER TESTING
				$('#testSuite').val(data.testSuite);
				$('#testCase').val(data.testCase);
				$('#gridType').val(data.gridType);
				$('#gridLabUrl').val(data.gridLabUrl);
				// TESTING FUNCTIONAL TESTING
				$('#toolName').val(data.toolName);
				$('#tstScrt').val(data.tstScrt);

				// edit flag
				$('#newOrEdit').val(data.newOrEdit);

			}
		});
	});
});

function changeTestCases() {
	var selectedTestSuite = $('#TestSuiteSelectId').val();
	var classname = "TestSuite_" + selectedTestSuite;
	var innerhtmlstr = "<option name='Select' id='Select' value='Select'>-Select-</option>";
	var testCases = document.getElementsByClassName(classname);
	for (var count = 0; count < testCases.length; count++) {
		var ele = testCases[count];
		var eleValue = ele.value;
		innerhtmlstr = innerhtmlstr + "<option value='" + eleValue + "'>"
				+ eleValue + "</option>";
	}
	document.getElementById("testCaseId").innerHTML = innerhtmlstr;
}

function selectUrl() {
	var selectGridTypeVal = $('#gridTypeId').val();

	/*
	 * var gridUrl = document.getElementById(selectGridTypeVal)
	 * .getAttribute("data-url"); document.getElementById("gridUrlId").value =
	 * gridUrl;
	 */

	var gridUrls = "<option value='Select'>Select</option>";
	$.ajax({
		url : "fetchUrlsForGridType?gridType=" + selectGridTypeVal,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			for (var counter = 0; counter < data.length; counter++) {
				gridUrls = gridUrls + "<option id='" + data[counter].gridUrl
						+ "' value='" + data[counter].gridUrl
						+ "' data-urlId='" + data[counter].gridId + "'>"
						+ data[counter].gridUrl + "</option>";
			}
			document.getElementById("gridUrlId").innerHTML = gridUrls;
		}
	});

	if (selectGridTypeVal == 'Pefecto') {
		document.getElementById("mobileSelectionDivId").style.display = "block";
		document.getElementById("mobileSelectedDivId").style.display = "block";

		document.getElementById("browserSelectionDivId").style.display = "none";
		document.getElementById("browserSelectedDivId").style.display = "none";
	} else {
		document.getElementById("mobileSelectionDivId").style.display = "none";
		document.getElementById("mobileSelectedDivId").style.display = "none";

		document.getElementById("browserSelectionDivId").style.display = "block";
		document.getElementById("browserSelectedDivId").style.display = "block";
	}
}

function changeBrowser() {
	var osSelectedId = $('#OSSelectId').val();

	document.getElementById("versionId").innerHTML = "<option name='Select' id='Select' value='Select'>-Select-</option>";

	var osValue = document.getElementById(osSelectedId).value;
	var osId = document.getElementById(osSelectedId).getAttribute("data-osid");

	var browsers = "<option name='Select' id='Select' value='Select'>-Select-</option>";
	if (osId != "Select") {
		$.ajax({
			url : "getBrowserList?osId=" + osId,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				for (var count = 0; count < data.length; count++) {
					var browserName = data[count];
					browsers = browsers + "<option name='" + browserName
							+ "' id='" + browserName + "' value='"
							+ browserName + "'>" + browserName + "</option>";
				}
				document.getElementById("browserId").innerHTML = browsers;
			}
		});
	}
}

function changeVersion() {
	var browSelectedId = $('#browserId').val();
	var osSelectedId = $('#OSSelectId').val();

	var osValue = document.getElementById(osSelectedId).value;
	var osId = document.getElementById(osSelectedId).getAttribute("data-osid");

	var version = "<option name='Select' id='Select' value='Select'>-Select-</option>";
	if (browSelectedId != "Select") {
		$.ajax({
			url : "getVersionList?osId=" + osId + "&browser=" + browSelectedId,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				for (var count = 0; count < data.length; count++) {
					var versionNumber = data[count];
					version = version + "<option name='" + versionNumber
							+ "' id='" + versionNumber + "' value='"
							+ versionNumber + "'>" + versionNumber
							+ "</option>";
				}
				document.getElementById("versionId").innerHTML = version;
			}
		});
	}
}

function getToolColumnValues() {
	var toolColumn = document.getElementById("toolColumnId").value;
	var toolName = document.getElementById("toolNameId").value;

	if (toolColumn != "None") {
		if (toolName != "None") {
			$.ajax({
				url : "getToolColumnValues?toolName=" + toolName
						+ "&toolColumn=" + toolColumn,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					var dropDownEle = document
							.getElementById("toolColumnValueId");
					var innerHtmlStr = '<option value="None">None</option>';
					for (var counter = 0; counter < data.length; counter++) {
						innerHtmlStr = innerHtmlStr + '<option value="'
								+ data[counter] + '">' + data[counter]
								+ '</option>';
					}
					dropDownEle.innerHTML = "";
					dropDownEle.innerHTML = innerHtmlStr;
				}
			});
		} else {
			var dropDownEle3 = document.getElementById("toolColumnValueId");
			dropDownEle2.innerHTML = '<option value="None">None</option>';
			var dropDownEle4 = document.getElementById("toolColumnId");
			dropDownEle2.innerHTML = '<option value="None">None</option>';
		}
	} else {
		var dropDownEle2 = document.getElementById("toolColumnValueId");
		dropDownEle2.innerHTML = '<option value="None">None</option>';
		// getToolTestCase();
	}
}

function getToolColumnValuesPT() {
	var toolColumn = document.getElementById("toolColumnIdPT").value;
	var toolName = document.getElementById("toolNameIdPT").value;

	if (toolColumn != "None") {
		if (toolName != "None") {
			$.ajax({
				url : "getToolColumnValues?toolName=" + toolName
						+ "&toolColumn=" + toolColumn,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					var dropDownEle = document
							.getElementById("toolColumnValueIdPT");
					var innerHtmlStr = '<option value="None">None</option>';
					for (var counter = 0; counter < data.length; counter++) {
						innerHtmlStr = innerHtmlStr + '<option value="'
								+ data[counter] + '">' + data[counter]
								+ '</option>';
					}
					dropDownEle.innerHTML = "";
					dropDownEle.innerHTML = innerHtmlStr;
				}
			});
		} else {
			var dropDownEle3 = document.getElementById("toolColumnValueIdPT");
			dropDownEle2.innerHTML = '<option value="None">None</option>';
			var dropDownEle4 = document.getElementById("toolColumnIdPT");
			dropDownEle2.innerHTML = '<option value="None">None</option>';
		}
	} else {
		var dropDownEle2 = document.getElementById("toolColumnValueIdPT");
		dropDownEle2.innerHTML = '<option value="None">None</option>';
		// getToolTestCase();
	}
}

function getToolTestCasePT() {

	var toolColumn = document.getElementById("toolColumnIdPT").value;
	var toolName = document.getElementById("toolNameIdPT").value;
	var toolColumnValue = document.getElementById("toolColumnValueIdPT").value;

	if (toolName != "None") {
		var rowsStr = "";
		$
				.ajax({
					url : "getToolTestCaseList?toolName=" + toolName
							+ "&toolColumn=" + toolColumn + "&toolColumnValue="
							+ toolColumnValue,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						$("#testCaseTableIdPT tbody").html("");
						if (data.length > 0) {
							for (var count = 0; count < data.length; count++) {
								var requirement = data[count].requirement;
								var testScenario = data[count].testScenario;
								var testCase = data[count].testCase;
								var testSet = data[count].testSet;
								var manualOrAutoFlag = data[count].manualOrAutoFlag;
								var estManualEff = data[count].estManualEff;
								var estAutomateEff = data[count].estAutomateEff;
								var priority = data[count].priority;
								var criticality = data[count].criticality;
								var defect = data[count].defect;
								var testId = data[count].testCaseId;
								var tdmDataExist = data[count].tdmDataExist;							
								if (Number(count) < 10) {
									rowsStr = rowsStr
											+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="selectedTestCasesList0'
											+ (Number(count) + 1)
											+ '" name="selectedTestCasesList['
											+ count
											+ ']" value="'
											+ testCase
											+ '#'
											+ testId
											+ '"/></td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ requirement
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testScenario
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testCase
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testSet
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ manualOrAutoFlag
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estManualEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estAutomateEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ priority
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ criticality
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ defect
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ tdmDataExist + '</td>' 
											+ '</tr>';
								} else {
									rowsStr = rowsStr
											+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="selectedTestCasesList'
											+ count
											+ '" name="selectedTestCasesList['
											+ count
											+ ']" value="'
											+ testCase
											+ '#'
											+ testId
											+ '"/></td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ requirement
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testScenario
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testCase
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testSet
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ manualOrAutoFlag
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estManualEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estAutomateEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ priority
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ criticality
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ defect
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ tdmDataExist + '</td>' 
											+ '</tr>';

								}
							}
						} else {
							$("#testCaseTableIdPT tbody")
									.html(
											'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Available For This Tool.</label></td></tr>');
						}
						$("#testCaseTableIdPT tbody").append(rowsStr);
					}
				});
	} else {
		$("#testCaseTableIdPT tbody")
				.html(
						'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Selected.</label></td></tr>');
	}
}

function getToolTestCase() {

	var toolColumn = document.getElementById("toolColumnId").value;
	var toolName = document.getElementById("toolNameId").value;
	var toolColumnValue = document.getElementById("toolColumnValueId").value;

	if (toolName != "None") {
		var rowsStr = "";
		$
				.ajax({
					url : "getToolTestCaseList?toolName=" + toolName
							+ "&toolColumn=" + toolColumn + "&toolColumnValue="
							+ toolColumnValue,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						$("#testCaseTableId tbody").html("");
						if (data.length > 0) {
							for (var count = 0; count < data.length; count++) {
								var requirement = data[count].requirement;
								var testScenario = data[count].testScenario;
								var testCase = data[count].testCase;
								var testSet = data[count].testSet;
								var manualOrAutoFlag = data[count].manualOrAutoFlag;
								var estManualEff = data[count].estManualEff;
								var estAutomateEff = data[count].estAutomateEff;
								var priority = data[count].priority;
								var criticality = data[count].criticality;
								var defect = data[count].defect;
								var testId = data[count].testCaseId;
								var tdmDataExist = data[count].tdmDataExist;
								if (Number(count) < 10) {
									rowsStr = rowsStr
											+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="selectedTestCasesList0'
											+ (Number(count) + 1)
											+ '" name="selectedTestCasesList['
											+ count
											+ ']" value="'
											+ testCase
											+ '#'
											+ testId
											+ '"/></td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ requirement
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testScenario
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testCase
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testSet
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ manualOrAutoFlag
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estManualEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estAutomateEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ priority
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ criticality
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ defect
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ tdmDataExist + '</td>' 
											+ '</tr>';
								} else {
									rowsStr = rowsStr
											+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="selectedTestCasesList'
											+ count
											+ '" name="selectedTestCasesList['
											+ count
											+ ']" value="'
											+ testCase
											+ '#'
											+ testId
											+ '"/></td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ requirement
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testScenario
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testCase
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ testSet
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ manualOrAutoFlag
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estManualEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ estAutomateEff
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ priority
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ criticality
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ defect
											+ '</td>'
											+ '<td style="width: 12.75%; padding-left: 1px;">'
											+ tdmDataExist + '</td>' 
											+ '</tr>';
								}
							}
						} else {
							$("#testCaseTableId tbody")
									.html(
											'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Available For This Tool.</label></td></tr>');
						}
						$("#testCaseTableId tbody").append(rowsStr);
					}
				});
	} else {
		$("#testCaseTableId tbody")
				.html(
						'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Selected.</label></td></tr>');
	}
}

function addBrowser() {

	var isDuplicate = false;

	var browSelected = $('#browserId').val();
	var osSelected = $('#OSSelectId').val();
	var verSelected = $('#versionId').val();

	var idee = "browDiv" + browSelected + osSelected + verSelected;

	if (browSelected != "Select" && osSelected != "Select"
			&& verSelected != "Select") {

		var divs = document.getElementsByClassName("browDivClass");
		for (var counter = 0; counter < divs.length; counter++) {
			var thisEle = divs[counter];
			var thisId = thisEle.getAttribute("id");
			if (thisId == idee) {
				isDuplicate = true;
			}
		}

		if (!isDuplicate) {
			var existing = document.getElementById("selectedBrowserShow").innerHTML;

			if (existing.indexOf("No Selections") > -1) {
				existing = "";
			}

			existing = existing
					+ "<div id='browDiv"
					+ browSelected
					+ osSelected
					+ verSelected
					+ "' data-os='"
					+ osSelected
					+ "' data-browser='"
					+ browSelected
					+ "' data-version='"
					+ verSelected
					+ "' class='browDivClass'><p><label><img src='img/Delete.png' width='20px' height='20px' id='"
					+ browSelected + osSelected + verSelected
					+ "'onclick='deleteBrow(this.id);'>&nbsp;On " + osSelected
					+ " Using " + browSelected + "-" + verSelected
					+ "</label></p></div>";
			document.getElementById("selectedBrowserShow").innerHTML = existing;
		} else {
			alert("Dont select duplicate combinations!");
		}
	} else {
		alert("Select all values to add.");
	}
}

function addDevice() {

	var isDuplicate = false;

	var deviceName = $('#perfectoDeviceNameId').val();
	var deviceUdid = $('#perfectoDeviceUdidId').val();

	var idee = "mobileDiv" + deviceName + deviceUdid;

	if (deviceName != "Select" && deviceUdid != "Select") {

		var divs = document.getElementsByClassName("mobileDivClass");
		for (var counter = 0; counter < divs.length; counter++) {
			var thisEle = divs[counter];
			var thisId = thisEle.getAttribute("id");
			if (thisId == idee) {
				isDuplicate = true;
			}
		}

		if (!isDuplicate) {
			var existing = document.getElementById("selectedMobileShow").innerHTML;

			if (existing.indexOf("No Selections") > -1) {
				existing = "";
			}

			existing = existing
					+ "<div id='mobileDiv"
					+ deviceName
					+ deviceUdid
					+ "' data-deviceName='"
					+ deviceName
					+ "' data-deviceUdid='"
					+ deviceUdid
					+ "' class='mobileDivClass'><p><label><img src='img/Delete.png' width='20px' height='20px' id='"
					+ deviceName + deviceUdid
					+ "'onclick='deleteDevice(this.id);'>&nbsp;On "
					+ deviceName + " With Udid " + deviceUdid
					+ "</label></p></div>";
			document.getElementById("selectedMobileShow").innerHTML = existing;
		} else {
			alert("Dont select duplicate combinations!");
		}
	} else {
		alert("Select all values to add.");
	}
}

function deleteBrow(id) {
	id = "browDiv" + id;
	var ele = document.getElementById(id);
	ele.parentNode.removeChild(ele);

	if (document.getElementById("selectedBrowserShow").innerHTML == "") {
		document.getElementById("selectedBrowserShow").innerHTML = "<label>No Selections.</label>";
	}
}

function deleteDevice(id) {
	id = "mobileDiv" + id;
	var ele = document.getElementById(id);
	ele.parentNode.removeChild(ele);

	if (document.getElementById("selectedMobileShow").innerHTML == "") {
		document.getElementById("selectedMobileShow").innerHTML = "<label>No Selections.</label>";
	}
}
function chkEnvAvil() {
	var envId = $('#enviorment').val();
	// loadAjax();

	var jsondata = [];
	var saveData = $.ajax({
		type : "get",
		async : false,
		url : "itapEnvCheck",
		data : envId,
		dataType : "text",
		success : function(resultData) {
			jsondata = jQuery.parseJSON(resultData);

		}
	});
	createEnvCheckModalBody(jsondata);

	/*
	 * 
	 * $('#envModel').modal({ show : false, remote : './itapEnvCheck?envId=' +
	 * envId });
	 */

}
function createEnvCheckModalBody(jsondata) {
	var passedDates = [];
	var listITAPBookEnvDTO = [];
	var avilableDates = [];
	var bookedDates = [];
	var appName = passedDates = jsondata["passedDates"];
	listITAPBookEnvDTO = jsondata["listITAPBookEnvDTO"][0];
	avilableDates = listITAPBookEnvDTO["avilableDates"];
	bookedDates = listITAPBookEnvDTO["bookedDates"];
	var appName = listITAPBookEnvDTO["appName"];
	var envname = listITAPBookEnvDTO["envName"];
	var passedDateString = "";
	var passedDateSize = passedDates.length;
	var availableDateSize = avilableDates.length;

	passedDateString = passedDateString
			+ '<th style="padding-right: 20px;"><strong>Application  Name</strong></th>';
	passedDateString = passedDateString
			+ '<th style="padding-right: 20px;"><strong>Environment  Name </strong></th>';
	for (i = 0; i < passedDateSize; i++) {
		passedDateString = passedDateString
				+ '<th style="padding-right: 20px;"><strong>' + passedDates[i]
				+ '</strong> </th>';
	}
	var bodyTrString = "";
	bodyTrString = bodyTrString + '<td align="left">'
			+ listITAPBookEnvDTO["appName"] + '</td>';
	bodyTrString = bodyTrString + '<td align="left">'
			+ listITAPBookEnvDTO["envName"] + '</td>';

	for (i = 0; i < availableDateSize; i++) {
		var date = avilableDates[i];
		if (date.includes("_")) {
			bodyTrString = bodyTrString
					+ '	<td align="left"><a class="btn btn-danger" style="padding: 3px 3px; font-size: xx-small;" href="#" >Booked</a></td>';

		} else {
			bodyTrString = bodyTrString
					+ '	<td align="left"><a class="btn btn-success" style="padding: 3px 3px; font-size: xx-small;" href="#" >Available</a></td>';
		}

	}
	$("#thTr").html = "";
	$("#envHead").find("tr").remove();
	$("#envHead").append('<tr id="thTr"></tr>"');
	$("#thTr").append(passedDateString);

	$("#envTbody").find("tr").remove();
	$("#envTbody").append('<tr id="bodyTr"></tr>"');
	$("#bodyTr").html = "";
	$("#bodyTr").append(bodyTrString);

	$('#envModelAmit').modal('show');
}

$(document).ready(function() {
	$("#ajaxloader").hide();
});
$(function() {
	$('a[data-toggle="collapse"]').on('click', function() {
		var objectID = $(this).attr('href');
		if ($(objectID).hasClass('in')) {
			$(objectID).collapse('hide');
		} else {
			$(objectID).collapse('show');
		}
	});

	$('#expandAll').on('click', function() {
		$('a[data-toggle="collapse"]').each(function() {
			var objectID = $(this).attr('href');
			if ($(objectID).hasClass('in') === false) {
				$(objectID).collapse('show');
			}
		});
	});

	$('#collapseAll').on('click', function() {
		$('a[data-toggle="collapse"]').each(function() {
			var objectID = $(this).attr('href');
			$(objectID).collapse('hide');
		});
	});
});

function getTestColValuesForToolNCol(toolName, dropDownId, nextDropDownId) {
	var toolColumn = document.getElementById(dropDownId).value;
	$.ajax({
		url : "getToolColumnValues?toolName=" + toolName + "&toolColumn="
				+ toolColumn,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var dropDownEle = document.getElementById(nextDropDownId);
			var innerHtmlStr = '<option value="None">None</option>';
			for (var counter = 0; counter < data.length; counter++) {
				innerHtmlStr = innerHtmlStr + '<option value="' + data[counter]
						+ '">' + data[counter] + '</option>';
			}
			dropDownEle.innerHTML = "";
			dropDownEle.innerHTML = innerHtmlStr;
		}
	});
}

function getTestColValuesForOPTIKNCol() {

	var testSuite = document.getElementById("OPTIKTSSelectId").value;
	var optikColumnName = document.getElementById("optikTestSuiteColumnNameId").value;
	var optikColumnValue = document
			.getElementById("optikTestSuiteColumnValueId").value;

	$.ajax({
		url : "getTestColValuesForOPTIKNCol?testSuite=" + testSuite
				+ "&toolColumn=" + optikColumnName,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var dropDownEle = document
					.getElementById("optikTestSuiteColumnValueId");
			var innerHtmlStr = '<option value="None">None</option>';
			for (var counter = 0; counter < data.length; counter++) {
				innerHtmlStr = innerHtmlStr + '<option value="' + data[counter]
						+ '">' + data[counter] + '</option>';
			}
			dropDownEle.innerHTML = "";
			dropDownEle.innerHTML = innerHtmlStr;
		}
	});
}

function getTestCasesForOPTIKNCol() {

	var testSuite = document.getElementById("OPTIKTSSelectId").value;

	if (testSuite != "Select") {

		var tableId = "#OPTIKTestCaseTableId tbody";

		var idPrefix = "selectedTestCases";

		var toolName = "OPTIK";

		var colName = document.getElementById("optikTestSuiteColumnNameId").value;
		var colValue = document.getElementById("optikTestSuiteColumnValueId").value;

		var rowsStr = "";
		$
				.ajax({
					url : "getToolTestCaseListForOPTIK?toolName=" + toolName
							+ "&toolColumn=" + colName + "&toolColumnValue="
							+ colValue + "&testSuite=" + testSuite,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						$("#testCaseTableId tbody").html("");
						if (data.length > 0) {
							for (var count = 0; count < data.length; count++) {
								var requirement = data[count].requirement;
								var testScenario = data[count].testScenario;
								var testCase = data[count].testCase;
								var testSet = data[count].testSet;
								var manualOrAutoFlag = data[count].manualOrAutoFlag;
								var estManualEff = data[count].estManualEff;
								var estAutomateEff = data[count].estAutomateEff;
								var priority = data[count].priority;
								var criticality = data[count].criticality;
								var defect = data[count].defect;
								var testId = data[count].testCaseId;
								var tdmDataExist = data[count].tdmDataExist;
								rowsStr = rowsStr
										+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="'
										+ idPrefix
										+ (Number(count) + 1)
										+ '" name="'
										+ idPrefix
										+ '" value="'
										+ testCase
										+ '#'
										+ testId
										+ '"/><input type="hidden" name="_'
										+ idPrefix
										+ '" value="on"></td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ requirement
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ testScenario
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ testCase
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ testSet
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ manualOrAutoFlag
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ estManualEff
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ estAutomateEff
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ priority
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ criticality
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ defect
										+ '</td>'
										+ '<td style="width: 12.75%; padding-left: 1px;">'
										+ tdmDataExist + '</td>'
										+ '</tr>';
							}
						} else {
							$(tableId)
									.html(
											'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Available For This Tool.</label></td></tr>');
						}
						$(tableId).html("");
						$(tableId).append(rowsStr);
					}
				});
	} else {
		alert("Select test suite to proceed!")
	}
}

function getTestCasesForToolNCol(toolName, colNameDropDwnId, colValDropDwnId) { 

	var tableId = "#" + toolName + "TestCaseTableId tbody";
	var idPrefix = "";
	if (toolName == "BDD") {
		idPrefix = "bddSct"
	} else if (toolName == "APITESTING") {
		idPrefix = "apiTest"
	}else if (toolName == "SPT") {
		idPrefix = "spTest"
	}
	var colName = document.getElementById(colNameDropDwnId).value;
	var colValue = document.getElementById(colValDropDwnId).value;
	
	var rowsStr = "";
	$
			.ajax({
				url : "getToolTestCaseList?toolName=" + toolName
						+ "&toolColumn=" + colName + "&toolColumnValue="
						+ colValue,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					$("#testCaseTableId tbody").html(""); 
					if (data.length > 0) {
						for (var count = 0; count < data.length; count++) {
							var requirement = data[count].requirement;
							var testScenario = data[count].testScenario;
							var testCase = data[count].testCase;
							var manualOrAutoFlag = data[count].manualOrAutoFlag;
							var estManualEff = data[count].estManualEff;
							var estAutomateEff = data[count].estAutomateEff;
							var priority = data[count].priority;
							var criticality = data[count].criticality;
							var defect = data[count].defect;
							var testId = data[count].testCaseId;
							var tdmDataExist = data[count].tdmDataExist;							
							var testSet = data[count].testSet;
							//var apiTestSet = data[count].apiTestSet;
							rowsStr = rowsStr
									+ '<tr style="height: 15px;"><td style="width: 2%; padding-left: 1px;"><input type="checkbox" id="'
									+ idPrefix
									+ (Number(count) + 1)
									+ '" name="'
									+ idPrefix
									+ '" value="'
									+ testCase
									+ '#'
									+ testId
									+ '"/><input type="hidden" name="_'
									+ idPrefix
									+ '" value="on"></td>'
									+ '<td style="width: 12.5%; padding-left: 1px;">'
									+ requirement
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ testScenario
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ testCase
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ testSet
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ manualOrAutoFlag
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ estManualEff
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ estAutomateEff
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ priority
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ criticality
									+ '</td>'
									+ '<td style="width: 12.75%; padding-left: 1px;">'
									+ defect
									+ '</td>'
									+ '<td style="width: 12.5%; padding-left: 1px;">'
									+ tdmDataExist + '</td>' 
									+ '</tr>';
						}
					} else {
						$(tableId)
								.html(
										'<tr><td colspan="9">&nbsp;</td><tr><td colspan="9" style="align: center;" align="center"><label>No Test Case Available For This Tool.</label></td></tr>');
					}
					$(tableId).html("");
					$(tableId).append(rowsStr);
				}
			});
}

function getOptionsGivenGrid() {

	var selectedGridUrl = document.getElementById("gridUrlId").value;
	var selectedGridUrlId = document.getElementById(selectedGridUrl)
			.getAttribute("data-urlId");

	var gridDevices = "<option value='Select'>Select</option>";

	if (selectedGridUrl != 'Select') {
		$
				.ajax({
					url : "getPerfectoDevicesForGivenGrid?gridId="
							+ selectedGridUrlId,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						for (var counter = 0; counter < data.length; counter++) {
							gridDevices = gridDevices + "<option id='"
									+ data[counter] + "' value='"
									+ data[counter] + "'>" + data[counter]
									+ "</option>";
						}
						document.getElementById("perfectoDeviceNameId").innerHTML = gridDevices;
					}
				});
	}
}

function getDeviceUdid() {
	var selectedGridUrl = document.getElementById("gridUrlId").value;
	var selectedGridUrlId = document.getElementById(selectedGridUrl)
			.getAttribute("data-urlId");

	var deviceName = document.getElementById("perfectoDeviceNameId").value;
	var gridDeviceUdids = "<option value='Select'>Select</option>";
	$
			.ajax({
				url : "getPerfectoDevicesUdid?gridId=" + selectedGridUrlId
						+ "&deviceName=" + deviceName,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					for (var counter = 0; counter < data.length; counter++) {
						gridDeviceUdids = gridDeviceUdids + "<option id='"
								+ data[counter] + "' value='" + data[counter]
								+ "'>" + data[counter] + "</option>";
					}
					document.getElementById("perfectoDeviceUdidId").innerHTML = gridDeviceUdids;
				}
			});
}