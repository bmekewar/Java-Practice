/*
 Minimum number of deletions & insertions to transform a string to another:
	
	Given two strings S1 and S2, count the minimum number of deletion and insertion operations required to transform S1 into S2 by inserting and deleting the charcters
	
	taught -> forgot
	-{'t','a','u','h'}| + {'f','o','r','o'}
	
	Output: 4 deletion and 4 insertion
	
	Solution:
		we need to find the least common characters matching in both substring (note: not least common substring)
		lcs = LCS(s1,s2)
		deletion = s1.length()-LCS(s1,s2)
		insertion = s2.length()-LCS(s1,s2)
*/

import java.util.Scanner;

public class TransformS1toS2{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String X = sc.next();
        String Y = sc.next();
        int lcs= lcs(X.toCharArray(), Y.toCharArray(), X.length(), Y.length());
        System.out.println("Result:"+lcs);
        
        System.out.println("Deletion:"+(X.length()-lcs));
        System.out.println("Insertion:"+(Y.length()-lcs));
    }

    private static int lcs(char[] X, char[] Y, int m, int n){
        int memo[] = new int[n+1]; // initialize with total column +1 

        for(int i=1;i<=m;i++){
            int prev = 0;
            for(int j=1;j<=n;j++){
                int temp = memo[j];
                if(X[i-1]==Y[j-1]){
                    memo[j] = prev + 1;
                } else {
                    memo[j] = Math.max(memo[j], memo[j-1]);
                }
                prev = temp;
            }
        }
        return memo[n];
    }
}