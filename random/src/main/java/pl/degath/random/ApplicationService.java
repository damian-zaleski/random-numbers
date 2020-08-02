package pl.degath.random;

import pl.degath.random.infrastructure.GetSumFromAllSourcesHandler;
import pl.degath.random.infrastructure.Validator;
import pl.degath.random.ports.RandomNumberSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService implements GetSumFromAllSourcesHandler {

    private final List<RandomNumberSupplier<? extends Number>> suppliers;

    public ApplicationService(List<RandomNumberSupplier<? extends Number>> suppliers) {
        this.suppliers = Validator.hasMoreThanTwoSuppliers(suppliers, "Should contain at least 2 suppliers.");
    }

    @Override
    public Sum getSumFromAllSources() {
        var numbers = suppliers.stream()
                .map(RandomNumberSupplier::get)
                .collect(Collectors.toList());
        return new Sum(numbers);
    }
}
