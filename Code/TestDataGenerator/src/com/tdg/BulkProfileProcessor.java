package com.tdg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class BulkProfileProcessor extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap uploadMapList = (HashMap) request.getSession().getAttribute("uploadMapList");
		String actualFile = (String) request.getSession().getAttribute("actualFileName");

		System.out.println(actualFile);
		CloseableHttpClient client = HttpClients.createDefault();
		String links = "";
		try {
			Iterator it = uploadMapList.entrySet().iterator();
			while (it.hasNext()) {
				HttpPost post = new HttpPost(
						"http://localhost:8099/TestDataGenerator/controlServlet");
				Map.Entry pairs = (Map.Entry) it.next();

				it.remove();
				String[] profileArray = pairs.getValue().toString()
						.split(System.getProperty("line.separator"));
				List nameValuePairs = new ArrayList();
				for (String aProfile : profileArray) {
					String[] keyValue = aProfile.split("=>");

					if (keyValue.length != 2)
						continue;
					nameValuePairs.add(new BasicNameValuePair(keyValue[0], keyValue[1]));
				}

				nameValuePairs.add(new BasicNameValuePair("isBulk", "true"));
				nameValuePairs.add(new BasicNameValuePair("actualFileName", actualFile));
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse httpresponse = client.execute(post);
				BufferedReader rd = new BufferedReader(new InputStreamReader(httpresponse
						.getEntity().getContent()));

				String line = "";

				while ((line = rd.readLine()) != null) {
					System.out.println(line);
					links = links + line + "<br>";
				}

			}

			PrintWriter out = response.getWriter();

			out.write(links);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
