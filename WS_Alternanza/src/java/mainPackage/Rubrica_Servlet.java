/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spinelli_loris
 */
public class Rubrica_Servlet extends HttpServlet {

    
    final private String driver = "com.mysql.jdbc.Driver";
    final private String dbms_url = "jdbc:mysql://localhost/";
    final private String database = "rubrica";
    final private String user = "root";
    final private String password = "";
    private Connection rubrica_database;
    private boolean connected;
    
    
    // attivazione servlet (connessione a DBMS)
    public void init() {
        String url = dbms_url + database;
        try {
            Class.forName(driver);
            rubrica_database = DriverManager.getConnection(url, user, password);
            connected = true;
        } catch (SQLException e) {
            connected = false;
        } catch (ClassNotFoundException e) {
            connected = false;
        }
    }
    
    // disattivazione servlet (disconnessione da DBMS)
    public void destroy() {
        try {
            rubrica_database.close();
        } catch (SQLException e) {
        }
    }
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Alternanza_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Alternanza_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
//    public String getParametri(){
//         
//    }

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
        processRequest(request, response);
        
        String name;
        String surname;
        String number;
        String description ="";
        String url;
        String[] url_section;
        
        // verifica stato connessione a DBMS
        if (!connected) {
            response.sendError(500, "DBMS server error!");
            return;
        }
        
        // estrazione nominativo da URL
        url = request.getRequestURL().toString();
        url_section = url.split("/");
        name = url_section[url_section.length - 1];
        //surname = url_section[url_section.length];
        if (name == null) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        if (name.isEmpty()) {
            response.sendError(400, "Request syntax error!");
            return;
        }
//        if (surname == null) {
//            response.sendError(400, "Request syntax error!");
//            return;
//        }
//        if (surname.isEmpty()) {
//            response.sendError(400, "Request syntax error!");
//            return;
//        }
        
        try{
            String descrizione = request.getParameter("descr");
            String sql = "SELECT nome, cognome";
            if(descrizione != null && descrizione.equals("si"))
                sql += ",descrizione";
            
            sql += " FROM rubrica WHERE Nome= '" + name + "';";
            
            // ricerca nominativo nel database
            Statement statement = rubrica_database.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                number = result.getString(3);
                if (descrizione != null && descrizione.equals("si")) {
                    description = result.getString(4);
                }
                    
            } else {
                response.sendError(404, "Entry not found!");
                result.close();
                statement.close();
                return;
            }
            result.close();
            statement.close();
            // scrittura del body della risposta
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<entry>");
                out.print("<name>");
                out.print(name);
                out.println("</name>");
//                out.print("<surname>");
//                out.print(surname);
//                out.println("</surname>");
                out.print("<number>");
                out.print(number);
                out.println("</number>");
                
                if (descrizione != null && descrizione.equals("si")) {
                    out.print("<description>");
                    out.print(description);
                    out.println("</description>");
                    
                }
                out.println("</entry>");
            } finally {
                out.close();
            }
            response.setStatus(200); // OK
        } catch (SQLException e) {
            response.sendError(500, "DBMS server error!");
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
        processRequest(request, response);
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
