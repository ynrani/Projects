package com.itap.util;

public class MethCalculation {

	public static double getByteToGB(long byteData) {
		double returnValue = 0;

		returnValue = byteData / 1000000 * 1000 * 100000;

		return returnValue;
	}

	public static void main(String[] args) {

		double l = MethCalculation.getByteToGB(7890944000l);
		// 7.890944
		System.out.println(l);

		// "availablePhysicalMemory" : 1309446144,
		// "availableSwapSpace" : 7905521664,
		// "totalPhysicalMemory" : 8589467648,
		// "totalSwapSpace" : 17177034752

		// show 7.35

		// 1.292357632
		// 8.589467648

	}
}
