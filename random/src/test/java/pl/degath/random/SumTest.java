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

class SumTest {

    @Test
    void shouldThrowExceptionWhenNoNumbers() {
        Throwable thrown = catchThrowable(() -> new Sum(null));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 numbers.");
    }

    @Test
    void shouldThrowExceptionWhenSingleNumber() {
        Throwable thrown = catchThrowable(() -> new Sum(withSingleNumber()));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 numbers.");
    }

    @Test
    void shouldSumTwoNumbers() {
        var result = new Sum(withTwoNumbers());

        assertThat(valueFrom(result)).isEqualTo("3");
    }

    @Test
    void shouldSumMoreThanTwoValues() {
        var result = new Sum(withMoreThanTwoNumbers());

        assertThat(valueFrom(result)).isEqualTo("6");
    }

    @Test
    void shouldSumDifferentTypes() {
        var result = new Sum(withDifferentTypesOfNumbers());

        assertThat(valueFrom(result)).isEqualTo("655.3");
    }

    @Test
    void shouldSumReallyBigNumbers() {
        var result = new Sum(withTwoReallyLongNumbers());

        assertThat(valueFrom(result)).isEqualTo("18446744073709551614");
    }

    @Test
    void shouldAcceptNegativeValues() {
        var result = new Sum(withNegativeNumbers());
        assertThat(valueFrom(result)).isEqualTo("-6");
    }

    private String valueFrom(Sum result) {
        return result.getValue().toString();
    }
}