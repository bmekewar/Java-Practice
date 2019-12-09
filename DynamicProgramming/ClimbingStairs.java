/*
Climbing Stairs: count the number of ways to climb the staircase if child hops 1 or 2 steps.

	Stair: 		1,2,3,4,5,6,7
	No.of Ways: 1,2,3,5,8,13,21

	Solution:
		Since child can hope 1 or 2 steps expected answer is  
		T(n)= T(n-1)+T(n-2)
		if child can hope 1,2 or 3 steps expected answer is  
		T(n)= T(n-1)+T(n-2)+T(n-3)
	General Formula: 
		f(n,m) = Summation of (1 to M) of f(n-m)
		TC : 
			Recursive solution O(m^n)
			DP Solution: O(n) 
*/

public class ClimbingStairs{
	public static void main(String[] args) {
		System.out.println("No of stairs:"+ 10);
		System.out.println("Result:"+ noOfWays(10));
	}
	private static int noOfWays(int n) {
		int first=1, second=1,sum=0;
		if(n<2)
			return n;
		for(int i=2;i<=n;i++){
			sum = first + second;
			first = second;
			second = sum;
		}
		return sum;
	}
}