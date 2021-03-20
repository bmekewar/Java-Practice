package FY21_Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VMQ1EmployeeRelation {

    public static void main(String[] args) {

        Map<String, String> givenMap = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();

        givenMap.put("A", "C");
        givenMap.put("B", "C");
        givenMap.put("C", "F");
        givenMap.put("D", "E");
        givenMap.put("E", "F");
        givenMap.put("F", "F");

        givenMap.entrySet().stream().forEach(m -> {

            if (map.containsKey(m.getValue())) {
                // list append A
                String e = m.getValue();
                List<String> l_c = map.get(m.getValue());
                //System.out.println(""+l_c);
                l_c.add(m.getKey());
                map.replace(e, l_c);
            } else {
                // map add C
                List<String> initialList = new ArrayList<String>();
                initialList.add(m.getKey());
                map.put(m.getValue(), initialList);
                //System.out.println(""+initialList);
            }

        });
        System.out.println("Given Map: " + givenMap);
        System.out.println("Relation Map: " + map);

        System.out.println("Enter employee to be searched: ");
        Scanner sc = new Scanner(System.in);
        String searchKey = sc.nextLine();

        System.out.println("Search Employee relations for: "+searchKey+" : "+map.get(searchKey));
    }
}
