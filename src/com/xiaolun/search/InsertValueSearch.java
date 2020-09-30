package com.xiaolun.search;


public class InsertValueSearch {
	public static void main(String[] args) {
		int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
		int index = insertValueSearch(arr, 0, arr.length - 1, 1000);
		System.out.println("index = " + index);
	}

	/**
	 * @param arr     数组
	 * @param left    左边的索引
	 * @param right   右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到就返回下标，反之，返回-1
	 */
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
		/**
		 * findVal < arr[0] 和 findVal > arr[arr.length - 1]都需要
		 * 否则我们得到的mid可能越界
		 */
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		// 求mid,自适应
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if (findVal > midVal) { // 说明应该向右边递归查找
			return insertValueSearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // 说明应该向左边递归查找
			return insertValueSearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}
	}
}
