/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.util.ArrayList;
import model.rbac.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.rbac.Feature;
import model.rbac.Role;

/**
 *
 * @author LENOVO
 */
public class UserDBContext extends DBContext<User>{

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public User get(String username, String password){
        User user = null;
        PreparedStatement stm = null;
        try{
            String sql = "SELECT u.uid,u.username\n"
                    + "		,r.roleid,r.rolename\n"
                    + "		,f.featureid,f.feature_name,f.access_url,f.httpmethod,f.params\n"
                    + "FROM users u LEFT JOIN matrix_users_roles mur ON u.uid = mur.uid\n"
                    + "					  LEFT JOIN roles r ON r.roleid = mur.roleid AND r.active = 1\n"
                    + "					  LEFT JOIN matrix_roles_features mrf ON mrf.roleid = r.roleid\n"
                    + "					  LEFT JOIN features f ON f.featureid = mrf.featureid AND f.active = 1\n"
                    + "WHERE username = ? AND password = ? ORDER BY u.uid,r.roleid,f.featureid ASC";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            int c_roleid = -1;
            Role r = null;
            int c_featureid = -1;
            Feature f = null;
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setId(rs.getInt("uid"));
                    user.setUsername(username);
                    user.setDisplayname(rs.getString("displayname"));
                }
                int roleid = rs.getInt("roleid");
                int featureid = rs.getInt("featureid");
                if (roleid != c_roleid && roleid!=0) {
                    r = new Role();
                    r.setId(roleid);
                    r.setRolename(rs.getString("rolename"));
                    user.getRoles().add(r);
                    c_roleid = roleid;
                }
                if (featureid != c_featureid && featureid !=0 && roleid!=0  ) {
                    f = new Feature();
                    f.setId(featureid);
                    f.setName(rs.getString("feature_name"));
                    f.setUrl(rs.getString("access_url"));
                    f.setHtppmethod(rs.getString("httpmethod"));
                    f.setParam(rs.getString("params"));
                    r.getFeatures().add(f);
                    c_featureid = featureid;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
