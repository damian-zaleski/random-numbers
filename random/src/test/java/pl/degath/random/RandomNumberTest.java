package pl.degath.random;

import org.junit.jupiter.api.Test;
import pl.degath.random.infrastructure.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.degath.random.RandomNumberFixtures.withDifferentTypesOfNumbers;
import static pl.degath.random.RandomNumberFixtures.withMoreThanTwoNumbers;
import static pl.degath.random.RandomNumberFixtures.withNegativeNumbers;
import static pl.degath.random.RandomNumberFixtures.withSingleNumber;
import static pl.degath.random.RandomNumberFixtures.withTwoNumbers;
import static pl.degath.random.RandomNumberFixtures.withTwoReallyLongNumbers;

class RandomNumberTest {

    @Test
    void shouldThrowExceptionWhenNoNumbers() {
        Throwable thrown = catchThrowable(() -> new RandomNumber(null));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 numbers.");
    }

    @Test
    void shouldThrowExceptionWhenSingleNumber() {
        Throwable thrown = catchThrowable(() -> new RandomNumber(withSingleNumber()));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 numbers.");
    }

    @Test
    void shouldSumTwoNumbers() {
        var result = new RandomNumber(withTwoNumbers());

        assertThat(valueFrom(result)).isEqualTo("3");
    }

    @Test
    void shouldSumMoreThanTwoValues() {
        var result = new RandomNumber(withMoreThanTwoNumbers());

        assertThat(valueFrom(result)).isEqualTo("6");
    }

    @Test
    void shouldSumDifferentTypes() {
        var result = new RandomNumber(withDifferentTypesOfNumbers());

        assertThat(valueFrom(result)).isEqualTo("655.3");
    }

    @Test
    void shouldSumReallyBigNumbers() {
        var result = new RandomNumber(withTwoReallyLongNumbers());

        assertThat(valueFrom(result)).isEqualTo("18446744073709551614");
    }

    @Test
    void shouldAcceptNegativeValues() {
        var result = new RandomNumber(withNegativeNumbers());
        assertThat(valueFrom(result)).isEqualTo("-6");
    }

    private String valueFrom(RandomNumber result) {
        return result.getValue().toString();
    }
}