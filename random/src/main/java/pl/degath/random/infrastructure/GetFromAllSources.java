package pl.degath.random.infrastructure;

import pl.degath.random.RandomNumber;

import java.util.function.Supplier;

public interface GetFromAllSources extends Supplier<RandomNumber> {
}
