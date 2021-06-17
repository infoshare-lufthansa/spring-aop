package pl.infoshare.aop.broken;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrokenController {

    private final BrokenService brokenService;

    @GetMapping("break-api")
    public void breakApi() {
        brokenService.breakCode();
    }
}
