package com.tdg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class BulkProfileLoadController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	//private final String UPLOAD_DIRECTORY = "D:/testdatageneratorfiles/uploadfile";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post received 1");

		if (ServletFileUpload.isMultipartContent(request)) {
			System.out.println("MultiPart true");
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
						.parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						String fileName = name;
						System.out.println("actual file name:" + name);
						request.getSession().setAttribute("actualFileName", name);
						item.write(new File("D:/testdatageneratorfiles/uploadfile" + File.separator
								+ fileName));
						String fileContent = FileUtils
								.readFileToString(new File("D:/testdatageneratorfiles/uploadfile"
										+ File.separator + fileName));
						HashMap uploadMapList = null;
						if (request.getSession().getAttribute("uploadMapList") != null) {
							uploadMapList = (HashMap) request.getSession().getAttribute(
									"uploadMapList");
						} else {
							uploadMapList = new HashMap();
						}

						uploadMapList.put(fileName, fileContent);
						System.out.println("File content : " + fileContent);
						request.getSession().setAttribute("uploadMapList", uploadMapList);
					}

				}

				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex.getMessage());
			}
		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
	}
}
