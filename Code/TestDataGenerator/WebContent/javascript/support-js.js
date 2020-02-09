$( document ).ready(function() {
	$("#bulkdownloadlinks").hide();
	$("#fileuploader").uploadFile({
		url:"BulkProfileLoadController",
		multiple:true,
		fileName:"myfile"
	}); 
	$("#generatebatch").click(function(){
		var divItems = $('div.ajax-file-upload-statusbar').length;
		if(divItems<1){
			alert("Please upload the file and then press 'Generate Data' ");
			return false;
		}
		$.post( "BulkProfileProcessor" ).done(function( data ) {
			$("#bulkdownloadlinks").show();
			$(".ajax-file-upload-statusbar").hide();
			$("#bulkdownloadlinks").html(data);
		});
	});  	
	$("#downloadid").click(function(){
		$(this).hide();
	});
	$("#saveprof").click(function(){
		$("#saveprofileform").submit();
	});
	$("#generatedata").click(function() {
		var tableID = 'dataTable';
		var ck_name = /^[A-Za-z0-9 ]{1,20}$/
		var ck_colLength = /^[0-9]{1,500}$/;
		var chk_delimit = /^[|~!@#$%&*]{1}$/
		var records=document.forms["dataForm"]["records"].value;
		var high=0,low=0;
		
		document.forms["dataForm"]["outputtype"].value="";
		
		if(records<1 || !ck_colLength.test(records)) {
			alert("Please enter valid number of required records");
			document.forms["dataForm"]["records"].focus();
			return;
		}
		if(document.getElementById('rowCnt').value < 1 ||  document.getElementById('rowCnt').value > 167  || !ck_colLength.test(document.getElementById('rowCnt').value)){
			alert("Adding columns should be greater than 1 or equal to 166 (Max-167 rows)");
			return;
		}
		if(document.getElementById('showDelimit').checked==true){
			document.forms["dataForm"]["outputtype"].value="TEXT";
			if(document.getElementById('delimit').value==''||!chk_delimit.test(document.getElementById('delimit').value)){
				alert("Enter the delimiter.Valid delimiter is | ~ ! @ # $ % & *");
				document.getElementById('delimit').focus();
				return;
			}
		}
		if(document.getElementById('hideDelimit').checked==true){
			document.getElementById('delimit').value='';
			document.forms["dataForm"]["outputtype"].value="EXCEL";
		}
		
		if(document.getElementById('showDelimit1').checked==true){
			document.getElementById('delimit').value='';
			document.forms["dataForm"]["outputtype"].value="CSV";
		}
		//alert(document.forms["dataForm"]["outputtype"].value);
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var j=0;
		var i=0;
		for(i=1; i<rowCount; i++){
			//alert(document.getElementById('colUnique_'+i).value);
			if(document.getElementById('colName_'+i)){
				if(document.getElementById('colName_'+i).value=='' || !ck_name.test(document.getElementById('colName_'+i).value)){
					alert("Enter the Valid Column Name in Row "+i+". [5-20 characters]");
					document.getElementById('colName_'+i).focus();
					return;
				}
			}
			if(document.getElementById('colType_'+i)){
				if(document.getElementById('colType_'+i).value==0){
					alert("Select the column type in  Row "+i);
					return;
				}
			}
		/*	if(document.getElementById('colLength_'+i)){
				if(!document.getElementById('colType_'+i).value==7 ||!document.getElementById('colType_'+i).value==5 ||!document.getElementById('colType_'+i).value==6){
					if(document.getElementById('colLength_'+i).value=='' || document.getElementById('colLength_'+i).value<0 || !ck_colLength.test(document.getElementById('colLength_'+i).value)){
						alert("Enter the Valid Column Length in Row "+i);
						document.getElementById('colLength_'+i).value=='';
						document.getElementById('colLength_'+i).focus();
						return;
					}
				}
			}*/
			if(document.getElementById('integerseltype_' + i)){
				if(document.getElementById('integerseltype_' + i).value==0){
					alert("Select the Integer format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('integerseltype_' + i))
			{
				if(document.getElementById('integerseltype_' + i).value=="Random")
				{
					if(document.getElementById('between_'+i))
					{
						if(document.getElementById('between_'+i).value=='' || document.getElementById('and_'+i).value=='' )
						{
							low=1;
							if(document.getElementById('colLength_'+i) && document.getElementById('colLength_'+i).value==""){
								high=999999;
							}else{
								//alert(document.getElementById('colLength_'+i).value.length);
								for(var j=1;j<=document.getElementById('colLength_'+i).value.length;j++){
									high = high + (9 * (10 ^ (j - 1)));
								}
							}
							var x=high - low;
							if(document.getElementById('colUnique_'+i).value=="yes"){
								if(x<records){
									alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
									return;
								}
							}	
							document.getElementById('between_'+i).value=low;
							document.getElementById('and_'+i).value=high;
						
						}
					else if (document.getElementById('between_'+i).value!=null || document.getElementById('and_'+i).value!=''){
						low=document.getElementById('between_'+i).value;
						high=document.getElementById('and_'+i).value;
						x=high - low;
						if(document.getElementById('colUnique_'+i).value=="yes"){
							if(x<records){
								alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
								return;
							}
						}	
					}
				}
				}	
			}
			if(document.getElementById('integerseltype_' +i))
			{
				if(document.getElementById('integerseltype_' + i).value=="Sequence")
				{
					if(document.getElementById('start_'+i)){
						if(document.getElementById('start_'+i).value=='' || document.getElementById('step_'+i).value=='')
						{
							low=1;
							if(document.getElementById('colLength_'+i) && document.getElementById('colLength_'+i).value==""){
								high=999999;
							}else{
								for(var j=1;j<=document.getElementById('colLength_'+i).value.length;j++){
									high = high + (9 * (10 ^ (j - 1)));
								}
							}
							var x=high - low;
							if(document.getElementById('colUnique_'+i).value=="yes"){
								if(x<records){
									alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
									return;
								}
							}	
							document.getElementById('start_'+i).value=low;
							document.getElementById('step_'+i).value=high;
						
						
					}else if (document.getElementById('start_'+i).value!='' && document.getElementById('step_'+i).value!=''){
						low=document.getElementById('start_'+i).value;
						high=document.getElementById('step_'+i).value;
						var x=high - low;
						if(document.getElementById('colUnique_'+i).value=="yes"){
							if(x<records){
								alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
								return;
							}
						}	
					}}
				}	
			}
			
			if(document.getElementById('floatseltype_' + i)){
				//alert(document.getElementById('floatseltype_' + i).value);
				if(document.getElementById('floatseltype_' + i).value=='0'){
					alert("Select the Float format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('startrange_'+i) && document.getElementById('endrange_'+i)){
				if(document.getElementById('startrange_'+i).value=='' || document.getElementById('endrange_'+i).value==''  ){
					low=1;
					if(document.getElementById('colLength_'+i) && document.getElementById('colLength_'+i).value==""){
						high=999999;
					}else{
						for(var j=1;j<=document.getElementById('colLength_'+i).value.length;j++){
							high = high + (9 * (10 ^ (j - 1)));
						}
					}
					var x=high - low;
					if(document.getElementById('colUnique_'+i).value=="yes"){
						if(x<records){
							alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
							return;
						}
					}
					//alert("Enter the Valid Data in 'Between' field of  Row "+i);
					//alert(low);
					document.getElementById('startrange_'+i).value=low;
					document.getElementById('endrange_'+i).value=high;
					
				}else if (document.getElementById('startrange_'+i).value!='' && document.getElementById('endrange_'+i).value!=''){
					low=document.getElementById('startrange_'+i).value;
					high=document.getElementById('endrange_'+i).value;
					var x=high - low;
					if(document.getElementById('colUnique_'+i).value=="yes"){
						if(x<records){
							alert("Row="+i+":Entered Range of FLoat numbers is not sufficient to generate required no of Unique Float Numbers");
							return;
						}
					}	
				}
			}
			
			if(document.getElementById('stringseltype_' + i)){
				if(document.getElementById('stringseltype_' + i).value==0){
					alert("Select the String format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('humanseltype_' + i)){
				if(document.getElementById('humanseltype_' + i).value==0){
					alert("Select the Human Data format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('dateseltype_' + i)){
				if(document.getElementById('dateseltype_' + i).value==0){
					alert("Select the Date format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('startdate_'+i)){
				if(document.getElementById('startdate_'+i).value=='' ){
					alert("Enter the Valid Data in 'From' field of  Row "+i);
					document.getElementById('startdate_'+i).value=='';
					document.getElementById('startdate_'+i).focus();
					return;
				}
			}
			if(document.getElementById('enddate_'+i)){
				if(document.getElementById('enddate_'+i).value==''){
					alert("Enter the Valid Data in 'To' field of  Row "+i);
					document.getElementById('enddate_'+i).value=='';
					document.getElementById('enddate_'+i).focus();
					return;
				}
			}
			if(document.getElementById('startdate_'+i) && document.getElementById('enddate_'+i)){
				var d1 = new Date(document.getElementById('enddate_'+i).value); //"now"
				var d2 = new Date(document.getElementById('startdate_'+i).value); // before one year
				var msPerDay = 1000*60*60*24;
				var y=parseInt((d1 - d2) / msPerDay).toFixed(0);
				var x=parseInt(y)+1;
				if(document.getElementById('colUnique_'+i).value=="yes"){
					if(x<records){
						alert("Row="+i+":Entered Range of Date is not sufficient to generate required no of Unique Dates");
						return;
					}
				}	
			}
			
			if(document.getElementById('timeFormat_' + i)){
				if(document.getElementById('timeFormat_' + i).value==0){
					alert("Select the Time format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('timefrom_'+i)){
				if(document.getElementById('timefrom_'+i).value=='' ){
					alert("Enter the Valid Data in 'From' field of  Row "+i);
					document.getElementById('timefrom_'+i).value=='';
					document.getElementById('timefrom_'+i).focus();
					return;
				}
			}
			if(document.getElementById('timeto_'+i)){
				if(document.getElementById('timeto_'+i).value==''){
					alert("Enter the Valid Data in 'To' field of  Row "+i);
					document.getElementById('timeto_'+i).value=='';
					document.getElementById('timeto_'+i).focus();
					return;
				}
			}
			if(document.getElementById('dateTimeFormat_' + i)){
				if(document.getElementById('dateTimeFormat_' + i).value==0){
					alert("Select the Date & Time format type in  Row "+i);
					return;
				}
			}
			if(document.getElementById('datetimefrom_'+i)){
				if(document.getElementById('datetimefrom_'+i).value=='' ){
					alert("Enter the Valid Data in 'From' field of  Row"+i);
					document.getElementById('datetimefrom_'+i).value=='';
					document.getElementById('datetimefrom_'+i).focus();
					return;
				}
			}
			if(document.getElementById('datetimeto_'+i)){
				if(document.getElementById('datetimeto_'+i).value==''){
					alert("Enter the Valid Data in 'To' field of  Row "+i);
					document.getElementById('datetimeto_'+i).value=='';
					document.getElementById('datetimeto_'+i).focus();
					return;
				}
			}
			
		}  
		//alert('rowCount'+rowCount);
		document.dataForm.rowCount.value = rowCount-1;
		document.dataForm.action = "controlServlet.do";
		//document.forms["dataForm"].submit();
		$.post( "controlServlet", $( "#dataForm" ).serialize() ).done(function( data ) {
			alert( data );
			
			//$("#redirectform").submit();
			
			$("#indexform").submit();
		});
		
		//document.dataForm.action = "index.jsp";
		//alert("test");
		//document.forms["dataForm"].submit();
		
		
});

});



jQuery(window).load(function() {
	        // will first fade out the loading animation
	    jQuery("#status").fadeOut();
	        // will fade out the whole DIV that covers the website.
	    jQuery("#preloader").delay(1000).fadeOut("slow");	
});


$(function () {
			if(window.screen.availHeight<800){
				
				 if (navigator.appName == "Microsoft Internet Explorer"){
					$("#dataTable").freezeHeader({ 'height': '240px' });
				 }else{
				 	$("#dataTable").freezeHeader({ 'height': '255px' });
				 }
			}else{
				$("#dataTable").freezeHeader({ 'height': '440px' });
			}    });

$(function () {
	
	if(window.screen.availHeight<800){
		
		 if (navigator.appName == "Microsoft Internet Explorer"){
			$("#profileTable").freezeHeader({ 'height': '340px' });
		 }else{
		 	$("#profileTable").freezeHeader({ 'height': '355px' });
		 }
	}else{
		 
		$("#profileTable").freezeHeader({ 'height': '540px' });
	}    });
//370 
$(function () {
	$("#datetimepicker").datetimepicker();
});

$(function() {
	$( "#tabs" ).tabs();
});

$(function() {
	$( "input[type=submit],button" )
	.button()
	.click(function( event ) {
		event.preventDefault();
	});
});

$(function() {
    $( "#datepicker" ).datepicker();
});

if (!$.support.leadingWhitespace) { // if IE6/7/8
    $('select.wide')
        .bind('focus mouseover', function() { $(this).addClass('expand').removeClass('clicked'); })
        .bind('click', function() { $(this).toggleClass('clicked'); })
        .bind('mouseout', function() { if (!$(this).hasClass('clicked')) { $(this).removeClass('expand'); }})
        .bind('blur', function() { $(this).removeClass('expand clicked'); });
}


function getColType(rowid,colType,cellid){
	try{
		if(colType==0){//Reset other cell
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value='';
			y.innerHTML="<input type='text' name='colName["+rowid+"][colFormat]' id='colFormat_"+rowid+" size='20'  />";
		}
		if(colType=="Integer"){//Integer
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value=10;
			document.getElementById('colLength_'+rowid).disabled=false;
			y.innerHTML="<select name='colName["+rowid+"][integerseltype]' id='integerseltype_"+rowid+"' onchange='showHideIntFormat(this.value,"+rowid+")'><option value=0>--Select Format--</option><option value='Random'>Random</option><option value='Sequence'>Sequence</option></select><span id='Ran["+rowid+"]' style='display:none'>Between:<input type='text' name='colName["+rowid+"][between]' id='between_"+rowid +"' size='5' />And:<input name='colName["+rowid+"][and]'  id='and_"+rowid +"' type='text' size='5' /></span><span id='Seq["+rowid+"]' style='display:inline'>Start:<input type='text' id='start_"+rowid+"' name='colName["+rowid+"][start]' size='5' />Step:<input name='colName["+rowid+"][step]' id='step_"+rowid+"'  type='text' size='5' /></span>";
		}
		if(colType=="Float"){//Float
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value=10;
			document.getElementById('colLength_'+rowid).disabled=false;
			y.innerHTML="<select name='colName["+rowid+"][floatseltype]' id='floatseltype_"+rowid+"'><option value='0'>--Select Format--</option><option value='0.0'>0.0</option><option value='0.00'>0.00</option><option value='0.000'>0.000</option><option value='0.0000'>0.0000</option><option value='0.00000'>0.00000</option><option value='0.000000'>0.000000</option></select>Between:<input type='text' size='4' name='colName["+rowid+"][startrange]' id='startrange_"+rowid +"' />&nbspAnd:<input name='colName["+rowid+"][endrange]' id='endrange_"+rowid +"'type='text' size='4' />";
		}
		if(colType=="String"){//String
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value=20;
			document.getElementById('colLength_'+rowid).disabled=false;
			y.innerHTML="<select name='colName["+rowid+"][stringseltype]' id='stringseltype_"+rowid+"'><option value=0>--Select Format--</option><option value='Alphanumeric'>Alphanumeric</option><option value='Character Set'>Character Set</option></select>&nbsp<INPUT type='text' name='colName["+rowid+"][stringColFormat]' id='stringColFormat_"+rowid+"'size='30'  />";
			//var x = getRow.deleteCell(6);
			//var z = insertRowCol.insertCell(6);
		}
		if(colType=="Human Data"){//Human Data
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			document.getElementById('colLength_'+rowid).disabled=false;
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			y.innerHTML="<select name='colName["+rowid+"][humanseltype]' id='humanseltype_"+rowid+"' onchange='showHumanDataFormat(this.value,"+rowid+")'><option value=0>--Select Format--</option><option value='Email Address'>Email Address</option><option value='Bank Names'>Bank Names</option><option value='Card Type'>Card Type</option><option value='State'>State</option><option value='City'>City</option><option value='Pin Code'>Pin Code</option><option value='Name'>Name</option><option value='Pan Card'>Pan Card</option><option value='Sign'>Sign</option></select><span id='humanName["+rowid+"]' style='display:none'><select name='colName["+rowid+"][nameFormat]' id='nameFormat_"+rowid+"' ><option value='First Name'>First Name</option><option value='Last Name'>Last Name</option><option value='Full Name'>Full Name</option><option value='First Initial Last Name'>First Initial Last Name</option></select></span>";
		}
		if(colType=="Date"){//Date
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value='';
			document.getElementById('colLength_'+rowid).disabled=true;
			y.innerHTML="<select name='colName["+rowid+"][dateseltype]' id='dateseltype_"+rowid+"'  ><option value='0'>--Select Format--</option><option value='dd/mm/yyyy'>dd/mm/yyyy</option><option value='dd/mm/yy'>dd/mm/yy</option><option value='dd-mmm-yy'>dd-mmm-yy</option><option value='dd-mm-yy'>dd-mm-yy</option><option value='Mon dd,yyyy'>Mon dd,yyyy</option><option value='Month dd,yyyy'>Month dd,yyyy</option><option value='YYYYMMDD'>YYYYMMDD</option></select>&nbsp;From:<input type='text' size='12' class='date' id='startdate_"+rowid+"'  name='colName["+rowid+"][startdate]' />&nbspTo:<input class='date' name='colName["+rowid+"][enddate]' id='enddate_"+rowid+"'type='text' size='12' />"
			showDate();
		}
		if(colType=="Time"){//Time
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value='';
			document.getElementById('colLength_'+rowid).disabled=true;
        		y.innerHTML="<select name='colName["+rowid+"][timeFormat]'  id='timeFormat_"+rowid+"' ><option value=0>--Select Format--</option><option value='hh:mm (24 hour)'>hh:mm (24 hour)</option><option value='hh:mm AM/PM'>hh:mm AM/PM</option><option value='hh:mm:ss AM/PM'>hh:mm:ss AM/PM</option><option value='hh:mm:ss (24 hour)'>hh:mm:ss (24 hour)</option></select>From:<input type='text' size='12'  name='colName["+rowid+"][timefrom]' id='timefrom_"+rowid+"' />&nbspTo:<input type='text' id='timeto_"+rowid+"' name='colName["+rowid+"][timeto]' size='12' />"
		}
		if(colType=="Date Time"){//Date Time
			var getRow = document.getElementById("dataTable").rows[rowid];
			var x = getRow.deleteCell(5);
			var insertRowCol = document.getElementById("dataTable").rows[rowid];
			var y=insertRowCol.insertCell(5);
			y.setAttribute("nowrap","nowrap");
			document.getElementById('colLength_'+rowid).value='';
			document.getElementById('colLength_'+rowid).disabled=true;
			y.innerHTML="<select name='colName["+rowid+"][dateTimeFormat]' class='wide' id='dateTimeFormat_"+rowid+"' style='width:230px' ><option value=0 >--Select Format--</option><option value='dd/mm/yy hh:mm:ss AM/PM(12 hour)' >dd/mm/yy hh:mm:ss AM/PM(12 hour)</option><option value='dd/mm/yy hh:mm:ss (24 hour)' >dd/mm/yy hh:mm:ss (24 hour)</option><option value='dd/mm/yyyy hh:mm:ss AM/PM(12 hour)' >dd/mm/yyyy hh:mm:ss AM/PM(12 hour)</option><option value='dd/mm/yyyy hh:mm:ss (24 hour)' >dd/mm/yyyy hh:mm:ss (24 hour)</option><option value='dd-mm-yy hh:mm (24 hour)' >dd-mm-yy hh:mm (24 hour)</option><option value='dd-mm-yy hh:mm AM/PM(12 hour)' >dd-mm-yy hh:mm AM/PM(12 hour)</option></select>From:<input type='text' size='15' class='datetime' id='datetimefrom_"+rowid+"'alt='this.value'  name='colName["+rowid+"][datetimefrom]' />&nbspTo:<input class='datetime'  type='text' id='datetimeto_"+rowid+"' name='colName["+rowid+"][datetimeto]' size='15' />"
			showDateTime();
		}
	}catch(e){
	}
}


function showHumanDataFormat(intFormatype,rowid){
	//alert(intFormatype);
	if(intFormatype=="Name"){
		if(document.getElementById('humanName['+rowid+']' ).style.display=="none"){
			document.getElementById('humanName['+rowid+']').style.display="inline";
		}	
	}
	else{
		document.getElementById('humanName['+rowid+']').style.display="none";
	}	
}


function showHideIntFormat(intFormatype,rowid){
	if(intFormatype=="Random"){
		if(document.getElementById('Seq['+rowid+']').style.display=="inline"){
			document.getElementById('Seq['+rowid+']').style.display="none";
			document.getElementById('Ran['+rowid+']').style.display="inline";
		}	
	}else{
		if(document.getElementById('Ran['+rowid+']').style.display=="inline"){
			document.getElementById('Seq['+rowid+']').style.display="inline";
			document.getElementById('Ran['+rowid+']').style.display="none";
		}
	}	
}


function hidelink(element){
 
	element.style.display="none";
}



function addNewRow(tableID,rowCnt) {
			var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			
			if(rowCount > 167){
				alert("Maximum column count reached");
				exit();
			}
			
			if(rowCnt!='' && rowCnt>0){
				for(var j=0; j<rowCnt ; j++){
					var dynaRowCount = table.rows.length;
					
					if(dynaRowCount > 167){
						alert("Maximum column count reached");
						exit();
					}
					
					var row = table.insertRow(rowCount);

					var cell1 = row.insertCell(0);
					var element1 = document.createElement("input");
					element1.type = "checkbox";
					element1.name="colName[" + rowCount +  "][chk]";
					cell1.setAttribute("align","center");
					cell1.appendChild(element1);

					var cell2 = row.insertCell(1);
					cell2.innerHTML = rowCount ;

					var cell3 = row.insertCell(2);
					var element2 = document.createElement("input");
					element2.type = "text";
					element2.name = "colName[" + (rowCount) +  "][colName]";
					element2.setAttribute("class","InputName");
					element2.setAttribute("id","colName_"+ rowCount);
					cell3.appendChild(element2);
					
					var cell4 = row.insertCell(3);
					var items = {"0":"--Select Type--","Integer":"Integer","Float":"Float","String":"String","Human Data":"Human Data","Date":"Date","Time":"Time","Date Time":"Date Time"};
					var element3 = document.createElement("select");
					//element3.type = "select";
					element3.name = "colName[" + (rowCount) +  "][colType]";
					element3.onchange= function(){ getColType(this.parentNode.parentNode.rowIndex,this.value,4);};
					for ( var key in items) {
					    var ov = document.createElement("option");
					    ov.value = key; 
					    ov.appendChild(document.createTextNode(items[key]))
					    element3.appendChild(ov);
					}
					element3.setAttribute("id","colType_"+ rowCount);
					cell4.appendChild(element3)

					var cell5 = row.insertCell(4);
					var element4 = document.createElement("input");
					element4.type = "text";
					element4.name = "colName[" + (rowCount) +  "][colLength]";
					element4.setAttribute("id","colLength_"+ rowCount);
					//element4.setAttribute("value","12");
					element4.size=5;
					cell5.appendChild(element4);

					var cell6 = row.insertCell(5);
					var element5 = document.createElement("input");
					element5.type = "text";
					element5.name= "colName[" + (rowCount) +  "][colFormat]";
					element5.size=20;
					element5.setAttribute("id","colFormat_"+ rowCount);
					cell6.appendChild(element5);

					var cell7 = row.insertCell(6);
					var element6 = document.createElement("input");
					element6.type = "checkbox";
					element6.name="colName[" + (rowCount) +  "][unique]";
					element6.setAttribute("id","colUnique_"+ rowCount);
					element6.setAttribute("value","no");
					element6.onclick= function(){toggleValue(this,this.value);};
					cell7.appendChild(element6);
					
					rowCount++;
					
				}
				
			}
			showDate();
			showDateTime();
			
}


function deleteRow(tableID){
	try{
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var selected = false;
		for(var i=0; i<rowCount; i++){
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if(null != chkbox && true == chkbox.checked){
				if(rowCount <= 2){
					alert("Cannot delete all the rows.");
					break;
				}
				table.deleteRow(i);
				rowCount--;
				i--;
				selected=true;
			}
		}
		if(selected==false){ 
			alert("No rows selected"); 
			return false;
		}
		resetSequenceNo(tableID);
	}catch(e){
		alert(e);
	}
}
function selectAll(tableID){
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	for(var i=1; i<rowCount; i++){
		var row = table.rows[i];
		var inp1 = row.cells[0].getElementsByTagName('input')[0];
		inp1.setAttribute("checked","checked");
	}
	return false;
}

function resetSequenceNo(tableID){
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	for(var i=1; i<rowCount; i++){
		var row = table.rows[i];
		row.cells[1].innerHTML=i;
		var inp1 = row.cells[2].getElementsByTagName('input')[0];
		inp1.id = 'colName_' + i;
		var inp11 = row.cells[2].getElementsByTagName('input')[0];
		inp11.name= 'colName['+ i+'][colName]';
		var inp2 = row.cells[3].getElementsByTagName('select')[0];
		inp2.id = 'colType_' + i;
		var inp22 = row.cells[3].getElementsByTagName('select')[0];
		inp22.name = 'colName['+ i+'][colType]';
		if(inp22.value==1){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][integerseltype]';
				selectBox.id = 'integerseltype_' + i;
				var inputBetweenBox = row.cells[5].getElementsByTagName('input')[0];
				inputBetweenBox.name='colName['+ i+'][between]';
				inputBetweenBox.id = 'between_' + i;
				var inputAndBox = row.cells[5].getElementsByTagName('input')[1];
				inputAndBox.name='colName['+ i+'][and]';
				inputAndBox.id = 'and_' + i;
				var inputStartBox = row.cells[5].getElementsByTagName('input')[2];
				inputStartBox.name='colName['+ i+'][start]';
				inputStartBox.id = 'start_' + i;
				var inputStepBox = row.cells[5].getElementsByTagName('input')[3];
				inputStepBox.name='colName['+ i+'][step]';
				inputStepBox.id = 'step_' + i;
		}else if(inp22.value==2){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][floatseltype]';
				selectBox.id = 'floatseltype_' + i;
				var inputBetweenBox = row.cells[5].getElementsByTagName('input')[0];
				inputBetweenBox.name='colName['+ i+'][startrange]';
				inputBetweenBox.id = 'startrange_' + i;
				var inputEndBox = row.cells[5].getElementsByTagName('input')[1];
				inputEndBox.name='colName['+ i+'][endrange]';
				inputEndBox.id = 'endrange_' + i;
		}else if(inp22.value==3){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][stringseltype]';
				selectBox.id = 'stringseltype_' + i;
				var inputColFormatBox = row.cells[5].getElementsByTagName('input')[0];
				inputColFormatBox.name='colName['+ i+'][stringColFormat]';
				inputColFormatBox.id = 'stringColFormat_' + i;
		}else if(inp22.value==4){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][humanseltype]';
				selectBox.id = 'humanseltype_' + i;
				var selectFormatBox = row.cells[5].getElementsByTagName('select')[1];
				selectFormatBox.name='colName['+ i+'][nameFormat]';
				selectFormatBox.id = 'nameFormat_' + i;
		}else if(inp22.value==5){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][dateseltype]';
				selectBox.id = 'dateseltype_' + i;
				var inputStartBox = row.cells[5].getElementsByTagName('input')[0];
				inputStartBox.name='colName['+ i+'][startdate]';
				inputStartBox.id = 'startdate_' + i;
				var inputEndBox = row.cells[5].getElementsByTagName('input')[1];
				inputEndBox.name='colName['+ i+'][enddate]';
				inputEndBox.id = 'enddate_' + i;
		}else if(inp22.value==6){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][timeFormat]';
				selectBox.id = 'timeFormat_' + i;
				var inputStartBox = row.cells[5].getElementsByTagName('input')[0];
				inputStartBox.name='colName['+ i+'][timefrom]';
				inputStartBox.id = 'timefrom_' + i;
				var inputEndBox = row.cells[5].getElementsByTagName('input')[1];
				inputEndBox.name='colName['+ i+'][timeto]';
				inputEndBox.id = 'timeto_' + i;
		}else if(inp22.value==7){
				var selectBox = row.cells[5].getElementsByTagName('select')[0];
				selectBox.name='colName['+ i+'][dateTimeFormat]';
				selectBox.id = 'dateTimeFormat_' + i;
				var inputStartBox = row.cells[5].getElementsByTagName('input')[0];
				inputStartBox.name='colName['+ i+'][datetimefrom]';
				inputStartBox.id = 'datetimefrom_' + i;
				var inputEndBox = row.cells[5].getElementsByTagName('input')[1];
				inputEndBox.name='colName['+ i+'][datetimeto]';
				inputEndBox.id = 'datetimeto_' + i;
		}
		var inp3 = row.cells[4].getElementsByTagName('input')[0];
		inp3.id = 'colLength_' + i;
		var inp33 = row.cells[4].getElementsByTagName('input')[0];
		inp33.name=  'colName['+i+'][colLength]';
		var inp4 = row.cells[6].getElementsByTagName('input')[0];
		inp4.name=  'colName['+i+'][unique]';
		var inp44 = row.cells[4].getElementsByTagName('input')[0];
		inp44.id = 'colUnique_'+i;
  	}
}

function hideshow(which){
	if (!document.getElementById)
	return
	//alert(which.value);
	if (which.value=="Excel" || which.value=="CSV"){
		document.getElementById('delimit').value='';
		document.getElementById('delimiter').style.display="none";
	}
	else{
		document.getElementById('delimiter').style.display="block";
	}
}

function showDateTime() {
    $('.datetime').each(function() {
        $(this).datetimepicker();
    });
    //alert("r");
}

function showDate() {
    $('.date').each(function() {
        $(this).datepicker();
    });
    //alert("r");
}

function toggleValue(element,unique){
	//alert(element.value);
	if(unique=="no"){
		element.value="yes";
	}else{
		element.value="no";
	}
	//alert(element.value);
}
	    
	    
function validateForm(tableID,form){

	//document.forms["dataForm"].submit();
	//this.submit();
}


function savefile( f,tableID ) {
	f = f.elements;  //  reduce overhead
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	var w = window.frames.w;
	if( !w ) {
		w = document.createElement( 'iframe' );
		w.id = 'w';
		w.style.display = 'none';
		document.body.insertBefore( w, null );
		w = window.frames.w;
		if( !w ) {
			w = window.open( '', '_temp', 'width=100,height=100' );
			if( !w ) {
				window.alert( 'Sorry, the file could not be created.' ); return false;
			}
		}
	}
	
	var d = w.document,ext = ".txt",name = "saveprofile"+ ext;
	
	d.open( 'text/plain', 'replace' );
	d.charset = "utf-8";
	d.write('records=>');
	d.write( f.records.value );
	d.write( '\n' );
	if(f.showDelimit){
		d.write("delimiter=>");	
		d.write( f.delimit.value );d.write( '\n' );
	}else{
		d.write('delimiter=>');	
		d.write("");d.write( '\n' );	
	}
	if(document.getElementById('showDelimit').checked==true){
		f.outputtype.value="TEXT";
		if(document.getElementById('delimit').value==''){
			alert("Enter the delimiter.Valid delimiter is | ~ ! @ # $ % & *");
			document.getElementById('delimit').focus();
			return;
		}
	}
	if(document.getElementById('hideDelimit').checked==true){
		document.getElementById('delimit').value='';
		f.outputtype.value="EXCEL";
	}
	
	if(document.getElementById('showDelimit1').checked==true){
		document.getElementById('delimit').value='';
		f.outputtype.value="CSV";
	}
	d.write('outputtype=>');	
	d.write(f.outputtype.value);d.write( '\n' );
	d.write('rowCount=>');	
	d.write(rowCount-1);d.write( '\n' );
	var i;
	
	for(i = 1; i <= rowCount; i++){
			//alert(document.getElementById('colName_'+i).value);
			if(document.getElementById('colName_'+i)){
				d.write('colName['+i+'][colName]=>');
				d.write(document.getElementById('colName_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('colType_'+i)){
				d.write('colName['+i+'][colType]=>');
				d.write(document.getElementById('colType_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('colLength_'+i)){
				d.write('colName['+i+'][colLength]=>');
				d.write(document.getElementById('colLength_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('integerseltype_' + i) && document.getElementById('integerseltype_' + i).value=="Random"){
				d.write('colName['+i+'][integerseltype]=>');
				d.write(document.getElementById('integerseltype_' + i).value);
				d.write( '\n' );
			
				if(document.getElementById('between_'+i)){
					d.write('colName['+i+'][between]=>');
					d.write(document.getElementById('between_'+i).value);
					d.write( '\n' );
	
				}
				if(document.getElementById('and_'+i)){
					d.write('colName['+i+'][and]=>');
					d.write(document.getElementById('and_'+i).value);
					d.write( '\n' );
	
				}
			}
			if(document.getElementById('integerseltype_' + i) && document.getElementById('integerseltype_' + i).value=="Sequence"){
				d.write('colName['+i+'][integerseltype]=>');
				d.write(document.getElementById('integerseltype_' + i).value);
				d.write( '\n' );
				if(document.getElementById('start_'+i)){
					d.write('colName['+i+'][start]=>');
					d.write(document.getElementById('start_'+i).value);;
					d.write( '\n' );
				}
				if(document.getElementById('step_'+i)){
					d.write('colName['+i+'][step]=>');
					d.write(document.getElementById('step_'+i).value);;
					d.write( '\n' );
	
				}
			}
			if(document.getElementById('floatseltype_' + i)){
				d.write('colName['+i+'][floatseltype]=>');
				d.write(document.getElementById('floatseltype_' + i).value);;
				d.write( '\n' );
				
			}
			if(document.getElementById('startrange_'+i)){
				d.write('colName['+i+'][startrange]=>');
				d.write(document.getElementById('startrange_'+i).value);;
				d.write( '\n' );
				
			}
			if(document.getElementById('endrange_'+i)){
				d.write('colName['+i+'][endrange]=>');
				d.write(document.getElementById('endrange_'+i).value);;
				d.write( '\n' );
				
			}
			if(document.getElementById('stringseltype_' + i)){
				d.write('colName['+i+'][stringseltype]=>');
				d.write(document.getElementById('stringseltype_' + i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('stringColFormat_' + i)){
				if(document.getElementById('stringColFormat_' + i).value!=''){
					d.write('colName['+i+'][stringColFormat]=>');
					d.write(document.getElementById('stringColFormat_' + i).value);
					d.write( '\n' );
				}
			}
			if(document.getElementById('humanseltype_' + i)){
				d.write('colName['+i+'][humanseltype]=>');
				d.write(document.getElementById('humanseltype_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('nameFormat_'+i)){
				d.write('colName['+i+'][nameFormat]=>');
				d.write(document.getElementById('nameFormat_'+i).value);
				d.write( '\n' );
			}
			if(document.getElementById('dateseltype_' + i)){
				d.write('colName['+i+'][dateseltype]=>');
				d.write(document.getElementById('dateseltype_' + i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('startdate_'+i)){
				d.write('colName['+i+'][startdate]=>');
				d.write(document.getElementById('startdate_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('enddate_'+i)){
				d.write('colName['+i+'][enddate]=>');
				d.write(document.getElementById('enddate_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('timeFormat_' + i)){
				d.write('colName['+i+'][timeFormat]=>');
				d.write(document.getElementById('timeFormat_' + i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('timefrom_'+i)){
				d.write('colName['+i+'][timefrom]=>');
				d.write(document.getElementById('timefrom_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('timeto_'+i)){
				d.write('colName['+i+'][timeto]=>');
				d.write(document.getElementById('timeto_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('dateTimeFormat_' + i)){
				d.write('colName['+i+'][dateTimeFormat]=>');
				d.write(document.getElementById('dateTimeFormat_' + i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('datetimefrom_'+i)){
				d.write('colName['+i+'][datetimefrom]=>');
				d.write(document.getElementById('datetimefrom_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('datetimeto_'+i)){
				d.write('colName['+i+'][datetimeto]=>');
				d.write(document.getElementById('datetimeto_'+i).value);
				d.write( '\n' );
				
			}
			if(document.getElementById('colUnique_'+i)){
				d.write('colName['+i+'][colUnique]=>');
				d.write(document.getElementById('colUnique_'+i).value);
				d.write( '\n' );
				
			}
			
		} 
	
		d.close();
		if( d.execCommand( 'SaveAs', null, name ) ){
			window.alert( name + ' has been saved.' );
		} else {
			window.alert( 'The file has not been saved.\nIs there a problem?' );
		}
		w.close();
		return false;  //  don't submit the form
}









/* ------------------------------------------------------------------------
Class: freezeHeader
Use:freeze header row in html table
Example 1:  $('#tableid').freezeHeader();
Example 2:  $("#tableid").freezeHeader({ 'height': '300px' });
Author: Laerte Mercier Junior
Version: 1.0.3
-------------------------------------------------------------------------*/
(function ($) {
    $.fn.freezeHeader = function (params) {
        var copiedHeader = false;
        var idObj = this.selector.replace('#', '');
        var container;
        var grid;
        var conteudoHeader;
        var openDivScroll = '';
        var closeDivScroll = '';

        if (params && params.height !== undefined) {
            divScroll = '<div id="hdScroll' + idObj + '" style="height: ' + params.height + '; overflow-y: scroll; position:auto;">';
            closeDivScroll = '</div>';
        }

        grid = $('table[id$="' + idObj + '"]');
        conteudoHeader = grid.find('thead');

        if (params && params.height !== undefined) {
            if ($('#hdScroll' + idObj).length == 0) {
                grid.wrapAll(divScroll);
            }
        }

        var obj = params && params.height !== undefined
           ? $('#hdScroll' + idObj)
           : $(window);

        if ($('#hd' + idObj).length == 0) {
            grid.before('<div id="hd' + idObj + '"></div>');
        }

        obj.scroll(function () { freezeHeader(); })

        function freezeHeader() {

            if ($('table[id$="' + idObj + '"]').length > 0) {

                container = $('#hd' + idObj);
                if (conteudoHeader.offset() != null) {
                    if (limiteAlcancado(params)) {
                        if (!copiedHeader) {
                            cloneHeaderRow(grid);
                            copiedHeader = true;
                        }
                    }
                    else {

                        if (($(document).scrollTop() > conteudoHeader.offset().top)) {
                            container.css("position", "auto");
                            container.css("top", (grid.find("tr:last").offset().top - conteudoHeader.height()) + "px");
                        }
                        else {
                            container.css("visibility", "hidden");
                            container.css("top", "0px");
                            container.width(0);
                        }

                        copiedHeader = false;

                    }
                }
            }
        }

        function limiteAlcancado(params) {
            if (params && params.height !== undefined) {
                return (conteudoHeader.offset().top <= obj.offset().top);
            }
            else {
                return ($(document).scrollTop() > conteudoHeader.offset().top && $(document).scrollTop() < (grid.height() - conteudoHeader.height() - grid.find("tr:last").height()) + conteudoHeader.offset().top);
            }
        }

        function cloneHeaderRow() {
            container.html('');
            container.val('');
            var tabela = $('<table style="margin: 0 0;"></table>');

            var atributos = grid.prop("attributes");

            $.each(atributos, function () {

                if (this.name != "id") {
                    tabela.attr(this.name, this.value);
                }
            });

            tabela.append('<thead>' + conteudoHeader.html() + '</thead>');

            container.append(tabela);
            container.width(conteudoHeader.width());
            container.height(conteudoHeader.height);
            container.find('th').each(function (index) {
                var cellWidth = grid.find('th').eq(index).width();
                $(this).css('width', cellWidth);
            });

            container.css("visibility", "visible");

            if (params && params.height !== undefined) {
		//alert(obj.offset().top);
                container.css("top", 258 + "px");
                container.css("position", "fixed");
            } else {
                container.css("top", "0px");
                container.css("position", "fixed");
            }
        }
    };
})(jQuery);



