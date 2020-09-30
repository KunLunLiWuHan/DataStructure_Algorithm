package com.xiaolun.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
	public static void main(String[] args) {
		//从小到大排序 6, 9, 2, 4, 5, 1, 8, 7
//		int[] arr = {-9,78,0,23,-567,70};
		int[] arr = {2,1};

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
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
//
//		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("排序后时间=" + date2Str);
	}

	public static void quickSort(int[] arr, int left, int right) {
		int l = left; //左下标
		int r = right; //右下标
		//pivot 中轴值
		int pivot = arr[(left + right) / 2];
		int temp = 0; //临时变量，作为交换时使用
		//while循环的目的就是让比pivot值小的放到左边，大的放到右边
		while (l < r) {
			//在pivot左边一直找，找到大于等于pivot值，才退出
			while (arr[l] < pivot) {
				l += 1;
			}
			//在pivot右边一直找，找到小于等于pivot值，才退出
			while (arr[r] > pivot) {
				r -= 1;
			}
			/**
			 * 如果 1 >= r ,说明pivot左右两边的值，已经按照左边全部是
			 * 小于等于pivot值，右边全部是大于等于pivot值
			 */
			if (l >= r) {
				break;
			}

			//交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			//如果交换完，发现arr[l] == pivot值相等，r--，前移
			if (arr[l] == pivot) {
				r -= 1;
			}
			//如果交换完后，发现arr[r] == pivot值相等， l++后移
			if (arr[r] == pivot) {
				l += 1;
			}
		}

		// 如果 l == r, 必须l++, r--,将中轴值去掉，否则出现栈溢出。
		if (l == r) {
			l += 1;
			r -= 1;
		}
		//向左递归 left第一次递归时为0
		if (left < r) {
			quickSort(arr, left, r);
		}
		//向右递归 第一次递归时为 length - 1
		if (right > l) {
			quickSort(arr, l, right);
		}
	}
}

