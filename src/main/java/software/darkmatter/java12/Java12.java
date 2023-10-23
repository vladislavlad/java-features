package software.darkmatter.java12;

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
        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.SHORT);
        shortFormat.setMaximumFractionDigits(2);
        String formatted = shortFormat.format(2592);

        assert "2.59K".equals(formatted);
        System.out.println(formatted);

        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.LONG);
        longFormat.setMaximumFractionDigits(2);

        String formattedLong = longFormat.format(2592);
        assert "2.59 thousand".equals(formattedLong);
        System.out.println(formattedLong);
    }
}
