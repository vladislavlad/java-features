package software.darkmatter.java11;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java11 {

    public void httpClient() throws URISyntaxException, IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(new URI("https://postman-echo.com/get"))
                                             .GET()
                                             .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
    }

    public void stringNewMethods() {
        String s = "String\n string \n\n string";
        s.lines()
         .filter(Predicate.not(String::isBlank))
         .map(String::strip)
         .forEach(System.out::println);

        String repeated = "J11".repeat(5);
        System.out.println(repeated);
    }

    public void fileNewMethods() throws IOException {
        Path filePath = Files.writeString(Files.createTempFile("file", ".txt"), "Files.writeString text");
        String fileContent = Files.readString(filePath);
        System.out.println(fileContent);
    }

    public void toArray() {
        List<String> sampleList = Arrays.asList("Java", "11");
        String[] sampleArray = sampleList.toArray(String[]::new);
        for (String s : sampleArray) {
            System.out.println(s);
        }
    }

    public void predicateNot() {
        List<String> sampleList = Arrays.asList("Java", " ");
        sampleList.stream()
                  .filter(Predicate.not(String::isBlank))
                  .forEach(System.out::println);
    }

    public void varLambda() {
        Optional.of("String")
                .filter((var s) -> s.startsWith("Str"))
                .ifPresent(System.out::println);

    }

    public void nestedMembers() {
        Set<String> nestedMembers = Arrays.stream(FirstClass.NestedClass.class.getNestMembers())
                                          .map(Class::getName)
                                          .collect(Collectors.toSet());
        boolean containsNestedMembers = nestedMembers.contains(FirstClass.class.getName())
                                        && nestedMembers.contains(FirstClass.NestedClass.class.getName());
        System.out.println("getNestMembers() returns class and nested member: " + containsNestedMembers);
    }

    class FirstClass {

        class NestedClass {
        }
    }
}
