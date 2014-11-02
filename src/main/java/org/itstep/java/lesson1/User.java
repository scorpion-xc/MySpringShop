package org.itstep.java.lesson1;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private boolean banned;
    
    public void doSomething() throws IOException {
        System.out.println("something");
        throw new IOException("something is not working :(");
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
        if (this.banned != other.banned) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + (this.banned ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name + ", " + email + ", " + password;
    }
    
    
    
}
