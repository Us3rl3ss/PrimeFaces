package hr.primefaces.converter;

import hr.primefaces.imdb.ImdbAPI;
import hr.primefaces.imdb.ImdbJsonModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@ManagedBean
@RequestScoped
public class ImdbMovieConverter implements Converter {

	/**
	 * getAsObject
	 */
	public Object getAsObject(final FacesContext p_fc, final UIComponent p_uic, final String p_value) {

		if (p_value != null && p_value.trim().length() > 0) {

			try {
				return ImdbAPI.getMovieFromImdbById(p_value);
			}
			catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		}
		else {
			return null;
		}
	}

	/**
	 * getAsString
	 */
	public String getAsString(final FacesContext p_fc, final UIComponent p_uic, final Object p_object) {

		if (p_object != null) {
			return String.valueOf(((ImdbJsonModel) p_object).getIdIMDB());
		}
		else {
			return null;
		}
	}

}
