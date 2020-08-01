package pl.degath.random.adapters;

import pl.degath.random.ports.RandomNumberSupplier;

import java.util.Objects;
import java.util.Random;

public class JavaRandomNumberAsLong implements RandomNumberSupplier<Long> {

    private final Random random;

    public JavaRandomNumberAsLong(Random random) {
        this.random = Objects.requireNonNull(random, "Random has to be specified.");
    }

    @Override
    public Long get() {
        return random.nextLong();
    }
}
