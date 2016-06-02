/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.converter;

import cr.ac.una.icai.sipreli.clases.Libro;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author trycatch
 */
@FacesConverter("libroConverter")
public class LibroConverter implements Converter{

        @Override
	public Object getAsObject(FacesContext context, UIComponent arg1, String valor) {
		try {			
			Libro libro = new Libro();
			String[] valores = valor.split("~");
			if(valores.length == 2){
				libro.setIsbn(Integer.parseInt(valores[0].trim()));
				libro.setNombre(valores[1].trim());
				return libro;				
			} else{		
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error 1 de conversion de Libro");
				throw new ConverterException(msg);				
			}
		} catch (NumberFormatException e){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error 2 de conversion de Libro");
			throw new ConverterException(msg);
		}
	}

        @Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		String valor = "";
		if(object!=null){
			if(object instanceof Libro){ 			
				Libro libro = (Libro) object;			
				valor = libro.getIsbn()+ "~" + libro.getNombre();			
			}else{			
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversion, el objeto no corresponde a un Libro");
				throw new ConverterException(msg);			
			}
		}else{
			valor = "";
		}
		return valor;
	}
}
