/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate.interfaces;

import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.datos.hibernate.LibroHb;
import java.util.List;

/**
 *
 * @author eric.martinez
 */
public interface ILibroHb {

    List<Libro> consultaNombre(String nombre);

    List<Libro> consultarTodos();

    LibroHb consultarXCodigo(Integer id);

    Boolean eliminar(Libro elLibro);

    Boolean insertar(Libro elLibro);

    Boolean modificar(Libro elLibro);
    
}
