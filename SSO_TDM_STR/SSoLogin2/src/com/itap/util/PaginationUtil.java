package com.itap.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itap.constant.ITAPConstants;
import com.itap.exception.ServiceException;

public class PaginationUtil
{
	private static final Logger LOGGER = Logger.getLogger(PaginationUtil.class);
	int startPage;
	int lastPage;

	public int getStartPage() {
		return this.startPage;
	}

	public int getLastPage() {
		return this.lastPage;
	}

	/**
	 * Pagination Method to handle the pagination. It works on number of pages and number of records
	 * to be shown.
	 * 
	 * @param totalRecords
	 * @param request
	 * @param defaultPagesToBeShown
	 * @param recordsPerPage
	 * @throws ServiceException
	 */
	public void paginate(Long totalRecords, HttpServletRequest request, Double defaultPagesToBeShown, int recordsPerPage)
			throws ServiceException {
		LOGGER.info(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_PAGENATE
				+ ITAPConstants.REMS_PGE_UTIL_PAGENATE_NO);
		try {
			int currentPage = Integer.parseInt((request.getAttribute(ITAPConstants.CURRENT_PAGE)).toString());
			Double totalPages = Math.ceil(totalRecords.doubleValue() / recordsPerPage);
			request.setAttribute(ITAPConstants.NO_OF_PAGES, totalPages.intValue());
			if (totalPages < defaultPagesToBeShown) {
				defaultPagesToBeShown = totalPages;
			}
			int startPage = 1;
			int lastPage = defaultPagesToBeShown.intValue();
			if (lastPage > currentPage) {
				lastPage = currentPage;
			}
			Double totalBlocks = totalPages / defaultPagesToBeShown;
			totalBlocks = Math.ceil(totalBlocks);
			Map<Integer, Integer> pageBlock = new HashMap<Integer, Integer>();
			if (totalBlocks > 0) {
				for (int i = 0; i < totalBlocks; i++) {
					lastPage = startPage + defaultPagesToBeShown.intValue() - 1;

					if (lastPage > totalPages) {
						lastPage = totalPages.intValue();
					}
					pageBlock.put(startPage, lastPage);
					startPage = lastPage + 1;
				}
			}
			if (pageBlock != null && !pageBlock.isEmpty()) {
				for (Map.Entry<Integer, Integer> entry : pageBlock.entrySet()) {
					Integer startPageTemp = entry.getKey();
					Integer endPageTemp = entry.getValue();

					if (currentPage >= startPageTemp && currentPage <= endPageTemp) {
						startPage = startPageTemp;
						lastPage = endPageTemp;
						break;
					}
				}
			}
			request.setAttribute(ITAPConstants.START_PAGE, startPage);
			request.setAttribute(ITAPConstants.LAST_PAGE, lastPage);
		} catch (NullPointerException nullPointerEx) {
			LOGGER.error(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_PAGENATE
					+ ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			LOGGER.error(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_PAGENATE
					+ ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
		LOGGER.info(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_PAGENATE + ITAPConstants.LOG_INFO_RETURN);
	}

	public int getOffset(HttpServletRequest request, int recordsPerPage) throws ServiceException {
		// offset = Starting point to fetch records
		// e.g. if we want records from 11-20, offset should be 10.
		LOGGER.info(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_OFFSET + ITAPConstants.LOG_INFO_PARAMS_NO);
		int offset = 0;
		int currentPage = 1;
		try {
			if ((request.getParameter(ITAPConstants.PAGE) != null)
					&& (Integer.parseInt(request.getParameter(ITAPConstants.PAGE)) != 1)) {
				// Get current Page from request
				currentPage = Integer.parseInt(request.getParameter(ITAPConstants.PAGE));

				// Calculate offset value from current Page
				offset = (currentPage - 1) * recordsPerPage;
			}
			request.setAttribute(ITAPConstants.CURRENT_PAGE, currentPage);
			LOGGER.info(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_OFFSET
					+ ITAPConstants.LOG_INFO_RETURN);
			return offset;
		} catch (NullPointerException nullPointerEx) {
			LOGGER.error(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_OFFSET
					+ ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			LOGGER.error(ITAPConstants.REMS_PGE_UTIL + ITAPConstants.REMS_PGE_UTIL_OFFSET
					+ ITAPConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

}
