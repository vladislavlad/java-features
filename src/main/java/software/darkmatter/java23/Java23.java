package software.darkmatter.java23;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class Java23 {

    /*
     * JEP 442: Foreign Function & Memory API (GA)
     */
    public void ffmApi() {
        try (var allocator = Arena.ofConfined()) {

            // Allocate memory from a string
            MemorySegment greeting = allocator.allocateFrom("Hello from FFM!");
            System.out.println("String: " + greeting.getString(0));

            // Allocate an array of doubles using allocateFrom varargs
            MemorySegment segment = allocator.allocateFrom(ValueLayout.JAVA_DOUBLE, 1.0, 2.0, 3.0, 4.0, 5.0);

            for (long i = 0; i < 5; i++) {
                double value = segment.getAtIndex(ValueLayout.JAVA_DOUBLE, i);
                System.out.println("value[" + i + "] = " + value);
            }

            // Struct layout with VarHandle
            var pointLayout = MemoryLayout.structLayout(
                ValueLayout.JAVA_INT.withName("x"),
                ValueLayout.JAVA_INT.withName("y")
            );

            MemorySegment point = allocator.allocate(pointLayout);

            var xHandle = pointLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
            var yHandle = pointLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));

            xHandle.set(point, 0L, 10);
            yHandle.set(point, 0L, 20);

            int x = (int) xHandle.get(point, 0L);
            int y = (int) yHandle.get(point, 0L);
            System.out.println("Point(" + x + ", " + y + ")");
        }
    }
}
