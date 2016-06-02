/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.datos.hibernate;

import cr.ac.una.icai.sipreli.datos.hibernate.interfaces.IUsuarioHb;
import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eric.martinez
 */
public class UsuarioHb implements IUsuarioHb {
    
    private Session session;

    public UsuarioHb() {
        this.session = null;
    }
    

    @Override
    public Boolean insertar(UsuarioLogin elUsuario){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.save(elUsuario);
            tr.commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean eliminar(UsuarioLogin elUsuario){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.delete(elUsuario);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Boolean modificar(UsuarioLogin elUsuario){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tr = this.session.beginTransaction();
            this.session.merge(elUsuario);
            tr.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<UsuarioLogin> consultarTodos(){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<UsuarioLogin> lista = this.session.createQuery("From UsuarioLogin").list();
            this.session.close();
            return lista;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public UsuarioLogin consultarXCedula(Integer id){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<UsuarioLogin> lista = this.session.createQuery("From UsuarioLogin where id="+id).list();
            this.session.close();
            return lista.get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Integer consultaUser(String usuario, String contrasena){
    
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            this.session.getTransaction().begin();
            List<UsuarioLogin> lista = this.session.createQuery("From UsuarioLogin where usuario='"+usuario+"' and contrasena='"+contrasena+"'").list();
            this.session.close();
            if (!lista.isEmpty()) {
                return 1;
            }
            else return 0;
            
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
