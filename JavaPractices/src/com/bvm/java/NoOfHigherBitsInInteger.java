package com.bvm.java;


public class NoOfHigherBitsInInteger {

	int countBits(int n) {
		int result = 0, temp = 0;
		while (n != 0) {
			result++;
			temp = n--;
			n = n & temp;
			System.out.println(result+" === "+temp+" === "+n);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("Result:= "
				+ new NoOfHigherBitsInInteger().countBits(15));
	}

}
