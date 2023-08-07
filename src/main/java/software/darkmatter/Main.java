package software.darkmatter;

import software.darkmatter.java8.Java8;
import software.darkmatter.java9.Java9;

public class Main {

    private static final Java8 JAVA_8 = new Java8();
    private static final Java9 JAVA_9 = new Java9();

    public static void main(String[] args) throws Exception {
        JAVA_8.lambda();
        JAVA_8.methodReference();

        JAVA_9.httpClient();
        JAVA_9.tryWithResources();
    }
}
