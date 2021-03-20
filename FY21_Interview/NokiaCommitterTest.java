package FY21_Interview;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class NokiaCommitterTest {

    public static void main(String[] args) {
        NokiaCommitterTest test = new NokiaCommitterTest();

        Integer no_of_days = 0;
        Integer team_size = 0;

        Map<Integer, List<Committer>> map = new HashMap<Integer, List<Committer>>();

        InputStream is = NokiaCommitterTest.class.getResourceAsStream("nokia_data.txt");
        Scanner scanner = new Scanner(new BufferedInputStream(is), "UTF-8");

        try {
            if (scanner.hasNextInt()) {
                no_of_days = scanner.nextInt();
                team_size = scanner.nextInt();
                // System.out.println("no_of_days: " + no_of_days);
                // System.out.println("no_of_days: " + team_size);
            } else {
                System.out.println("Invalid input provided");
                throw new RuntimeException("Invalid input provided");
            }

            if (no_of_days != 0 && team_size != 0) {
                int daysCount = 0;
                int teamSizeCount = 0;
                List<Committer> committer = new ArrayList<Committer>(teamSizeCount);
                ;
                do {
                    committer.add(new Committer(new String(scanner.next()), scanner.nextInt(), scanner.nextInt()));
                    teamSizeCount++;
                    if (teamSizeCount >= team_size) {
                        daysCount++;
                        map.put(daysCount, committer);
                        teamSizeCount = 0;
                        committer = new ArrayList<Committer>(teamSizeCount);
                    }
                } while (scanner.hasNextLine() && daysCount < no_of_days);
                // System.out.println("Map of committers:" + map);
            }

            // get list of cleanCommitter
            Map<Integer, String> cleanCommitter = new HashMap<Integer, String>();

            AtomicInteger count = new AtomicInteger();
            map.entrySet().stream().forEach(e -> {
                Optional<Committer> c_comiter = e.getValue().stream()
                        .collect(Collectors.maxBy(Comparator.comparingInt(Committer::getCleanCommits)));
                if (c_comiter.isPresent()) {
                    cleanCommitter.put(count.incrementAndGet(), c_comiter.get().getName());
                }
            });
            // System.out.println("\nClean Committers:"+cleanCommitter);

            // get list of star committer
            System.out.println("Day\tCleanCommitter");
            Map<String, Integer> frequency = new HashMap<String, Integer>();
            cleanCommitter.entrySet().forEach(m -> {
                if (frequency.containsKey(m.getValue())) {
                    int f_count = frequency.get(m.getValue());
                    frequency.put(m.getValue(), f_count + 1);
                } else {
                    frequency.put(m.getValue(), 1);
                }
                System.out.println(m.getKey() + "\t" + m.getValue());
            });
            String star = Collections.max(frequency.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            System.out.println("\nStarCommitter: " + star);
        } catch (Exception e) {
            System.out.println("Insufficient data to process" + e.getMessage());
        }
    }

}

class Committer {

    String name;
    Integer x;
    Integer y;
    Integer cleanCommits;

    @Override
    public String toString() {
        return "Committer [name=" + name + ", x=" + x + ", y=" + y + "]";
    }

    public Committer(String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getCleanCommits() {
        this.cleanCommits = this.x - this.y;
        return cleanCommits;
    }
}

/*class Report{
    Integer day;
    String cleanCommitter;
    String starCommitter;

    public Report(Integer day, String cleanCommitter, String starCommitter) {
        this.day = day;
        this.cleanCommitter = cleanCommitter;
        this.starCommitter = starCommitter;
    }

    @Override
    public String toString() {
        return day+"\t"+cleanCommitter+"\t"+starCommitter+"\n";
    }   
}*/
