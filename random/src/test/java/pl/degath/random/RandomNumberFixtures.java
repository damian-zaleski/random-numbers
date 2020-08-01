package pl.degath.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RandomNumberFixtures {

    private RandomNumberFixtures() {
        throw new UnsupportedOperationException();
    }

    public static List<? extends Number> withSingleNumber() {
        return Collections.singletonList(123);
    }

    public static List<? extends Number> withTwoNumbers() {
        return Arrays.asList(1, 2);
    }

    public static List<? extends Number> withMoreThanTwoNumbers() {
        return Arrays.asList(1, 2, 3);
    }

    public static List<? extends Number> withTwoReallyLongNumbers() {
        return Arrays.asList(Long.MAX_VALUE, Long.MAX_VALUE);
    }

    public static List<? extends Number> withNegativeNumbers() {
        return Arrays.asList(-3, -3);
    }

    public static List<? extends Number> withDifferentTypesOfNumbers() {
        return Arrays.asList(10L, 20, 30.1f, 40.2, 555);
    }


}
