package com.bvm.datastructure;

public class ListNode {
	Object data;
	ListNode nextNode;
	
	public ListNode(Object obj){
		this(obj,null);
	}
	public ListNode(Object obj, ListNode node){
		data = obj;
		nextNode = node;
	}
	public Object getData(){
		return data;
	}
	public ListNode getNext(){
		return nextNode;
	}
}
