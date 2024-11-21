/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Configuration.ConnectionBD;
import Model.FrutaModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

/**
 *
 * @author florc
 */
@WebServlet(name = "xmlServlet", urlPatterns = {"/xmlServlet"})
public class xmlServlet extends HttpServlet {

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
            out.println("<title>Servlet xmlServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet xmlServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        ConnectionBD conexion = new ConnectionBD();
        int idFruta = Integer.parseInt(request.getParameter("idFruta"));
        String sql = "SELECT idFruta, nombre, peso, color, cantidad, tipo FROM fruta WHERE idFruta = ?";
        FrutaModel fruta = null;

        try {
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idFruta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fruta = new FrutaModel();
                fruta.setIdFruta(rs.getInt("idFruta"));
                fruta.setNombre(rs.getString("nombre"));
                fruta.setPeso(rs.getDouble("peso"));
                fruta.setColor(rs.getString("color"));
                fruta.setCantidad(rs.getInt("cantidad"));
                fruta.setTipo(rs.getString("tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
        }

        if (fruta != null) {
            String xmlContent = generarXmlFruta(fruta);

            request.setAttribute("xmlContent", xmlContent);
            request.getRequestDispatcher("/jsp/vistaXml.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fruta no encontrada");
        }

    }

    private String generarXmlFruta(FrutaModel fruta) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.newDocument();
            // Crear la raíz del XML
            Element root = document.createElement("Fruta");
            document.appendChild(root);

            Element nombreElement = document.createElement("Nombre");
            nombreElement.appendChild(document.createTextNode(fruta.getNombre()));
            root.appendChild(nombreElement);

            Element pesoElement = document.createElement("Peso");
            pesoElement.appendChild(document.createTextNode(String.valueOf(fruta.getPeso())));
            root.appendChild(pesoElement);

            Element colorElement = document.createElement("Color");
            colorElement.appendChild(document.createTextNode(fruta.getColor()));
            root.appendChild(colorElement);

            Element cantidadElement = document.createElement("Cantidad");
            cantidadElement.appendChild(document.createTextNode(String.valueOf(fruta.getCantidad())));
            root.appendChild(cantidadElement);

            Element tipoElement = document.createElement("Tipo");
            tipoElement.appendChild(document.createTextNode(fruta.getTipo()));
            root.appendChild(tipoElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(new DOMSource(document), result);

            // Obtener el XML como una cadena
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
