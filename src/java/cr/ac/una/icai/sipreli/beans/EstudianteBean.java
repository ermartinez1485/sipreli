/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this testulate file, choose Tools | Testulates
 * and open the testulate in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IEstudianteHb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eric.martinez
 */
public class EstudianteBean implements Serializable{

  private Estudiante elEstudiante;
    private FacesMessage msj;
    private List<Estudiante> listaEstudiante;
    private HtmlDataTable tableEstudiante;
    private Boolean modificando;
    private UIInput nombre;
    private IEstudianteHb estudianteAD;
    
    public EstudianteBean() {
        reiniciarBean();
    }
    
    private void reiniciarBean(){
        elEstudiante = new Estudiante();
        this.msj = new FacesMessage();
        this.listaEstudiante = new ArrayList<>();
        this.tableEstudiante = new HtmlDataTable();
        this.modificando = false;
        this.nombre = new UIInput();
    }

    public Estudiante getElEstudiante() {
        return elEstudiante;
    }

    public void setElEstudiante(Estudiante elEstudiante) {
        this.elEstudiante = elEstudiante;
    }
    
    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }
    
    public List<Estudiante> getListaEstudiante() {
        if (this.listaEstudiante.isEmpty()) {
            this.listaEstudiante = this.estudianteAD.consultarTodos();
        }
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public HtmlDataTable getTableEstudiante() {
        return tableEstudiante;
    }

    public void setTableEstudiante(HtmlDataTable tableEstudiante) {
        this.tableEstudiante = tableEstudiante;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public UIInput getNombre() {
        return nombre;
    }

    public void setNombre(UIInput nombre) {
        this.nombre = nombre;
    }

    public void setEstudianteAD(IEstudianteHb estudianteAD) {
        this.estudianteAD = estudianteAD;
    }
        
    public String buscarXNombre(){
        this.listaEstudiante = this.estudianteAD.consultaNombre((String) nombre.getSubmittedValue());
        return "ManEstudiante";
    }
    
    public String agregar(){
        if (this.estudianteAD.insertar(this.elEstudiante)) {
            this.msj.setSummary("La Estudiante se agrego satisfestoriamente");
        }else{
            this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:");
            
        }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManEstudiante";
    }
    
    public String seleccionar(){
        
        this.elEstudiante = (Estudiante) this.tableEstudiante.getRowData();
        this.modificando =true;
        return "ManEstudiante";  
    }
    
    public String modificar(){
        if(this.estudianteAD.modificar(elEstudiante)){
            this.msj.setSummary("Estudiante modificada satisfestoriamente.");
        }else{
            this.msj.setSummary("Error modificando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.modificando = false;
        this.reiniciarBean();
        return "ManEstudiante";
    }
    
     public String eliminar(){
        this.elEstudiante = (Estudiante) this.tableEstudiante.getRowData();
        if(this.estudianteAD.eliminar(elEstudiante)){
            this.msj.setSummary("Estudiante eliminada satisfestoriamente.");
        }else{
            this.msj.setSummary("Error eliminando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.reiniciarBean();
        return "ManEstudiante";
    }
    
    public List<SelectItem> llenaEscuela(){
        
       List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null,"Seleccione"));
        lista.add(new SelectItem(1,"1-Informatica"));
        lista.add(new SelectItem(2,"2-Biologia"));
        lista.add(new SelectItem(3,"3-Matematica"));
        lista.add(new SelectItem(4,"4-Educativo"));
        return lista;

    }
    
    public String buscar(){
        this.listaEstudiante = this.estudianteAD.consultaNombre((String)nombre.getSubmittedValue());
        if (listaEstudiante.isEmpty()) {
            this.msj.setSummary("no se encontro el estudiante");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
    public String listar(){
        this.listaEstudiante = this.estudianteAD.consultarTodos();
        if (listaEstudiante.isEmpty()) {
            this.msj.setSummary("no hay libros");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
    public void cancelar(){
        reiniciarBean();
    }
    
    public List<SelectItem> llenaEstudiantes(){
       
       List<Estudiante> est = this.estudianteAD.consultarTodos();
       List<SelectItem>  lista =new ArrayList<SelectItem> ();
       Estudiante ac = new Estudiante();
       ac.setCarnet(0);
       ac.setNombre("--seleccione--");
       
       lista.add(new SelectItem(ac,ac.getNombre()));
        for (int i = 0; i < est.size(); i++) {
            Estudiante elA = est.get(i);
            lista.add(new SelectItem(elA,elA.getNombre()));
        }
       return lista; 
    }
    
    public String generaReporte(){
        try {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporteLibrosSolicitados");
		dispatcher.forward(request, response);
		return "";
	} catch (Exception e) {
		e.printStackTrace();
		return "";
	}
    }
}
