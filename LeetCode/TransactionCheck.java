import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TransactionCheck{
    
    public static void main(String[] args) {
        List<Transaction> list = new ArrayList<Transaction>();
        String[] givenList= {"alice,20,800,mtv","bob,50,1200,mtv","alice,50,100,beijing"};
        List<String> l = Arrays.asList(givenList);
        System.out.println("List1: "+l);

        Stream.of(givenList).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

                String[] arr = s.split(",");
                Transaction t = new Transaction();
                t.setName(arr[0]);
                t.setTime(Long.parseLong(arr[1]));
                t.setAmount(Double.parseDouble(arr[2]));
                t.setCity(arr[3]);
                list.add(t);
            }
        });
        System.out.println("List: "+list);


    }
}

class Transaction{
    String name;
    Long time;
    Double amount;
    String city;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public Long getTime(){
        return time;
    }
    public void setTime(Long time){
        this.time = time;
    }
    public Double getAmount(){
        return amount;
    }
    public void setAmount(Double amount){
        this.amount = amount;
    }

    public String toString(){
        return "["+name+","+time+","+amount+","+city+"]";
    }
}