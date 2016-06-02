/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.servlet;

import cr.ac.una.icai.sipreli.beans.PrestamoBean;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

/**
 *
 * @author eric.martinez
 */
public class ServletReporteLibrosSolicitados extends HttpServlet {

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
        try {
            //********    SE DECLARAN E INICIALIZAN VARIABLES   **********//
            OutputStream servletOutputStream = response.getOutputStream();
            HashMap parametrosReporte = new HashMap();
            InputStream reportStream = null;
            String nombreArchivo = "";
            String nombreJasper = "";
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map sessionMap = ctx.getExternalContext().getSessionMap();
            //**************************************************************************//

            PrestamoBean prestamo = (PrestamoBean)sessionMap.get("prestamoBean");  //****  SE OBTIENEN LOS VALORES QUE VIENEN DEL BEAN  ******//
            Integer tipo = prestamo.getElPrestamo().id.getElEstudiante().getCarnet();
            //HASHMAP PARA LOS PARAMETROS DEL REPORTE
            parametrosReporte.put("carnet",tipo); //***  SE PASA EL PARAMETRO AL REPORTE-> EL 1 ES EL NOMBRE DEL PAREMETRO EN EL REPORTE, EL 2 ES EL VALOR QUE VA   *******//
            

            //********INDICA CUAL ES EL JASPER QUE SE VA A UTILIZAR Y EL NOMBRE QUE TENDRA EL ARCHIVO**********//
            
            response.setContentType("application/pdf");     //******  SE INDICA EL FORMATO QUE TENDRA EL REPORTE ******************//
            nombreJasper = "LibroSolicitado.jasper";               //******  SE ESPECIFICA EL NOMBRE DEL JASPER A UTILIZAR ******************//
            nombreArchivo = "LibroSolicitado.pdf";         //******  SE ESPECIFICA EL NOMBRE DEL REPORTE A GENERAR ******************//
            
            ctx.responseComplete();
            reportStream = this.getServletConfig().getServletContext().getResourceAsStream("/reportes/" + nombreJasper);
            response.setHeader("Content-disposition", "attachment;filename=" + nombreArchivo);

            //**********  SE OBTIENE LA CONEXION PARA PASARLA AL REPORTE  **********//            
            Session session = HibernateUtil.getSessionFactory().openSession();
            SessionImpl sessionImpl = (SessionImpl) session;
            Connection conn = sessionImpl.connection(); 
            //**************************************************************************//

            //**********  SE GENERA EL REPORTE Y CIERRA LA CONEXION  **********//
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametrosReporte, conn);
            conn.close();
            //**************************************************************************//

            //**********  DESPLIEGA EL REPORTE  **********//
            servletOutputStream.flush();
            servletOutputStream.close();
            //**************************************************************************//
            
        } catch (Throwable e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
