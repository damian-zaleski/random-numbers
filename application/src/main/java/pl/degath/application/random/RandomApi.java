package pl.degath.application.random;

import pl.degath.random.Sum;

/**
 * Random operations.
 */
public interface RandomApi {

    /**
     * Get a {@link Sum sum} from all suppliers
     *
     * @return a {@link Sum sum}, if at least two suppliers are found.
     */
    Sum getSum();
}