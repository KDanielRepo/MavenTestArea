import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Time {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        Object a = zonedDateTime;

        System.out.println( a.toString());
    }
}
