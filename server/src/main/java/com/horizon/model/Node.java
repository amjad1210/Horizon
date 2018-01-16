package com.horizon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Amjad
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter @Setter @NoArgsConstructor
public class Node {

    @JsonInclude
    private boolean online;

    @Id
    private String id;

    private String lan;

    private String hostname;

    private String user;

    private String os;

    @Transient
    private int cpu;

    @Transient
    private String ramUsage;

    @Transient
    private int ramUsagePercentage;

    @Transient
    private String uptime;

    @Transient
    private long ping;

    public Node(String id) {
        this.id = id;
    }

}
