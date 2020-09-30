package com.xiaolun.tree;

import java.util.Arrays;

//堆排序
public class HeapSort {
	public static void main(String[] args) {
		int arr[] = {4, 6, 8, 5, 9}; //将数组进行升序排列（大顶堆）
		heapSort(arr);
		System.out.println("排序后输出=" + Arrays.toString(arr));
	}

	//堆排序的方法
	public static void heapSort(int arr[]) {
		int temp = 0;
		System.out.println("堆排序");

		//分步完成
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("第一次调整：" + Arrays.toString(arr)); // 4, 9, 8, 5, 6
//		
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("第二次调整：" + Arrays.toString(arr)); // 9,6,8,5,4

		/**
		 * 1、将无序序列构建成一个堆，根据升序需求选择大顶堆，下面是一个大鼎退
		 * 其中，arr.length / 2 - 1 表示非叶子节点
		 * 只考虑完全二叉树
		 */
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		/**
		 * 2、将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
		 * 3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，
		 * 直到整个序列有序。
		 */
		for (int j = arr.length - 1; j > 0; j--) {
			//交换
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	/**
	 * 将一个数组(二叉树), 调整成一个大顶堆
	 * 功能： 完成将以 i 对应的非叶子结点的树调整成大顶堆
	 * 举例int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
	 * 如果我们再次调用adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
	 *
	 * @param arr    待调整的数组
	 * @param i      表示非叶子结点在数组中索引
	 * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
	 */
	public static void adjustHeap(int arr[], int i, int length) {
		int temp = arr[i];//先取出当前元素的值，保存在临时变量

		/**
		 * 开始调整
		 * 1、k = i * 2 + 1 k 是 i 结点的左子结点
		 * k = k * 2 + 1 表示当前节点的左子节点
		 */
		//找到最大值，并放到适当的位置
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			//k + 1 < length 保证 右子节点存在
			if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子结点的值小于右子结点的值
				k++; // k 指向右子结点
			}
			if (arr[k] > temp) { //假设此时为arr[k]对应的是右子节点， 并且如果子结点大于父结点
				arr[i] = arr[k]; //把较大的值赋给当前结点
				i = k; //!!! i 指向 k,继续循环比较(可能还有左/右子树)
			} else {
				break; //从左至右，从下至上。此时下面的已经调整好了。
			}
		}
		//当 for 循环结束后，我们已经将以 i 为父结点的树的最大值，放在了 最顶(局部)
		arr[i] = temp;//将 temp 值放到调整后的位置
	}
}