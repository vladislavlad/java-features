package software.darkmatter.java26;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java26 {

    /*
     * JEP 517: HTTP/3 for the HTTP Client API (GA)
     */
    public void http3() throws Exception {
        // Opt-in to HTTP/3 at the client level
        var clientBuilder = HttpClient.newBuilder()
                                      .version(HttpClient.Version.HTTP_3);

        try (var client = clientBuilder.build()) {
            var request = HttpRequest.newBuilder(URI.create("https://www.http3check.net/"))
                                     .GET()
                                     .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP/3 response status: " + response.statusCode());
        }
    }

    /*
     * JEP 500: Prepare to Make Final Mean Final (GA)
     */
    public void finalFieldRestrictions() throws Exception {
        class C {
            final int x;

            C() {x = 100;}
        }

        C obj = new C();
        System.out.println("Before mutation: " + obj.x);

        Field f = C.class.getDeclaredField("x");
        f.setAccessible(true);

        // In JDK 26 this issues a warning (default --illegal-final-field-mutation=warn)
        // Use --enable-final-field-mutation=ALL-UNNAMED to suppress
        try {
            f.set(obj, 200);
            System.out.println("After mutation: " + obj.x);
        } catch (IllegalAccessException e) {
            System.out.println("Mutation blocked: " + e.getMessage());
        }
    }
}
