package com.xiaolun.tree;


public class HeroNode {
	private int no;
	private String name;
	private HeroNode left; //默认为null
	private HeroNode right; //默认为null

	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}

	/**
	 * 递归删除节点
	 * 1、如果删除的节点是叶子节点，则删除该节点
	 * 2、如果删除的节点是非叶子节点，则删除该子树
	 */
	public void delNode(int no) {

		/**
		 * 思路
		 * 1、因为我们的二叉树是单向的，因此，我们判断当前节点的子节点是否是需要删除的节点，
		 * 而不能去判断当前这个节点是不是需要删除的节点。
		 * 2、如果当前节点的左子节点不为空，且删除的节点为左子节点，那么就将this.left=null;
		 * 并且返回（删除后需要结束递归）
		 * 3、右子节点同上。
		 * 4、如果第2，3步没有删除节点，那么就需要向左子树进行递归删除
		 * 5、如果第4步也没有删除节点，那么应该向右子树递归删除
		 */
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		if (this.left != null) {
			this.left.delNode(no);
		}
		if (this.right != null) {
			this.right.delNode(no);
		}
	}

	//前序遍历的方法
	public void preOrder() {
		System.out.println(this); //先输出父节点
		//递归向左子树前序遍历
		if (this.left != null) {
			this.left.preOrder();
		}
		//递归向右子树前序遍历
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	//中序遍历
	public void infixOrder() {
		//递归向左子树前序遍历
		if (this.left != null) {
			this.left.infixOrder();
		}
		//输出父节点
		System.out.println(this);
		//递归向右子树前序遍历
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	//后序遍历
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}


	/**
	 * 前序遍历查找
	 *
	 * @param no 查找no
	 * @return 如果找到就返回该Node, 反之，返回 null
	 */
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历");
		//比较当前节点
		if (this.no == no) {
			return this;
		}

		/**
		 * 1、判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
		 * 2、如果左递归前序查找，找到节点，就返回
		 */
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {//说明我们的左子树找到
			return resNode;
		}
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}

	//中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("进入中序查找");
		if (this.no == no) {
			return this;
		}
		//否则继续进行右递归的中序查找
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}

	//后序遍历查找
	public HeroNode postOrderSearch(int no) {

		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}

		if (this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("进入后序查找");
		if (this.no == no) {
			return this;
		}
		return resNode;
	}
}


