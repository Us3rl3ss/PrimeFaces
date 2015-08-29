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

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {
			try {
				return theaterService.getTheaterById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Theater) object).getId());
		} else {
			return null;
		}
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

}
