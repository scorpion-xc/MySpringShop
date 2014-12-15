/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.web.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import org.itstep.java.web.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrii
 */
@Repository(value = "userDAO")
public class UserDAO implements UserService, UserDetailsService {
    Properties props;
    @Autowired
    Connection conn;
    
    @Override
//    public List<User> findAll() throws SQLException {
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        
        String query = "SELECT * FROM users";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
//----------                                
                u.setIsAdmin(rs.getBoolean("isAdmin"));
//----------                                
                users.add(u);
            }
            
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
        
        return users;
    }
    
//---------- 
    @Override
    public boolean deleteUser(Integer id) {
        
        String query = "DELETE FROM users where id=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the ststement! " + e.getMessage());
            }
        }       
    }   
//---------- 
    
    @Override
    public int save(User u) {
        String query = "INSERT INTO users (name, email, password, isAdmin) values (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());            
            stmt.setBoolean(4, u.isIsAdmin());
           
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage() );
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
    }
    
    @Override
    public boolean edit(User u) {   
        String query = "UPDATE users SET name=?, email=?, password=?,isAdmin=? where id=?";
        PreparedStatement stmt = null;
            try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setBoolean(4, u.isIsAdmin());
            stmt.setInt(5, u.getId());
            //stmt.setInt(5, 27);
            
            return stmt.execute();
         
        }catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage());
            }
        }
    }
    
    @Override
    public User find(Integer id) {
        User user = null;
        
        String query = "SELECT * FROM users where id = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
        
        return user;
    }
    
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = null;
        
        String query = "SELECT * FROM users where email = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, string);
            rs = stmt.executeQuery();


            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
//---------- 
                user.setIsAdmin(rs.getBoolean("isAdmin"));
//---------- 
            }
        } catch (SQLException e) {
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
        if (user == null) throw new UsernameNotFoundException("User " + string + " not found!");
        return user;
    }
}
