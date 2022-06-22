package com.example.exame_backend.security;

import com.example.exame_backend.model.persisitece.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    final com.example.exame_backend.model.persisitece.UserDetails userDetails;

    public CustomUserDetails(com.example.exame_backend.model.persisitece.UserDetails userDetails) {
        this.userDetails = userDetails;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userDetails.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getAppUserRole()));
                }

        );
        return authorities;
    }


    public Long getUserId() {
        return userDetails.getUserId();
    }


    public String getRole() {



        Role role = userDetails.getRoles().iterator().next();
        return role.getAppUserRole();
    }
    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
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
}
