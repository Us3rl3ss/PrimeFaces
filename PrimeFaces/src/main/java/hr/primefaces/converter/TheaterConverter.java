package hr.primefaces.converter;

import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@ManagedBean
@RequestScoped
public class TheaterConverter implements Converter {

	@ManagedProperty("#{TheaterService}")
	private ITheaterService theaterService;

	/**
	 * getAsObject
	 */
	public Object getAsObject(final FacesContext p_fc, final UIComponent p_uic, final String p_value) {

		if (p_value != null && p_value.trim().length() > 0) {

			try {
				return getTheaterService().getTheaterById(Integer.parseInt(p_value));
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
			return String.valueOf(((Theater) p_object).getId());
		}
		else {
			return null;
		}
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(final ITheaterService p_theaterService) {
		this.theaterService = p_theaterService;
	}

}
