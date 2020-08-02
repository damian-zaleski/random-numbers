package pl.degath.random;

import org.junit.jupiter.api.Test;
import pl.degath.random.infrastructure.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.degath.random.FakeRandomNumberSuppliers.withDifferentTypesOfSuppliers;
import static pl.degath.random.FakeRandomNumberSuppliers.withMultipleSuppliers;
import static pl.degath.random.FakeRandomNumberSuppliers.withNoSuppliers;
import static pl.degath.random.FakeRandomNumberSuppliers.withNullSuppliers;
import static pl.degath.random.FakeRandomNumberSuppliers.withSingleSupplier;
import static pl.degath.random.FakeRandomNumberSuppliers.withTwoSuppliers;

class ApplicationServiceTest {

    private ApplicationService applicationService;

    @Test
    void shouldThrowExceptionWhenNullSuppliers() {
        Throwable thrown = catchThrowable(() -> applicationService = new ApplicationService(withNullSuppliers()));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 suppliers.");
    }

    @Test
    void shouldThrowExceptionWhenNoSuppliers() {
        Throwable thrown = catchThrowable(() -> applicationService = new ApplicationService(withNoSuppliers()));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 suppliers.");
    }


    @Test
    void shouldThrowExceptionWhenSingleSupplier() {
        Throwable thrown = catchThrowable(() -> applicationService = new ApplicationService(withSingleSupplier()));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Should contain at least 2 suppliers.");
    }

    @Test
    void shouldGetRandomNumberWithTwoSuppliers() {
        applicationService = new ApplicationService(withTwoSuppliers());

        Sum result = applicationService.getSumFromAllSources();

        assertThat(valueFrom(result)).isEqualTo("3");
    }

    @Test
    void shouldGetRandomNumberWithMultipleSuppliers() {
        applicationService = new ApplicationService(withMultipleSuppliers());

        Sum result = applicationService.getSumFromAllSources();

        assertThat(valueFrom(result)).isEqualTo("6");
    }

    @Test
    void shouldGetRandomNumberWithSuppliersWithDifferentTypes() {
        applicationService = new ApplicationService(withDifferentTypesOfSuppliers());

        Sum result = applicationService.getSumFromAllSources();

        assertThat(valueFrom(result)).isEqualTo("655.3");
    }

    private String valueFrom(Sum result) {
        return result.getValue().toString();
    }
}