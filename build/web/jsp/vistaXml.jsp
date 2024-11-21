<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar XML de Fruta</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 800px;
                margin-top: 50px;
            }
            .xml-container {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 5px;
                border: 1px solid #ddd;
                margin-top: 10px;
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

            <h1 class="text-center">XML de Fruta Generado</h1>

            <a href="${pageContext.request.contextPath}/fruta_servlet" class="btn btn-custom">Volver a la lista de frutas</a>

            <%
                String xmlContent = (String) request.getAttribute("xmlContent");
            %>

            <div class="xml-container">

                <x:parse xml="<%= xmlContent%>" var="frutaXML" />
                <b>Nombre de la Fruta:</b> <x:out select="$frutaXML/Fruta/Nombre" /><br>
                <b>Peso:</b> <x:out select="$frutaXML/Fruta/Peso" /><br>
                <b>Color:</b> <x:out select="$frutaXML/Fruta/Color" /><br>
                <b>Cantidad:</b> <x:out select="$frutaXML/Fruta/Cantidad" /><br>
                <b>Tipo:</b> <x:out select="$frutaXML/Fruta/Tipo" /><br>
            </div>

            <a href="data:application/xml,<%= java.net.URLEncoder.encode(xmlContent, "UTF-8").replace("+", "%20")%>" 
               download="fruta.xml" class="btn btn-custom btn-block mt-4">Descargar XML</a>

        </div>
    </body>
</html>
