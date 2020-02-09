package com.tdg;

import java.util.Random;

public class PanCardUtil {
	String CHAR_LIST=null;
	
	//getting First three char
	public String getFistThreeChar(){
	CHAR_LIST ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int RANDOM_STRING_LENGTH = 3;
	return generateRandomNumOrChar(CHAR_LIST,RANDOM_STRING_LENGTH);
	}
	
	//generating random no or char
	public String generateRandomNumOrChar(String CHAR_LIST,int RANDOM_STRING_LENGTH){
	StringBuffer randStr = new StringBuffer();
	for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	int number = getRandomNumber();
	char ch = CHAR_LIST.charAt(number);
	randStr.append(ch);
	}
	return randStr.toString();
	}
	
	private int getRandomNumber() {
	int randomInt = 0;
	Random randomGenerator = new Random();
	randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	if (randomInt - 1 == -1) {
	return randomInt;
	} else {
	return randomInt - 1;
	}
	}
	
//geting domain char
	public String getDomainChar(){
		String st="P";
	/*String st=new String();
	List <String>li=new ArrayList<String>();
	li.add("P");
	li.add("A");
	li.add("B");
	li.add("C");
	li.add("F");
	li.add("G");
	li.add("H");
	li.add("L");
	li.add("j");	
	li.add("T ");
	while(true){
	Scanner s=new Scanner(System.in);
	System.out.println("Enter Domain");
	if(s.hasNext()){
	st=s.next().toString();
	}
	if(li.contains(st)){
	break;
	}
	} */
	return st;
	}
	
	//geting firt char of sur name
	public String getFistCharOfSurName(String lname){
	//String st=new String();
	//Scanner s=new Scanner(System.in);
	//System.out.println("Enter Your Name");
	/*if(s.hasNext()){
	st=s.nextLine().toString();
	}*/
	//String str[]=st.split("");
	//return lname[lname.length()-1].substring(0, 1);
		return lname.substring(0, 1);
	}
	
	//
	public String getNextFourRandomNumber(){
	CHAR_LIST ="0123456789";
	int RANDOM_STRING_LENGTH = 4;
	return generateRandomNumOrChar(CHAR_LIST,RANDOM_STRING_LENGTH);
	}
	
	public String getRandomLastChar(){
	CHAR_LIST ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int RANDOM_STRING_LENGTH = 1;
	return generateRandomNumOrChar(CHAR_LIST,RANDOM_STRING_LENGTH);
	}
	}

	

