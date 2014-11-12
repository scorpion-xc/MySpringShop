package org.itstep.java.web.shop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.itstep.java.web.shop.model.Category;
import org.itstep.java.web.shop.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "goodDAO")
public class GoodDAO implements GoodService {

    @Autowired
    Connection conn;
    
    @Override
    public List<Good> getList(Integer id) {
        List<Good> goods = new ArrayList<>();
        
        String query = "SELECT * FROM goods where category_id=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Good g = new Good();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCatId(rs.getInt("category_id"));
                g.setPrice(rs.getFloat("price"));
                goods.add(g);
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
        
        return goods;
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> cats = new ArrayList<>();
        
        String query = "SELECT * FROM categories";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Category g = new Category();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                cats.add(g);
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
        
        return cats;
    }

    @Override
    public Good find(Integer id) {
        
        String query = "SELECT * FROM goods where id=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Good g = new Good();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCatId(rs.getInt("category_id"));
                g.setPrice(rs.getFloat("price"));
                return g;
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
        
        return null;
    }
    
}
