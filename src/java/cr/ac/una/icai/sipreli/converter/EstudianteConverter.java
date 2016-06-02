/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.converter;

import cr.ac.una.icai.sipreli.clases.Estudiante;
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
@FacesConverter("estudianteConverter")
public class EstudianteConverter implements Converter{

        @Override
	public Object getAsObject(FacesContext context, UIComponent arg1, String valor) {
		try {			
			Estudiante est = new Estudiante();
			String[] valores = valor.split("~");
			if(valores.length == 2){
				est.setCarnet(Integer.parseInt(valores[0].trim()));
				est.setNombre(valores[1].trim());
				return est;				
			} else{		
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error 1 de conversion de Estudiante");
				throw new ConverterException(msg);				
			}
		} catch (NumberFormatException e){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error 2 de conversion de Estudiante");
			throw new ConverterException(msg);
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		String valor = "";
		if(object!=null){
			if(object instanceof Estudiante){ 			
				Estudiante est = (Estudiante) object;			
				valor = est.getCarnet() + "~" + est.getNombre();			
			}else{			
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversion, el objeto no corresponde a un Estudiante");
				throw new ConverterException(msg);			
			}
		}else{
			valor = null;
		}
		return valor;
	}
   
}
