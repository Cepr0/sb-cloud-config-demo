package io.github.cepr0.config.server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property, Property.PK> {
}
