package pl.degath.random;

import pl.degath.random.infrastructure.DomainObject;
import pl.degath.random.infrastructure.Validator;

import java.math.BigDecimal;
import java.util.List;

public class RandomNumber implements DomainObject {

    private final BigDecimal value;

    public RandomNumber(List<? extends Number> numbers) {
        Validator.hasSizeBiggerThanOne(numbers, "Should contain at least 2 numbers.");
        this.value = sum(numbers);
    }

    private BigDecimal sum(List<? extends Number> numbers) {
        return numbers.stream()
                .map(this::toBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal toBigDecimal(Number number) {
        return new BigDecimal(number.toString());
    }

    public BigDecimal getValue() {
        return value;
    }
}
