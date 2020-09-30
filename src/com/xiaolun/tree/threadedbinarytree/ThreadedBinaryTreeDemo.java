package com.xiaolun.tree.threadedbinarytree;

//测试中序线索二叉树
public class ThreadedBinaryTreeDemo {
	public static void main(String[] args) {
		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "dim");

		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		//中序线索化
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();

		//测试: 以 10 号节点测试
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("10 号结点的前驱结点是 =" + leftNode); //3
		System.out.println("10 号结点的后继结点是=" + rightNode); //1

		//当线索化二叉树后，能在使用原来的遍历方法
		System.out.println("使用线索化的方式遍历 线索化二叉树");
		threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
	}
}




