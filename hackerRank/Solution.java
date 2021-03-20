package hackerRank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static void main(String[] args) {
        int i = 4;
        double d = 4.0;
        String s = "HackerRank ";
		
        Scanner scan = new Scanner(System.in);

        /* Declare second integer, double, and String variables. */
        i=i+scan.nextInt();
        d=d+scan.nextDouble();
        s.concat(scan.nextLine());
        /* Read and save an integer, double, and String to your variables.*/
        // Note: If you have trouble reading the entire String, please go back and review the Tutorial closely.

        /* Print the sum of both integer variables on a new line. */
        System.out.println(i);
        
        /* Print the sum of the double variables on a new line. */
        System.out.println(d);
		
        /* Concatenate and print the String variables on a new line; 
        	the 's' variable above should be printed first. */
        System.out.println(s+" "+scan.nextLine());

        int n=24;

        if(n%2!=0){
            System.out.println("Wierd");
        }
        if(n%2==0){
            if((n>2 && n <5) || n>20) {
                System.out.println("Not Wierd");
            } else if( n>6 && n<20){
                System.out.println("Wierd");
            }
        }

        scan.close();
    }
}