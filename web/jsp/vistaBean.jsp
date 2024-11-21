<%
    response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="frutaBean" class="Model.FrutaModel" scope="page"/>
<jsp:setProperty name="frutaBean" property="*"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fruta</title>
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
            <h2 class="text-center">Detalles de la Fruta con Bean</h2>
            <table class="table table-striped">
                <thead class="header-table">
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><%= frutaBean.getNombre() %></td>
                    </tr>
                    <tr>
                        <td>PESO</td>
                        <td><%= frutaBean.getPeso()%></td>
                    </tr>
                    <tr>
                        <td>Color</td>
                        <td><%= frutaBean.getColor() %></td>
                    </tr>
                    <tr>
                        <td>Cantidad</td>
                        <td><%= frutaBean.getCantidad() %></td>
                    </tr>
                    <tr>
                        <td>Tipo</td>
                        <td><%= frutaBean.getTipo() %></td>
                    </tr>
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/fruta_servlet" class="btn btn-custom">Volver a la lista de frutas</a>
        </div>
    </body>
</html>
