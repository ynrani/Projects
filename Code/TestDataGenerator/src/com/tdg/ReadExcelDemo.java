package com.tdg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDemo
{
	private String Outfile;
	private String Timestamp = new Date().getTime() + "";
	private String filenamein = null;
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	private static List<PanCardPOJO> list  = new  ArrayList<PanCardPOJO>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getExcelData(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws FileNotFoundException, IOException {
		request=httpServletRequest;
		String path = null;
		String filepath = null;
		File inputFile = null;
		String filename = null;
		HashMap bulkMap = null;
		String bulk = httpServletRequest.getParameter("isBulk");
		boolean isBulk = (bulk != null) && (bulk.equalsIgnoreCase("true"));
	
		if (httpServletRequest.getSession().getAttribute("bulkMap") != null)
			bulkMap = (HashMap) httpServletRequest.getSession().getAttribute("bulkMap");
		else {
			bulkMap = new HashMap();
		}
		Enumeration parameterNames = httpServletRequest.getParameterNames();
		String profileRequest = "";
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			profileRequest = profileRequest + paramName + ":";
			String[] paramValues = httpServletRequest.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				profileRequest = profileRequest + paramValue + System.getProperty("line.separator");
			}

		}

		httpServletRequest.getSession().setAttribute("profileRequest", profileRequest);
		//Map params = httpServletRequest.getParameterMap();
		//Iterator i = params.keySet().iterator();
		PrintWriter out = httpServletResponse.getWriter();
		/*while (i.hasNext()) {
			String key = (String) i.next();
			String str1 = ((String[]) params.get(key))[0];
		}*/
		try {
			this.Outfile = httpServletRequest.getParameter("outputtype").trim();
			if (httpServletRequest.getAttribute("PATH") != null) {
				path = (String) httpServletRequest.getAttribute("PATH");
			}

			populateData(httpServletRequest);

			if (httpServletRequest.getAttribute("TEMPPATH") != null) {
				filepath = (String) httpServletRequest.getAttribute("TEMPPATH");
				String filenamein = this.Timestamp + "_tempExcel.xlsx";
				inputFile = new File(filepath + filenamein);
			}
			if (this.Outfile.equalsIgnoreCase("CSV")) {
				filename = this.Timestamp + "_tempExcel.csv";
				this.filenamein = (this.Timestamp + "_tempExcel.xlsx");
				path = filepath + filename;
				File outputFile = new File(path);
				xlsxtocsv(inputFile, outputFile);

				if (isBulk) {
					String actualFile = httpServletRequest.getParameter("actualFileName");
					String bulkAnchorTag = "<a onclick='hidelink(this)' href="
							+ httpServletRequest.getContextPath() + "/RedirectorServlet?bulkpath="
							+ path + "> Click here to download : " + actualFile + "</a> ";
					bulkMap.put(bulkAnchorTag, httpServletRequest.getSession().getId());
					httpServletRequest.getSession().setAttribute("bulkMap", bulkMap);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);

					out.write(bulkAnchorTag);
				} else {
					httpServletRequest.getSession().setAttribute("tempfilepath", filepath);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);
					httpServletRequest.getSession().setAttribute("filenamein", this.filenamein);

					out.write("File created successfully. Click Download file");
				}

			} else if (this.Outfile.equalsIgnoreCase("TEXT")) {
				filename = this.Timestamp + "_tempExcel.txt";
				this.filenamein = (this.Timestamp + "_tempExcel.xlsx");
				path = filepath + filename;
				File outputFile = new File(path);
				xlsxtotext(inputFile, outputFile, httpServletRequest.getParameter("delimiter"));

				if (isBulk) {
					String actualFile = httpServletRequest.getParameter("actualFileName");
					String bulkAnchorTag = "<a onclick='hidelink(this)' href="
							+ httpServletRequest.getContextPath() + "/RedirectorServlet?bulkpath="
							+ path + "> Click here to download : " + actualFile + "</a> ";
					bulkMap.put(bulkAnchorTag, httpServletRequest.getSession().getId());
					httpServletRequest.getSession().setAttribute("bulkMap", bulkMap);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);

					out.write(bulkAnchorTag);
				} else {
					httpServletRequest.getSession().setAttribute("tempfilepath", filepath);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);
					httpServletRequest.getSession().setAttribute("filenamein", this.filenamein);

					out.write("File created successfully. Click Download file");
				}
			} else if (this.Outfile.equalsIgnoreCase("EXCEL")) {
				filename = this.Timestamp + "_tempExcel.xlsx";
				path = filepath + filename;
				if (isBulk) {
					String actualFile = httpServletRequest.getParameter("actualFileName");
					String bulkAnchorTag = "<a onclick='hidelink(this)' href="
							+ httpServletRequest.getContextPath() + "/RedirectorServlet?bulkpath="
							+ path + "> Click here to download : " + actualFile + "</a> ";
					bulkMap.put(bulkAnchorTag, httpServletRequest.getSession().getId());
					httpServletRequest.getSession().setAttribute("bulkMap", bulkMap);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);

					out.write(bulkAnchorTag);
				} else {
					httpServletRequest.getSession().setAttribute("tempfilepath", filepath);
					httpServletRequest.getSession().setAttribute("tempfilename", filename);
					httpServletRequest.getSession().setAttribute("filenamein", this.filenamein);

					out.write("File created successfully. Click Download file");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String actualFile = httpServletRequest.getParameter("actualFileName");
			out.write("<label style='color:red;font-weight:bold'>Error: File uploaded is not in required format.--></label>"
					+ e.getMessage() + actualFile);
		}
	}

	public void populateData(HttpServletRequest httpServletRequest) {
		try {
			String columnName = "";
			String columntype = "";
			int TotalNoOfRec = Integer.parseInt(httpServletRequest.getParameter("records")) + 1;
			String[] StrList = new String[TotalNoOfRec];
			int rowCount = Integer.parseInt(httpServletRequest.getParameter("rowCount"));
			String FileSeparator = httpServletRequest.getParameter("delimiter");
			String path = (String) httpServletRequest.getAttribute("PATH");
			int writeStatus = 0;
			for (int i = 1; i <= rowCount; i++) {
				columnName = httpServletRequest.getParameter("colName[" + i + "][colName]");
				columntype = httpServletRequest.getParameter("colName[" + i + "][colType]");
				String unique = httpServletRequest.getParameter("colName[" + i + "][unique]");
				if (unique == null) {
					unique = "no";
				}
				if (columntype.equals("Integer")) {
					String integerSelType = httpServletRequest.getParameter("colName[" + i
							+ "][integerseltype]");
					int between = 0;
					int and = 0;
					if (integerSelType.equalsIgnoreCase("Random")) {
						if ((httpServletRequest.getParameter("colName[" + i + "][between]") != null)
								&& (httpServletRequest.getParameter("colName[" + i + "][and]") != null)) {
							between = Integer.parseInt(httpServletRequest.getParameter("colName["
									+ i + "][between]"));
							and = Integer.parseInt(httpServletRequest.getParameter("colName[" + i
									+ "][and]"));
						} else {
							between = 1;
							if ((httpServletRequest.getParameter("colName[" + i + "][colLength]") == "")
									|| (httpServletRequest.getParameter("colName[" + i
											+ "][colLength]") == null)) {
								and = 99999;
							} else {
								int columnLength = Integer.parseInt(httpServletRequest
										.getParameter("colName[" + i + "][colLength]"));

								for (int j = 1; j <= Integer.toString(columnLength).length(); j++) {
									and += 9 * (0xA ^ j - 1);
								}
							}
						}
						StrList = generateInteger(TotalNoOfRec, between, and, integerSelType,
								unique, columnName);
					} else if (integerSelType.equalsIgnoreCase("Sequence")) {
						int start = 0;
						int step = 0;
						if ((httpServletRequest.getParameter("colName[" + i + "][start]") != null)
								&& (httpServletRequest.getParameter("colName[" + i + "][step]") != null)) {
							start = Integer.parseInt(httpServletRequest.getParameter("colName[" + i
									+ "][start]"));
							step = Integer.parseInt(httpServletRequest.getParameter("colName[" + i
									+ "][step]"));
						} else {
							start = 1;
							if ((httpServletRequest.getParameter("colName[" + i + "][colLength]") == "")
									|| (httpServletRequest.getParameter("colName[" + i
											+ "][colLength]") == null)) {
								step = 99999;
							} else {
								int columnLength = Integer.parseInt(httpServletRequest
										.getParameter("colName[" + i + "][colLength]"));

								for (int j = 1; j <= Integer.toString(columnLength).length(); j++) {
									step += 9 * (0xA ^ j - 1);
								}
							}
						}
						StrList = generateInteger(TotalNoOfRec, start, step, integerSelType,
								unique, columnName);
					}
				} else if (columntype.equals("Float")) {
					String floatSelType = httpServletRequest.getParameter("colName[" + i
							+ "][floatseltype]");
					int between = 0;
					int and = 0;
					if ((httpServletRequest.getParameter("colName[" + i + "][startrange]") != null)
							&& (httpServletRequest.getParameter("colName[" + i + "][step]") != null)) {
						between = Integer.parseInt(httpServletRequest.getParameter("colName[" + i
								+ "][startrange]"));
						and = Integer.parseInt(httpServletRequest.getParameter("colName[" + i
								+ "][endrange]"));
					} else {
						between = 1;
						if ((httpServletRequest.getParameter("colName[" + i + "][colLength]") == "")
								|| (httpServletRequest
										.getParameter("colName[" + i + "][colLength]") == null)) {
							and = 99999;
						} else {
							int columnLength = Integer.parseInt(httpServletRequest
									.getParameter("colName[" + i + "][colLength]"));

							for (int j = 1; j <= Integer.toString(columnLength).length(); j++) {
								and += 9 * (0xA ^ j - 1);
							}
						}
					}
					StrList = generateFloat(TotalNoOfRec, between, and, floatSelType, unique,
							columnName);
				} else if (columntype.equals("String")) {
					String stringFormat = "";
					String stringSelType = httpServletRequest.getParameter("colName[" + i
							+ "][stringseltype]");
					int stringLength = Integer.parseInt(httpServletRequest.getParameter("colName["
							+ i + "][colLength]"));
					stringFormat = httpServletRequest.getParameter("colName[" + i
							+ "][stringColFormat]");
					String modeType = "";

					if ((stringSelType.equalsIgnoreCase("Alphanumeric")) && (stringFormat == null))
						modeType = "ALPHANUMERIC";
					else if ((stringSelType.equalsIgnoreCase("Character Set"))
							&& (stringFormat == null))
						modeType = "ALPHA";
					else {
						modeType = "FORMAT";
					}
					StrList = generateString(TotalNoOfRec, stringLength, modeType, columnName,
							stringFormat);
				} else if (columntype.equals("Human Data")) {
					String humanSelType = httpServletRequest.getParameter("colName[" + i
							+ "][humanseltype]");
					int stringLength = Integer.parseInt(httpServletRequest.getParameter("colName["
							+ i + "][colLength]"));
					String nameSelType = "";
					if (humanSelType.equalsIgnoreCase("Email Address")) {
						String humanType = "email";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					} else if (humanSelType.equalsIgnoreCase("Bank Names")) {
						String humanType = "bank";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					} else if (humanSelType.equalsIgnoreCase("Card Type")) {
						String humanType = "card";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					}else if (humanSelType.equalsIgnoreCase("State")) {
						String humanType = "state";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					} else if (humanSelType.equalsIgnoreCase("City")) {
						String humanType = "city";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					}else if (humanSelType.equalsIgnoreCase("Pin Code")) {
						String humanType = "pin";
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					} else if (humanSelType.equalsIgnoreCase("Name")) {
						String humanType = "name";
						nameSelType = httpServletRequest.getParameter("colName[" + i
								+ "][nameFormat]");
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
						//start mukesh
					}else if (humanSelType.equalsIgnoreCase("Pan Card")) {
						String humanType = "Pan Card";
						nameSelType = httpServletRequest.getParameter("colName[" + i
								+ "][nameFormat]");
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					}else if (humanSelType.equalsIgnoreCase("Sign")) {
						String humanType = "sign";
						nameSelType = httpServletRequest.getParameter("colName[" + i
								+ "][nameFormat]");
						StrList = generateHumanData(TotalNoOfRec, stringLength, humanType,
								nameSelType, columnName, path);
					}
					//end mukesh
					httpServletRequest.setAttribute("pojodata", list);
					System.out.println("POJODATA======"+list);
					} 
				else if (columntype.equals("Date")) {
					String dateSelType = httpServletRequest.getParameter("colName[" + i
							+ "][dateseltype]");
					String StartDate = httpServletRequest.getParameter("colName[" + i
							+ "][startdate]");
					String EndDate = httpServletRequest.getParameter("colName[" + i + "][enddate]");
					StrList = generateDateData(TotalNoOfRec, StartDate, EndDate, dateSelType,
							unique, columnName);
				} else if (columntype.equals("Time")) {
					String timeSelType = httpServletRequest.getParameter("colName[" + i
							+ "][timeFormat]");
					String StartTime = httpServletRequest.getParameter("colName[" + i
							+ "][timefrom]");
					String EndTime = httpServletRequest.getParameter("colName[" + i + "][timeto]");
					StrList = generateTimeData(TotalNoOfRec, StartTime, EndTime, timeSelType,
							unique, columnName);
				} else if (columntype.equals("Date Time")) {
					String datetimeSelType = httpServletRequest.getParameter("colName[" + i
							+ "][dateTimeFormat]");
					String StartDateTime = httpServletRequest.getParameter("colName[" + i
							+ "][datetimefrom]");
					String EndDateTime = httpServletRequest.getParameter("colName[" + i
							+ "][datetimeto]");
					StrList = generateDateTimeData(TotalNoOfRec, StartDateTime, EndDateTime,
							datetimeSelType, columnName, unique);
				}
				String TEMPPATH = (String) httpServletRequest.getAttribute("TEMPPATH");
				writeDataFile(StrList, FileSeparator, writeStatus, TEMPPATH);
				writeStatus++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] generateDateTimeData(int records, String StartDateTime,
			String EndDateTime, String timeformat, String columnName, String Unique)
			throws Exception {
		List dates = new ArrayList();
		DateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date str_time = sdf1.parse(StartDateTime);
		Date end_time = sdf1.parse(EndDateTime);
		String timeFormat = "";
		String[] dateDataList = new String[records];
		String[] dateDataList1 = new String[records];
		int x = 0;
		int i = 0;

		if (timeformat.equalsIgnoreCase("dd/mm/yy hh:mm:ss AM/PM(12 hour)"))
			timeFormat = "dd/MM/yy hh:mm:ss a";
		else if (timeformat.equalsIgnoreCase("dd/mm/yy hh:mm:ss (24 hour)"))
			timeFormat = "dd/MM/yy HH:mm:ss";
		else if (timeformat.equalsIgnoreCase("dd/mm/yyyy hh:mm:ss AM/PM(12 hour)"))
			timeFormat = "dd/MM/yyyy hh:mm:ss a";
		else if (timeformat.equalsIgnoreCase("dd/mm/yyyy hh:mm:ss (24 hour)"))
			timeFormat = "dd/MM/yyyy HH:mm:ss";
		else if (timeformat.equalsIgnoreCase("dd-mm-yy hh:mm (24 hour)"))
			timeFormat = "dd-mm-yy hh:mm";
		else if (timeformat.equalsIgnoreCase("dd-mm-yy hh:mm AM/PM(12 hour)")) {
			timeFormat = "dd-mm-yy hh:mm a";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		long difference = (end_time.getTime() - str_time.getTime()) / (records - 2);
		long endTime = end_time.getTime();
		long curTime = str_time.getTime();
		while ((curTime <= endTime) && (x < records - 1)) {
			if (Unique.equalsIgnoreCase("yes")) {
				dates.add(new Date(curTime));
				x++;
				curTime += difference;
			} else {
				x++;
				if (curTime == endTime) {
					dates.add(new Date(curTime));
					curTime = str_time.getTime();
				} else {
					dates.add(new Date(curTime));
					curTime += difference;
				}
			}
		}
		dateDataList1[0] = columnName;
		int y = 1;
		for (i = 0; i < records - 1; i++) {
			Date lDate = (Date) dates.get(i);
			dateDataList[i] = sdf.format(lDate);
			dateDataList1[y] = dateDataList[i];
			y++;
		}

		return dateDataList1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] generateTimeData(int records, String StartDate, String EndDate,
			String timeformat, String Unique, String columnName) throws Exception {
		List dates = new ArrayList();
		DateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		Date str_time = sdf1.parse(StartDate);
		Date end_time = sdf1.parse(EndDate);
		String timeFormat = "";
		String[] dateDataList = new String[records];
		String[] dateDataList1 = new String[records];
		int x = 0;
		int i = 0;

		if (timeformat.equalsIgnoreCase("hh:mm (24 hour)"))
			timeFormat = "HH:mm";
		else if (timeformat.equalsIgnoreCase("hh:mm AM/PM"))
			timeFormat = "hh:mm a";
		else if (timeformat.equalsIgnoreCase("hh:mm:ss AM/PM"))
			timeFormat = "hh:mm:ss a";
		else if (timeformat.equalsIgnoreCase("hh:mm:ss (24 hour)")) {
			timeFormat = "HH:mm:ss a";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		long difference = (end_time.getTime() - str_time.getTime()) / (records - 2);
		long endTime = end_time.getTime();
		long curTime = str_time.getTime();
		while ((curTime <= endTime) && (x < records - 1)) {
			if (Unique.equalsIgnoreCase("yes")) {
				dates.add(new Date(curTime));
				x++;
				curTime += difference;
			} else {
				x++;
				if (curTime == endTime) {
					dates.add(new Date(curTime));
					curTime = str_time.getTime();
				} else {
					dates.add(new Date(curTime));
					curTime += difference;
				}
			}
		}
		dateDataList1[0] = columnName;
		int y = 1;
		for (i = 0; i < records - 1; i++) {
			Date lDate = (Date) dates.get(i);
			dateDataList[i] = sdf.format(lDate);
			dateDataList1[y] = dateDataList[i];
			y++;
		}

		return dateDataList1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] generateDateData(int records, String StartDate, String EndDate,
			String dateformat, String Unique, String columnName) throws Exception {
		String[] dateDataList = new String[records];
		List dates = new ArrayList();
		String startDate2 = "";
		String endDate2 = "";
		int x = 0;

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate1 = df.parse(StartDate);
		Date endDate1 = df.parse(EndDate);
		if (dateformat.equalsIgnoreCase("dd/MM/yyyy")) {
			DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		} else if (dateformat.equalsIgnoreCase("dd/MM/yy")) {
			DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		} else if (dateformat.equalsIgnoreCase("dd-MMM-yy")) {
			DateFormat df2 = new SimpleDateFormat("dd-MMM-yy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		} else if (dateformat.equalsIgnoreCase("dd-MM-yy")) {
			DateFormat df2 = new SimpleDateFormat("dd-MM-yy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		} else if (dateformat.equalsIgnoreCase("MMM dd,yyyy")) {
			DateFormat df2 = new SimpleDateFormat("MMM dd,yyyy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		} else if (dateformat.equalsIgnoreCase("MMMMM dd,yyyy")) {
			DateFormat df2 = new SimpleDateFormat("MMMMM dd,yyyy");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}

		}else if (dateformat.equalsIgnoreCase("YYYYMMDD")) {
			DateFormat df2 = new SimpleDateFormat("YYYYMMDD");
			startDate2 = df2.format(startDate1);
			endDate2 = df2.format(endDate1);
			Date startDate = df2.parse(startDate2);
			Date endDate = df2.parse(endDate2);
			long interval = 86400000L;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while ((curTime <= endTime) && (x <= records - 1)) {
				if (Unique.equalsIgnoreCase("yes")) {
					dates.add(new Date(curTime));
					curTime += interval;
					x++;
				} else {
					x++;
					if (curTime == endTime) {
						dates.add(new Date(curTime));
						curTime = startDate.getTime();
					} else {
						dates.add(new Date(curTime));
						curTime += interval;
					}
				}
			}
			dateDataList[0] = columnName;
			for (int i = 1; i < records; i++) {
				Date lDate = (Date) dates.get(i);
				dateDataList[i] = df2.format(lDate);
			}
		}

		return dateDataList;
	}

	@SuppressWarnings("rawtypes")
	public static String[] generateHumanData(int records, int length, String type,
			String nameformat, String columnName, String path) throws Exception {
		String[] HumanDataList = new String[records];
		FileInputStream file = null;
		int cellNo = 0;

		XSSFSheet sheet = null;
         
		file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);

		if (type.equalsIgnoreCase("email"))
			cellNo = 3;
		else if (type.equalsIgnoreCase("bank"))
			cellNo = 4;
		else if (type.equalsIgnoreCase("card"))
			cellNo = 5;
		else if (type.equalsIgnoreCase("city"))
			cellNo = 6;
		else if (type.equalsIgnoreCase("pin"))
			cellNo = 7;
		else if (type.equalsIgnoreCase("state"))
			cellNo = 9;
		else if (type.equalsIgnoreCase("sign"))
			cellNo = 10;
		else if (type.equalsIgnoreCase("name")) {
			if (nameformat.equalsIgnoreCase("First Name"))
				cellNo = 0;
			else if (nameformat.equalsIgnoreCase("Last Name"))
				cellNo = 1;
			else if (nameformat.equalsIgnoreCase("Full Name")) {
				cellNo = 2;
			}
		}
		int noofrows = 0;
		Iterator rowIterator = sheet.iterator();
		HumanDataList[0] = columnName;
		PanCardGenerating panCardGenerating=new PanCardGenerating();
	/*	if(type.equalsIgnoreCase("Pan Card"))
		{
			HumanDataList[0]="Pan Card";
		}*/
		while ((rowIterator.hasNext()) && (noofrows < records)) {
			Row row = (Row) rowIterator.next();
			if (noofrows != 0) {
				if(type.equalsIgnoreCase("Pan Card"))
				{
					Cell fcell=row.getCell(0);
					Cell lcell=row.getCell(1);
					//list.add(new PanCardPOJO(fcell.getStringCellValue(),lcell.getStringCellValue()) );
					HumanDataList[noofrows] = panCardGenerating.getPanCard(fcell.getStringCellValue(), lcell.getStringCellValue());
				}
				else{
				Cell cell = row.getCell(cellNo);
				Integer z = new Integer(cellNo);
				Integer y = new Integer(cellNo);
				if (cell != null && z.equals(7)) {					
					HumanDataList[noofrows] = String.valueOf(cell.getNumericCellValue());
				}
				else if(cell != null && y.equals(10) ){
					HumanDataList[noofrows] = String.valueOf(cell.getNumericCellValue()+".jpg").replace(".0","");
					
				}
				else{
					HumanDataList[noofrows] = cell.getStringCellValue();
				}
				}
				
			}
			noofrows++;
		}
		
		return HumanDataList;
	}

	public static String[] generateString(int records, int length, String mode1, String columnName,
			String stringFormat) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String[] AlphaCheckList = new String[records];
		String characters = "";
		String character = "";

		if (mode1 == "ALPHA") {
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		} else if (mode1 == "ALPHANUMERIC") {
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		} else if (mode1 == "FORMAT") {
			AlphaCheckList[0] = columnName;
			for (int i = 1; i < records; i++) {
				AlphaCheckList[i] = stringFormat;
			}
		}
		if ((mode1 == "ALPHA") || (mode1 == "ALPHANUMERIC")) {
			character = characters;
			int charactersLength = characters.length();
			AlphaCheckList[0] = columnName;
			for (int i = 1; i < records; i++) {
				characters = character;
				for (int j = 0; j < length; j++) {
					double index = Math.random() * charactersLength;
					buffer.append(characters.charAt((int) index));
				}
				AlphaCheckList[i] = buffer.toString();
				buffer.delete(0, buffer.length());
			}
		}
		return AlphaCheckList;
	}

	public static String[] generateInteger(int Records, int min, int max, String formatType,
			String Unique, String columnName) {
		String[] intList = new String[Records];
		int[] integerList = new int[Records];
		int stat = 0;
		if (formatType.equalsIgnoreCase("Random")) {
			Random randomGenerator = new Random();
			if (Unique.equalsIgnoreCase("yes")) {
				intList[0] = columnName;
				for (int i = 1; i < Records; i++) {
					int randomInt = randomGenerator.nextInt(max + 1 - min) + min;
					stat = 0;
					while (stat < Records) {
						if (integerList[stat] == randomInt) {
							i--;
							break;
						}
						stat++;
					}

					if (stat == Records) {
						integerList[i] = randomInt;
					}

				}

				for (int i = 1; i < integerList.length; i++)
					intList[i] = Integer.toString(integerList[i]);
			} else if (Unique.equalsIgnoreCase("no")) {
				intList[0] = columnName;
				for (int i = 1; i < Records; i++) {
					int randomInt = randomGenerator.nextInt(max + 1 - min) + min;
					intList[i] = Integer.toString(randomInt);
				}
			}
		}
		if (formatType.equalsIgnoreCase("Sequence")) {
			intList[0] = columnName;
			int minval = 0;
			for (int idx = 1; idx < Records; idx++) {
				minval = min + max * (idx - 1);
				intList[idx] = Integer.toString(minval);
			}
		}
		return intList;
	}

	public static String getRandomValue(Random random, int lowerBound, int upperBound,
			int decimalPlaces) {
		if ((lowerBound < 0) || (upperBound <= lowerBound) || (decimalPlaces < 0)) {
			throw new IllegalArgumentException("Put error message here");
		}

		double dbl = (random == null ? new Random() : random).nextDouble()
				* (upperBound - lowerBound) + lowerBound;
		return String.format("%." + decimalPlaces + "f", new Object[] { Double.valueOf(dbl) });
	}

	public static String[] generateFloat(int Records, int min, int max, String formatType,
			String Unique, String columnName) {
		String[] list = new String[Records];
		String listItem = "";
		Random rnd = new Random();
		int n = 0;
		list[0] = columnName;
		int decStr = 0;
		if (formatType.equalsIgnoreCase("0.0"))
			decStr = 1;
		if (formatType.equalsIgnoreCase("0.00"))
			decStr = 2;
		if (formatType.equalsIgnoreCase("0.000"))
			decStr = 3;
		if (formatType.equalsIgnoreCase("0.0000"))
			decStr = 4;
		if (formatType.equalsIgnoreCase("0.00000"))
			decStr = 5;
		if (formatType.equalsIgnoreCase("0.000000"))
			decStr = 6;
		for (int l = 1; l < Records; l++) {
			listItem = getRandomValue(rnd, min, max, decStr);
			if (Unique.equalsIgnoreCase("yes")) {
				while (n < 10) {
					if (list[n] == listItem) {
						l--;
						break;
					}
					n++;
				}

				if (Records == n)
					list[l] = listItem;
			} else {
				list[l] = listItem;
			}
		}

		return list;
	}

	public void writeDataFile(String[] newArray, String delimit, int status, String TEMPPATH)
			throws IOException {
		FileOutputStream file = null;
		String tempPath = TEMPPATH + this.Timestamp + "_tempExcel.xlsx";
		try {
			if (status == 0) {
				XSSFSheet sheet = null;

				file = new FileOutputStream(tempPath);
				XSSFWorkbook workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("tempTab");

				for (int i = 0; i < newArray.length; i++) {
					XSSFRow row = sheet.createRow((short) i);
					XSSFCell cell = row.createCell(status);
					cell.setCellType(1);
					cell.setCellValue(newArray[i]);
				}
				workbook.write(file);
			} else {
				FileInputStream file_read = new FileInputStream(tempPath);
				XSSFWorkbook my_xls_workbook = new XSSFWorkbook(file_read);
				XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);

				for (int i = 0; i < newArray.length; i++) {
					XSSFRow row = my_worksheet.getRow((short) i);
					XSSFCell cell = row.getCell(status);
					if (cell == null)
						cell = row.createCell(status);
					cell.setCellType(1);
					cell.setCellValue(newArray[i]);
				}
				FileOutputStream file1 = new FileOutputStream(tempPath);
				my_xls_workbook.write(file1);
				file1.close();
				file_read.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (IOException localIOException1) {
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void xlsxtocsv(File inputFile, File outputFile) {
		StringBuffer data = new StringBuffer();
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);

			XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));

			XSSFSheet sheet = wBook.getSheetAt(0);

			Iterator rowIterator = sheet.iterator();
			Cell cell=null;
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();

				Iterator cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					 cell = (Cell) cellIterator.next();
                         switch (cell.getCellType()) {
						case 4:
							data.append(cell.getBooleanCellValue() + ",");
							break;
						case 0:
							data.append(cell.getNumericCellValue() + ",");
							break;
						case 1:
							data.append(cell.getStringCellValue() + ",");
							break;
						case 3:
							data.append(",");
							break;
						case 2:
						default:
							data.append(cell + ",");
					}
                  }
                  
				
						
				data.append("\r\n");
			}
			
			fos.write(data.toString().getBytes());
			fos.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void xlsxtotext(File inputFile, File outputFile, String delimiter) throws IOException {
		FileWriter output = null;
		BufferedWriter writer = null;
		FileInputStream file = null;
		StringBuilder sb = new StringBuilder("");
		try {
			XSSFSheet sheet = null;
			file = new FileInputStream(inputFile);

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			sheet = workbook.getSheetAt(0);

			output = new FileWriter(outputFile);
			writer = new BufferedWriter(output);

			Iterator rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();
					sb.append(cell.toString()).append(delimiter);
				}

				writer.write(sb.toString().substring(0, sb.toString().length() - 1));
				writer.newLine();
				sb.setLength(0);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (output != null)
				try {
					writer.close();
					output.close();
				} catch (IOException localIOException) {
				}
			file.close();
		}
	}
}
