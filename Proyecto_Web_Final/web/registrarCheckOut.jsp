<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.TipoHabitacion" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.Reserva" %>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML>

    <HEAD>
        <LINK href="css/styles.css" rel="stylesheet" type="text/css" >
        <LINK href="calendario/calendar-blue.css" type="text/css" rel="stylesheet">
        <script src="calendario/calendar.js"></script>
        <script src="calendario/calendar-es.js"></script>
        <script src="js/fecha.js"></script>



    </HEAD>


    <form id="frmBuscarReserva" >

    <table width="100%">
        <tr>
            <td align="left">
                <h4>
                    Confirmación de Check Out
                </h4>
            </td>
        </tr>
    </table>
    <br />

    <body>

         <h1>Detalle de Facturación </h1>
         <h1> Codigo        : ${varCodigo}</h1>
         <h1> Cliente       : ${varCliente}</h1>
         <h1> Tipo Habitacion  : ${varTipoHab}</h1>
         <h1> Habitacion    : ${varHabitacion}</h1>
         <h1> F. Inicio     : ${varFechaInicio}</h1>
         <h1> F. Fin        : ${varFechaFin}</h1>
         <h1> SubTotal        : $ ${varSubtotal}</h1>
         <h1> IGV             : $ ${varIgv}</h1>
         <h1> Total a Pagar   : $ ${varTotal}</h1>
    </body>
    </form>
</HTML>