package hr.primefaces.bean;

import hr.primefaces.constants.Messages;
import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{navigationController}")
	NavigationControllerBean navigationControllerMB;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	private String username;
	private String password;

	private User user;

	public String login() {

		String result = null;

		user = userService.getUserByDistinctUsername(username);

		if (user != null) {

			if (user.getPassword().equals(password)) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage(
						Messages.SUCCESSFUL_LOGIN_MSG));

				result = "home";
				// result = "templates/common.xhtml";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage(
						Messages.WRONG_PASSWORD_MSG));
			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage(
					Messages.USER_DONT_EXIST_MSG));
		}

		return result;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return navigationControllerMB.doViewLogin();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public NavigationControllerBean getNavigationControllerMB() {
		return navigationControllerMB;
	}

	public void setNavigationControllerMB(
			NavigationControllerBean navigationControllerMB) {
		this.navigationControllerMB = navigationControllerMB;
	}

}