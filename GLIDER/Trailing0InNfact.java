package GLIDER;

public class Trailing0InNfact {
    
    public static void main(String[] args) {
        System.out.println(Trailing0InNfact.trail(35));
    }

    /*
        check number of 2,5,10
        Digits  Tr0s   n/5   condition
        1..4     0     0      none 
        5        1     1      (2x5)  
        10       2     2      (2x5,10)  
        15       3     3      (2x5, 2x5, 10)  
        20       4     4      (2x5, 2x5, 10,10)  
        25       6     5      (2x5, 2x5, 2x5, 2x5, 10,10)  
        30       7     6      (2x5, 2x5, 2x5, 2x5, 10,10,10)  

        answer = (n/5)+1;
    */
    public static int trail(int n){
        int zeros = 0;
        int i =  5;
        while(n/i >=1){
            zeros += n /i;
            i *= 5;
        }
        return zeros;
    }
}
