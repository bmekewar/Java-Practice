package GLIDER;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumber {
   
    public static void main(String[] args) {
        int[] numbers = {3,4,5,2,7};
        // Solution1 = TC = O(n) : n=arr.length+1; x = n*(n+1)/2 - sum(numbers)= 28-22=6 ;
        // Solution2 = TC = O(1) : x = (1^3^4^5) ^ (1^2^3^4^5) = 4

        // Solution 1:
        int n = numbers.length+1;
        int sum = Arrays.stream(numbers).boxed().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        System.out.println("Missing Number: "+((n*(n+1)/2)-sum));

        // Solution 2: 
        int s = numbers[0];
        int sn = 1;
        for (int j : numbers) {
            s ^= j;
        }
        System.out.println(s);
        
        for (int j=0;j<=numbers.length+1;j++) {
            sn ^= j;
        }
        System.out.println(sn);
        System.out.println("Missing Number: "+ (s^sn));


        LongAdder a = new LongAdder();
        Arrays.stream(numbers).forEach(a::add);
        System.out.println(a.intValue());
        System.out.println(Arrays.stream(numbers).boxed().collect(Collectors.summingInt(Integer::intValue)));
    }
}
