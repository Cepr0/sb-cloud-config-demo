package io.github.cepr0.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Data
@RefreshScope
@ConfigurationProperties("demo")
public class DemoProps {
    private String text = "text";
    private int num = 0;
}
