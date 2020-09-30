package com.xiaolun.sort;

import java.util.Arrays;

public class ShellSort {
	public static void main(String[] args) {
		//测试1：从小到大排序
		int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		//希尔排序的第一轮，将10个数据分成5组
//		int temp = 0;
//		for (int i = 5; i < arr.length; i++) {
//			// 遍历各组中的所有元素（共5组，每组有2个元素），步长为5
//			for (int j = i - 5; j >= 0; j -= 5) {
//				if (arr[j] > arr[j + 5]) {
//					temp = arr[j];
//					arr[j] = arr[j + 5];
//					arr[j + 5] = temp;
//				}
//			}
//		}
//		System.out.println("希尔排序1轮后=" + Arrays.toString(arr));
//
//		for (int i = 2; i < arr.length; i++) {
//			for (int j = i - 2; j >= 0; j -= 2) {
//				if (arr[j] > arr[j + 2]) {
//					temp = arr[j];
//					arr[j] = arr[j + 2];
//					arr[j + 2] = temp;
//				}
//			}
//		}

		//测试2:运行时间，比插入排序时间满了
//		int[] arr = new int[8000000];
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 8000000);
//		}
//
//		Date data1 = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1Str = simpleDateFormat.format(data1);
//		System.out.println("排序前时间=" + date1Str);
//
//		//shellSort(arr); //交换方式
//		shellSort2(arr);//移位方式
//
//		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("排序后时间=" + date2Str);
	}

	//对有序序列在插入时采用交换法
	public static void shellSort(int[] arr) {
		int temp = 0;
		int count = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				/**
				 * 遍历各组中所有元素（共gap组，每组个元素），步长gap
				 * {1（交换）, 5, 3(交换), 6, 0（交换）, 8, 9, 4, 7, 2}
				 * j -= gap 作用是将3，0交换后，再将1，0进行比较，然后将
				 * 0放到前端,如下：
				 * {0(), 5, 1(), 6, 3(), 8, 9, 4, 7, 2}
				 */
				for (int j = i - gap; j >= 0; j -= gap) {
					// 如果当前元素大于加上步长后的那个元素，进行交换
					if (arr[j] > arr[j + gap]) {
						//发现一个交换一个，耗费时间很长
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
		}
		
		/*
		 //希尔排序的第一轮，将10个数据分成5组
		for (int i = 5; i < arr.length; i++) {
			// 遍历各组中的所有元素（共5组，每组有2个元素），步长为5
			for (int j = i - 5; j >= 0; j -= 5) {
				if (arr[j] > arr[j + 5]) {
					temp = arr[j];
					arr[j] = arr[j + 5];
					arr[j + 5] = temp;
				}
			}
		}
		System.out.println("希尔排序1轮后=" + Arrays.toString(arr));//
		
		// 希尔排序第2轮
		// 将10个数据分成5/2 = 2组
		for (int i = 2; i < arr.length; i++) {
			for (int j = i - 2; j >= 0; j -= 2) {
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
				}
			}
		}
		System.out.println("希尔排序2轮后=" + Arrays.toString(arr));//
		*/
	}

	//对交换式的希尔排序进行优化 -》移位法，运行时间很快
	public static void shellSort2(int[] arr) {
		// 增量gap，并逐步缩小增量
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素，逐个对其所在的组进行直接插入排序
			for (int i = gap; i < arr.length; i++) {
				int j = i; //待插入的下标保存起来
				int temp = arr[j]; //记录要插入的数
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						//移动
						arr[j] = arr[j - gap];
						j -= gap;
					}
					//当退出while后，就给temp找到插入的位置
					arr[j] = temp;
				}
			}
		}
	}
}
