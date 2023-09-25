package software.darkmatter.java21;

public class Java21 {

    public void patternMatching() {
        Object o = new Object();

        switch (o) {
            case Integer i -> System.out.printf("o is a Integer: %d%n", i);
            case String s -> System.out.printf("o is a String: %s%n", s);
            default -> System.out.printf("o is something else: %s%n", o);
        }
    }
}
