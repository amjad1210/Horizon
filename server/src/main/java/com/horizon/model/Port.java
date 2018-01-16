package com.horizon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.beans.Transient;

/**
 * @author Amjad
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter @Setter @NoArgsConstructor
public class Port {

    private boolean active;

    @Id
    private int port;

    private String description;

    public Port(int port) {
        this.port = port;
    }

    @Transient
    public boolean isValid() {
        return port > 0 && port < 65535;
    }

}