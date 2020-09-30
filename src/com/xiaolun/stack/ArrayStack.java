package com.xiaolun.stack;

//定义一个Arraystack栈
public class ArrayStack {
	private int maxSize; // 栈的大小
	private int[] stack; // 数组模拟栈，数据就放到数组中
	private int top = -1;// top表示栈顶，初始化为-1

	//构造器
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		//数组初始化后才能放数据
		stack = new int[this.maxSize];
	}

	//栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	//栈空
	public boolean isEmpty() {
		return top == -1;
	}

	//入栈-push
	public void push(int value) {
		//先判断栈是否满
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	//出栈，将栈顶的数据返回
	public int pop() {
		//先判断栈是否空
		if (isEmpty()) {
			//抛出异常
			throw new RuntimeException("栈空，无数据~");
		}
		int value = stack[top];
		top--;
		return value;
	}

	//显示栈的情况（遍历），遍历时，从栈顶开始遍历数据
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空，无数据~");
			return;
		}
		//需要从栈顶显示数据
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}
}
