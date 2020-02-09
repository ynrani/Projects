/*---------------------------------------------------------------------------------------
 * Object Name: DownloadUtils.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name               		 Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          26/02/16   NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2016 
 *---------------------------------------------------------------------------------------*/

package com.itap.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itap.constant.ITAPConstants;
import com.itap.exception.ServiceException;

/**
 * 
 * @author Seshadri Chowdary
 *
 */
public class DownloadUtils
{
	private static final Logger LOGGER = Logger.getLogger(DownloadUtils.class);
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	public static void download(HttpServletRequest request, HttpServletResponse response, String filePath)
			throws ServiceException {

		LOGGER.info(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.LOG_INFO_PARAMS_NO);
		try {
			// get absolute path of the application
			ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");

			// construct the complete absolute path of the file
			String fullPath = appPath + filePath;
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = ITAPConstants.APP_STREAM;
			}
			LOGGER.info(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.MIME_TYPE + mimeType);
			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			// set headers for the response
			String headerKey = ITAPConstants.HEADER_DISP;
			String headerValue = String.format(ITAPConstants.ATTACHMENT, downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			// get output stream of the response
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			LOGGER.info(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.LOG_INFO_RETURN);
			inputStream.close();
			outStream.close();
		} catch (IOException ie) {
			LOGGER.error(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.IE_EXCEPTION, ie);
		} catch (NullPointerException nullPointerEx) {
			LOGGER.error(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			LOGGER.error(ITAPConstants.REMS_DOWNLOAD_UTIL + ITAPConstants.DOWNLOAD + ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

}
