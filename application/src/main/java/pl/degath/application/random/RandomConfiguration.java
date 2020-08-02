package pl.degath.application.random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.degath.random.ApplicationService;
import pl.degath.random.adapters.JavaRandomNumberAsDouble;
import pl.degath.random.adapters.JavaRandomNumberAsLong;
import pl.degath.random.adapters.RandomOrgRandomNumber;
import pl.degath.random.infrastructure.HttpClient;
import pl.degath.random.infrastructure.RestTemplateHttpClient;
import pl.degath.random.ports.RandomNumberSupplier;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Configuration
public class RandomConfiguration {

    private static final String RANDOM_ORG_URL = "https://www.random.org/integers/?num=1&min=1&max=10000&col=1&base=10&format=html&rnd=new&format=plain";

    @Bean
    public List<RandomNumberSupplier<? extends Number>> suppliers(Random random, HttpClient httpClient) {
        var javaRandomDouble = new JavaRandomNumberAsDouble(random);
        var javaRandomLong = new JavaRandomNumberAsLong(random);
        var randomOrgInteger = new RandomOrgRandomNumber(RANDOM_ORG_URL, httpClient);

        return List.of(javaRandomDouble, javaRandomLong, randomOrgInteger);
    }

    @Bean
    public Random random() {
        return new SecureRandom();
    }

    @Bean
    public HttpClient restTemplateHttpClient() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(converter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return new RestTemplateHttpClient(restTemplate);
    }

    @Bean
    public ApplicationService applicationService(List<RandomNumberSupplier<? extends Number>> suppliers) {
        return new ApplicationService(suppliers);
    }
}
