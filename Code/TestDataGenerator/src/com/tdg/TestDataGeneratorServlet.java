package com.tdg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestDataGeneratorServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private ServletConfig servletConfig = null;
	

	@Override
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		InputStream inputStream = config.getServletContext().getResourceAsStream(
				"/WEB-INF/TDGProperties.properties");
		this.servletConfig = config;
		Properties props = new Properties();
		try {
			props.load(inputStream);
			props.getProperty("TDGDICTPATH");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getParameter("records");
		String path = null;
		String TEMPPATH = null;		
		InputStream inputStream = getServletConfig().getServletContext().getResourceAsStream(
				"/WEB-INF/TDGProperties.properties");
		//ServletContext context = getServletContext();

		Properties props = new Properties();
		try {
			props.load(inputStream);
			path = props.getProperty("TDGDICTPATH");
			TEMPPATH = props.getProperty("TEMPPATH");
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		ReadExcelDemo demo = new ReadExcelDemo();	
		request.setAttribute("PATH", path);
		request.setAttribute("TEMPPATH", TEMPPATH);
		demo.getExcelData(request, response);
		
	}
}
