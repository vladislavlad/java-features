package software.darkmatter.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    public void forEach() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.forEach(System.out::println);
    }

    public void removeIf() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        numbers.removeIf(i -> i < 8);
        numbers.forEach(System.out::println);
    }

    public void optional() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        Optional<Integer> optionalN = numbers
                .stream()
                .findFirst();

        optionalN.map(n -> "N: " + n)
                 .ifPresent(System.out::println);
    }

    public void time() {
        Duration duration = Duration.ofMinutes(10);

        Instant instantNow = Instant.now();
        Instant instant10MinAgo = instantNow.minus(duration);
        System.out.println(instant10MinAgo);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);

        LocalDateTime localDateTime10MinAgo = localDateTime.minus(duration);
        System.out.println(localDateTime10MinAgo);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OffsetDateTime offsetDateTime10MinAgo = offsetDateTime.minus(duration);
        System.out.println(offsetDateTime10MinAgo);
    }

    public void defaultMethod() {

        interface Named {

            default void printClassName() {
                System.out.println(this.getClass().getSimpleName());
            }
        }

        class SimpleNamed implements Named {}

        Named named = new SimpleNamed();
        named.printClassName();
    }

    public void completableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future.thenApplyAsync(String::toUpperCase);
        String s = future.get();
        System.out.println(s);
    }

    public void parallelSort() {
        int[] array = new int[5];
        for (int i = 0; i < 5; i++) {
            array[i] = 60 / (i + 1);
        }
        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));
    }
}
