package pl.degath.random.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RestTemplateHttpClient implements HttpClient {

    private final RestTemplate restTemplate;

    public RestTemplateHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Response getResponse(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return new Response(Objects.requireNonNull(response.getBody()));

    }
}