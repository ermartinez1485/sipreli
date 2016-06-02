/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate;

import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.ILibroHb;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eric.martinez
 */
public class LibroHb implements ILibroHb {
    
    private Session session;

    public LibroHb() {
        this.session = null;
    }

    @Override
    public Boolean insertar(Libro elLibro){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.save(elLibro);
            tr.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean eliminar(Libro elLibro){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.delete(elLibro);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean modificar(Libro elLibro){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.merge(elLibro);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<Libro> consultarTodos(){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<Libro> lista = this.session.createQuery("From Libro").list();
            this.session.close();
            return lista;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public LibroHb consultarXCodigo(Integer id){

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<LibroHb> lista = this.session.createQuery("From LibroHb where id="+id).list();
            this.session.close();
            return lista.get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Libro> consultaNombre(String nombre){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<Libro> lista = this.session.createQuery("From Libro where nombre='"+nombre+"'").list();
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
