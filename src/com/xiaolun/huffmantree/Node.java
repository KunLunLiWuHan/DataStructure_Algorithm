package com.xiaolun.huffmantree;

/**
 * 创建结点类
 * 为了让 Node 对象持续排序 Collections 集合排序
 * 让 Node 实现 Comparable 接口
 */
public class Node implements Comparable<Node> {
	int value; // 节点权值
	char c; //
	Node left; // 指向左子结点
	Node right; // 指向右子结点

	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		//表示从小到大排序
		return this.value - o.value;
	}
}