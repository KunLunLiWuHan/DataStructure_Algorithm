package com.xiaolun.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList; // 存储顶点的集合
	private int[][] edges; //存储图对应的邻接矩阵
	private int numOfEdges; //边的个数
	//定义数组boolean[], 记录某个节点是否被访问
	private boolean[] isVisited;

	public static void main(String[] args) {
		String Vertexs[] = {"A", "B", "C", "D", "E"};

		Graph graph = new Graph(5);
		for (String vertex : Vertexs) {
			graph.insertVertex(vertex);
		}

		//A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);

		graph.showGraph();
		System.out.println("深度优先遍历----------");
		graph.dfs(); // A->B->C->D->E
	}

	//构造器
	public Graph(int n) {
		//初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;
	}

	/**
	 * 得到第一个邻接点的下标 w
	 *
	 * @param index 将当前节点的下标给我，我才能返回
	 * @return 如果存在，就返回对应的下标，反之为 -1
	 */
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] > 0) { //说明下一个邻接点是存在的
				return j;
			}
		}
		return -1;
	}


	/**
	 * 根据v1的前一个邻接节点的下标来获取下一个邻接节点
	 * 此时，v2仅仅代表被遍历过的一个数据，我们在其基础上进行遍历
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] > 0) { //说明存在
				return j; //返回对应的下标
			}
		}
		return -1;
	}

	/**
	 * 深度优先遍历算法
	 *
	 * @param isVisited 需要判断该节点是不是被访问，所以需要将该数组传进来
	 * @param i         需要访问节点 i（第一次时，i = 0）
	 */
	private void dfs(boolean[] isVisited, int i) {
		//首先我们访问该节点，输出
		System.out.print(getValueByIndex(i) + "->");
		//将该节点设置为已经访问
		isVisited[i] = true;
		//查找结点 i 的第一个邻接结点 w
		int w = getFirstNeighbor(i);
		while (w != -1) {//说明有（存在）
			//判断该节点是不是被访问过（当前假设没有被访问）
			if (!isVisited[w]) {
				dfs(isVisited, w); //递归
			}
			/**
			 * 1、如果 w 结点已经被访问过的情况
			 * 2、i表示正在被访问的节点，w表示找到的下一个节点
			 */
			w = getNextNeighbor(i, w);
		}
	}

	//对 dfs 进行一个重载, 遍历我们所有的结点，并进行 dfs
	public void dfs() {
		isVisited = new boolean[vertexList.size()];
		//遍历所有的结点，进行 dfs[回溯]
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}

	//图中常用方法
	//返回节点个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 显示图对应的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.err.println(Arrays.toString(link));
		}
	}

	//得到边的个数
	public int getNumOfEdges() {
		return numOfEdges;
	}

	//返回节点i（下标）对应的数据，比如 0->"A" 1->"B" 2->"C"
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	//返回v1和v2的权值（默认为1）
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}

	//插入节点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	/**
	 * 添加边（无向图）
	 *
	 * @param v1     表示点的下标，即使第几个顶点"A"-"B" "A"（第1个顶点）->0 "B"->1
	 * @param v2     第二个顶点对应的下标，如果表示二者有关系v1,v2传入 0，1即可。
	 * @param weight （0/1）矩阵里面想用什么来表示他们之间是关联的。
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
}
