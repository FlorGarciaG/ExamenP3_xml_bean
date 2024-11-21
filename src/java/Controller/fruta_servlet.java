/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Configuration.ConnectionBD;
import Model.FrutaModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author florc
 */
@WebServlet(name = "fruta_servlet", urlPatterns = {"/fruta_servlet"})
public class fruta_servlet extends HttpServlet {

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet fruta_servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fruta_servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        ConnectionBD conexion = new ConnectionBD();
        List<FrutaModel> listaFrutas = new ArrayList<>();
        String sql = "SELECT idFruta, nombre, peso, color, cantidad, tipo FROM fruta";

        try {
            conn = conexion.getConnectionBD();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FrutaModel fruta = new FrutaModel();
                int idFrutaFinal = rs.getInt("idFruta");
                fruta.setIdFruta(idFrutaFinal);
                fruta.setNombre(rs.getString("nombre"));
                double pesoFinal = rs.getDouble("peso");
                fruta.setPeso(pesoFinal);
                fruta.setColor(rs.getString("color"));
                int cantidadFinal = rs.getInt("cantidad");
                fruta.setCantidad(cantidadFinal);
                fruta.setTipo(rs.getString("tipo"));

                listaFrutas.add(fruta);
            }

            request.setAttribute("frutas", listaFrutas);
            request.getRequestDispatcher("/jsp/frutas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las frutas" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        request.setCharacterEncoding("UTF-8");

        ConnectionBD conexion = new ConnectionBD();
        String nombre = request.getParameter("nombre");
        String peso = request.getParameter("peso");
        String color = request.getParameter("color");
        String cantidad = request.getParameter("cantidad");
        String tipo = request.getParameter("tipo");

        double pesoFinal = Double.parseDouble(peso);
        int cantidadFinal = Integer.parseInt(cantidad);

        try {
            String sql = "INSERT INTO fruta (nombre, peso, color, cantidad, tipo) VALUES (?, ?, ?, ?, ?)";
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, pesoFinal);
            ps.setString(3, color);
            ps.setInt(4, cantidadFinal);
            ps.setString(5, tipo);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                request.setAttribute("mensaje", "Fruta registrada con éxito!");
            } else {
                request.setAttribute("mensaje", "Error al registrar la fruta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession();
            session.setAttribute("mensajeAlerta", "¡Fruta registrada exitosamente!");
            //request.getRequestDispatcher("jsp/").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/jsp/");
        }

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
