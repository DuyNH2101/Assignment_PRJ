/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.grade;

import databaseconnector.GradeDBContext;
import databaseconnector.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.business.Grade;
import model.business.Semester;
import model.business.Student;
import model.rbac.User;

/**
 *
 * @author LENOVO
 */
public class ViewStudentGradeController extends HttpServlet {
   
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
            out.println("<title>Servlet ViewStudentGradeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewStudentGradeController at " + request.getContextPath () + "</h1>");
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
        User user = (User)request.getSession().getAttribute("user");
        switch(user.getRole().getRolename()){
            case "manager":{
                
                break;
            }
            case "lecturer":{
                
                break;
            }
            case "student":{
                String semid = request.getParameter("semid");
                String cid = request.getParameter("cid");
                if(semid==null){
                    int sid = user.getStudent().getId();
                    StudentDBContext sdb = new StudentDBContext();
                    Student s = sdb.getStudentSemesterAndCourseStudied(sid);                    
                    Semester sem = s.getSemesters().get(s.getSemesters().size()-1);
                    
                    request.setAttribute("student", s);
                    request.setAttribute("semester", sem);
                    request.getRequestDispatcher("../view/grade/ViewStudentGrade.jsp").forward(request, response);
                } else{
                    int sid = user.getStudent().getId();
                    StudentDBContext sdb = new StudentDBContext();
                    Student s = sdb.getStudentSemesterAndCourseStudied(sid);
                    Semester sem = null;
                    for(Semester asem: s.getSemesters()){
                        if(asem.getId()==Integer.parseInt(semid)){
                            sem = asem;
                            break;
                        }
                    }
                    if(sem==null){
                        sem = s.getSemesters().get(s.getSemesters().size()-1);
                    }
                    if(cid!=null){
                        GradeDBContext gdb = new GradeDBContext();
                        ArrayList<Grade> grades = gdb.getGradeForCourseOfStudent(sid, Integer.parseInt(cid));
                        request.setAttribute("grades", grades);
                    }
                    request.setAttribute("semester", sem);
                    request.setAttribute("student", s);
                    
                    request.getRequestDispatcher("../view/grade/ViewStudentGrade.jsp").forward(request, response);
                }
                break;
            }
        }
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
        processRequest(request, response);
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
