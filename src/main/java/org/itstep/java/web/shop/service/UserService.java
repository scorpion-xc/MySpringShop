/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.web.shop.service;

import java.sql.SQLException;
import java.util.List;
import org.itstep.java.web.shop.model.User;

/**
 *
 * @author andrii
 */
public interface UserService {

    User find(Integer id);
//    List<User> findAll(); throws SQLException;
    List<User> findAll();
    int save(User u);
//   boolean authorize(String name, String password);
    boolean deleteUser(Integer id);
    boolean edit(User u);

}
