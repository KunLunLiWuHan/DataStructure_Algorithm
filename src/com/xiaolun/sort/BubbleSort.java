package com.xiaolun.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
	public static void main(String[] args) {
		//测试1：简单数据排列
		int arr[] = {3, 9, -1, 20, 7}; //从小到大排列
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		//使用封装的方法
//		System.out.println("排序后");
//		bubbleSort(arr);
//		System.out.println(Arrays.toString(arr));

			/*
		// 第一趟排序，就是将最大的数排在最后
		for (int j = 0; j < arr.length - 1 - 0; j++) {
			// 如果前面的数比后面的大就交换
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
		System.out.println("第二趟排序后的数组");
		System.out.println(Arrays.toString(arr));

		// 第二趟排序，就是将第二大的数排在倒数第二位
		for (int j = 0; j < arr.length - 1 - 1 ; j++) {
			// 如果前面的数比后面的大就交换
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
		System.out.println("第二趟排序后的数组");
		System.out.println(Arrays.toString(arr));
		*/

		//测试2：排序速度O(n^2), 创建一个80000个随机的数据
//		int[] arr = new int[80000];
//		for (int i = 0; i < 80000; i++) {
//			arr[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000)的数
//		}
//
//		Date data1 = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1Str = simpleDateFormat.format(data1);
//		System.out.println("排序前的时间为=" + date1Str);
//
//		//测试冒泡排序
//		bubbleSort(arr);
//
//		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("排序后的时间=" + date2Str);
	}

	// 将前面的冒泡算法，封装成一个方法
	public static void bubbleSort(int[] arr) {
		// 时间复杂度O(n^2),
		int temp = 0; // 临时变量
		boolean flag = false; // 标识变量，自己是否进行过交换
		for (int i = 0; i < arr.length - 1; i++) {
			//数组进行(arr.length-1)趟排序
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			//System.out.println("第" + (i + 1) + "趟排序后的数组");
			//System.out.println(Arrays.toString(arr));

			if (!flag) { // 在一趟排序中，一次交换都没有发生过
				break;
			} else {
				flag = false; // 重置flag,进行下次判断。
			}
		}
	}
}
