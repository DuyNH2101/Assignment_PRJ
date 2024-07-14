/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import databaseconnector.ExamDBContext;
import databaseconnector.GradeDBContext;
import databaseconnector.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import model.business.Exam;
import model.business.Grade;
import model.business.Student;

/**
 *
 * @author LENOVO
 */
public class LecturerUpdateGradeController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LecturerUpdateGradeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LecturerUpdateGradeController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StudentDBContext stuDB = new StudentDBContext();
        ExamDBContext examDB = new ExamDBContext();
        GradeDBContext graDB = new GradeDBContext();
        
        int cid = Integer.parseInt(request.getParameter("cid"));
        ArrayList<Student> students = stuDB.getStudentsByCourse(cid);
        
        String[] raw_eids = request.getParameterValues("eid");
        ArrayList<Integer> eids = new ArrayList<>();
        for (String raw_eid : raw_eids) {
            eids.add(Integer.parseInt(raw_eid));
        }
        
        ArrayList<Exam> exams = examDB.getExamsByExamIds(eids);
        ArrayList<Grade> grades = graDB.getGradesFromExamIds(eids);
        
        request.setAttribute("students", students);
        request.setAttribute("exams", exams);
        request.setAttribute("grades",grades);
        
        request.getRequestDispatcher("../../view/exam/LecturerGradeExam.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        HashSet<Integer> eids = new HashSet<>();
        
        String[] raw_gradeids = request.getParameterValues("gradeid");
        ArrayList<Grade> grades = new ArrayList<>();
        for (String raw_gradeid : raw_gradeids) {
          int sid = Integer.parseInt(raw_gradeid.split("_")[0]);
          int eid = Integer.parseInt(raw_gradeid.split("_")[1]);
          
          eids.add(eid);
          
          String raw_score = request.getParameter("score"+sid+"_"+eid);
          if(raw_score.length()>0)
          {
              Grade g = new Grade();
              Exam e = new Exam();
              e.setId(eid);
              
              Student s = new Student();
              s.setId(sid);
              
              g.setExam(e);
              g.setStudent(s);
              g.setScore(Float.parseFloat(raw_score));
              
              grades.add(g);
          } else{
              Grade g = new Grade();
              Exam e = new Exam();
              e.setId(eid);
              
              Student s = new Student();
              s.setId(sid);
              
              g.setExam(e);
              g.setStudent(s);
              g.setScore(-1.0f);
              
              grades.add(g);
          }
        }
        
        GradeDBContext db = new GradeDBContext();
        db.insertGradesForCourse(cid, grades);
        String url_param = "";
        for (Integer eid : eids) {
            url_param+="&eid="+eid;
        }
        response.sendRedirect("update?cid="+cid+url_param);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
