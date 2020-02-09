/*
 * Object Name : CSVGenerator.java
 * Modification Block
 * ---------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ---------------------------------------------------------------------
 * 	1.	  vkrish14		Jun 15, 2015			NA             Created
 * ---------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tesda.util;

import java.util.List;
import java.util.Map;

public class CSVGenerator{
	public static String getCSV(Map<String, String> colvalMap, Long count, List<List<String>> list){
		StringBuffer sb = new StringBuffer();
		for (String key : colvalMap.keySet()) {
			sb.append(key);
			sb.append(',');
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < count; i++) {
			for (String value : list.get(i)) {
				sb.append(value);
				sb.append(',');
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
