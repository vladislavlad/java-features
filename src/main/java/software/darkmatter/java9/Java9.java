package software.darkmatter.java9;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java9 {

    public void httpClient() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(new URI("https://postman-echo.com/get"))
                                         .GET()
                                         .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void tryWithResources() {
        try (InputStream is = new ByteArrayInputStream("Hello from try-with-resources".getBytes())) {

            while (is.available() > 0) {
                int n = Math.min(is.available(), 32);
                var bytes = is.readNBytes(n);

                System.out.print(new String(bytes));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
