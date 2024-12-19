package com.collections.assignment.actions;
import com.collections.assignment.configs.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class CollectionActions {
    @Autowired
    private ScenarioContext context;

    @Autowired
    private HttpActions http;


    private final String contentType = "application/json";
    public HttpResponse<String> getCollections(String url) throws IOException, InterruptedException {
        log.info("get collection request url:"+url);
        context.getScenario().log("get collection request url:"+url);
        HttpRequest request = http.createGetRequest(url);
        HttpResponse<String> response = http.executeRequest(request);
        log.info("get collections response :" + response.body());
        context.getScenario().log("get collections response :" + response.body());
        return response;
    }
}
