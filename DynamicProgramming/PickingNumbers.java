/*
Picking Numbers:
	Given an array of integers, find and print the maximum number of integers you can select from the array such that the absolute difference between any two of the chosen integers is less than or equal to 1. For example, if your array is a=[1,1,2,2,4,4,5,5,5], you can create two subarrays meeting the criterion: [1,1,2,2] and [4,4,5,5,5]. The maximum length subarray has 5 elements.
	
	It should return an integer that represents the length of the longest array that can be created.
	
	pickingNumbers has the following parameter(s):
	a: an array of integers
	2<=n<=100 and 0< a[i] <100 
	the answer will be >=2

	Output Format:
		A single integer denoting the maximum number of integers you can choose from the array such that the absolute difference between any two of the chosen integers is .	

	Sample: 
		Input : 1 1 3 3 3 4 4 4 5 5 5 5 6 6 
		Output: 7

	Approach: 
		
		- find the frequency of each number repeating
		- check for immediate next and immediate previous elements 
	
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PickingNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        while(n-->0){
            list.add(sc.nextInt());            
        }
        System.out.println("Result:"+pickingNumbers(list));
    }

    static int pickingNumbers(List<Integer> a) {
        int frequency[] = new int[101];
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            int index = a.get(i);
            frequency[index]++;
        }
        for (int i = 1; i <= 100; i++) {
            result = Math.max(result, frequency[i] + frequency[i - 1]);
        }
        return result;
    }
}