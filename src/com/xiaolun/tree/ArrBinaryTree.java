package com.xiaolun.tree;

//创建一个 ArrBinaryTree ，实现顺序二叉树的遍历
public class ArrBinaryTree {
	private int[] arr;  //存储数据结点的数组

	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}

	public void preOrder() {
		this.preOrder(0);
	}

	/**
	 * 编写一个方法，完成顺序存储二叉树的前序遍历
	 *
	 * @param index 数组的下标
	 */
	public void preOrder(int index) {
		//如果数组为空，或者 arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}
		//输出当前这个元素
		System.out.println(arr[index]);
		//向左递归遍历
		if ((index * 2 + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		//向右递归遍历
		if ((index * 2 + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
}