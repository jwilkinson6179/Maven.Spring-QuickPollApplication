package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
@Table(name = "OPTIONS")
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONS_ID")
    private Long id;
    @Column(name = "OPTIONS_VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
