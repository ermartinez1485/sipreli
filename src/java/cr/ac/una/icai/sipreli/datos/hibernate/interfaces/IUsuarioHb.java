/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate.interfaces;

import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import java.util.List;

/**
 *
 * @author eric.martinez
 */
public interface IUsuarioHb {

    Integer consultaUser(String usuario, String contrasena);

    List<UsuarioLogin> consultarTodos();

    UsuarioLogin consultarXCedula(Integer id);

    Boolean eliminar(UsuarioLogin elUsuario);

    Boolean insertar(UsuarioLogin elUsuario);

    Boolean modificar(UsuarioLogin elUsuario);
    
}
