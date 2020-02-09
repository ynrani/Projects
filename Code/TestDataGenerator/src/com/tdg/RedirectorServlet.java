package com.tdg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectorServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		String filename = (String) request.getSession().getAttribute("tempfilename");
		String filepath = (String) request.getSession().getAttribute("tempfilepath");
		//String filenamein = (String) request.getSession().getAttribute("filenamein");
		String bulkFilePath = request.getParameter("bulkpath");

		String filetoDownload = filepath + filename;

		if (bulkFilePath != null) {
			filetoDownload = bulkFilePath;
			filename = filetoDownload.substring(filetoDownload.lastIndexOf("/"));
		}

		httpServletResponse.setContentType("text/html");
		PrintWriter out = httpServletResponse.getWriter();

		httpServletResponse.setContentType("APPLICATION/OCTET-STREAM");

		httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename
				+ "\"");
		FileInputStream fileInputStream = new FileInputStream(filetoDownload);
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();

		request.getSession().setAttribute("tempfilepath", null);
		request.getSession().setAttribute("tempfilename", null);

		out.close();
	}
}
