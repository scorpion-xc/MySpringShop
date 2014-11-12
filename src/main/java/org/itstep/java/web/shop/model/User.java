package org.itstep.java.web.shop.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements Serializable, UserDetails {
    static GrantedAuthority auth = new GrantedAuthority() {
        @Override
        public String getAuthority() {
            return "ROLE_USER";
        }
    };
    
    private String name;
    private String email;
    private String password;
    private boolean banned;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public User() {
        
    }
    
    public boolean isBanned() {
        return banned;
    }

    void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(auth);
        return list;
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
        return true;
    }
    
    
    
}
