package com.bvm.datastructure;
public class LinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList list = new LinkedList(); // create the List container
		// insert integers in list
		list.addFirst( "-1" );
		list.print();
		list.addFirst( "0" );
		list.print();
		list.addLast( "1" );
		list.print();
		list.addLast( "5" );
		list.print();
		list.reverse();
		list.print();
		list.recursiveReverse(list.getFirstNode());
		list.print();
		list.remove("0");
		list.print();
	}
}
