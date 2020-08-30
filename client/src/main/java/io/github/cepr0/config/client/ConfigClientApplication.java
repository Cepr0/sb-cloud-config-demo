package io.github.cepr0.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(DemoProps.class)
public class ConfigClientApplication {

    private final DemoProps props;

    public ConfigClientApplication(DemoProps props) {
        this.props = props;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @GetMapping("/demo")
    public DemoProps demo() {
        return props;
    }
}
