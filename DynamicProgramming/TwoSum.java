/*
Two Sum for Sorted input array:
	Given an array of integers that is already sorted in ascending order find two numbers such that they add up to a specific target numer.
	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
	
	Input: nums = [2,7,11,15], target =9;
	Output : [1,2]
*/


public class TwoSum{
    public static void main(String[] args) {
        int[] num = {3,3,4,5,6,7,9};
        System.out.println("Result:"+twoSum(num, 9));
    }
    static int[] twoSum(int num[],int target){
        int start=0;
        int end = num.length-1;
        int result[] = new int[2];
        while (start<end){
            int sum = num[start]+num[end];
            if(sum==target){
                result[0]=start+1;
                result[1]=end+1;
                break;
            } else if(sum<target){
                start++;
            } else {
                end--;
            }
        }
        System.out.println("index1:"+result[0]);
        System.out.println("index2:"+result[1]);
        return result;
    }
}

