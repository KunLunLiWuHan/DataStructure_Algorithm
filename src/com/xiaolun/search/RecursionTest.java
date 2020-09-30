package com.xiaolun.search;

/**
 * @time: 2020-09-28 16:10
 * @author: likunlun
 * @description: 11
 */
public class RecursionTest {
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}

	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
}