package pl.degath.random.infrastructure;

import pl.degath.random.ports.RandomNumberSupplier;

import java.util.List;

public class Validator {

    private Validator() {
        throw new UnsupportedOperationException();
    }

    public static void hasSizeBiggerThanOne(List<? extends Number> suppliers, String message) {
        if (suppliers == null || suppliers.size() <= 1) {
            throw new ValidationException(message);
        }
    }

    public static List<RandomNumberSupplier<? extends Number>> hasMoreThanTwoSuppliers(List<RandomNumberSupplier<? extends Number>> suppliers, String message) {
        if (suppliers == null || suppliers.size() <= 1) {
            throw new ValidationException(message);
        }
        return suppliers;
    }

    public static String notBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(message);
        }
        return value;
    }
}
