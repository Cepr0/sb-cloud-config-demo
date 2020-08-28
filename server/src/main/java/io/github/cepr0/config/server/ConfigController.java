package io.github.cepr0.config.server;

import lombok.Value;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("configs")
public class ConfigController {

    @PutMapping("/demo")
    public void demo(@RequestBody DemoConfig demoConfig) {

    }

    @Value
    static class DemoConfig {
        String text = "text";
        int num = 0;
    }
}
