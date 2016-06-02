/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate;

import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IEstudianteHb;
import cr.ac.una.icai.sipreli.clases.Estudiante;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eric.martinez
 */
public class EstudianteHb implements IEstudianteHb {
    
    private Session session;

    public EstudianteHb() {
        this.session = null;
    }

    @Override
    public Boolean insertar(Estudiante elEstudiante){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.save(elEstudiante);
            tr.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean eliminar(Estudiante elEstudiante){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.delete(elEstudiante);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean modificar(Estudiante elEstudiante){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.merge(elEstudiante);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<Estudiante> consultarTodos(){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<Estudiante> lista = this.session.createQuery("From Estudiante").list();
            this.session.close();
            return lista;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public EstudianteHb consultarXCodigo(Integer id){

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<EstudianteHb> lista = this.session.createQuery("From Estudiante where id="+id).list();
            this.session.close();
            return lista.get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Estudiante> consultaNombre(String nombre){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<Estudiante> lista = this.session.createQuery("From Estudiante where nombre='"+nombre+"'").list();
            this.session.close();
                return lista;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
