/*
 for unsigned integers use >>> operator for right shift
 */

public class HammingWeight{
    public static void main(String[] args) {
        System.out.println("result:"+hammingWeight(Integer.MAX_VALUE));
    } 
    // you need to treat n as an unsigned value
      public static int hammingWeight(int n) {
        int bits = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                bits++;
            }
            n = n >>> 1;
        }
        return bits;
    }
}