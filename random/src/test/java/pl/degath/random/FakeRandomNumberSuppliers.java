package pl.degath.random;

import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FakeRandomNumberSuppliers {

    private FakeRandomNumberSuppliers() {
        throw new UnsupportedOperationException();
    }

    public static List<RandomNumberSupplier<? extends Number>> withNullSuppliers() {
        return null;
    }

    public static List<RandomNumberSupplier<? extends Number>> withNoSuppliers() {
        return Collections.emptyList();
    }

    public static List<RandomNumberSupplier<? extends Number>> withSingleSupplier() {
        return Collections.singletonList(() -> 1);
    }

    public static List<RandomNumberSupplier<? extends Number>> withTwoSuppliers() {
        return Arrays.asList(() -> 1, () -> 2);
    }

    public static List<RandomNumberSupplier<? extends Number>> withMultipleSuppliers() {
        return Arrays.asList(() -> 1, () -> 2, () -> 3);
    }

    public static List<RandomNumberSupplier<? extends Number>> withDifferentTypesOfSuppliers() {
        return Arrays.asList(() -> 10L, () -> 20, () -> 30.1f, () -> 40.2, () -> 555);
    }
}
