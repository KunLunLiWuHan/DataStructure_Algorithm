package com.xiaolun.sort;

import java.util.Arrays;

//基数排序
public class RadixSort {
	public static void main(String[] args) {
		int arr[] = {53, 3, 542, 748, 14, 214};
		radixSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void radixSort(int[] arr) {
		//1.得到数组中最大的数的位数
		int max = arr[0]; //假设第一个数就是最大数
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max + "").length();

		//定义一个二维数组，表示10个桶，每一个桶就是一个一维数组
		//1. 二维数组包含10个一维数组
		//2. 为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定义为arr.length
		//3. 基数排序使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];

		/**
		 * 为了记录每个桶中，实际放了多少个数据，我们定义一个一维数组
		 * 来记录每个桶的每次放入数据个数
		 * 比如：
		 * bucketElementCounts[0]记录的就是bucket[0]桶放入数据的个数
		 */
		int[] bucketElementCounts = new int[10];

		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			//（针对每个元素的对应位进行排序处理），第一个是个位，依次是十位，百位
			for (int j = 0; j < arr.length; j++) {
				//取出每个元素对应位的值
				int digitOfElement = arr[j] / n % 10;
				//放入对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			//按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
			int index = 0;
			//遍历每一桶，并将桶中的数据放入到原数组
			for (int k = 0; k < bucketElementCounts.length; k++) {
				//如果桶中有数据，我们才放入原数组
				if (bucketElementCounts[k] != 0) {
					//循环该桶即第K个桶（即第k个一维数组），放入
					for (int l = 0; l < bucketElementCounts[k]; l++) {
						//将元素取出放入到arr
						arr[index++] = bucket[k][l];
					}
				}
				//第i+1轮处理后，需要将每个bucketElementCounts[k] = 0 ！！
				bucketElementCounts[k] = 0;
			}
		}
		
		/*
		//第1轮（针对每个元素的个位进行排序处理）
		for(int j = 0; j < arr.length; j++) {
			//取出每个元素的个位的值
			int digitOfElement = arr[j] / 1 % 10;
			//放入对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		//按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
		int index = 0;
		for(int k = 0; k < bucketElementCounts.length; k++) {
			if(bucketElementCounts[k] != 0) {
				for(int l = 0; l < bucketElementCounts[k]; l++) {
					arr[index++] = bucket[k][l];
				}
			}
			bucketElementCounts[k] = 0;
		}
		System.out.println("第一轮处理后 arr =" + Arrays.toString(arr));

	  //第2轮（针对每个元素的十位进行排序处理）
		for (int j = 0; j < arr.length; j++) {
			int digitOfElement = arr[j] / 10  % 10; //748 / 10 => 74 % 10 => 4
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		index = 0;
		for (int k = 0; k < bucketElementCounts.length; k++) {
			if (bucketElementCounts[k] != 0) {
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					arr[index++] = bucket[k][l];
				}
			}
			bucketElementCounts[k] = 0;
		}
		System.out.println("第一轮处理后 arr =" + Arrays.toString(arr));
		*/
	}
}
