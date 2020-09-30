package com.xiaolun.search;

import java.util.ArrayList;
import java.util.List;

//二分查找算法，数组必须有序
public class BinarySearch {
	public static void main(String[] args) {
//		int arr[] = { 1,2,3};
//		int resIndex = binarySearch(arr, 0, arr.length - 1, 4);
//		System.out.println("resIndex=" + resIndex);

		int arr[] = {1, 1,2, 3};
		List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1);
		System.out.println("resIndexList=" + resIndexList);
	}

	/**
	 * @param arr     数组
	 * @param left    左边的索引
	 * @param right   右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到就返回下标，反之，返回-1
	 */
	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		// 当left > right时，说明递归整个数组，但是没有找到
		if (left > right) {
			return -1;
		}

		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // 向右递归
			//当数组是 arr[] = { 1,2,3} 时，mid=2会返回
			return binarySearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // 向左递归
			return binarySearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}
	}

	/**
	 * 当有多个相同的数值时，如何将所有的数值都查找到
	 * 思路
	 * 1、在找到mid索引值时，不要马上返回
	 * 2、向mid索引值的左边扫描，将所有满足1000的元素找到，加入到集合ArrayList
	 * 3、向mid索引值的右边扫描，将所有满足1000的元素找到，加入到集合ArrayList
	 * 4、将ArrayList返回
	 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) {
			return binarySearch2(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {
			return binarySearch2(arr, left, mid - 1, findVal);
		} else {
			List<Integer> resIndexlist = new ArrayList<Integer>();
			//向mid索引值的左边扫描，将所有满足1000的元素找到，加入到集合ArrayList
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != findVal) {//退出
					break;
				}
				//否则，将temp放入到resIndexlist
				resIndexlist.add(temp);
				temp -= 1; //temp左移
			}
			resIndexlist.add(mid); //将周阿金啊这个放进去
			//向mid索引值的右边扫描，将所有满足1000的元素找到，加入到集合ArrayList
			temp = mid + 1;
			while (true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
					break;
				}
				//否则，将temp放入到resIndexlist
				resIndexlist.add(temp);
				temp += 1; //temp右移
			}
			return resIndexlist;
		}
	}
}
