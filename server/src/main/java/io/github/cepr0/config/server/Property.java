package io.github.cepr0.config.server;

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

    @Id
    @EmbeddedId
    private PK pk;

    @Version
    private Short version;

    private String value;

    @Tolerate
    public Property(String application, String profile, String key, String value) {
        this.pk = new PK(application, profile, "master", key);
        this.value = value;
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
