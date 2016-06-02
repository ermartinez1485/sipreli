/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IUsuarioHb;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author eric.martinez
 */
public class UserBean implements Serializable{
    
    private UsuarioLogin elUser;
    private FacesMessage msj;
    private IUsuarioHb usuarioAD;

    public UsuarioLogin getElUser() {
        return elUser;
    }

    public void setElUser(UsuarioLogin elUser) {
        this.elUser = elUser;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public void setUsuarioAD(IUsuarioHb usuarioAD) {
        this.usuarioAD = usuarioAD;
    }
    
    



    public UserBean() {
        this.elUser = new UsuarioLogin();
        this.msj = new FacesMessage();
    }
    
    public String aceptar(){
        
        Integer status = 0;
        
        status = usuarioAD.consultaUser(this.elUser.getUsuario(),this.elUser.getContrasena());

            if(status==1) {
                return "index";
            }
            else{
                this.msj.setSummary("Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null,getMsj());
                 return "";
            }
    }
    
}
