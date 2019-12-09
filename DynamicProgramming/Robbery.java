/** House Robber Problem:  	
* -> You are a professional robber planning to rob houses along a street. Each house has certain amount of money stashed, 
* the only constraint stopping you from robbing each of the them is that adjacent houses have security system connected 
* and it will automatically contact the police if two adjacent houses were broken into on the same night.
* Given a list of non-negative integers representing the amount of money of each house, 
* determine the maximum amount of money you can rob tonight without alerting the police.

* -> Find maximum sum in a array such that no two elements are adjacent 
	
*	Input: 5,3,4,11,2
*	Output: 16
*/
public class Robbery {
    public static void main(String[] args){
        Robbery robbery = new Robbery();

        int amount[] = {5,3,4,11,2};
        System.out.println("Max amount:"+robbery.rob(amount));
    }    
    private int rob(int[] amount){
        if(amount ==null || amount.length==0)
            return 0;
        int prevMax = amount[0];
        int max = Math.max(amount[0],amount[1]);
    
        for(int c=2;c<amount.length;c++){
            int tmp = max;
            max = Math.max(prevMax+amount[c], max);
            prevMax = tmp;
        }
        return max;
    }
}
