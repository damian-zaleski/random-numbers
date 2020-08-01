package pl.degath.application.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.degath.random.ApplicationService;
import pl.degath.random.RandomNumber;

@RestController
@RequestMapping("api/v1/randoms")
public class RandomRestController implements RandomApi {

    @Autowired
    private ApplicationService applicationService;

    @Override
    @GetMapping("/sum")
    public RandomNumber getSum() {
        return applicationService.getSumFromAllSources();
    }
}
