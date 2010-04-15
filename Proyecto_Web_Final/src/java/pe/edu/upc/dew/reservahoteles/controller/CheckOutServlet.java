/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.edu.upc.dew.reservahoteles.model.Reserva;
import pe.edu.upc.dew.reservahoteles.service.ReservaService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author joel
 */
public class CheckOutServlet extends HttpServlet {

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

        if(req.getParameter("accion").toString().equals("Registrarcheckout"))
        {
            agregarCheckOut(session, req, resp);
        }
    }


    public void agregarCheckOut(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

           int intIdReserva = Integer.parseInt(req.getParameter("idreserva").toString());


            //int intIdTipoHab = Integer.parseInt(req.getParameter("idtipohab").toString());

            Reserva reserva = reservaService.agregarCheckOut(intIdReserva);

            double igv=reserva.getPrecio()*0.19;
            double total=reserva.getPrecio()+igv;

            req.setAttribute("varRes", intIdReserva);
            req.setAttribute("varCodigo", reserva.getCodigo());
            req.setAttribute("varHabitacion", reserva.getHabitacion().getCodigo());
            req.setAttribute("varFechaInicio", reserva.getFecInicio());
            req.setAttribute("varFechaFin", reserva.getFecFin());
            req.setAttribute("varSubtotal",    reserva.getPrecio());
            req.setAttribute("varIgv",    igv);
            req.setAttribute("varTotal",    total);
            req.setAttribute("varCliente",    reserva.getCliente());
            req.setAttribute("varTipoHab",    reserva.getTipoHabitacion());

            req.getRequestDispatcher("registrarCheckOut.jsp").forward(req, resp);


    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOutServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
