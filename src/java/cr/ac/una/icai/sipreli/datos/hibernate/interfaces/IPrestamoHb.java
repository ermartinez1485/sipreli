/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate.interfaces;

import cr.ac.una.icai.sipreli.clases.Prestamo;
import cr.ac.una.icai.sipreli.datos.hibernate.PrestamoHb;
import java.util.List;

/**
 *
 * @author eric.martinez
 */
public interface IPrestamoHb {

    List<Prestamo> consultarTodos();

    PrestamoHb consultarXCodigo(Integer id);

    Boolean eliminar(Prestamo elPrestamo);

    Boolean insertar(Prestamo elPrestamo);

    Boolean modificar(Prestamo elPrestamo);
    
}
