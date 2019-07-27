package com.bvm.datastructure;

public class Tree {
	private TreeNode root;

	public Tree() {
		root = null;
	}

	public void insertNode(int insertData) {
		if (root == null)
			root = new TreeNode(insertData);
		else
			root.insert(insertData);
	}

	/*
	 * Function to find LCA of n1 and n2. The function assumes that both n1 and
	 * n2 are present in BST
	 */
	TreeNode lca(TreeNode root, int n1, int n2) {
		if (root == null) {
			return null;
		}

		// If both n1 and n2 are smaller than root, then LCA lies in left
		if (root.data > n1 && root.data > n2) {
			return lca(root.leftNode, n1, n2);
		}

		// If both n1 and n2 are greater than root, then LCA lies in right
		if (root.data < n1 && root.data < n2) {
			return lca(root.rightNode, n1, n2);
		}

		return root;
	}

	public int sumTree(TreeNode n, int add) {
		if (n == null) {
			return add;
		}
		int curVal = n.data;
		n.data = sumTree(n.rightNode, add);
		return sumTree(n.leftNode, n.data + curVal);
	}
	
	/*public static int chSum(TreeNode node){
		if(node == null){
			return 0;
		}else if(node.leftNode == null && node.rightNode == null){
			return 0;  // return 0,since we dont have anything left
		}else{
			int  tmp = node.data;
			node.data = chSum(node.rightNode);
			return tmp + node.data + chSum(node.leftNode);
		}
	}*/


	public TreeNode getRootNode() {
		return this.root;
	}

	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(TreeNode node) {
		if (node == null)
			return;
		System.out.printf(" %d ", node.data);
		preOrderHelper(node.leftNode);
		preOrderHelper(node.rightNode);
	}

	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(TreeNode node) {
		if (node == null)
			return;
		inOrderHelper(node.leftNode);
		System.out.printf(" %d ", node.data);
		inOrderHelper(node.rightNode);
	}

	public void postOrderTraversal() {
		postOrderHelper(root);
	}

	private void postOrderHelper(TreeNode node) {
		if (node == null)
			return;
		postOrderHelper(node.leftNode);
		postOrderHelper(node.rightNode);
		System.out.printf(" %d ", node.data);
	}
}
