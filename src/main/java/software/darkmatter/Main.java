package software.darkmatter;

import software.darkmatter.java10.Java10;
import software.darkmatter.java11.Java11;
import software.darkmatter.java8.Java8;
import software.darkmatter.java9.Java9;

public class Main {

    private static final Java8 JAVA_8 = new Java8();
    private static final Java9 JAVA_9 = new Java9();
    private static final Java10 JAVA_10 = new Java10();
    private static final Java11 JAVA_11 = new Java11();

    public static void main(String[] args) throws Exception {
        JAVA_8.lambda();
        JAVA_8.methodReference();
        JAVA_8.streamApi();
        JAVA_8.forEach();
        JAVA_8.removeIf();
        JAVA_8.optional();
        JAVA_8.time();
        JAVA_8.defaultMethod();
        JAVA_8.completableFuture();
        JAVA_8.parallelSort();

        JAVA_9.tryWithResources();
        JAVA_9.interfacePrivateMethod();
        JAVA_9.diamondAnonymousClass();
        JAVA_9.streamImprovement();
        JAVA_9.collectionsFactoryMethods();

        JAVA_10.var();
        JAVA_10.immutableCollections();
        JAVA_10.collectionCopyOf();

        JAVA_11.httpClient();
    }
}
