package GLIDER;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntervalOverlap {

    public static void main(String[] args) {

        int interval = 3;
        int[] numbers = { 1, 3, 2, 5, 7, 8 };
        int[][] result = new int[interval][2];
        int[][] arr = new int[interval][2];

        int k = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j <result[i].length; j++) {
                result[i][j] = numbers[k];
                k++;
            }
        }
        for (int[] a : result) {
            for (int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println("\n");
        }

        /*for (int i = 0; i <interval; i++) {
            arr[i][0]=result[i][0];
            if (result[i][1] >= result[i + 1][0]) {
                arr[i][1] = Integer.max(result[i][1], result[i + 1][1]);
                //result[i + 1] = null;
                i++;
            } 
        }*/
        System.out.println(arr);
    }

}
