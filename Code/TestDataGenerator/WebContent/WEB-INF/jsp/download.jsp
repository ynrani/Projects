<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ page import="java.io.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.ServletOutputStream"%>

<%
		PrintWriter out1 = null;
		try{
		String filename = (String)request.getAttribute("filename");
		String filenamein = (String)request.getAttribute("filenamein");
		response.setContentType("APPLICATION/OCTET-STREAM");  
		String filepath = (String) request.getAttribute("TEMPPATH");
		response.setHeader("Content-Disposition","attachment; filename=\"" +filename+ "\"");  
		out1 = response.getWriter();
		File inputFile = new File(filepath+filename);
		File outFile = new File(filepath+filenamein);
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		int i;   
		while ((i=fileInputStream.read()) != -1) {  
			out1.write(i);   
		}
		out1.close();
		fileInputStream.close(); 
		if(inputFile.exists() && outFile.exists()){
				inputFile.delete() ;
				//System.out.println("hi");
				outFile.delete() ;
		}		
		response.setHeader("Refresh", "5; url=index.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
		}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Download the generated file</title>
</head>
<body>
Click on the link to download: <a href="http://localhost:8081/TestDataGenerator/tempExcel.txt">Download Link</a>

</body>
</html>