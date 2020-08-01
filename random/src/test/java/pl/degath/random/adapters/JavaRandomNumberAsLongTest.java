package pl.degath.random.adapters;

import org.junit.jupiter.api.Test;
import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class JavaRandomNumberAsLongTest {

    @Test
    void shouldGetLongRandomNumber() {
        RandomNumberSupplier<Long> source = new JavaRandomNumberAsLong(new Random());

        Long result = source.get();

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Long.class);
    }

    @Test
    void shouldThrowExceptionWhenRandomNotSpecified() {
        Throwable thrown = catchThrowable(() -> new JavaRandomNumberAsDouble(null));

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Random has to be specified.");
    }
}