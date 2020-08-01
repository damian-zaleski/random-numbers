package pl.degath.random.adapters;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.degath.random.infrastructure.RestTemplateHttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class RandomOrgRandomNumberTest {

    private static final String API_URL = "https://www.random.org/integers/?num=1&min=1&max=10000&col=1&base=10&format=html&rnd=new&format=plain";

    @Test
    void shouldThrowExceptionWhenMissingURL() {
        Throwable thrown = catchThrowable(
                () -> new RandomOrgRandomNumber(null, getRestTemplateHttpClient())
        );

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Api URL has to be specified.");
    }

    @Test
    void shouldThrowExceptionWhenMissingClient() {
        Throwable thrown = catchThrowable(
                () -> new RandomOrgRandomNumber(API_URL, null)
        );

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("HttpClient has to be specified.");
    }

    @Test
    void shouldGetRandomNumber() {
        RandomOrgRandomNumber randomOrgRandomNumber = new RandomOrgRandomNumber(API_URL, getRestTemplateHttpClient());

        Integer result = randomOrgRandomNumber.get();

        assertThat(result).isNotNull();
    }

    private RestTemplateHttpClient getRestTemplateHttpClient() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(converter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return new RestTemplateHttpClient(restTemplate);
    }
}