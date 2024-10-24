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
}
