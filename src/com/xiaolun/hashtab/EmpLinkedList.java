package com.xiaolun.hashtab;

//创建EmpLinkedList，表示链条。里面会存放很多数据
public class EmpLinkedList {
	//头指针，执行第一个雇员Emp,因此，我们这个链表的head是直接指向第一个Emp
	private Emp head; //默认为null

	/**
	 * 添加雇员到链表
	 * 1、假定，当添加雇员时，id是自增长，即id的分配总是从小到大
	 * 因此，我们将该雇员直接加入到本链表的最后即可
	 *
	 * @param emp
	 */
	public void add(Emp emp) {
		//如果是，添加第一个雇员
		if (head == null) {
			head = emp;
			return;
		}
		//如果不是第一个雇员，使用辅助指针，帮助定位到最后
		Emp curEmp = head;
		while (true) {
			if (curEmp.next == null) {//说明链表到最后
				break;
			}
			curEmp = curEmp.next; //后移
		}
		//退出时，直接将emp加入链表
		curEmp.next = emp;
	}

	//遍历链表中雇员的信息
	public void list(int no) {
		if (head == null) { //链表为null
			System.out.println("第" + (no + 1) + "链表为空");
			return;
		}
		System.out.print("当前"+(no + 1)+"链表的信息为：");
		Emp curEmp = head; //辅助指针
		while (true) {
			System.out.printf(" => id=%d name=%s\t \n", curEmp.id, curEmp.name);
			if (curEmp.next == null) {//说明curEmp已经到了最后节点
				break;
			}
			curEmp = curEmp.next; //后移，遍历
		}
	}

	/**
	 * 根据id查雇员
	 * 如果找到，就直接返回Emp,反之，返回null
	 *
	 * @param id
	 * @return
	 */
	public Emp findEmpById(int id) {
		//判断链表是否为null
		if (head == null) {
			System.out.println("链表为空");
			return null;
		}
		//辅助指针
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == id) {//找到
				break;//curEmp指向要查找的雇员
			}
			if (curEmp.next == null) {//遍历当前链表中没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}
}

