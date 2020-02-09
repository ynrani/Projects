package com.tdg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProfileServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String profileRequest = (String) request.getSession().getAttribute("profileRequest");

		PrintWriter out = response.getWriter();
		String agent = request.getHeader("USER-AGENT");
		System.out.println(agent);
		if ((agent != null) && (agent.indexOf("MSIE") != -1)) {
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=profile.txt");
		} else if ((agent != null) && (agent.indexOf("Mozilla") != -1)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment;filename=profile.txt");
		}
		out.write(profileRequest);

		out.close();
	}
}