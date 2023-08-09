package software.darkmatter.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Java8 {

    public void lambda() {
        Function<String, String> f = (s) -> "String: " + s;
        String r = f.apply("hello");
        System.out.println(r);
    }

    public void methodReference() {
        Function<String, String> f = String::trim;
        String r = f.apply(" hello ");
        System.out.println(r);
    }

    public void streamApi() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Integer r = numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .reduce(Integer::sum).orElse(0);
        System.out.println(r);
    }
}
