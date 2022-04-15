package com.gallo.dom.analytics_server_dev.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity(name = "Domain")
@Table(name = "domain")
public class Domain {
    @Id
    @SequenceGenerator(
            name = "domain_sequence_generator",
            sequenceName = "domain_id_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "domain_id_sequence_generator"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "domain_base"
    )
    private String domainBase;

    @OneToOne(
            mappedBy = "domain"
    )
    private User user;

    @OneToMany(mappedBy = "domain")
    private List<PageView> pageViewList;

    public Domain() {
    }

    public Domain(String domainBase, User user) {
        this.domainBase = domainBase;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainBase() {
        return domainBase;
    }

    public void setDomainBase(String domainBase) {
        this.domainBase = domainBase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PageView> getPageViewList() {
        return pageViewList;
    }

    public void setPageViewList(List<PageView> pageViewList) {
        this.pageViewList = pageViewList;
    }

//    @Override
//    public String toString() {
//        return "Domain{" +
//                "id=" + id +
//                ", domainBase='" + domainBase + '\'' +
//                ", user=" + user +
//                ", pageViewList=" + pageViewList +
//                '}';
//    }
}
