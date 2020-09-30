package com.xiaolun.linkedlist;

public class Josepfu {
	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);// 加入5个小孩节点
		circleSingleLinkedList.showBoy();

		circleSingleLinkedList.countBoy(1, 2, 5); // 2->4->1->5->3
	}
}

