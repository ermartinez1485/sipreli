/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.ILibroHb;
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
public class LibroBean implements Serializable{

    /**
     * Creates a new instance of LibroBean
     */
    private Libro elLibro;
    private FacesMessage msj;
    private List<Libro> listaLibro;
    private HtmlDataTable tableLibro;
    private Boolean modificando;
    private UIInput nombre;
    private ILibroHb libroAD;
    
    public LibroBean() {
        reiniciarBean();
    }
    
    private void reiniciarBean(){
        elLibro = new Libro();
        this.msj = new FacesMessage();
        this.listaLibro = new ArrayList<>();
        this.tableLibro = new HtmlDataTable();
        this.modificando = false;
        this.nombre = new UIInput();
    }

    public Libro getElLibro() {
        return elLibro;
    }

    public void setElLibro(Libro elLibro) {
        this.elLibro = elLibro;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTableLibro() {
        return tableLibro;
    }

    public void setTableLibro(HtmlDataTable tableLibro) {
        this.tableLibro = tableLibro;
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

    public void setListaLibro(List<Libro> listaLibro) {
        this.listaLibro = listaLibro;
    }

    public void setLibroAD(ILibroHb libroAD) {
        this.libroAD = libroAD;
    }

    public List<Libro> getListaLibro() {
        if (this.listaLibro.isEmpty()) {
            this.listaLibro = this.libroAD.consultarTodos();
        }
        return listaLibro;
    }

    public String buscarXNombre(){
        this.listaLibro = this.libroAD.consultaNombre((String) nombre.getSubmittedValue());
        return "ManLibros";
    }
    
    public String agregar(){
        if (this.libroAD.insertar(this.elLibro)) {
            this.msj.setSummary("La Libro se agrego satisfliboriamente");
        }else{
            this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:");
            
        }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManLibros";
    }
    
    public String seleccionar(){
        
        this.elLibro = (Libro) this.tableLibro.getRowData();
        this.modificando =true;
        return "ManLibros";  
    }
    
    public String modificar(){
        if(this.libroAD.modificar(elLibro)){
            this.msj.setSummary("Libro modificada satisfliboriamente.");
        }else{
            this.msj.setSummary("Error modificando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.modificando = false;
        this.reiniciarBean();
        return "ManLibros";
    }
    
     public String eliminar(){
        this.elLibro = (Libro) this.tableLibro.getRowData();
        if(this.libroAD.eliminar(elLibro)){
            this.msj.setSummary("Libro eliminada satisfliboriamente.");
        }else{
            this.msj.setSummary("Error eliminando la pelicula.");
        }
        FacesContext.getCurrentInstance().addMessage(null, getMsj());
        this.reiniciarBean();
        return "ManLibros";
    }
    
    public List<SelectItem> llenaGenero(){
        
       List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null,"Seleccione"));
        lista.add(new SelectItem(1,"1-Novela"));
        lista.add(new SelectItem(2,"2-Comedia"));
        lista.add(new SelectItem(3,"3-Poesia"));
        lista.add(new SelectItem(4,"4-Educativo"));
        return lista;

    }
    
    public String buscar(){
        this.listaLibro = this.libroAD.consultaNombre((String)nombre.getSubmittedValue());
        if (listaLibro.isEmpty()) {
            this.msj.setSummary("no se encontro el libro");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
    public String listar(){
        this.listaLibro = this.libroAD.consultarTodos();
        if (listaLibro.isEmpty()) {
            this.msj.setSummary("no hay libros");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
    public void cancelar(){
        reiniciarBean();
    }
    
    public List<SelectItem> llenaLibros(){
       
       List<Libro> lib = this.libroAD.consultarTodos();
       List<SelectItem>  lista =new ArrayList<SelectItem> ();
       Libro li = new Libro();
       li.setIsbn(0);
       li.setNombre("--seleccione--");
       
       lista.add(new SelectItem(li,li.getNombre()));
        for (int i = 0; i < lib.size(); i++) {
            Libro elA = lib.get(i);
            lista.add(new SelectItem(elA,elA.getNombre()));
        }
       return lista;
    }
    
    public String generaReporte(){
        try {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporteEstudianteSolicitados");
		dispatcher.forward(request, response);
		return "";
	} catch (Exception e) {
		e.printStackTrace();
		return "";
	}
    }
    
}
