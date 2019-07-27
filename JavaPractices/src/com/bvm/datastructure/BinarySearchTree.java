package com.bvm.datastructure;

public class BinarySearchTree {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insertNode(20);
		tree.insertNode(8);
		tree.insertNode(22);
		tree.insertNode(4);
		tree.insertNode(12);
		tree.insertNode(10);
		tree.insertNode(14);
		//tree.insertNode(11);
		//tree.insertNode(23);

		TreeNode t1 = tree.lca(tree.getRootNode(), 11, 4);
		System.out.println("LCA of " + 10 + " and " + 14 + " is " + t1.data);

		TreeNode t2 = tree.lca(tree.getRootNode(), 14, 8);
		System.out.println("LCA of " + 14 + " and " + 8 + " is " + t2.data);

		TreeNode t3 = tree.lca(tree.getRootNode(), 10, 22);
		System.out.println("LCA of " + 10 + " and " + 22 + " is " + t3.data);

		System.out.println("\n\nPreorder traversal");
		tree.preOrderTraversal(); // perform preorder traversal of tree

		System.out.println("\n\nInorder traversal");
		tree.inOrderTraversal(); // perform inorder traversal of tree

		System.out.println("\n\nPostorder traversal");
		tree.postOrderTraversal(); // perform postorder traversal of tree
		System.out.println();

		System.out.println(tree.sumTree(tree.getRootNode(), 0));

	}
}
