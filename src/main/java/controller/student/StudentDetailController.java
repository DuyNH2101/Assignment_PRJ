/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import databaseconnector.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import model.business.Student;
import model.rbac.User;

/**
 *
 * @author LENOVO
 */
public class StudentDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int a = Integer.parseInt(request.getParameter("sid"));
            User curr = ((User)request.getSession().getAttribute("user"));
            if(curr.getStudent()!=null){
                if(curr.getStudent().getId()!=a){
                    throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "Access Denied, you cannot view detail of other students " 
                    );
                } else {
                    request.setAttribute("student", curr.getStudent());
                    request.getRequestDispatcher("../view/student/Detail.jsp").forward(request, response);
                }
            }
            StudentDBContext sdb =  new StudentDBContext();
            Student s = sdb.get(a);
        } catch (NumberFormatException e){
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "There is an error in the student id you want to view " 
                    );
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
