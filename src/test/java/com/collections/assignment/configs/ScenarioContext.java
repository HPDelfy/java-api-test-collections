package com.collections.assignment.configs;

import java.net.http.HttpResponse;
import java.util.HashMap;

import com.collections.assignment.models.Collections;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ScenarioContext {
    @Getter
    @Setter
    private HashMap<String, Collections> collectionsData;

    @Getter
    @Setter
    private HttpResponse<String> collectionsResponse;
}
