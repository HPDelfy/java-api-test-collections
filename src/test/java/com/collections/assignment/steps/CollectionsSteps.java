package com.collections.assignment.steps;

import com.collections.assignment.actions.CollectionActions;
import com.collections.assignment.actions.HttpActions;
import com.collections.assignment.configs.ScenarioContext;
import com.collections.assignment.models.Collections;
import com.collections.assignment.models.art.ArtObjects;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CollectionsSteps {
    @Autowired
    private HttpActions http;

    @Autowired
    private CollectionActions collections;

    @Value("${base.url}")
    private String rijksMuseumUrl;

    @Value("${key}")
    private String rijksMuseumApiKey;
    private String COLLECTIONS_ENDPOINT = "/api/language/collection";

    private String PAGE_PARAM = "p";
    private String KEY_PARAM = "key";

    private HashMap<String, String> queryParams = new HashMap<>();
    private HashMap<String, Collections> collectionResponse = new HashMap<>();
    private ObjectMapper collectionMapper = new ObjectMapper();

    @Autowired
    private ScenarioContext context;
    private Scenario scenario;


    @Before()
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("the list of collections for {string}")
    public void getListOfCollections(String language) throws IOException, InterruptedException {
        queryParams.put(KEY_PARAM, rijksMuseumApiKey);
        HttpResponse<String> actualResponse = collections.getCollections(getCollectionsUrl(queryParams, language));
        assertThat(actualResponse.statusCode()).as("verify collections status code").isEqualTo(200);
        Collections collections = collectionMapper.readValue(actualResponse.body(), Collections.class);
        collectionResponse.put(language,collections);
        context.setCollectionsData(collectionResponse);
        log.info("Retrieved the collections response successfully");
    }

    @Given("retrieve the list of collections for {string} without key")
    public void getListOfCollectionsWithoutKey(String language) throws IOException, InterruptedException {
        HttpResponse<String> actualResponse = collections.getCollections(getCollectionsUrl(queryParams, language));
        context.setCollectionsResponse(actualResponse);
    }

    @Then("the collections response status code should be {int}")
    public void collectionsResponseShouldBe(int statusCode) {
        assertThat(context.getCollectionsResponse().statusCode()).as("verify collections status code").isEqualTo(statusCode);
    }

    @Then("Collections count should match for both {string} and {string} languages")
    public void collectionCountShouldMatchForBothNLAndENLanguages(String nlLanguage, String enLanguage) {
        int nlCollectionCount = context.getCollectionsData().get(nlLanguage).getCount();
        log.info("Collections count for the language nl: "+ nlCollectionCount);
        int enCollectionCount = context.getCollectionsData().get(enLanguage).getCount();
        log.info("Collections count for the language en: "+ enCollectionCount);
        assertThat(nlCollectionCount).as("verify Collections count between languages").isEqualTo(enCollectionCount);

    }

    @Then("All the Collection detail links should work for {string}")
    public void collectionDetailLinksShouldWork(String Language) {
        queryParams.put(KEY_PARAM, rijksMuseumApiKey);
        List<ArtObjects> artObjects = context.getCollectionsData().get(Language).getArtObjects();
        List<String> links = artObjects.stream()
                .map(artObject -> {
                    try{
                        String link = artObject.getLinks().getSelf().replace("http","https");
                        HttpResponse<String> actualResponse  = collections.getCollections(appendQueryParams(link,queryParams));
                        assertThat(actualResponse.statusCode()).as("verify the status code for the link :" + link).isEqualTo(200);
                        return link;
                    } catch (Exception e) {
                        return null;

                    }
                })
                .filter(link -> link != null) // Filter out null values (if any errors occurred)
                .collect(Collectors.toList());
        log.info("verified links are:" + links);
    }

    private String getCollectionsUrl(Map<String, String> params, String language) {
        return appendUri(appendQueryParams(COLLECTIONS_ENDPOINT.replace("language", language), params));
    }

    private String appendUri(String endPoint) {
        return rijksMuseumUrl + endPoint;
    }

    public static String appendQueryParams(String endpoint, Map<String, String> params) {
        // If the map is empty, return the endpoint as is
        if (params == null || params.isEmpty()) {
            return endpoint;
        }

        // Build query string using a lambda
        String queryString = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        // Append the query string to the endpoint
        return endpoint + "?" + queryString;
    }
}
