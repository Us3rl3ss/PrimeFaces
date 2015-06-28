package hr.primefaces.converter;

import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

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
public class UserConverter implements Converter {

	@ManagedProperty("#{UserService}")
	private IUserService userService;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {
			try {

				User user = (User) userService.getUserById(Integer
						.parseInt(value));

				return user;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid user."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((User) object).getId());
		} else {
			return null;
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
