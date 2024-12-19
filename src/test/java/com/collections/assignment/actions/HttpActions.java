package com.collections.assignment.actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HttpActions {
    @Autowired
    private HttpClient httpClient;

    private final String contentType = "application/json";


    public HttpRequest createGetRequest(String uri){
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Content-Type", contentType);

        return builder.build();
    }

    public HttpResponse<String> executeRequest(HttpRequest request) throws IOException, InterruptedException{
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
