package com.gallo.dom.analytics_server_dev.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
/*
    User model that gets mapped to DB
    TODO:
        1. Set unique constrains
        2. Allow for multiple domains

 */

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity(name = "User")
// Set name of table in DB
@Table(
        name = "user_primary"
)
public class User implements UserDetails {

    @Id
    // Create a name sequence generator
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            // Increase user id's by 1 for every additional insert
            allocationSize = 1
    )
    // Actually generate the value here
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            //Using generator above
            generator = "user_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    // add constraints later
    @Column(
            name = "email_address",
            unique = true
    )
    private String emailAddress;

    @Column(
            name = "password"
    )
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "domain_id",
            referencedColumnName = "id"

    )
    private Domain domain;

    @Column(
            name = "created_at"
    )
    private LocalDateTime created_at;

    public User() {
    }

    public User(String emailAddress, String password, LocalDateTime created_at) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", domain=" + domain +
                ", created_at=" + created_at +
                '}';
    }
}
