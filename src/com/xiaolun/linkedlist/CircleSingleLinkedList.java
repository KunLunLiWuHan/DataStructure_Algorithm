package com.xiaolun.linkedlist;

// 创建一个环形的单向链表
class CircleSingleLinkedList {
	// 创建一个first节点，当前没有编号
	private Boy first = null;

	// 添加小孩节点，构建成一个环形的链表
	public void addBoy(int nums) {
		// nums 构建一个数据校验
		if (nums < 1) {
			System.out.println("nums的值不正确");
			return;
		}
		Boy curBoy = null; //辅助指针，帮助构建环形链表
		// 使用for来创建我们的话逆行链表
		for (int i = 1; i <= nums; i++) {
			// 根据编号，创建小孩节点
			Boy boy = new Boy(i);
			// 如果是第一个小孩
			if (i == 1) {
				first = boy;
				first.setNext(first); // 构建环
				curBoy = first; // 让curBoy指向第一个小孩
			} else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}

	// 遍历当前的环形链表
	public void showBoy() {
		// 判断链表是否为null
		if (first == null) {
			System.out.println("no children");
			return;
		}
		// 因为first不能动，因此我们仍然使用一个辅助指针来完成遍历
		Boy curBoy = first;
		while (true) {
			System.out.printf("children number is %d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {// 说明已经遍历啊完毕
				break;
			}
			curBoy = curBoy.getNext(); // curBoy后移
		}
	}

	/**
	 * 根据用户输入，计算出小孩出圈的顺序
	 *
	 * @param startNo  表示从第几个小孩开始数数
	 * @param countNum 表示数几下
	 * @param nums     表示最初有多少个小孩在圈中
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		// 先对数据进行校验
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("args is wrong,please input in begin...");
			return;
		}
		// 创建一个辅助指针，帮助完成小孩出圈
		Boy helper = first;
		// 需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后这个节点
		while (true) {
			if (helper.getNext() == first) { // 说明helper指向最后小孩节点
				break;
			}
			helper = helper.getNext();
		}
		//小孩报数前，先让first和helper移动k-1次，移动到k这个小孩这里来
		for (int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//当小孩报数时，让first和helper指针同时移动m-1次(本身也要数一下)，然后出圈
		//这里是一个循环操作，直到圈中只有一个节点
		while (true) {
			if (helper == first) { //说明圈中只有一个节点
				break;
			}
			//让first和helper指针同时移动 countNum - 1
			for (int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//这时first指向的节点，就是要出圈的小孩节点
			System.out.printf("小孩%d出圈\n", first.getNo());
			//这里将first指向的小孩节点出圈（指向相同的引用）
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.printf("最后留在圈中的小孩编号 %d \n", first.getNo());
	}
}

