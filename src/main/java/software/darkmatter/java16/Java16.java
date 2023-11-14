package software.darkmatter.java16;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Java16 {

    public void dayPeriodSupport() {
        OffsetDateTime date = OffsetDateTime.now();
        var oldFormatter = DateTimeFormatter.ofPattern("h a");
        var newFormatter = DateTimeFormatter.ofPattern("h B");
        System.out.println("Day Period Support: " + date.format(oldFormatter));
        System.out.println("Day Period Support: " + date.format(newFormatter));
    }

    public void streamToList() {
        var stream = Stream.of(1, 2, 3, 4, 5, 6)
                           .filter(i -> i % 2 == 0);
        System.out.println("Stream toList: " + stream.toList());
    }
}
