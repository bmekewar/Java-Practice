/*
 Sliding Window Technique: Maximum Sum Subarray of Size K 
	
	Given an array of integers n and a positive number k, find the maximum sum of any contiguous subarray of size k.
	Input: [2,1,5,1,3,2], k=3
	Output: 9
	Solution: subArray with maximum sum [5,1,3]
	
*/

public class SlidingWindowTechnique{
    public static void main(String[] args) {
        int[] num = {2,1,5,1,3,2};
        int k = 3;
        System.out.println("Result:"+SWT(num,k));
    }

    private static int SWT(int[] num, int k){
        int windowSum=0, maxSum=0;
        for(int i=0;i<k;i++){
            windowSum += num[i];
        }
        
        for(int j=k;j<num.length;j++){
            windowSum += num[j] - num[j-k];
            maxSum = Math.max(maxSum,windowSum);
        }
        return maxSum;
    }
}