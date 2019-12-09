public class PrintStairCase {

    public static void main(String[] args) {
        int n =4;
        String str = "#";
		for(int i=0;i<n;i++){
			System.out.printf("%"+n+"s\n",str);
			str+="#";
		}
    }
}