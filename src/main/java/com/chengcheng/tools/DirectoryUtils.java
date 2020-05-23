package com.chengcheng.tools;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;

@Api("搜索路径形成树型结构")
public class DirectoryUtils {  //存储指定文件夹所有文件名的 树类
	private static TreeNode root = new TreeNode(null, null, null);  // 树根（相当于链表的头指针）
	private static String PATH = "F:/Message";

	public static TreeNode getRoot() {  //获取树根
		return root;
	}

	@Api("树节点两个指针，一个指向第一个儿子 一个指向兄弟")
	private static class TreeNode {  //树节点类
		String data;
		TreeNode firstChild;  // 第一儿子链
		TreeNode nextSibling;  // 下一兄弟链

		//构造器
		TreeNode(String data, TreeNode firstChild, TreeNode nextSibling) {
			super();
			this.data = data;
			this.firstChild = firstChild;
			this.nextSibling = nextSibling;
		}
	}

	@ApiOperation("实现将指定文件夹的所有文件存入树中")
	private static void getFile(String path, TreeNode treeNode) {
		File file = new File(path);
		File[] array = file.listFiles();
		TreeNode newNode = null;
		if (array != null) {
			for (File value : array) {
				newNode = new TreeNode(value.getName(), null, null);
				// 判断当前节点有没有firstChild，没有的话为其添加一个
				if (treeNode.firstChild == null) {
					if (value.isFile())
						treeNode.firstChild = newNode;
					if (value.isDirectory()) {
						treeNode.firstChild = newNode;
						getFile(value.getPath(), newNode);
					}
				}
				// 当前节点已经存在firstChild，所以后面的都是firstChild节点的兄弟
				else {
					TreeNode p = treeNode.firstChild;
					while (p.nextSibling != null)
						p = p.nextSibling;
					if (value.isFile())
						p.nextSibling = newNode;
					if (value.isDirectory()) {
						p.nextSibling = newNode;
						getFile(value.getPath(), newNode);
					}
				}
			}
		}

	}

	@ApiOperation("输出树中的内容")
	private static void printTree(TreeNode root, int deep) {
		if (root.data != null) {  // 这个if是为了区分root节点，因为root节点data=null
			for (int i = 0; i < deep; i++)  // 输出前置空格
				System.out.print("  ");
			System.out.println("┨" + root.data);
		}
		if (root.firstChild != null)
			printTree(root.firstChild, deep + 1);
		if (root.nextSibling != null)
			printTree(root.nextSibling, deep);
	}



	@ApiOperation("测试方法")
	public static void main(String[] args) {
		double start = System.currentTimeMillis();

		DirectoryUtils.getFile(PATH, DirectoryUtils.getRoot());
		DirectoryUtils.printTree(DirectoryUtils.root, 0);

		double end = System.currentTimeMillis();
		double uptime = (end - start) / 1000;
		System.out.println("---------------------------");
		System.out.println("总耗时: " + uptime + "秒");
	}

}