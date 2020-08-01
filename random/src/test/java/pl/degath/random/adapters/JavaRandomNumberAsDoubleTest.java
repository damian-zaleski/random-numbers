package pl.degath.random.adapters;

import org.junit.jupiter.api.Test;
import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class JavaRandomNumberAsDoubleTest {


    @Test
    void shouldGetDoubleRandomNumber() {
        RandomNumberSupplier<Double> source = new JavaRandomNumberAsDouble(new Random());

        Double result = source.get();

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Double.class);
    }

    @Test
    void shouldThrowExceptionWhenRandomNotSpecified() {
        Throwable thrown = catchThrowable(() -> new JavaRandomNumberAsDouble(null));

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Random has to be specified.");
    }
}