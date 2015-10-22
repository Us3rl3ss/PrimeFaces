package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getUserMB")
@ViewScoped
public class GetUserView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	private List<User> userList;

	@PostConstruct
	public void init() {

		setUserList(getUserService().getUsers());
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_userList the userList to set
	 */
	public void setUserList(final List<User> p_userList) {
		this.userList = p_userList;
	}

}