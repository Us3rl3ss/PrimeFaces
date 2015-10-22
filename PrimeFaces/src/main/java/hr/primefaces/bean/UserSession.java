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

import org.primefaces.context.RequestContext;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String NAV_COMPONENT = ":leftNavForm :upperNavForm";

	@ManagedProperty(value = "#{navigationController}")
	private NavigationControllerBean navigationControllerMB;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	private User user;
	private boolean isLogged = false;
	private String userRole;
	private String username;
	private String password;

	public String login() {

		setUser(getUserService().getUserByDistinctUsername(getUsername()));

		if (getUser() != null) {

			if (getUser().getPassword().equals(getPassword())) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Messages.SUCCESSFUL_LOGIN_MSG));

				setLogged(true);
				setUserRole(getUser().getRole().getCode());

				RequestContext.getCurrentInstance().update(NAV_COMPONENT);
				return getNavigationControllerMB().doViewHome();
			}
			else {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Messages.WRONG_PASSWORD_MSG));
				return "";
			}
		}
		else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Messages.USER_DONT_EXIST_MSG));
			return "";
		}
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		setLogged(false);
		setUserRole(null);

		RequestContext.getCurrentInstance().update(NAV_COMPONENT);
		return getNavigationControllerMB().doViewHome();
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the navigationControllerMB
	 */
	public NavigationControllerBean getNavigationControllerMB() {
		return navigationControllerMB;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param p_navigationControllerMB the navigationControllerMB to set
	 */
	public void setNavigationControllerMB(final NavigationControllerBean p_navigationControllerMB) {
		this.navigationControllerMB = p_navigationControllerMB;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

	/**
	 * @param p_isLogged the isLogged to set
	 */
	public void setLogged(final boolean p_isLogged) {
		this.isLogged = p_isLogged;
	}

	/**
	 * @param p_userRole the userRole to set
	 */
	public void setUserRole(final String p_userRole) {
		this.userRole = p_userRole;
	}

	/**
	 * @param p_username the username to set
	 */
	public void setUsername(final String p_username) {
		this.username = p_username;
	}

	/**
	 * @param p_password the password to set
	 */
	public void setPassword(final String p_password) {
		this.password = p_password;
	}

}