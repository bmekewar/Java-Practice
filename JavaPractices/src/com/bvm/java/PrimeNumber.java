package com.bvm.java;
import java.io.*;

class PrimeNumber {
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter number:");
    int num = Integer.parseInt(bf.readLine());
    System.out.println("Prime numbers: ");
    for (int i=1; i < num; i++ ){
      int j;
	  for (j=2; j<i; j++){
        int n = i%j;
        if (n==0){
          break;
        }
      }
      if(i == j){
        System.out.print("  "+i);
      }
    }
  }
} 
