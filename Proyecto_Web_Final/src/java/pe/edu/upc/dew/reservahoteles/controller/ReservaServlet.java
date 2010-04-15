/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.Reserva;
import pe.edu.upc.dew.reservahoteles.service.ReservaService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author Ricardo
 */
public class ReservaServlet extends HttpServlet {
   
     private ReservaService reservaService;


    @Override
    public void init() throws ServletException {  //el tomcat lo llama a la hora de inicializar el servlet
       System.out.println("antes--");
       WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       //ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
      //desde el servlet estamos obteniendo una referencia al applicationContext de spring
       System.out.println("antes");
       this.reservaService = (ReservaService) context.getBean("reservaService");
       System.out.println("paso");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos los parametros del jsp

        HttpSession session=req.getSession(true);

        System.out.println(req.getParameter("accion").toString());



        if(req.getParameter("accion").toString().equals("registrar"))
        {
           agregarReserva(session, req, resp);
        }
        else if(req.getParameter("accion").toString().equals("buscarTodos"))
        {
            listarReservasTodos(session, req, resp);
        }
        else if(req.getParameter("accion").toString().equals("buscar"))
        {
            listarReservas(session, req, resp);
        }
        else if(req.getParameter("accion").toString().equals("checkin"))
        {
            listarReservasCheckIn(session, req, resp);
        }
         else if(req.getParameter("accion").toString().equals("registrarCheckIn"))
        {
            agregarCheckIn(session, req, resp);
        }
        else if(req.getParameter("accion").toString().equals("checkout"))
        {
            listarReservasCheckOut(session, req, resp);
        }


    }

    public void agregarReserva(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
            Cliente cliente = new Cliente();

            if(session.getAttribute("SS_CLIENTE")!=null)
                cliente =  (Cliente)session.getAttribute("SS_CLIENTE");

            int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
            String strFechaInicio = req.getParameter("txtDesde").toString();
            String strFechaFin = req.getParameter("txtHasta").toString();

            Reserva reserva = new Reserva();
            reserva.setIdCliente(cliente.getIdCliente());
            reserva.setIdTipoHab(intTipoHab);
            reserva.setPrecio(Double.parseDouble(req.getParameter("txtPrecio").toString()));
            reserva.setFecInicio(strFechaInicio);
            reserva.setFecFin(strFechaFin);
            reserva.setEstado(1);//Registrada

            //ReservaService reservaService = new ReservaServiceImpl();
            reserva = reservaService.agregarReserva(reserva);

            session.setAttribute("precio", req.getParameter("txtPrecio"));

            if(reserva.getIdReserva() == -1)
            {
                session.setAttribute("registromensaje","No hay habitaciones disponibles.");
                reserva.setCodigo("-");
            }
            else
            {
                session.setAttribute("registromensaje","Reserva Registrada");
                
            }

            session.setAttribute("SS_RESERVA",reserva);
            session.setAttribute("selecgob","");
            session.setAttribute("selecgcl","");
            session.setAttribute("selecpre","");
            session.setAttribute("selecsui","");


            if(intTipoHab==1)
                session.setAttribute("selecgob","selected");
            else if(intTipoHab==2)
                session.setAttribute("selecgcl","selected");
            else if(intTipoHab==3)
                session.setAttribute("selecpre","selected");
            else if(intTipoHab==4)
                session.setAttribute("selecsui","selected");

            req.getRequestDispatcher("registrar.jsp").forward(req, resp);
            // Ejecuto el servicio crearReserva
            //reservaService.crearReserva(reserva);
            //sesion.setAttribute("sesion_reservaService",reservaService);
            //req.getRequestDispatcher("registrar.jsp").forward(req, resp);
    }

     public void listarReservasTodos(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //ReservaService reservaService = new ReservaServiceImpl();

        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String strFechaInicio = req.getParameter("txtDesde").toString();
        String strFechaFin = req.getParameter("txtHasta").toString();

        int intIdCliente = 0;

         if(session.getAttribute("SS_CLIENTE")!=null)
            intIdCliente =  ((Cliente)session.getAttribute("SS_CLIENTE")).getIdCliente();

            Reserva reserva = new Reserva();
            reserva.setIdTipoHab(intTipoHab);
            reserva.setFecInicio(strFechaInicio);
            reserva.setFecFin(strFechaFin);



            List<Reserva> ListReserva = reservaService.listarReservasTodos(strFechaInicio, strFechaFin, intTipoHab, intIdCliente);

            session.setAttribute("SS_RESERVA",reserva);
            session.setAttribute("selecgob","");
            session.setAttribute("selecgcl","");
            session.setAttribute("selecpre","");
            session.setAttribute("selecsui","");

            if(intTipoHab==1)
                session.setAttribute("selecgob","selected");
            else if(intTipoHab==2)
                session.setAttribute("selecgcl","selected");
            else if(intTipoHab==3)
                session.setAttribute("selecpre","selected");
            else if(intTipoHab==4)
                session.setAttribute("selecsui","selected");

            req.setAttribute("ListReserva", ListReserva);

        req.getRequestDispatcher("buscar.jsp").forward(req, resp);
    }


     public void listarReservas(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //ReservaService reservaService = new ReservaServiceImpl();

        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String strFechaInicio = req.getParameter("txtDesde").toString();
        String strFechaFin = req.getParameter("txtHasta").toString();

        int intIdCliente = 0;
         if(session.getAttribute("SS_CLIENTE")!=null)
            intIdCliente =  ((Cliente)session.getAttribute("SS_CLIENTE")).getIdCliente();

        Reserva reserva = new Reserva();
        reserva.setIdTipoHab(intTipoHab);
        reserva.setFecInicio(strFechaInicio);
        reserva.setFecFin(strFechaFin);


        List<Reserva> ListReserva = reservaService.listarReservas(strFechaInicio, strFechaFin, intTipoHab, intIdCliente);
        
        session.setAttribute("SS_RESERVA",reserva);
        session.setAttribute("selecgob","");
        session.setAttribute("selecgcl","");
        session.setAttribute("selecpre","");
        session.setAttribute("selecsui","");

        if(intTipoHab==1)
            session.setAttribute("selecgob","selected");
        else if(intTipoHab==2)
            session.setAttribute("selecgcl","selected");
        else if(intTipoHab==3)
            session.setAttribute("selecpre","selected");
        else if(intTipoHab==4)
            session.setAttribute("selecsui","selected");

        req.setAttribute("ListReserva", ListReserva);
        req.getRequestDispatcher("buscar.jsp").forward(req, resp);
    }

  
    public void listarReservasCheckIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //ReservaService reservaService = new ReservaServiceImpl();

        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String strFechaInicio = req.getParameter("txtDesde").toString();
        String strFechaFin = req.getParameter("txtHasta").toString();

        int intIdCliente = 0;
         if(session.getAttribute("SS_CLIENTE")!=null)
            intIdCliente =  ((Cliente)session.getAttribute("SS_CLIENTE")).getIdCliente();

        Reserva reserva = new Reserva();
        reserva.setIdTipoHab(intTipoHab);
        reserva.setFecInicio(strFechaInicio);
        reserva.setFecFin(strFechaFin);


        List<Reserva> ListReserva = reservaService.listarReservas(strFechaInicio, strFechaFin, intTipoHab, intIdCliente);

        session.setAttribute("SS_RESERVA",reserva);
        session.setAttribute("selecgob","");
        session.setAttribute("selecgcl","");
        session.setAttribute("selecpre","");
        session.setAttribute("selecsui","");

        if(intTipoHab==1)
            session.setAttribute("selecgob","selected");
        else if(intTipoHab==2)
            session.setAttribute("selecgcl","selected");
        else if(intTipoHab==3)
            session.setAttribute("selecpre","selected");
        else if(intTipoHab==4)
            session.setAttribute("selecsui","selected");

        req.setAttribute("ListReserva", ListReserva);
        req.getRequestDispatcher("checkin.jsp").forward(req, resp);
    }

    public void agregarCheckIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {


           /* int intIdReserva = Integer.parseInt(req.getParameter("idreserva").toString());
            int intIdTipoHab = Integer.parseInt(req.getParameter("idtipohab").toString());

            //ReservaService reservaService = new ReservaServiceImpl();

            reservaService.agregarCheckIn(intIdReserva, intIdTipoHab);

            req.getRequestDispatcher("checkin.jsp").forward(req, resp);*/

    }

    public void listarReservasCheckOut(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {


      // ReservaService reservaService = new ReservaServiceImpl();

        
        String strNombreCliente = req.getParameter("txtNombreCliente").toString().toUpperCase();
        String strApellidoCliente = req.getParameter("txtApellidoCliente").toString().toUpperCase();
        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String habitacion = req.getParameter("txtHabitacion").toString();

        int intIdCliente = 0;
        Reserva reserva = new Reserva();
        reserva.setIdTipoHab(intTipoHab);
     

        List<Reserva> ListReserva = reservaService.listarReservasCheckIn(strNombreCliente, strApellidoCliente, habitacion, intTipoHab, intIdCliente);
        session.setAttribute("SS_RESERVA",reserva);
        session.setAttribute("nombre",strNombreCliente);
        session.setAttribute("apellido",strApellidoCliente);
        session.setAttribute("habitacion",habitacion);
        session.setAttribute("selecgob","");
        session.setAttribute("selecgcl","");
        session.setAttribute("selecpre","");
        session.setAttribute("selecsui","");

        if(intTipoHab==1)
            session.setAttribute("selecgob","selected");
        else if(intTipoHab==2)
            session.setAttribute("selecgcl","selected");
        else if(intTipoHab==3)
            session.setAttribute("selecpre","selected");
        else if(intTipoHab==4)
            session.setAttribute("selecsui","selected");

        req.setAttribute("ListReserva", ListReserva);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);

        
    }
}
