package com.xiaolun.sort;

import java.util.Arrays;

//归并排序
public class MergetSort {
	public static void main(String[] args) {
		//测试1：从小到大排序
//		int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
		int arr[] = {8, 4, 5, 7};
		//归并排序，需要额外的时间开销
		int temp[] = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1, temp);
		System.out.println(Arrays.toString(arr));

		//测试2：运行时间测试
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
//		int temp[] = new int[arr.length]; //?鲢????????????????
//		mergeSort(arr, 0, arr.length - 1, temp);
//
//		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("排序后时间=" + date2Str);
	}

	/**
	 * 分+合方法
	 * 1、首先分解到的是 8 4 ，然后是 5 7
	 * 2、分解到8 4 之后，会在 if (left < right)中不满足条件而跳出循环，即此时已经弹出栈
	 * 然后程序会继续往下运行 mergeSort(arr, mid + 1, right, temp);
	 * @param arr
	 * @param left
	 * @param right
	 * @param temp
	 */
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2; //中间索引
			//向左递归进行分解
			mergeSort(arr, left, mid, temp);
			//向右递归进行分解,分解到最后 8 4 5 7 1 3 6 2
			mergeSort(arr, mid + 1, right, temp);
			//合并
			merge(arr, left, mid, right, temp);
		}
	}

	/**
	 * 合并的方法
	 *
	 * @param arr   排序的原始数组
	 * @param left  左边右序序列的初始索引
	 * @param mid   中间索引
	 * @param right 右边索引
	 * @param temp  做中转的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left; // 初始化i,左边有序序列的初始索引
		int j = mid + 1; //初始化j,右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引

		//(一)
		//先把左右两边（有序）的数据按照规则填充到temp数组
		//直到左右两边的有序序列，有一边处理完毕为止
		while (i <= mid && j <= right) {//继续
			/**
			 * 如果左边的有序序列的当前元素，小于等于右边序列的当前元素
			 * 即将左边的当前元素，填充到temp数组中
			 * 然后 t++,i++
			 */
			if (arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else { //反之，将右边的有序序列的当前元素，填充到temp数组
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		//(二)
		//把有剩余数据一边的数据全部填充到temp
		while (i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}

		while (j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		//(三)
		/**
		 * 将temp数组的元素拷贝到arr。注意：并不是每次拷贝所有
		 * 第一次合并 tempLeft = 0 , right = 1
		 * 第二次合并 tempLeft = 2  right = 3
		 * 第三次合并 tempLeft=0    right = 3(将上面两个合并的再拷贝过去)
		 * ...
		 * 最后一次合并 tempLeft = 0  right = 7
		 */
		t = 0;
		int tempLeft = left;
		while (tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
	}
}