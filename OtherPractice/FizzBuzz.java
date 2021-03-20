package OtherPractice;

import java.time.Instant;
import java.util.stream.IntStream;

public class FizzBuzz {
public static void main(String[] args) {
    
    System.out.println("\n1: now:" + Instant.now());
    IntStream.range(1, Integer.MAX_VALUE).forEach(i->{
        String result="";
        if(i%5==0 && i%3==0) result="fizzbuzz";
        else if(i%5==0) result="buzz";
        else if(i%3==0) result="fizz";
        //else if(result =="") System.out.print(i+" ");
        //else System.out.print(result+" ");
    });
    System.out.println("\nnow:" + Instant.now());

    System.out.println("\n2: now:" + Instant.now());
    IntStream.range(1, Integer.MAX_VALUE).forEach(i->{
        String result= "";
        if(i%3==0) result +="fizz";
        if(i%5==0) result +="buzz";
        //if(result =="") System.out.print(i+" ");
        //else System.out.print(result+" ");
    });
    System.out.println("\nnow:" + Instant.now());

    System.out.println("\n3: now:" + Instant.now());
    int c3=0,c5=0;
    for(int i=1;i<=Integer.MAX_VALUE;i++){
        String result = "";
        c3++; c5++;
        if(c3==3) {result+="fizz"; c3=0;}
        if(c5==5) {result+="buzz"; c5=0;}
        //if(result =="") System.out.print(i+" ");
        //else System.out.print(result+" ");
    }
    System.out.println("\nnow:" + Instant.now());

}
}
