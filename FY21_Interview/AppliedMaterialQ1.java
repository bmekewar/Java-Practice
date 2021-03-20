package FY21_Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppliedMaterialQ1 {

    public static void main(String[] args) {
        //int arCount = 5;

        List<String> ar = new ArrayList<String>();
        String[] artempItems = { "1", "2", "3", "three", "test","elevan","four","two"};

        for (int i = 0; i <artempItems.length; i++) {
            String arItem = artempItems[i];
            ar.add(arItem);
            // System.out.println(arItem);
        }
        int K = 2;
        int outcome = Outcome.solve(ar, K);
        System.out.println("RESULT:=" + outcome);
    }

}

class Outcome {

    /*
     * Implement method/function with name 'solve' below. The function accepts
     * following as parameters. 1. ar is of type List<String>. 2. K is of type int.
     * return int.
     */

    public static int solve(List<String> ar, int K) {
        // Write your code here
        String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        List<String> nums = Arrays.asList(numbers);

        List<Integer> result = new ArrayList<Integer>();
        
        System.out.println(ar.size());
        System.out.println(ar);

        ar.stream().forEach(input -> {
            if (nums.contains(input)) {
                result.add(nums.indexOf(input) + 1);
            } else {
                Scanner sc = new Scanner(input);
                if(sc.hasNextInt()){
                    result.add(sc.nextInt());
                }
            }
            // System.out.println(result);
        });
        result.sort(Comparator.naturalOrder());
        System.out.println(result);
        return result.stream().filter(e -> e > K).collect(Collectors.toList()).size();
    }

}
