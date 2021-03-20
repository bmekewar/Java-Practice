package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TTP {

    public static void main(String args[]){
        int[] arr= {12,12,4,23,1,24,24,2412,5,65};
        List<Integer> ilist = Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());

        String[] sArray= {"asd3","wer3","gasgdas3g","trtrt","zxc"};
        List<String> slist = Arrays.stream(sArray).sorted().collect(Collectors.toList());

        System.out.println("arr:"+ilist);
        System.out.println("arr:"+slist);

    }
    
}
