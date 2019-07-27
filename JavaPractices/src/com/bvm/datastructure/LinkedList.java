package com.bvm.datastructure;

import com.bvm.java.EmptyListException;

public class LinkedList {

	private Object data;
	private ListNode firstNode, lastNode;

	public LinkedList() {
		this("Hi");
	}

	public LinkedList(Object obj) {
		data = obj;
		firstNode = lastNode = null;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	public void print() {

		if (isEmpty()) {
			System.out.println("List is empty");
			return;
		}

		System.out.print("List : [");
		ListNode current = firstNode;
		while (current != null) {
			System.out.print("  " + current.data);
			current = current.nextNode;
		}
		System.out.print("  ]\n");
	}

	public void addFirst(Object obj) {
		if (isEmpty())
			firstNode = lastNode = new ListNode(obj);
		else
			firstNode = new ListNode(obj, firstNode);
	}

	public void addLast(Object obj) {
		if (isEmpty())
			firstNode = lastNode = new ListNode(obj);
		else
			lastNode = lastNode.nextNode = new ListNode(obj);
	}

	public Object removeFirst() {
		if (isEmpty())
			throw new EmptyListException();
		Object removedItem = firstNode.data;
		if (firstNode == lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;
		return removedItem;
	}

	public Object removeLast() {
		if (isEmpty())
			throw new EmptyListException();
		Object removedItem = lastNode.data;
		if (firstNode == lastNode)
			firstNode = lastNode = null;
		else {
			ListNode current = firstNode;
			while (current.nextNode != lastNode) {
				current = current.nextNode;
			}
			current.nextNode = null;
			lastNode = current;
		}
		return removedItem;
	}

	public void remove(Object d) {
		if (isEmpty())
			throw new EmptyListException();
		if (firstNode.data.equals(d))
			firstNode = firstNode.nextNode;
		else {
			ListNode current = firstNode;
			do{
				ListNode next = current.nextNode;
				if(next.data.equals(d)){
					current.nextNode = next.nextNode;
					break;
				} 
				current = next;
			}while(current.nextNode!=null);
		}
	}

	public void reverse() {

		if (firstNode == null || firstNode.nextNode == null) {
			return;
		}

		ListNode tempSecond = firstNode.nextNode;
		ListNode tempThird = tempSecond.nextNode;

		// mark second node with first node reference
		tempSecond.nextNode = firstNode;
		firstNode.nextNode = null;

		if (tempThird == null)
			return;

		ListNode current = tempThird;
		ListNode previous = tempSecond;

		while (current != null) {
			ListNode next = current.nextNode;
			current.nextNode = previous;

			previous = current;
			current = next;
		}

		firstNode = previous;
	}

	public void recursiveReverse(ListNode currentNode) {
		if(currentNode == null)
			return;
		
		if(currentNode.nextNode == null){
			firstNode = currentNode;
			return;
		}
		
		recursiveReverse(currentNode.nextNode);
		currentNode.nextNode.nextNode = currentNode;
		currentNode.nextNode = null;
	}
	
	public ListNode getFirstNode(){
		return firstNode;
	}
	public ListNode getLastNode(){
		return lastNode;
	}
}