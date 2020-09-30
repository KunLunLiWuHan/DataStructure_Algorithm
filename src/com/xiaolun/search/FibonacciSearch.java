package com.xiaolun.search;

import java.util.Arrays;

//斐波那契算法
public class FibonacciSearch {
	public static int maxSize = 20;

	public static void main(String[] args) {
		int[] arr = {1, 8, 10,12};
		System.out.println("index=" + fibSearch(arr, 8));// 0
	}

	/**
	 * 因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列，所以我们需要获取一个斐波那契数列
	 * 非递归方法获取一个斐波那契数列
	 */
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	/**
	 * 非递归方式的斐波那契查找算法
	 *
	 * @param a   数组
	 * @param key 我们需要查找的关键字（码）
	 * @return 返回对应的下标，如果没有返回-1
	 */
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int k = 0; //表示斐波那契分割数值的下标
		int mid = 0; //存放mid值
		int f[] = fib(); //获取斐波那契数列
		//获取斐波那契数值的下标,让F[k]-1长度大于顺序表的长度，之后顺序表不够的进行补零
		while (high > f[k] - 1) {
			k++;
		}
		/**
		 * 因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组
		 * 并指向temp[],长度为f[k]（斐波那契数列第k个元素）不足的部分使用0填充
		 *
		 */
		int[] temp = Arrays.copyOf(a, f[k]);
		/**
		 * 实际上需要使用a数组的最后的数填充temp
		 * temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234}
		 */
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = a[high];
		}

		// 使用while循环处理，找到我们的数key
		while (low <= high) { // 只要这个条件满足，就可以找
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) { //我们继续向数组的前面查找（左边）
				high = mid - 1;
				/**
				 * 1、全部元素=前面的元素+后面的元素
				 * 2、f[k] = f[k-1] + f[k-2]
				 * 3、因为前面有 f[k-1]个元素,因此可以继续拆分 f[k-1] = f[k-2] + f[k-3]
				 * 即在f[k-1]的前面继续查找 k--
				 * 即下次循环 mid = f[k-1-1]-1
				 */
				k--;
			} else if (key > temp[mid]) { // 我们应该继续在数组的后面查找（右边）
				low = mid + 1;
				/**
				 * 1、全部元素=前面的元素+后面的元素
				 * 2、f[k] = f[k-1] + f[k-2]
				 * 3、因为后面有 f[k-2]个元素,因此可以继续拆分 f[k-2] = f[k-3] + f[k-4]
				 * 即在f[k-1]的前面继续查找 k-=2
				 * 即下次循环 mid = f[k-1-2]-1
				 */
				k -= 2;
			} else { //找到
				//需要确定，返回的是那个下标
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
