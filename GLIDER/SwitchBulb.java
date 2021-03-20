package GLIDER;

public class SwitchBulb {
   
    public static void main(String[] args) {
        
        SwitchBulb s = new SwitchBulb();
        System.out.println(s.isLighting(true,false,true));
    }

    boolean isLighting(boolean switch1, boolean switch2, boolean switch3){
        int count = 0;
        if(switch1)
            count++;
        if(switch2)
            count++;            
        if(count ==0)
            return false;
        if(switch3)
            count++;
            
        return count == 2;
    }
}
