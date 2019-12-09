/*
Longest Common Substring:
	Given two string S1 and S2, find the length of the longest common substring which is common in both the strings.
	Input: S1="JAVACODE", S2="JAVACOMPILER"
	Output: 6
*/

import java.util.Scanner;

public class LCSSubString{

    static int cache[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String X = sc.next();
        String Y = sc.next();
        int lcs= LCSSubString(X.toCharArray(), Y.toCharArray(), X.length(), Y.length());
        System.out.println("Result:"+lcs);
    }
    			
    private static int LCSSubString(char X[],char Y[],int m,int n){
        int memo[][] = new int[m+1][n+1];
        int result=0;
        
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    memo[i][j]=0;
                } else if(X[i-1]==Y[j-1]){
                    memo[i][j] = memo[i-1][j-1] + 1;
                    result = Math.max(result,memo[i][j]);
                } else {
                    memo[i][j]=0;
                }
                //test
            }
            //test
        }
        cache=memo;
        return result;
    }
}