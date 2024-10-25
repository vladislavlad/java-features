package software.darkmatter.java22;

import java.util.List;
import java.util.Random;

public class Java22 {

    public void unnamedVar() {
        int size = 0;
        var list = List.of(1, 2, 3);
        for (Integer _ : list) {
            size++;
        }
        System.out.println(size);

        String s = "abc";
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException _) {
            System.out.println("Error while parsing: " + s);
        }

        var randomList = list.stream()
                             .map(_ -> new Random().nextInt())
                             .toList();
        randomList.forEach(System.out::println);
    }

    public void unnamedPattern() {
        Color color = new Color.Green();
        switch (color) {
            case Color.Green _, Color.Blue _ -> System.out.println("Color is: " + color);
            case Color.Red _ -> System.out.println("Error, color is Red");
            default -> throw new IllegalStateException("Unexpected value: " + color);
        }
    }

    sealed static class Color {

        static final class Red extends Color {}

        static final class Blue extends Color {}

        static final class Green extends Color {}
    }
}
