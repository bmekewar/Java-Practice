package GLIDER;

public class RemoveA {

    public static void main(String[] args) {
        
        //String s = "aabfa";
        //String s = "aaaaa";
        String s = "abcdafa";
        //String s = "aabfa";

        System.out.println(new RemoveA().removeA(s.toCharArray()));
    }
    
    String removeA(char[] str){
        String result =""+str[0];
        for(int i=1;i<str.length;i++){
            if(str[i] !='a'){
                result += str[i];
            }
        }
        return result;
    }
}
