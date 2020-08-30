package io.github.cepr0.config.server;

import lombok.Value;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("properties")
public class ConfigController {

    private final PropertyRepo propertyRepo;
    private final ApplicationEventPublisher publisher;

    public ConfigController(
            PropertyRepo propertyRepo,
            ApplicationEventPublisher publisher
    ) {
        this.propertyRepo = propertyRepo;
        this.publisher = publisher;
    }

    @PutMapping("/demo")
    public List<Property> demo(@Valid @RequestBody DemoConfig demoConfig) {
        List<Property> properties = propertyRepo.saveAll(List.of(
                new Property("demo-client", "demo.text", demoConfig.text),
                new Property("demo-client", "demo.num", demoConfig.num)
        ));
        publisher.publishEvent(new RefreshRemoteApplicationEvent(this, "config-service", "demo-client"));
        return properties;
    }

    @GetMapping
    public List<Property> getAll() {
        return propertyRepo.findAll();
    }

    @Value
    static class DemoConfig {
        @NotEmpty String text;
        @NotNull Integer num;
    }
}
