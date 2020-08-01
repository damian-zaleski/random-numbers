package pl.degath.random.adapters;

import pl.degath.random.infrastructure.HttpClient;
import pl.degath.random.infrastructure.Response;
import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Objects;


public class RandomOrgRandomNumber implements RandomNumberSupplier<Integer> {

    private final String apiUrl;
    private final HttpClient httpClient;

    public RandomOrgRandomNumber(String apiUrl, HttpClient httpClient) {
        this.apiUrl = Objects.requireNonNull(apiUrl, "Api URL has to be specified.");
        this.httpClient = Objects.requireNonNull(httpClient, "HttpClient has to be specified.");
    }

    @Override
    public Integer get() {
        Response response = httpClient.getResponse(apiUrl);
        String body = response.getBody();
        return Integer.parseInt(body);
    }
}
