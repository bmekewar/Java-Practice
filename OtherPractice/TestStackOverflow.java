package OtherPractice;

public class TestStackOverflow {

    public static void main(String[] argv){
        try{
            main(null);
        }
        catch(Throwable e){
            System.err.println("ouch!");
        }
    }
}