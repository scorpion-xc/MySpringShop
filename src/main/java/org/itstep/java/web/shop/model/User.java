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
    private boolean isAdmin;
    private Integer id;    
   
    public User(String name, String email, String password, boolean isAdmin, Integer id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.id = id;
    }
    
    public User(String name, String email, String password, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public User() {
        
    }    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
//----------     

    
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
//---------- 
    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
@Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + (this.isAdmin ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "name = " + name + ", \temail = " + email + ", \tpassword = " + password + ", \tisAdmin = " + isAdmin + '}' +"\n";
    }    
//----------     
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
