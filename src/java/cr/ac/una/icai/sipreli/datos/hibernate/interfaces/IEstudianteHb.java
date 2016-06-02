/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate.interfaces;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.datos.hibernate.EstudianteHb;
import java.util.List;

/**
 *
 * @author eric.martinez
 */
public interface IEstudianteHb {

    List<Estudiante> consultaNombre(String nombre);

    List<Estudiante> consultarTodos();

    EstudianteHb consultarXCodigo(Integer id);

    Boolean eliminar(Estudiante elEstudiante);

    Boolean insertar(Estudiante elEstudiante);

    Boolean modificar(Estudiante elEstudiante);
    
}
