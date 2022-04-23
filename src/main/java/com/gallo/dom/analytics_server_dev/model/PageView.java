package com.gallo.dom.analytics_server_dev.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gallo.dom.analytics_server_dev.model.requests.PageViewRequest;

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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public Domain getDomain() {
        return domain;
    }

    public PageView() {
    }
    public PageView(PageViewRequest pvr, User user) {
        this.viewedAt = pvr.getTimeViewed();
        this.domain = new Domain(pvr.getDomainBase(), user);
        this.url_viewed = pvr.getPath();
    }
    public PageView(Domain d, String url){
        this.viewedAt = LocalDateTime.now();
        this.url_viewed = url;
        this.domain = d;
    }
    public PageView(LocalDateTime viewedAt, String url_viewed, Domain domain) {
        this.viewedAt = viewedAt;
        this.url_viewed = url_viewed;
        this.domain = domain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }

    public String getUrl_viewed() {
        return url_viewed;
    }

    public void setUrl_viewed(String url_viewed) {
        this.url_viewed = url_viewed;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
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
