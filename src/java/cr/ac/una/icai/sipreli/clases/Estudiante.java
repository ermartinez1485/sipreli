package cr.ac.una.icai.sipreli.clases;
// Generated May 19, 2016 3:09:41 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Estudiante generated by hbm2java
 */
public class Estudiante  implements java.io.Serializable {


     private int carnet;
     private String nombre;
     private Integer telefono;
     private String direccion;
     private String email;
     private String escuela;
     private Set<Prestamo> prestamos = new HashSet<Prestamo>(0);

    public Estudiante() {
    }

	
    public Estudiante(int carnet) {
        this.carnet = carnet;
    }
    public Estudiante(int carnet, String nombre, Integer telefono, String direccion, String email, String escuela, Set<Prestamo> prestamos) {
       this.carnet = carnet;
       this.nombre = nombre;
       this.telefono = telefono;
       this.direccion = direccion;
       this.email = email;
       this.escuela = escuela;
       this.prestamos = prestamos;
    }
   
    public int getCarnet() {
        return this.carnet;
    }
    
    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEscuela() {
        return this.escuela;
    }
    
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
    public Set<Prestamo> getPrestamos() {
        return this.prestamos;
    }
    
    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.carnet;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estudiante other = (Estudiante) obj;
        if (this.carnet != other.carnet) {
            return false;
        }
        return true;
    }




}


