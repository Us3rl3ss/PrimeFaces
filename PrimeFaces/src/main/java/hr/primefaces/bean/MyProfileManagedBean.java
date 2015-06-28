package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "myProfileMB")
@ViewScoped
public class MyProfileManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	LoginManagedBean loginMB;
	
	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	private User user;

	@PostConstruct
	public void init() {
		this.user = loginMB.getUser();
	}
	
	public void spremi() {

		try {
			user.setUpdated(new Date());
			userService.updateUser(user);
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
		}
	}

	public LoginManagedBean getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginManagedBean loginMB) {
		this.loginMB = loginMB;
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

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}