<%-- 
    Document   : pruebaConsultar
    Created on : 18-feb-2010, 0:14:40
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

         <h1>Datos de la reserva</h1>
        idReserva: ${varRes.idReserva}
        Fecha Inicio: ${varRes.fecInicio}
        Fecha Fin: ${varRes.fecFin}
        Cliente: ${varRes.cli.nombre} ${varRes.cli.apellido}
        Tipo Habitación: ${varRes.tipoHab.nombre}
        Precio: ${varRes.tipoHab.precio}
    </body>
</html>
