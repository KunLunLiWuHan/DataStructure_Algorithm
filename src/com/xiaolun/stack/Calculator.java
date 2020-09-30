package com.xiaolun.stack;

public class Calculator {
	public static void main(String[] args) {
		/**
		 * 1、表达式为 3-2*6-2 结果错误：-7（正确为-11）
		 */
		String expression = "3-2*6-2"; // 如何处理多位数的计算
		//创建两个栈，一个是数栈，*一个是符号栈
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		//定义需要的相关变量
		int index = 0;//用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' '; //将每次扫描得到的char保存到ch
		String keepNum = ""; //用于拼接多位数
		boolean flag = false; //为表达式中第一位为-号使用
		//while循环扫描expression
		while (true) {
			//依次得到expression的每一个字符
			ch = expression.substring(index, index + 1).charAt(0);
			//判断ch是什么，然后做相应的处理
			if (expression.substring(0, 0 + 1).charAt(0) == '-') {
				flag = true; //为表达式中第一位为-号使用
			}

			if (operStack.isOper(ch)) {//如果是运算符
				//判断当前的符号栈是否为null
				if (!operStack.isEmpty()) {
					/**
					 * 如果符号栈中有操作符，就进行比较，如果当前的操作符优先级小于或者等于栈中的操作符，
					 * 就需要从数栈中pop出两个数。
					 * 再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈
					 */
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//把运算的结果入数栈
						numStack.push(res);
						//然后将当前的操作符入符号符
						operStack.push(ch);
					} else {
						//如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
						operStack.push(ch);
					}
				} else {
					if (flag) {
						//开始时为负号，添加0
						numStack.push(0);
					}
					//如果为符号栈null,直接入符号栈
					operStack.push(ch); // 1 + 3
				}
			} else { //如果是数据，直接入数栈
				/**
				 * numStack.push(ch - 48);
				 * 扫描"1+3" 是字符'1'而不是数字1，字符1和数字1ASCII相差48.
				 *
				 * 1、当处理多位数时，不能发现是一个数就直接入栈，因为它可能是一个多位数
				 * 2、在处理数时，需要向expression的表达式的index后再看一位，如果是数就进行扫描，
				 * 如果是符号才入栈
				 * 3、我们需要定义一个字符串，用于拼接
				 */
				keepNum += ch; //处理多位数->拼接

				//如果ch是expression的最后一位，就直接入栈
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				} else {

					/**
					 * 判断下一个字符是不是数字，如果是数字，继续扫描，如果是运算符，则入栈
					 */
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						//如果后一位是运算符，则入栈 keepNum="1" 或者“123”
						numStack.push(Integer.parseInt(keepNum));
						//重要的，keepNum清空
						keepNum = "";
					}
				}
			}
			//让index + 1,并判断是不是扫描到expression最后
			index++;
			if (index >= expression.length()) {
				break;
			}
		}

		//当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
		while (true) {
			int temp = 0;
			//如果符号栈为null,则计算到最后的结果，数栈中只有一个数字（结果）
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop(); //将符号出栈

			//针对 3-2*6-2 这种算法的异常情况 12-2，3-（12-2）
			if (!operStack.isEmpty()) {
				temp = operStack.pop(); //再出一个栈，放到了临时变量中
				res = numStack.cal(num1, num2, oper, temp);
				//然后再将符号重新压回到栈中
				operStack.push(temp);
			} else {
				res = numStack.cal(num1, num2, oper);
			}

			numStack.push(res);//入栈
		}
		//将数栈的最后数，pop出，就是结果
		int res2 = numStack.pop();
		System.out.printf("表达式%s = %d", expression, res2);
	}
}

