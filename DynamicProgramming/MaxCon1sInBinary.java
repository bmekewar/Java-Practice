import java.util.Scanner;

public class MaxCon1sInBinary {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println("Number:"+number);      
        System.out.println("binary:"+Integer.toBinaryString(number));      
        System.out.println("Result:"+count(number));      
    }
    
    public static int count(int number){
		int count=0;
		while(number >0){
			number = number & (number << 1);
			count++;
		}
		return count;
	}
}