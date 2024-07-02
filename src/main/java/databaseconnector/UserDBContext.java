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
import model.business.Lecturer;
import model.business.Major;
import model.business.Student;
import model.rbac.Feature;
import model.rbac.Manager;
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
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        try{
            String sql1 = "SELECT u.uid,u.username,\n" +
                        "	r.roleid,r.rolename,\n" +
                        "	f.featureid,f.feature_name,f.access_url,f.httpmethod,f.params\n" +
                        "FROM users u LEFT JOIN roles r ON r.roleid = u.[uid] AND r.active = 1\n" +
                        "		LEFT JOIN matrix_roles_features mrf ON mrf.roleid = r.roleid\n" +
                        "		LEFT JOIN features f ON f.featureid = mrf.featureid AND f.active = 1\n" +
                        "WHERE username = ? AND password = ? ORDER BY u.uid,r.roleid,f.featureid ASC";
            stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, username);
            stm1.setString(2, password);
            stm1.executeQuery();
            ResultSet rs1 = stm1.executeQuery();
            int c_roleid = -1;
            Role r = null;
            int c_featureid = -1;
            Feature f = null;
            while (rs1.next()) {
                if (user == null) {
                    user = new User();
                    user.setId(rs1.getInt("uid"));
                    user.setUsername(username);
                    user.setDisplayname(rs1.getString("displayname"));
                }
                int roleid = rs1.getInt("roleid");
                int featureid = rs1.getInt("featureid");
                if (roleid != c_roleid && roleid!=0) {
                    r = new Role();
                    r.setId(roleid);
                    String rolename = rs1.getString("rolename");
                    r.setRolename(rolename);
                    user.setRole(r);
                    c_roleid = roleid;
                    switch (rolename) {
                        case "lecturer":{
                            String sql2 = "SELECT l.lid, l.lname, l.dob, l.email, l.phonenumber\n" +
                                            "FROM lecturers_users lu JOIN lecturers l ON l.lid = lu.lid\n" +
                                            "				 JOIN users u ON lu.[uid] = u.[uid]\n" +
                                            "WHERE u.username = ? AND u.[password] = ?";
                            stm2 = connection.prepareStatement(sql2);
                            stm2.setString(1, username);
                            stm2.setString(2, password);
                            ResultSet rs2 = stm2.executeQuery();
                            Lecturer l = new Lecturer();
                            l.setId(rs2.getInt("lid"));
                            l.setName(rs2.getString("lname"));
                            l.setDob(rs2.getDate("dob"));
                            l.setEmail(rs2.getString("email"));
                            l.setPhonenumber(rs2.getString("phonenumber"));
                            user.setLec(l);
                            break;
                        }
                        case "student":{
                            String sql2 = "SELECT s.[sid], s.sname, s.gender, s.mid, s.email, s.phonenumber, s.currentterm, s.[address], s.dob,\n"
                                    + "           m.mid, m.mcodename, m.mname\n"
                                    + "FROM students_users su JOIN students s ON s.[sid] = su.[sid]\n"
                                    + "				 JOIN users u ON su.[uid] = u.[uid]\n"
                                    + "				 JOIN majors m ON m.mid = s.mid\n"
                                    + "WHERE u.username = ? AND u.[password] = ?  ";
                            stm2 = connection.prepareStatement(sql2);
                            stm2.setString(1, username);
                            stm2.setString(2, password);
                            ResultSet rs2 = stm2.executeQuery();
                            Student s = new Student();
                            s.setId(rs2.getInt("sid"));
                            s.setName(rs2.getString("sname"));
                            s.setGender(rs2.getBoolean("gender"));
                            
                            Major m = new Major();
                            m.setId(rs2.getInt("mid"));
                            m.setCodename(rs2.getString("mcodename"));
                            m.setName(rs2.getString("mname"));
                            
                            s.setMajor(m);
                            s.setEmail(rs2.getString("email"));
                            s.setPhonenumber(rs2.getString("phonenumber"));
                            s.setCurrterm(rs2.getInt("currentterm"));
                            s.setAddress(rs2.getString("address"));
                            s.setDob(rs2.getDate("dob"));
                            user.setStudent(s);
                            break;
                        }
                        case "manager":{
                            Manager m = new Manager();
                            user.setManager(m);
                            break;
                        }
                        default:{
                            throw new SQLException();
                            
                        }
                    }
                }
                if (featureid != c_featureid && featureid !=0 && roleid!=0  ) {
                    f = new Feature();
                    f.setId(featureid);
                    f.setName(rs1.getString("feature_name"));
                    f.setUrl(rs1.getString("access_url"));
                    f.setHtppmethod(rs1.getString("httpmethod"));
                    f.setParam(rs1.getString("params"));
                    r.getFeatures().add(f);
                    c_featureid = featureid;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                stm1.close();
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
