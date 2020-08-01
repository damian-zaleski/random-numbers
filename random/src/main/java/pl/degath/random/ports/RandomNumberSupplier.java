package pl.degath.random.ports;

public interface RandomNumberSupplier<T extends Number> {
    T get();
}
