package com.tdg;

public class PanCardGenerating {
	// @mukesh  

	public String getPanCard(String fName, String lname) {
		String str ="";
		StringBuffer panCardNumber = new StringBuffer();
		PanCardUtil panCardUtil = new PanCardUtil();
		panCardNumber.append(panCardUtil.getFistThreeChar());
		panCardNumber.append(panCardUtil.getDomainChar());
		panCardNumber.append(panCardUtil.getFistCharOfSurName(lname));
		panCardNumber.append(panCardUtil.getNextFourRandomNumber());
		panCardNumber.append(panCardUtil.getRandomLastChar());
		return str+panCardNumber;
		
	}
}
