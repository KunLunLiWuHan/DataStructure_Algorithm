package com.xiaolun.hashtab;

//创建HashTab 管理多条链表
public class HashTab {
	private EmpLinkedList[] empLinkedListArray;
	private int size; //表示有多少条链表

	//构造器
	public HashTab(int size) {
		this.size = size;
		//初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		/**
		 * !!!这里不要初始化每一个链表,不然会报错。空指针异常。
		 * 1、原因是，上面一句的确是将链表数组创建，但是里面全部为null。
		 * 即（All elements are null）
		 * 2、需要进行下面的for循环的初始化操作
		 * 3、在执行下面的add添加操作时，null中不能添加元素，故报错。
		 */
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}

	//添加雇员
	public void add(Emp emp) {
		//根据雇员的id，得到该雇员应当添加到那条链表
		int empLinkedListNO = hashFun(emp.id);
		//将emp添加到对应的链表中
		empLinkedListArray[empLinkedListNO].add(emp);
	}

	//遍历所有链表，遍历hashtab
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}

	//根据输入的id,查找雇员
	public void findEmpById(int id) {
		//使用散列函数确定到那条链表上查找
		int empLinkedListNO = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if (emp != null) {//找到
			System.out.printf("在第%d条链表中找到雇员， id = %d\n", (empLinkedListNO + 1), id);
		} else {
			System.out.println("在哈希表中，没有找到雇员");
		}
	}

	//编写散列函数，使用一个简单取模法
	public int hashFun(int id) {
		return id % size;
	}
}