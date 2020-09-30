package com.xiaolun.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
	public static void main(String[] args) {
		/**
		 * 后缀表达式测试
		 * 1、先定义一个逆波兰表达式
		 * 2、为了说明方便，逆波兰表达式的数字和符号使用空格隔开
		 * (3+4)×5-6 =>就是 3 4 + 5 × 6 –
		 * 3、思路
		 * （1）先将 3 4 + 5 × 6 – 放到ArrayList中
		 * （2）将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
		 */
//		String suffixExpression = "3 4 + 5 * 6 -";
//		List<String> list = getListString(suffixExpression);
//		System.out.println("List=" + list);
//
//		int res = calculate(list);
//		System.out.println("计算的结果是=" + res);

		/**
		 * 中缀表达式转后缀表达式
		 * 1、因为直接对表达式直接进行操作，不太方便，因此，先将1+((2+3)×4)-5中缀表达式
		 * 转换成对应的list => ArrayList[1,+,(,(,2,+,3,),×,4,),-,5]
		 * 2、将得到的中缀表达式的list转换成后缀表达式的list
		 *  ArrayList[1,2,3,+,4,×,+,5,–]
		 */
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);

		System.out.println("中缀表达式List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
		List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
		System.out.println("后缀表达式List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5]
		System.out.printf("expression=%d", calculate(suffixExpreesionList));
	}

	/**
	 * 将中缀表达式转成对应的list转换成后缀表达式的list
	 * ArrayList[1,+,(,(,2,+,3,),×,4,),-,5] -> ArrayList[1,2,3,+,4,×,+,5,–]
	 *
	 * @param ls
	 * @return
	 */
	public static List<String> parseSuffixExpreesionList(List<String> ls) {
		//定义两个栈
		Stack<String> s1 = new Stack<String>(); // 符号栈
		/**
		 * 说明
		 * 因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序操作，我们
		 * 不使用下面的Stack<String>，而是使用List<String> s2
		 * Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
		 */
		List<String> s2 = new ArrayList<String>(); // 储存中间结果的栈s2

		//遍历ls
		for (String item : ls) {
			//如果是一个数，加入s2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				/**
				 * 如果是右括号），则弹出s1栈顶的运算符，并压入s2，直到遇到左括号
				 * 为止，此时将该对括号丢弃
				 */
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//!!! 将 （弹出s1栈，消除小括号
			} else {
				/**
				 * item此时是中缀表达式的运算符
				 * 1、当item的运算符的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出
				 * 并放到s2中，再次转到（4.1）与s1中新的栈顶运算符比较
				 * 2、需要一个优先级比较的方法
				 */
				while (true) {
					if (s1.size() == 0 || s1.peek() == "(") {
						s1.push(item);
						break;
					} else if (Operation.getValue(item) > Operation.getValue(s1.peek())) {
						s1.push(item);
						break;
					} else {
						s2.add(s1.pop());
					}
				}
			}
		}
		//将s1中剩余的运算符依次弹出并加入s2
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}

		//因为是存放到list中，所以按顺序输出就是后缀表达式的list
		return s2;
	}

	/**
	 * 将中缀表达式转换为对应的List
	 * s="1+((2+3)??4)-5";
	 *
	 * @param s
	 * @return
	 */
	public static List<String> toInfixExpressionList(String s) {
		List<String> ls = new ArrayList<String>();
		int i = 0; //指针，用于遍历中缀表达式对应的内容
		String str; // 对于多位数的拼接
		char c; // 每遍历一个字符，就放入到c
		do {
			//如果c是一个非数字，就加入到ls
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++; //i后移
			} else { //如果是一个数，需要考虑多位数
				str = ""; //先将str置成“” '0'[48]->'9'[57]
				while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c;//拼接
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;//返回
	}

	//将一个逆波兰表达式，依次将数据和运算符放到ArrayList中（当成字符串处理）
	public static List<String> getListString(String suffixExpression) {
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}

	/**
	 * 完成对逆波兰表达式的运算：
	 * （1）从左至右扫描，将3和4压入堆栈；
	 * （2）遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
	 * （3）将5入栈；
	 * （4）接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
	 * （5）将6入栈；
	 * （6）最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
	public static int calculate(List<String> ls) {
		// 创建一个栈，只需要一个栈即可
		Stack<String> stack = new Stack<String>();
		//遍历 ls
		for (String item : ls) {
			// 使用正则表达式取出数
			if (item.matches("\\d+")) { //匹配的是多位数
				// 入栈
				stack.push(item);
			} else {
				// pop出两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num1 - num2;
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num1 / num2;
				} else {
					throw new RuntimeException("运算符有误");
				}
				//将res入栈
				stack.push("" + res);
			}
		}
		//最后留在stack中的数据是运算结果
		return Integer.parseInt(stack.pop());
	}
}