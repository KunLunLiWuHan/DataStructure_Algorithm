package com.xiaolun.sort;

//插入排序
public class InsertSort {
	public static void main(String[] args) {
		//测试1：从小到大排序
		int[] arr = {101, 34, 119, 1};

		//测试2：速度测试
//		int[] arr = new int[80000];
//		for (int i = 0; i < 80000; i++) {
//			arr[i] = (int) (Math.random() * 8000000);
//		}
//
//		Date data1 = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1Str = simpleDateFormat.format(data1);
//		System.out.println("排序前时间=" + date1Str);
//
//		insertSort(arr); //插入排序算法
//
//		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("排序后时间=" + date2Str);
	}

	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			//定义待插入的数
			insertVal = arr[i];
			insertIndex = i - 1; // 即arr[1]前面的这个数的下标

			/**
			 * 给insertVal找到插入的位置
			 * 1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
			 * 2. insertVal < arr[insertIndex] 待插入的数，还没找到插入位置
			 *  3.将arr[insertIndex] 后移
			 *  比如插入34时：
			 *  {101, 34, 119, 1} => {101, 101, 119, 1} =>{34, 101, 119, 1}
			 *  插入1时：
			 *  {34, 101, 119, 1} => {34, 101, 119, 119} => {34, 101, 101, 119}
			 *  {34, 34, 101, 1} => {1,34, 101, 119}
			 */
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}

			/**
			 * 当退出while循环时,说明插入的位置找到，insertIndex + 1
			 * 判断是否需要赋值
			 */
			if (insertIndex + 1 == i) {
				//不符合while循环，直接跳出循环了
			} else {
				arr[insertIndex + 1] = insertVal;
			}
		}
		
		/*
		//逐步推导
		//第1轮：{101, 34, 119, 1};  => {34, 101, 119, 1}
		//{101, 34, 119, 1}; => {101,101,119,1}
		//定义待插入的数
				//定义待插入数 arr[1]（34）
		int insertVal = arr[1];
		//定义待插入数的索引，即arr[1]前面这个数的下标
		int insertIndex = 1 - 1;

		//{101, 34, 119, 1} =》 {101, 101, 119, 1}
		//insertVal 保存着34这个数据
		while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];
			insertIndex--; //arr[1]和前面的数相比较
		}

		arr[insertIndex + 1] = insertVal;

		System.out.println("第1轮插入：");
		System.out.println(Arrays.toString(arr));
		
		//第2轮
		insertVal = arr[2];
		insertIndex = 2 - 1; 
		
		while(insertIndex >= 0 && insertVal < arr[insertIndex] ) {
			arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
			insertIndex--;
		}
		
		arr[insertIndex + 1] = insertVal;
		System.out.println("第2轮插入");
		System.out.println(Arrays.toString(arr));
		*/
	}
}
