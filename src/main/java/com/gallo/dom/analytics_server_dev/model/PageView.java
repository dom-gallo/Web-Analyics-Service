package com.gallo.dom.analytics_server_dev.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "PageView")
@Table(name = "pageview")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PageView {
    @Id
    @SequenceGenerator(
            name = "pageview_sequence_generator",
            sequenceName = "pageview_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pageview_sequence_generator"
    )
    private Long id;

    @Column(
            name = "created_at"
    )
    private LocalDateTime viewedAt;

    @Column(
            name = "url_viewed"
    )
    private String url_viewed;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public Domain getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "PageView{" +
                "id=" + id +
                ", viewedAt=" + viewedAt +
                ", url_viewed='" + url_viewed + '\'' +
                ", domain=" + domain +
                '}';
    }
}
