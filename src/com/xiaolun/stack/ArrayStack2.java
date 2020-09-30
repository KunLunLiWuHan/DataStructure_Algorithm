package com.xiaolun.stack;

//先创建一个栈，使用前面创建好的
//定义一个ArrayStack2，需要扩展功能
public class ArrayStack2 {
	private int maxSize; // 栈的大小
	private int[] stack;
	private int top = -1;

	//构造器
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	//增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
	public int peek() {
		return stack[top];
	}

	//栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	//栈空
	public boolean isEmpty() {
		return top == -1;
	}

	//push
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	//pop
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，无数据");
		}
		int value = stack[top];
		top--;
		return value;
	}

	//遍历
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空，无数据");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}

	/**
	 * 返回运算的优先级，优先级由程序员确定，优先级使用数字表示。
	 * 数字越大，优先级越高
	 *
	 * @param oper 操作符
	 * @return
	 */
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1; //假定目前只含有加减乘除，没有小括号。
		}
	}

	//判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	//计算方法
	public int cal(int num1, int num2, int oper) {
		return cal(num1, num2, oper, 0);
	}

	//计算方法
	public int cal(int num1, int num2, int oper, int temp) {
		int res = 0; // res 用于存放计算的结果
		switch (oper) {
			case '+':
				res = num1 + num2;
				break;
			case '-':
				if (temp == '-') {
					res = num2 + num1;// 注意顺序
				} else {
					res = num2 - num1;// 注意顺序
				}
				break;
			case '*':
				res = num1 * num2;
				break;
			case '/':
				res = num2 / num1;
				break;
			default:
				break;
		}
		return res;
	}
}
