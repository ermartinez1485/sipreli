/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.clases.Prestamo;
import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IPrestamoHb;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

/**
 *
 * @author eric.martinez
 */
public class PrestamoBean implements Serializable{
    
    private Prestamo elPrestamo;
    private FacesMessage msj;
    private List<Prestamo> listaPrestamo;
    private HtmlDataTable tablePrestamo;
    private Boolean modificando;
    private IPrestamoHb prestamoAD;


    /**
     * Creates a new instance of PrestamoBean
     */
    public PrestamoBean() {
        reiniciarBean();
    }
    
    private void reiniciarBean(){
        elPrestamo = new Prestamo();
        this.msj = new FacesMessage();
        this.listaPrestamo = new ArrayList<>();
        this.tablePrestamo = new HtmlDataTable();
        this.modificando = false;
    }

    public Prestamo getElPrestamo() {
        return elPrestamo;
    }

    public void setElPrestamo(Prestamo elPrestamo) {
        this.elPrestamo = elPrestamo;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }
    
    public List<Prestamo> getListaPrestamo() {
            this.listaPrestamo = this.prestamoAD.consultarTodos();
        return listaPrestamo;
    }

    public void setListaPrestamo(List<Prestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public HtmlDataTable getTablePrestamo() {
        return tablePrestamo;
    }

    public void setTablePrestamo(HtmlDataTable tablePrestamo) {
        this.tablePrestamo = tablePrestamo;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public void setPrestamoAD(IPrestamoHb prestamoAD) {
        this.prestamoAD = prestamoAD;
    }
        
    public String agregar(){
        
        Date fechaHoy;
        Date fechaDevolucion;

        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        fechaHoy = c.getTime();
        c.add(Calendar.DATE, 5); // Adding 5 days
        fechaDevolucion = c.getTime();
        elPrestamo.setFechaPrestamo(fechaHoy);
        elPrestamo.setFechaDevolucion(fechaDevolucion);
        
        if (this.prestamoAD.insertar(elPrestamo)) {
            this.msj.setSummary("La Prestamo se agrego satisfactoriamente");
        }else{
            this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:");
            
        }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManPrestamos";
    }
    
    public String seleccionar(){
        
        this.elPrestamo = (Prestamo) this.tablePrestamo.getRowData();
        this.modificando =true;
        return "ManPrestamos";  
    }
    
    public String modificar(){
        if(this.prestamoAD.modificar(elPrestamo)){
            this.msj.setSummary("Prestamo modificada satisfactoriamente.");
        }else{
            this.msj.setSummary("Error modificando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.modificando = false;
        this.reiniciarBean();
        return "ManPrestamos";
    }
    
     public String eliminar(){
        this.elPrestamo = (Prestamo) this.tablePrestamo.getRowData();
        if(this.prestamoAD.eliminar(elPrestamo)){
            this.msj.setSummary("Prestamo eliminada satisfactoriamente.");
        }else{
            this.msj.setSummary("Error eliminando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.reiniciarBean();
        return "ManPrestamos";
    }
    
}
