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
	IUserService userService;

	private List<User> userList;

	@PostConstruct
	public void init() {
		userList = userService.getUsers();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}