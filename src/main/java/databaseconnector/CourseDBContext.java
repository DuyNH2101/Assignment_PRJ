/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Course;
import model.business.Lecturer;
import model.business.Semester;
import model.business.Subject;

/**
 *
 * @author LENOVO
 */
public class CourseDBContext extends DBContext<Course>{
    public ArrayList<Course> getCoursesByLecturerID(int lecid){
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT c.cid, c.cname, c.slots,\n"
                    + "	   l.lid, l.lname, l.dob, l.phonenumber, l.email,\n"
                    + "	   sub.subid, sub.subname, sub.subcodename, sub.defaultterm, sub.credit,\n"
                    + "	   sem.semid, sem.[year], sem.season, sem.active, sem.[from], sem.[to]\n"
                    + "FROM courses c  JOIN lecturers l ON c.lid = l.lid\n"
                    + "				JOIN subjects sub ON sub.subid = c.subid\n"
                    + "				JOIN semesters sem ON sem.semid = c.semid\n"
                    + "WHERE l.lid = ? AND active = 1";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lecid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                c.setSlots(rs.getInt("slots"));
                
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                l.setDob(rs.getDate("dob"));
                l.setPhonenumber(rs.getString("phonenumber"));
                l.setEmail(rs.getString("email"));
                c.setLecturer(l);
                
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                sub.setCodename(rs.getString("subcodename"));
                sub.setDefterm(rs.getInt("defaultterm"));
                sub.setCredit(rs.getInt("credit"));
                c.setSubject(sub);
                
                Semester sem = new Semester();
                sem.setId(rs.getInt("semid"));
                sem.setYear(rs.getInt("year"));
                sem.setSeason(rs.getString("season"));
                sem.setActive(rs.getBoolean("active"));
                sem.setFrom(rs.getDate("from"));
                sem.setTo(rs.getDate("to"));
                c.setSem(sem);
                
                courses.add(c);
                
            }
            
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
