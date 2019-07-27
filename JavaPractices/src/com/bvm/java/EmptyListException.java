package com.bvm.java;

public class EmptyListException extends RuntimeException{

	public EmptyListException(){
		this("Empty List");
	}
	public EmptyListException(String str){
		super(str);
	}
}
