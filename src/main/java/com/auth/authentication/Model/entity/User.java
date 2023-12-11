package com.example.authentication.Model.entity;

import com.example.authentication.Model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.List ;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User extends AbstractEntity implements UserDetails {
    private String firstName ;
    private String lastName ;
    @Column(unique = true)
    private String email ;
    private String securePassword ;
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Wallet wallet ;
    @Enumerated(EnumType.STRING)
    private Role role ;
    private Boolean enabled = false ;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name()) ;
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return securePassword;
    }

    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }
}