package hr.primefaces.converter;

import hr.primefaces.model.Cinema;
import hr.primefaces.service.ICinemaService;

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
public class CinemaConverter implements Converter {

	@ManagedProperty("#{CinemaService}")
	private ICinemaService cinemaService;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {
			try {
				return cinemaService.getCinemaById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Cinema) object).getId());
		} else {
			return null;
		}
	}

	public ICinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(ICinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

}
