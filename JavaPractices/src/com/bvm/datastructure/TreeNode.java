package com.bvm.datastructure;

public class TreeNode {
	TreeNode leftNode;
	int data;
	TreeNode rightNode;
	
	public TreeNode(int nodeData){
		data = nodeData;
		leftNode = rightNode = null;
	}
	
	public void insert(int insertData){
		if(insertData < data)
		{
			if(leftNode == null)
				leftNode = new TreeNode(insertData);
			else 
				leftNode.insert(insertData);
		}
		else if(insertData > data)
		{
			if(rightNode == null)
				rightNode = new TreeNode(insertData);
			else 
				rightNode.insert(insertData);
		}
	}
}
