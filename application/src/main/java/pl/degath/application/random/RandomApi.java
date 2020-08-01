package pl.degath.application.random;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.degath.random.RandomNumber;

public interface RandomApi {

    @ApiOperation("Get sum from all random suppliers.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetched a sum from all random suppliers."),
            @ApiResponse(code = 400, message = "Validation failed."),
    })
    RandomNumber getSum();
}