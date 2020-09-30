package com.xiaolun.tree;

//定义二叉树
public class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	//删除节点
	public void delNode(int no) {
		if (root != null) {
			//如果只有一个root节点，那么立即判断root是不是就是要删除的节点
			if (root.getNo() == no) {
				root = null;
			} else {
				//递归删除
				root.delNode(no);
			}
		} else {
			System.out.println("空树，不能删除~");
		}
	}

	//前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	//中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	//后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	//前序遍历
	public HeroNode preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	//中序遍历
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		} else {
			return null;
		}
	}

	//后续遍历
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return this.root.postOrderSearch(no);
		} else {
			return null;
		}
	}
}

