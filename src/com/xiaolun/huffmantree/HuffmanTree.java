package com.xiaolun.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
	public static void main(String[] args) {
		int arr[] = {13, 7, 8, 3, 29, 6, 1};
		Node root = createHuffmanTree(arr);
		preOrder(root);
	}

	//编写前序遍历的方法
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("是空树，不能遍历~~");
		}
	}


	/**
	 * 创建赫夫曼树的方法
	 *
	 * @param arr 需要创建成哈夫曼树的数组
	 * @return 创建好后的赫夫曼树的 root 结点
	 */
	public static Node createHuffmanTree(int[] arr) {
		/**
		 * 第一步为了操作方便
		 * 1. 遍历 arr 数组
		 * 2. 将 arr 的每个元素构成成一个 Node
		 * 3. 将 Node 放入到 ArrayList 中
		 */
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		//我们处理的是一个循环的过程
		while (nodes.size() > 1) {

			//排序，从小到大
			Collections.sort(nodes);

			System.out.println("nodes =" + nodes);

			/**
			 * 取出根节点权值最小的两颗二叉树
			 * (1) 取出权值最小的结点（二叉树）
			 */
			Node leftNode = nodes.get(0);
			//(2) 取出权值第二小的结点（二叉树）
			Node rightNode = nodes.get(1);

			//(3)构建一颗新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			//(4)从 ArrayList 删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//(5)将 parent 加入到 nodes
			nodes.add(parent);
		}
		//返回哈夫曼树的 root 结点
		return nodes.get(0);
	}
}

