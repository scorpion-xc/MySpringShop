/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.jdbc.simplemvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.itstep.java.lesson1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrii
 */
@Repository(value = "userDAO")
public class UserDAO {
    Properties props;
    @Autowired
    Connection conn;
    
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        
        String query = "SELECT * FROM users";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
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
    
    public boolean save(User u) {
        String query = "INSERT INTO users (name, email, password) values (?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            
            return stmt.execute();
        } catch (SQLException e) {
            return false;
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
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
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
}
