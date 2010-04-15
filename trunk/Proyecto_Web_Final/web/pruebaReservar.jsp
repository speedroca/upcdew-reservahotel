<%-- 
    Document   : pruebaReservar
    Created on : 17-feb-2010, 23:52:35
    Author     : Ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registrar Reserva</h1>
        <form action="ReservaServlet" method="post">
            idReserva: <input type="text" name="idreserva" /><br />
            FechaInicio: <input type="text" name="fechainicio" /><br />
            FechaFin: <input type="text" name="fechafin" /><br />
            <input type="submit" name="Reservar" /><br />
        </form>
    </body>
</html>
