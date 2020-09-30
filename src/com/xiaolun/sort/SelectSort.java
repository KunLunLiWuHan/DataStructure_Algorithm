package com.xiaolun.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

//选择排序
public class SelectSort {
	public static void main(String[] args) {
		//测试1：从小到大排列
//		int[] arr = {101, 34, 119, 1};

			/*
		//逐步推导
		//第一轮
		//原始数据 	101, 34, 119, 1
		//排序后 :   1, 34, 119, 101
		//算法 先简单，后复杂（把复杂算法拆解成简单的问题，逐步解决）
		int minIndex = 0;
		int min = arr[0];
		for (int j = 0 + 1; j < arr.length; j++) {
			if (min > arr[j]) {
				min = arr[j];
				minIndex = j;
			}
		}

		if (minIndex != 0) {
			arr[minIndex] = arr[0];
			arr[0] = min;
		}
		System.out.println("第1轮后~~");
		System.out.println(Arrays.toString(arr));// 1, 34, 119, 101


		//第2轮
		minIndex = 1;
		min = arr[1];
		for (int j = 1 + 1; j < arr.length; j++) {
			if (min > arr[j]) {
				min = arr[j];
				minIndex = j;
			}
		}
		if(minIndex != 1) {
			arr[minIndex] = arr[1];
			arr[1] = min;
		}

		System.out.println("第2轮后");
		System.out.println(Arrays.toString(arr));// 1, 34, 119, 101
	}
*/
		//测试2：选择排序速度，创建80000随机数，比冒泡快
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}

		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前时间=" + date1Str);

		selectSort(arr);

		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序前时间=" + date2Str);
	}

	public static void selectSort(int[] arr) {
		//时间复杂度O(n^2)，使用for循环解决
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { // 说明假定的最小值，并不是最小
					min = arr[j]; // 重置min
					minIndex = j; // 重置minIndex（保存最小的值的索引值）
				}
			}

			/**
			 * 交换的时候，做一个判断，进行优化
			 * 因为，当经过上面的查询最小值后，发现最小值就是其本身，此时
			 * 不需要进行交换
			 */
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}

			//System.out.println("第"+(i+1)+"轮后~~");
			//System.out.println(Arrays.toString(arr));// 1, 34, 119, 101
		}
	}
}