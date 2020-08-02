package pl.degath.application.random;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.degath.random.ApplicationService;
import pl.degath.random.Sum;

@RestController
@RequestMapping("api/v1/randoms")
@Api("Random operations")
public class RandomRestController implements RandomApi {

    private final ApplicationService applicationService;

    public RandomRestController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    @GetMapping("/sum")
    @ApiOperation("Get sum from all suppliers.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetched the sum."),
            @ApiResponse(code = 400, message = "Validation failed."),
    })
    public Sum getSum() {
        return applicationService.getSumFromAllSources();
    }
}
