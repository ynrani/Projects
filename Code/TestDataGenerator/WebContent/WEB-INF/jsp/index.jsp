<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
  <link rel="shortcut icon" href="images/favicon.ico">
  <link rel="stylesheet" href="css/jquery.ui.theme.css" />
  <link rel="stylesheet" href="css/jquery-ui.min.css" />
  <script src="javascript/jquery-1.9.1.js"></script>
  <script src="javascript/jquery-ui.js"></script>
  <script src="javascript/jquery-ui.min.js"></script>
  <script src="javascript/DateTimePicker.js"></script>
  <script src="javascript/support-js.js"></script>
    <script src="javascript/jquery.form.js"></script>
  <link href="css/uploadfile.css" rel="stylesheet">
<script src=" javascript/jquery.uploadfile.min.js"></script>
<style type="text/css">
    body {
    margin:0;
    padding:0;
    font: 100%/1.3 arial,helvetica,sans-serif;

    }
    #content {
    margin:0 auto;
    padding: 0px;
    }
    #package_update {
    padding-left:20px;
    float: left;
    }
    #previous_update {
    padding:20px;
    float: right;
    }
    #dataTable td, th,tr {
    border: solid 1px #DDECEF;
    }
    #dataTable input,select {
    border: solid 1px #DDD;
    }
    #dataTable tr:hover {
    background-color: #DDECEF;
    }
</style>
<link rel="stylesheet" href="css/style.css" />
</head>
<body><!--onload="pageRefresh();"-->
  <div id="preloader">
    <div id="status">&nbsp;</div>
  </div>
  <div style="height:auto">
    <div id="content" style="margin: relative;position: absolute;bottom: 0;left: 0;top: 0;right: 0;width:1000px;background-color:#FFFFFF;">
      <div id="package_update" ><img src="images/header_logo_img.png" /></div>
      <div id="previous_update"><img src="images/logo.png"></div>
    </div>
    <div id="content" style="margin: relative; position: absolute; bottom:10px; left: 0;right: 0; width:1000px;" >
      <div id="package_update" class="ui-widget-label">Copyright 2013 by Capgemini India P Ltd. All Rights Reserved.</div>
    </div>
  </div>
  <div id="tabs">
    <ul>
      <li><a href="#tabs-1">Manual</a></li>
      <li><a href="#tabs-2">Batch</a></li>
      <!--<li><a href="#">Configuration</a></li>
        <li><a href="#">About</a></li>-->
    </ul>
    <div id="tabs-1" >
    <form id="dataForm" name="dataForm" method="post" >
<input type ="hidden" name = "rowCount" id="rowCount" value =''/>
      <table width="100%" border=0 >

          <tr>
            <td  width="100%">
            	<table width="100%" >
            		<tr>
						<td width="50%">
							<table  class="ui-widget ui-widget-content-table"  width="100%" border=0>
								<tr>
								  <td colspan=2 class="ui-state-default">Input Section</td>
								</tr>
								<tr>
								  <td colspan="2">&nbsp;</td>
								</tr>
								<tr>
								  <td nowrap>
									<label class="ui-widget ui-widget-label"  >Required Record(s)</label>&nbsp;
									<input id="records" type="text" name="records" size="10px" value=""/>
								  </td>
								  <td nowrap><input id="addRow" name="addRow" type="submit" class="ui-widget" onClick="addNewRow('dataTable',document.getElementById('rowCnt').value)" value="Add Column(s)" />&nbsp;<input id="rowCnt" type="text" name="rowCnt" value="1"  size="10px"/>
								  </td>
								</tr>
								<tr>
								  <td colspan=2>&nbsp;</td>
								</tr>
						  </table>
            			</td>
            			<td width="50%">
						  <table class="ui-widget ui-widget-content-table" width="100%" style="background-color:#FFF">
							<tr>
							  <td colspan=2  class="ui-state-default">Export File Format</td>
							</tr>
							<tr>
							  <td colspan=2>&nbsp;</td>
							</tr>
							<tr>
							  <td >
								<input type="radio" name="csv[]" id="showDelimit" value="TEXT" checked="checked"  onclick="javascript:hideshow(document.getElementById('showDelimit'))" />TEXT &nbsp;<input type="radio" name="csv[]" id="showDelimit1" value="CSV" onclick="javascript:hideshow(document.getElementById('showDelimit1'))" />CSV &nbsp;<input  type="radio" name="csv[]" id="hideDelimit" onclick="javascript:hideshow(document.getElementById('hideDelimit'))" value="Excel" />&nbsp;Excel<input type="hidden" name="outputtype" />
							  </td>
							  <td id="delimiter">Delimiter:<input type="text" name="delimiter" id="delimit" /></td>
							</tr>
							<tr>
							<% String filename = (String) request.getSession().getAttribute("tempfilename"); 
							   System.out.print(filename);
							%>
							  <td   colspan=2 >&nbsp;  <% if(filename!=null ) 
							   {%>
							   
							     <a id="downloadid"  href=" <%=request.getContextPath() %>/RedirectorServlet"><b style="color:red">Download File</b> </a>
							       
							   <%
							   }						   
							   %></td>
							</tr>
						  </table>
            			</td>
            		</tr>
            	</table>
            </td>
          </tr>
          <tr>
            <td colspan=2 width="100%">
              <table width="100%" class="ui-widget ui-widget-content" border=0 style="background-color:#eeeFFF">
                <tr>
                  <td colspan=4 class="ui-state-default" >Data Generation Rule</td>
                </tr>
                <tr>
                  <td width="100%" valign="top" colspan=4 style="background-color:#FFF ">
                    <div id="#content_2"  style="background-color:#FFF " >
                      <table id="dataTable" width="100%"  class="ui-widget gridview" style="background-color:#FFF;" border=0 >
                        <thead>
                          <tr  class="ui-state-default"  >
                            <th width="3%" style="background-color:black;"><strong><input type="submit" value="Del" onclick="deleteRow('dataTable')"  /></strong></th>
                            <th width="2%"><strong>S.No</strong></th>
                            <th width="12%"><strong>Column Name</strong></th>
                            <th width="12%" ><strong>Column Type</strong></th>
                            <th width="8%"  nowrap="nowrap"><strong>Column Length</strong></th>
                            <th width="70%" ><strong>Format</strong></th>
                            <th width="7%" ><strong>Unique</strong></th>
                          </tr>
                        </thead>
                        <tr >
                          <td align="center" ><input type="checkbox" name="colName[1][chk]" /></td>
                          <td id="sno">1</td>
                          <td><input type="text" name="colName[1][colName]" title="Column Name" class="InputName"  id="colName_1"/></td>
                          <td>
                            <select name="colName[1][colType]" title="Column Type" id="colType_1" class="wide" onchange="getColType(this.parentNode.parentNode.rowIndex,this.value,4)"   >
                              <option value="0">--Select Type--</option>
                              <option value="Integer">Integer</option>
                              <option value="Float">Float</option>
                              <option value="String">String</option>
                              <option value="Human Data">Human Data</option>
                              <option value="Date">Date</option>
                              <option value="Time">Time</option>
                              <option value="Date Time">Date Time</option>
                            </select>
                          </td>
                          <td ><input type="text" name="colName[1][colLength]" title="Data Length"  id="colLength_1" size="5"  maxlength="4"/></td>
                          <td nowrap="nowrap"><input type="text" name="colName[1][colFormat]" id="colFormat_1" size="20"  /></td>
                          <td><input type="checkbox" name="colName[1][unique]"  id="colUnique_1" value="no" onclick="toggleValue(document.getElementById('colUnique_1'),document.getElementById('colUnique_1').value)" /></td>
                        </tr>
                      </table>
                    </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
          	<td colspan=4>
          		<table>
          			<tr>
          				<td width="10%"><input type="submit" value="Select All" onclick="selectAll('dataTable')" /></td>
            			<td colspan=3 align="center" width="90%"><input type="submit" id="generatedata" name="runprof" value="Generate Data" style="width:200px;font-size:15px;"  />
             			&nbsp;<button name="saveprofile1" id="saveprof1" onclick="savefile(this.form,'dataTable')" style="width:200px;font-size:15px;" >Save Profile</button>&nbsp;<button name="reset"  style="width:200px;font-size:15px;" >Reset</button>
            			</td>
            		</tr>
            	</table>
            </td>
          </tr>

      </table>
      </form>
    </div>
    <div id="tabs-2">
      <div id="#content_3"  style="background-color:#FFF;" >
       <form name="fileload" id="fileload" method="post" enctype="multipart/form-data"  action="">
       <input type="hidden" id="filecount" value=1 />
        <table class="ui-widget ui-widget-content-table"  width="100%" border=0 >
        <tr>
            <td colspan=4 class="ui-state-default" >Profile File Upload</td>
        </tr>
         <tr>
             <td colspan="4" class="ui-widget-content-table" >
             <table id="profileTable" class="ui-widget"  width="100%" border=0 >
             <tr><td>
             <div id="fileuploader" >Browse your file</div></td>
             </tr>  <tr>
             <td colspan="4"><div id="bulkdownloadlinks">Click here to Download</div></td>
         </tr>
             </table>	 
    		</td>
         </tr>
        
         <tr>
             <td colspan="4" align="center"><button type="button" id="generatebatch" name="submitFile" value="Generate Batch" style="width:200px;font-size:15px;"  >Generate Data</button> </td>
         </tr>
         </table>
         </form>
         <form id="redirectform" action="RedirectorServlet"> </form>
           <form id="indexform" action="WelcomeController"> </form>
            <form id="saveprofileform" action="SaveProfileServlet"> </form>
      </div>
    </div>
  </div>
 
	
</body>
</html>