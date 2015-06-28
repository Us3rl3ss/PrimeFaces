package hr.primefaces.converter;

import hr.primefaces.bean.DropdownMenuManagedBean;
import hr.primefaces.model.Cinema;

import java.util.List;

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

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {
			try {

				List<Cinema> list = dropDownMB.getCinemaList();

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId() == Integer.parseInt(value))
						return list.get(i);
				}

				return null;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid cinema."));
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

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
	}

}
