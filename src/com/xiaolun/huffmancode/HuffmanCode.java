package com.xiaolun.huffmancode;

import java.util.*;

public class HuffmanCode {
	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length); //40

		/*
		System.out.println("---------------------------");
		List<Node> nodes = getNodes(contentBytes);
		System.out.println("nodes=" + nodes);

		System.out.println("---------------------------");
		//创建赫夫曼树
		System.out.println("赫夫曼树");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("前序遍历");
		huffmanTreeRoot.preOrder();

		System.out.println("---------------------------");
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		//{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
		System.out.println("生成的霍夫曼编码= " + huffmanCodes);

		System.out.println("---------------------------");
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		//[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//17
		 */
		//上面注释的语句等价于下面的代码
		byte[] huffmanCodeBytes = huffmanZip(contentBytes);
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));


		byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
		System.out.println("解码后输出=" + new String(sourceBytes)); // "i like like like java do you like a java"
	}

	/**
	 * 完成对于压缩数据的解码
	 * 1、将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	 * 先转成赫夫曼编码对应的二进制字符串 10101000101111111100100010111111110010001011111111001001010011011100011100000110111010001111001010
	 * 00101111111100110001001010011011100
	 * 2、将赫夫曼编码对应的二进制字符串“10101000101111111.....”对照赫夫曼编码，进而转成字符串
	 * i like like like java do you like a java
	 *
	 * @param huffmanCodes 赫夫曼编码表 map
	 * @param huffmanBytes 赫夫曼编码得到的字节数组
	 * @return 就是原来的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
		//1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式（113） 1010100010111...
		StringBuilder stringBuilder = new StringBuilder();

		//将 byte 数组转成二进制的字符串
		for (int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length - 1); //为true后就不用再补高位了
			stringBuilder.append(byteToBitString(!flag, b)); //拼接
		}
		//把字符串安装指定的赫夫曼编码进行解码
		//把赫夫曼编码表进行调换，因为反向查询（原本是a->100，现在反过来：100->a）
		Map<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		//map反向后-->{000=108, 01=32, 100=97, 101=105, 11010=121, 0011=111, 1111=107, 11001=117, 1110=101, 11000=100, 11011=118, 0010=106}
		System.out.println("map反向后-->" + map);

		//创建要给集合，存放 byte
		List<Byte> list = new ArrayList<>();
		//i 可以理解成就是索引(是一个大的计数器),扫描 stringBuilder
		for (int i = 0; i < stringBuilder.length(); ) {
			int count = 1; // 小的计数器
			boolean flag = true;
			Byte b = null;

			while (flag) {
				/**
				 * 1、1010100010111... 递增的取出 key -> 1
				 * 2、i 不动，让 count 移动，指定匹配到一个字符
				 */
				String key = stringBuilder.substring(i, i + count);
				b = map.get(key);
				if (b == null) {//说明没有匹配到
					count++;
				} else {
					//匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count;//i 直接移动到 count
		}
		/**
		 * 当 for 循环结束后，我们 list 中就存放了所有的字符"i like like like java do you like a java"
		 * 把 list 中的数据放入到 byte[] 并返回
		 */
		byte b[] = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	/**
	 * 将一个 byte 转成一个二进制的字符串
	 *
	 * @param b    传入的 byte
	 * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补, 如果是最后一个
	 *             字节，无需补高位
	 * @return 是该 b 对应的二进制的字符串，（注意是按补码返回）
	 */
	private static String byteToBitString(boolean flag, byte b) {
		//使用变量保存 b
		int temp = b; //将 b 转成 int
		//如果是正数我们还存在补高位
		if (flag) {
			temp |= 256; //按位或 256 1 0000 0000 | 0000 0001 => 1 0000 0001
		}
		String str = Integer.toBinaryString(temp); //返回的是 temp 对应的二进制的补码
		if (flag) {
			return str.substring(str.length() - 8); //取字符串后面的8位
		} else {
			return str;
		}
	}

	/**
	 * 使用一个方法，将前面的数据封装起来，便于我们的调用。
	 *
	 * @param bytes 原始的字符串对应的字节数组
	 * @return 经过霍夫曼编码处理后的字节数组（压缩后的数组）
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}

	/**
	 * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
	 *
	 * @param bytes        这是原始的字符串对应的 byte[]
	 * @param huffmanCodes 生成的赫夫曼编码 map类型
	 * @return 返回赫夫曼编码处理后的 byte[]
	 * 举例：
	 * 1、传入String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
	 * 因此，传入的byte就是 contentBytes
	 * 2、返回的 "10101000101111111100100010111111110010001011111111001
	 * 001010011011100011100000110111010001111001010001011111111
	 * 00110001001010011011100" 这个字符传对应的  byte[] huffmanCodeBytes
	 * （8位一组 对应一个 byte,放入到对应的huffmanCodeBytes数组中）
	 * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导（将其变成反码）  10101000 => 10101000 - 1 =>
	 * 10100111(反码)=> （反码的原码，符号位不变，取反）11011000= -88 ]
	 * => huffmanCodeBytes[1] = -88
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		//1 利用huffmanCodes 将 bytes 转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历 bytes 数组
		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}

		/**
		 * 1、将"1010100010111111110..." 转换成 byte[]来发送，
		 * 原本的数据长度为40 -》133 -》字节数组发送
		 * 2、下面的代码等价于：
		 * int len = (stringBuilder.length() + 7) / 8;
		 */
		int len;
		if (stringBuilder.length() % 8 == 0) {
			//哈夫曼编码后对应的字节数组长度
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		//创建存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;//定义一个计数器，记录是第几个byte(回头写代码)
		//因为是每8位对应一个byte,所以步长对应 +8
		for (int i = 0; i < stringBuilder.length(); i += 8) {
			String strByte;
			if (i + 8 > stringBuilder.length()) {
				//不够8位，下面的含义是从i位取到最后
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			//将strByte 转成 byte,放到huffmanCodeBytes
			huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
	}

	/**
	 * 生成赫夫曼树对应的赫夫曼编码
	 * 思路：
	 * 1、将赫夫曼编码以Map<Byte,String>格式存放
	 * 2、生成赫夫曼编码表
	 * {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
	 */
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	//3、生成的赫夫曼编码表需要去拼接路径，定义一个stringBuilder，存储某个叶子节点的路径
	static StringBuilder stringBuilder = new StringBuilder();


	//为了调用方便，我们重载getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		//处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		//处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}

	/**
	 * 功能：将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
	 *
	 * @param node          传入结点
	 * @param code          路径： 左子结点是 0, 右子结点 1
	 * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//将 code 加入到 stringBuilder2
		stringBuilder2.append(code);
		if (node != null) { //如果 node == null 不处理
			//判断当前 node 是叶子结点还是非叶子结点
			if (node.data == null) { //非叶子结点
				//向左递归
				getCodes(node.left, "0", stringBuilder2);
				//向右递归
				getCodes(node.right, "1", stringBuilder2);
			} else { //说明是一个叶子结点
				//就表示找到某个叶子结点的最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}

	//前序遍历的方法
	private static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("赫夫曼树为空");
		}
	}

	/**
	 * @param bytes 接收一个字节数组
	 * @return 返回list, 形式为  [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
	 */
	private static List<Node> getNodes(byte[] bytes) {
		//1创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();

		//遍历 bytes , 统计每一个 byte出现的次数->map[key,value]
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			//获取数据
			Integer count = counts.get(b);
			if (count == null) { // Map还没有整个字符数据，第一次
				counts.put(b, 1);
			} else {
				//如果不是第一次
				counts.put(b, count + 1);
			}
		}

		/**
		 *把每一个键值对转成一个Node对象，并加入到nodes集合中
		 * 下面的方法表示map的遍历
		 */
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	//可以通过 List 创建对应的赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			//排序, 从小到大
			Collections.sort(nodes);
			//取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			//取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			//创建一颗新的二叉树,它的根节点 没有 data, 只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;

			//将已经处理的两颗二叉树从 nodes 删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//将新的二叉树，加入到 nodes
			nodes.add(parent);
		}
		//nodes 最后的结点，就是赫夫曼树的根结点
		return nodes.get(0);
	}
}



