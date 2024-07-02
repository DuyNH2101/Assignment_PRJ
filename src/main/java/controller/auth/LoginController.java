/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.auth;

import databaseconnector.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.rbac.User;

/**
 *
 * @author LENOVO
 */
public class LoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        // Check if the user is already logged in via session
        if (sessionUser != null) {
            response.sendRedirect("view/ulti/Home.jsp"); // Redirect to a home page or dashboard
            return;
        }

        // Check for login cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String username = cookie.getValue();
                    UserDBContext udb = new UserDBContext();
                    User user = udb.getByUsername(username); // Implement this method in UserDBContext
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        response.sendRedirect("view/ulti/Home.jsp"); // Redirect to a home page or dashboard
                        return;
                    }
                }
            }
        }

        // If no session user or cookie, forward to login page
        request.getRequestDispatcher("view/auth/login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBContext udb = new UserDBContext();
        User user = udb.get(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);

            // Set a cookie to remember the login
            Cookie loginCookie = new Cookie("user", username);
            loginCookie.setMaxAge(60 * 60 * 24 * 7); // Cookie will expire in 7 days
            response.addCookie(loginCookie);

            response.sendRedirect("view/ulti/Home.jsp"); // Redirect to a home page or dashboard
        } else {
            request.setAttribute("error", "invalid username or password");
            request.getRequestDispatcher("view/auth/login.jsp").forward(request, response);
        }
        
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
