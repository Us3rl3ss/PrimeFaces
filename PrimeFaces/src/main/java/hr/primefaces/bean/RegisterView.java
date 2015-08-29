package hr.primefaces.bean;

import hr.primefaces.constants.Messages;
import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "registerMB")
@ViewScoped
public class RegisterView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	private User user = new User();

	public String register() {

		boolean pass = true;

		try {
			userService.addUser(user);
		} catch (DataIntegrityViolationException divex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(Messages.USER_ALREADY_EXIST_MSG));
			pass = false;
		}

		if (pass) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(Messages.SUCCESSFUL_REGISTER_MSG));

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('registracija').hide();");
		}

		return "";
	}

	public String odustani() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('registracija').hide();");

		return "";
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}