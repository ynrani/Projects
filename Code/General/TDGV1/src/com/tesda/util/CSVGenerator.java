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

import java.util.ArrayList;
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
	
	
	public static String getCSVForObjects(Map<Integer, String> colvalMap, Long count, List<Object[]> list,List<String> listSequenceOfColumns){
		StringBuffer sb = new StringBuffer();
		/*for (int iSize = 1; iSize<colvalMap.size();iSize++) {
			sb.append(colvalMap.get(iSize));
			sb.append('~');
		}*/
		//sb = sb.deleteCharAt(sb.length() - 1);
		//sb.append(System.getProperty("line.separator"));
		//below code is enhance for IDFC bank 
		/*if(listSequenceOfColumns != null && !listSequenceOfColumns.isEmpty()){
			List<String> listSequence = new ArrayList<String>();
			for (int iSize = 1; iSize<=colvalMap.size();iSize++) {
				listSequence.add(colvalMap.get(iSize));
			}
			
			for (int i = 0; i < list.size(); i++) {
				for (int iLength=0;iLength<list.get(i).length;iLength++) {
					if(listSequence.contains(listSequenceOfColumns.get(iLength))){
						sb.append(listSequenceOfColumns.get(iLength)+"_"+(String.valueOf(list.get(iLength)[listSequence.indexOf(listSequenceOfColumns.get(iLength))])));
						//sb.append('~');
						sb.append(',');
					}					
				}
				sb = sb.deleteCharAt(sb.length() - 1);
				sb.append(System.getProperty("line.separator"));
			}
		}else{
		for (int i = 0; i < list.size(); i++) {
			for (int iLength=0;iLength<list.get(i).length;iLength++) {
				sb.append(colvalMap.get(iLength+1)).append('_').append(list.get(i)[iLength]);
				sb.append(',');
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
		}
		}*/
		String regex = "\\d+";
		if(listSequenceOfColumns != null && !listSequenceOfColumns.isEmpty()){
			List<String> listSequence = new ArrayList<String>();
			for (int iSize = 1; iSize<=colvalMap.size();iSize++) {
				listSequence.add(colvalMap.get(iSize));
			}
			for(int iseq =0 ;iseq<listSequenceOfColumns.size();iseq++){
				sb.append(listSequenceOfColumns.get(iseq));
				sb.append(',');
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < list.size(); i++) {
				for (int iLength=0;iLength<list.get(i).length;iLength++) {
					if(listSequence.contains(listSequenceOfColumns.get(iLength))){
						/*if(i == 0){
							sb.append(listSequenceOfColumns.get(iLength));
						}else{*/
						//sb.append((String.valueOf(list.get(iLength)[listSequence.indexOf(listSequenceOfColumns.get(iLength))])));
							if(!String.valueOf(list.get(i)[listSequence.indexOf(listSequenceOfColumns.get(iLength))]).trim().matches(regex))
								sb.append("\"").append(String.valueOf(list.get(i)[listSequence.indexOf(listSequenceOfColumns.get(iLength))])).append("\"");
								else
									sb.append(String.valueOf(list.get(i)[listSequence.indexOf(listSequenceOfColumns.get(iLength))]).trim());	
						//}
						//sb.append('~');
						sb.append(',');
					}					
				}
				sb = sb.deleteCharAt(sb.length() - 1);
				sb.append(System.getProperty("line.separator"));
			}
		}/*else{
		for (int i = 0; i < list.size(); i++) {
			for (int iLength=0;iLength<list.get(i).length;iLength++) {
				if()
				sb.append(colvalMap.get(iLength+1)).append('_').append(list.get(i)[iLength]);
				sb.append(',');
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
		}
		}*/
		
		return sb.toString();
	}
	
	
	public static String getCSVForList(List<Object[]> list){
		StringBuffer sb = new StringBuffer();
		/*for (int iSize = 1; iSize<colvalMap.size();iSize++) {
			sb.append(colvalMap.get(iSize));
			sb.append('~');
		}*/
		//sb = sb.deleteCharAt(sb.length() - 1);
		//sb.append(System.getProperty("line.separator"));
		int count = list.size();
		String regex = "\\d+";
		for (int i = 0; i < count; i++) {
			for (int iLength=0;iLength<list.get(0).length;iLength++) {
				if(!list.get(i)[iLength].toString().trim().matches(regex)&& i !=0)
				sb.append("\"").append(list.get(i)[iLength]).append("\"");
				else
					sb.append(list.get(i)[iLength].toString().trim());	
				//sb.append(list.get(0)[iLength]).append('_').append(list.get(i)[iLength]);
				sb.append(',');
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
