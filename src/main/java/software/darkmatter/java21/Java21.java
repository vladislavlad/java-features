package software.darkmatter.java21;

import java.util.Random;

public class Java21 {

    public void patternMatching() {
        Object obj = new Object();

        final String result = switch (obj) {
            case Integer i -> String.format("Integer %d", i);
            case Long l -> String.format("Long %d", l);
            case Double d -> String.format("Double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };

        System.out.println(result);
    }

    public void patternMatchingWhen() {
        Object obj = new Random().nextInt();

        final String result = switch (obj) {
            case Integer i when i > 0 -> String.format("Positive Integer: %d", i);
            case Integer i when i == 0 -> String.format("Zero Integer: %d", i);
            case Integer i -> String.format("Negative Integer: %d", i);
            default -> obj.toString();
        };

        System.out.println(result);
    }

    public void patternMatchingNull() {
        Object obj = null;

        final String result = switch (obj) {
            case null -> "Null obj";
            case Integer i -> String.format("Integer %d", i);
            case Long l -> String.format("Long %d", l);
            default -> obj.toString();
        };

        final String result2 = switch (obj) {
            case Integer i -> String.format("Integer %d", i);
            case Long l -> String.format("Long %d", l);
            case null, default -> "Unexpected state";
        };

        System.out.println(result);
        System.out.println(result2);
    }

    public record Point(
        int x,
        int y
    ) {}

    public void patternMatchingRecord() {
        Object obj = new Point(0, 2);

        switch (obj) {
            case Point(int x, int y) -> System.out.println("Point x: " + x + "; y: " + y);
            default -> System.out.println("Not a point");
        }
    }

    record ColoredPoint(Point p, Color c) {


        enum Color {RED, GREEN, BLUE;}
    }
    public void patternMatchingNestedRecord() {
        Object obj = new ColoredPoint(new Point(0, 2), ColoredPoint.Color.RED);

        if (obj instanceof ColoredPoint(Point(int x, int y), ColoredPoint.Color c)) {
            System.out.println("Point x: " + x + "; y: " + y + "; color: " + c);
        }
    }

    public void patternMatchingInstanceOf() {
        Object obj = 1;

        if (obj instanceof Integer i) {
            System.out.println("Integer i: " + i);
        }
    }
}
