<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Frutas</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 600px;
                margin-top: 50px;
            }
            .btn-custom {
                background-color: #CD5C5C;
                border-color: #CD5C5C;
                color: white;
            }
            .btn-custom a {
                text-decoration: none;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Registro de Frutas</h1>
            <br/>
            <button class="btn btn-custom"><a href="${pageContext.request.contextPath}/fruta_servlet" class="btn-custom">Lista de Frutas</a></button>
            <br><br>
            <form action="${pageContext.request.contextPath}/fruta_servlet" method="POST" class="bg-light p-4 border rounded">
                <div class="form-group">
                    <label for="nombre">NOMBRE:</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required>
                </div>
                <div class="form-group">
                    <label for="peso">PESO (g):</label>
                    <input type="number" min="1" class="form-control" step="0.01" name="peso" id="peso" required>
                </div>
                <div class="form-group">
                    <label for="color">COLOR:</label>
                    <input type="text" class="form-control" name="color" id="color" required>
                </div>
                <div class="form-group">
                    <label for="cantidad">CANTIDAD:</label>
                    <input type="number" min="1" class="form-control" name="cantidad" id="cantidad" required>
                </div>
                <div class="form-group">
                    <label for="tipo">TIPO DE FRUTA:</label>
                    <select class="form-control" name="tipo" id="tipo" required>
                        <option value="">Seleccione el tipo de fruta</option>
                        <option value="tropical">Tropical</option>
                        <option value="cítrica">Cítrica</option>
                        <option value="baya">Baya</option>
                        <option value="rojos">Rojos</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-custom btn-block" name="accion" value="Agregar">Agregar</button>
            </form>
            <br>
            <%
                // No necesitas declarar la variable `session` de nuevo.
                String mensajeAlerta = (String) session.getAttribute("mensajeAlerta");
                if (mensajeAlerta != null) {
            %>
            <script type="text/javascript">
                alert("<%= mensajeAlerta%>");
            </script>
            <%
                    session.removeAttribute("mensajeAlerta");
                }
            %>
        </div>
    </body>
</html>
