package com.bvm.interview2;

public class PrintNumbers {

	static int n = 5;

	public static void main(String[] args) {

		for (int i = 1; i < n; i++) {
			int k = n / i;
			StringBuilder sb = new StringBuilder("");
			for (int j = 1; j <= k; j++) {
				if (j == 1) {
					sb.append(i);
				} else {
					sb.append(" + " + i);
				}
			}
			if (i * k < n) {
				display(n - (i * k), sb.toString());
			} else {
				System.out.println(sb.toString());
			}
		}
	}

	public static void display(int n, String str) {

		for (int i = 1; i <= n; i++) {
			StringBuilder sb = new StringBuilder(str);
			int k = n / i;
			for (int j = 1; j <= k; j++) {
				sb.append(" + " + i);
			}
			if (i * k < n) {
				display(n - (i * k), sb.toString());
			} else if (i * k == n) {
				System.out.println(sb.toString());
			}

		}
	}
}
