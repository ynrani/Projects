package com.tdg;

public class PanCardPOJO {
	String fName;
	String lName;
	
	public PanCardPOJO(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String toString()
	{
		return "fname=="+fName+"   "+"lName=="+lName;
	}

}
