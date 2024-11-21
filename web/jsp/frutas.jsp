<%@page import="java.util.ArrayList"%>
<%@page import="Model.FrutaModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Frutas</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 800px;
                margin-top: 50px;
            }
            .header-table {
                background-color: #E9967A;
                color: white;
            }
            .btn-custom {
                background-color: #CD5C5C;
                border-color: #CD5C5C;
                color: white;
                margin-bottom: 20px;
                text-decoration: none;
                padding: 10px 15px;
            }
        </style>        
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Lista de Frutas</h2>
            <a href="${pageContext.request.contextPath}/jsp/" class="btn btn-custom">Registrar Fruta</a>
            <table class="table table-striped">
                <thead class="header-table">
                    <tr>
                        <th>ID</th>
                        <th>NOMBRE</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<FrutaModel> listaFrutas = (ArrayList<FrutaModel>) request.getAttribute("frutas");

                        if (listaFrutas != null && !listaFrutas.isEmpty()) {
                            for (FrutaModel fruta : listaFrutas) {
                    %>
                    <tr>
                        <td><%= fruta.getIdFruta()%></td>
                        <td><%= fruta.getNombre()%></td>
                        <td>
                            <div class="btn-group" role="group">
                                <form action="${pageContext.request.contextPath}/jsp/vistaBean.jsp" method="POST">
                                    <input type="hidden" name="nombre" value="<%= fruta.getNombre()%>">
                                    <input type="hidden" name="peso" value="<%= fruta.getPeso()%>">
                                    <input type="hidden" name="color" value="<%= fruta.getColor()%>">
                                    <input type="hidden" name="cantidad" value="<%= fruta.getCantidad()%>">
                                    <input type="hidden" name="tipo" value="<%= fruta.getTipo()%>">
                                    <button type="submit" class="btn btn-sm btn-custom mr-2">Ver Bean</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/xmlServlet" method="POST">
                                    <input type="hidden" name="idFruta" value="<%= fruta.getIdFruta()%>">
                                    <button type="submit" class="btn btn-sm btn-custom">Ver XML</button>
                                </form>                               
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No hay frutas registradas.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
