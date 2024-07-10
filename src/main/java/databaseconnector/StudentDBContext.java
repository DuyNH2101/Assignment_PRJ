/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Course;
import model.business.Major;
import model.business.Semester;
import model.business.Student;
import model.business.Subject;

/**
 *
 * @author LENOVO
 */
public class StudentDBContext extends DBContext<Student>{
    public Student getStudentSemesterAndCourseStudied(int sid){
        Student s = null;
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.[sid], s.sname, s.mid, s.dob, s.gender, s.email, s.phonenumber, s.[address], s.currentterm,\n"
                    + "		c.cid, c.cname, c.lid, c.semid, c.slots,\n"
                    + "		sub.subid, sub.subname, sub.subcodename, sub.defaultterm, sub.credit, \n"
                    + "		sem.semid, sem.[year], sem.season, sem.active, sem.[from], sem.[to]\n"
                    + "FROM students s JOIN students_courses sc ON s.[sid] = sc.[sid]\n"
                    + "				JOIN courses c ON c.cid = sc.cid\n"
                    + "				JOIN subjects sub ON sub.subid = c.subid \n"
                    + "				JOIN semesters sem ON sem.semid = c.semid\n"
                    + "WHERE s.[sid] = ?\n"
                    + "ORDER BY sem.semid ASC";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            int c_semid = -1;
            Semester sem = null;
            while(rs.next()){
                if(s==null){
                    s = new Student();
                    s.setId(sid);
                }
                int semid = rs.getInt("semid");
                if(semid>0&&semid!=c_semid){
                    c_semid = semid;
                    sem = new Semester();
                    sem.setId(semid);
                    sem.setSeason(rs.getString("season"));
                    sem.setYear(rs.getInt("year"));
                    sem.setActive(rs.getBoolean("active"));
                    sem.setFrom(rs.getDate("from"));
                    sem.setTo(rs.getDate("to"));
                    s.getSemesters().add(sem);
                }
                
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                c.setSem(sem);
                c.setSlots(rs.getInt("slots"));
                sem.getCourses().add(c);
                
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                sub.setCodename(rs.getString("subcodename"));
                sub.setDefterm(rs.getInt("defaultterm"));
                sub.setCredit(rs.getInt("credit"));
                c.setSubject(sub);
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
        return s;
    }
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
