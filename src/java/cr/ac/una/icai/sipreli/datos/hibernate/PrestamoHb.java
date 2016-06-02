/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate;

import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IPrestamoHb;
import cr.ac.una.icai.sipreli.clases.Prestamo;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eric.martinez
 */
public class PrestamoHb implements IPrestamoHb {
    
    private Session session;

    public PrestamoHb() {
        this.session = null;
    }
    
    @Override
    public Boolean insertar(Prestamo elPrestamo){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.save(elPrestamo);
            tr.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean eliminar(Prestamo elPrestamo){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.delete(elPrestamo);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean modificar(Prestamo elPrestamo){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.merge(elPrestamo);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<Prestamo> consultarTodos(){

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<Prestamo> lista = this.session.createQuery("From Prestamo").list();
            this.session.close();
            return lista;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public PrestamoHb consultarXCodigo(Integer id){

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<PrestamoHb> lista = this.session.createQuery("From Prestamo where id="+id).list();
            this.session.close();
            return lista.get(0);
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
