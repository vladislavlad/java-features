package software.darkmatter;

import software.darkmatter.java10.Java10;
import software.darkmatter.java11.Java11;
import software.darkmatter.java12.Java12;
import software.darkmatter.java14.Java14;
import software.darkmatter.java15.Java15;
import software.darkmatter.java16.Java16;
import software.darkmatter.java17.Java17;
import software.darkmatter.java21.Java21;
import software.darkmatter.java8.Java8;
import software.darkmatter.java9.Java9;

public class Main {

    private static final Java8 JAVA_8 = new Java8();
    private static final Java9 JAVA_9 = new Java9();
    private static final Java10 JAVA_10 = new Java10();
    private static final Java11 JAVA_11 = new Java11();
    private static final Java12 JAVA_12 = new Java12();
    private static final Java14 JAVA_14 = new Java14();
    private static final Java15 JAVA_15 = new Java15();
    private static final Java16 JAVA_16 = new Java16();
    private static final Java17 JAVA_17 = new Java17();
    private static final Java21 JAVA_21 = new Java21();

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
        JAVA_10.orElseThrow();

        JAVA_11.httpClient();
        JAVA_11.stringNewMethods();
        JAVA_11.fileNewMethods();
        JAVA_11.toArray();
        JAVA_11.predicateNot();
        JAVA_11.varLambda();
        JAVA_11.nestedMembers();

        JAVA_12.stringNewMethods();
        JAVA_12.teeingCollector();
        JAVA_12.compactNumberFormat();
        JAVA_12.fileMismatch();

        JAVA_14.switchExpression();

        JAVA_15.textBlock();
        JAVA_15.sealedClass();
        JAVA_15.recordClass();

        JAVA_16.dayPeriodSupport();
        JAVA_16.streamToList();

        JAVA_21.patternMatching();
        JAVA_21.patternMatchingWhen();
        JAVA_21.patternMatchingNull();
        JAVA_21.patternMatchingRecord();
        JAVA_21.patternMatchingNestedRecord();
        JAVA_21.patternMatchingInstanceOf();
    }
}
