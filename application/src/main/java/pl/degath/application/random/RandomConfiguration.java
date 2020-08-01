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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Configuration
public class RandomConfiguration {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public JavaRandomNumberAsDouble javaRandomDouble(Random random) {
        return new JavaRandomNumberAsDouble(random);
    }

    @Bean
    public JavaRandomNumberAsLong javaRandomLong(Random random) {
        return new JavaRandomNumberAsLong(random);
    }

    @Bean
    public RestTemplateHttpClient restTemplateHttpClient() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(converter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return new RestTemplateHttpClient(restTemplate);
    }

    @Bean
    public RandomOrgRandomNumber randomOrgRandomNumber(HttpClient httpClient) {
        var apiUrl = "https://www.random.org/integers/?num=1&min=1&max=10000&col=1&base=10&format=html&rnd=new&format=plain";
        return new RandomOrgRandomNumber(apiUrl, httpClient);
    }

    @Bean
    public ApplicationService applicationService(JavaRandomNumberAsDouble randomDouble,
                                                 JavaRandomNumberAsLong randomLong,
                                                 RandomOrgRandomNumber randomOrgRandomNumber) {
        return new ApplicationService(List.of(randomDouble, randomLong, randomOrgRandomNumber));
    }
}
