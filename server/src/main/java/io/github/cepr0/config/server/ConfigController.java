package io.github.cepr0.config.server;

import lombok.Value;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("properties")
public class ConfigController implements ApplicationEventPublisherAware {

    public static final String DESTINATION_SERVICE = "demo-client";

    private ApplicationEventPublisher publisher;
    
    private final PropertyRepo propertyRepo;
    private final BusProperties busProperties;

    public ConfigController(PropertyRepo propertyRepo, BusProperties busProperties) {
        this.propertyRepo = propertyRepo;
        this.busProperties = busProperties;
    }

    @PutMapping("/demo")
    public List<Property> demo(@Valid @RequestBody DemoConfig demoConfig) {
        List<Property> properties = propertyRepo.saveAll(List.of(
                new Property(DESTINATION_SERVICE, "demo.text", demoConfig.text),
                new Property(DESTINATION_SERVICE, "demo.num", demoConfig.num)
        ));

        String busId = busProperties.getId();
        publisher.publishEvent(new RefreshRemoteApplicationEvent(this, busId, DESTINATION_SERVICE));
        return properties;
    }

    @GetMapping
    public List<Property> getAll() {
        return propertyRepo.findAll();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Value
    static class DemoConfig {
        @NotEmpty String text;
        @NotNull Integer num;
    }
}
