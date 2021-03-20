public class HammingDistance{
    public static void main(String[] args) {
        int x=93,y=73,count=0;
        int number = x^y;
        System.out.println("x:"+Integer.toBinaryString(x));      
        System.out.println("y:"+Integer.toBinaryString(y));      
        for(int i=0; i<32; i++) {
            count += (number>>i)&1;
        }
		System.out.println("Result:"+count);
    }
}