package pl.degath.random.adapters;

import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Objects;
import java.util.Random;

public class JavaRandomNumberAsDouble implements RandomNumberSupplier<Double> {

    private final Random random;

    public JavaRandomNumberAsDouble(Random random) {
        this.random = Objects.requireNonNull(random, "Random has to be specified.");
    }

    @Override
    public Double get() {
        return random.nextDouble();
    }
}
