/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Major;
import model.business.Student;

/**
 *
 * @author LENOVO
 */
public class StudentDBContext extends DBContext<Student>{
    public ArrayList<Student> getStudentsByCourse(int cid) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT s.sid,s.sname FROM students s INNER JOIN students_courses sc ON s.sid = sc.sid\n"
                    + "						INNER JOIN courses c ON c.cid = sc.cid\n"
                    + "						WHERE c.cid = ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }
    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        Student s = new Student();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT \n" +
                            "	s.[sid] ,s.sname ,s.mid ,s.dob ,s.gender ,s.email ,s.phonenumber ,s.[address] ,s.currentterm, m.mname\n" +
                            "FROM \n" +
                            "	students s\n" +
                            "	INNER JOIN majors m ON m.mid = s.mid\n" +
                            "WHERE \n" +
                            "	s.[sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                
                Major m = new Major();
                m.setId(rs.getInt("mid"));
                m.setName(rs.getString("mname"));
                
                s.setMajor(m);
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                s.setPhonenumber(rs.getString("phonenumber"));
                s.setAddress(rs.getString("address"));
                s.setCurrterm(rs.getInt("currentterm"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
