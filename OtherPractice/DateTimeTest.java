package OtherPractice;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {

    public static void main(String[] args) {
        ZoneId zId= ZoneId.systemDefault();
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(2021, 01, 1, 00, 00, 01);

        System.out.println(ZonedDateTime.of(ldt1, zId));
        System.out.println(ZonedDateTime.of(ldt2, zId)); 
        
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("YYYY/MM/dd Z");
        System.out.println(sdf.format(Instant.now()));
        System.out.println(sdf.format(ZonedDateTime.of(ldt1, zId).toLocalDate()));
    }
}
