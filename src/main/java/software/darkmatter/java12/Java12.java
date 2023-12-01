package software.darkmatter.java12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java12 {

    public void stringNewMethods() {
        var s = "Just a string";
        s = s.indent(4);
        System.out.println("String ident works like: " + s);

        String oneString = " 1 ";
        var l = s.transform(z -> Long.parseLong(oneString.trim()));
        System.out.println("String transform works like: " + l);
    }

    public void teeingCollector() {
        double result = Stream.of(1, 2, 3, 4, 5)
                              .collect(
                                  Collectors.teeing(
                                      Collectors.summingDouble(i -> i),
                                      Collectors.counting(),
                                      (sum, count) -> sum / count)
                              );
        System.out.println("Result: " + result);
    }

    public void compactNumberFormat() {
        System.out.println("NumberFormat.Style.SHORT");
        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        shortFormat.setMaximumFractionDigits(2);
        String formatted = shortFormat.format(2592);

        assert "2.59K".equals(formatted);
        System.out.println(formatted);

        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        longFormat.setMaximumFractionDigits(2);

        String formattedLong = longFormat.format(2592);
        assert "2.59 thousand".equals(formattedLong);
        System.out.println(formattedLong);
    }

    public void fileMismatch() throws IOException {
        Path filePath1 = Files.createTempFile("file1", ".txt");
        Path filePath2 = Files.createTempFile("file2", ".txt");
        Files.writeString(filePath1, "Java 12");
        Files.writeString(filePath2, "Java 12");

        long mismatch = Files.mismatch(filePath1, filePath2);
        assert mismatch == -1;
    }
}
