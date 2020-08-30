package io.github.cepr0.config.server;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "pk")
@ToString(of = {"pk", "value"})
@Entity
@Table(name = "properties")
public class Property {

    @JsonUnwrapped
    @Id
    @EmbeddedId
    private PK pk;

    private String value;

    @Tolerate
    public Property(String application, String key, Object value) {
        this.pk = new PK(application, "default", "master", key);
        this.value = value.toString();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class PK implements Serializable {
        private String application;
        private String profile;
        private String label;
        private String key;
    }
}
