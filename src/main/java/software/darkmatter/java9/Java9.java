package software.darkmatter.java9;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Java9 {

    public void tryWithResources() {
        try (InputStream is = new ByteArrayInputStream("Hello from try-with-resources".getBytes())) {

            while (is.available() > 0) {
                int n = Math.min(is.available(), 32);
                var bytes = is.readNBytes(n);

                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void interfacePrivateMethod() {

        interface A {

            default String getString1() {
                return getStr() + " 1";
            }

            default String getString2() {
                return getStr() + " 2";
            }

            private String getStr() {
                return "Abc";
            }
        }

        class B implements A {}
        var b = new B();

        System.out.println(b.getString1());
        System.out.println(b.getString2());
    }

    public void streamImprovement() {
        Stream.of(1, 2, 3)
              .takeWhile(i -> i < 3)
              .dropWhile(i -> i < 2)
              .forEach(System.out::println);
    }

    public void diamondAnonymousClass() {
        BiFunction<Integer, String, String> jsPlus = new BiFunction<>() {

            @Override
            public String apply(Integer a, String b) {
                return a.toString() + b;
            }
        };
        System.out.println(jsPlus.apply(1, "1"));
    }

    public void collectionsFactoryMethods() {
        List<String> list = List.of("One", "Two", "Three");
        for (String s : list) {
            System.out.println(s);
        }

        Set<String> set = Set.of("One", "Two", "Three");
        for (String s : set) {
            System.out.println(s);
        }

        Map<String, String> map = Map.of("One", "1", "Two", "2");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
