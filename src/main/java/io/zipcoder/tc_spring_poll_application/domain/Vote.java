package io.zipcoder.tc_spring_poll_application.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "OPTIONS_ID")
    private Option option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
