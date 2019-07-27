package com.bvm.datastructure;
import java.util.Random;

public class TreeTest {

	public static void main(String[] args) {
		Tree tree = new Tree();
		int value;
		Random randomNumber = new Random();
		System.out.println( "Inserting the following values: " );
		// insert 10 random integers from 0-99 in tree
		for ( int i = 1; i <= 10; i++ )
		{
			value = randomNumber.nextInt( 100 );
			System.out.print( value + " " );
			tree.insertNode( value );
		} // end for
		System.out.println ( "\n\nPreorder traversal" );
		tree.preOrderTraversal(); // perform preorder traversal of tree
		
		System.out.println ( "\n\nInorder traversal" );
		tree.inOrderTraversal(); // perform inorder traversal of tree
		
		System.out.println ( "\n\nPostorder traversal" );
		tree.postOrderTraversal(); // perform postorder traversal of tree
		System.out.println();

	}
}
