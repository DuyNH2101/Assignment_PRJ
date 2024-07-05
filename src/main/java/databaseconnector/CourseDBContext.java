/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Course;

/**
 *
 * @author LENOVO
 */
public class CourseDBContext extends DBContext<Course>{
    public ArrayList<Course> getCourseByLecturerID(int lecid){
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT l.lid, l.lname, l.dob, l.phonenumber, l.email,\n"
                    + "	   c.cid, c.cname, c.semid, c.slots,\n"
                    + "	   s.subid, s.subname, s.subcodename, s.defaultterm, s.credit\n"
                    + "FROM \n"
                    + "	courses c JOIN lecturers l ON c.lid = l.lid\n"
                    + "			  JOIN subjects s ON s.subid = c.subid\n"
                    + "WHERE\n"
                    + " 	l.lid = ?";
            stm = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
    @Override
    public ArrayList<Course> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
