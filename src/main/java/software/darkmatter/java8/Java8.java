package software.darkmatter.java8;

import java.util.function.Function;

public class Java8 {

    public void lambda() {
        Function<String, String> f = (s) -> "String: " + s;
        String r = f.apply("hello");
    }

    public void methodReference() {
        Function<String, String> f = String::trim;
        String r = f.apply(" hello ");
    }
}
