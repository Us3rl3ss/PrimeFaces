package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "myFriendsMB")
@ViewScoped
public class MyFriendsView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	LoginView loginMB;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	private List<User> userFollowList;

	private User selectedUser;

	private Integer numberOfFollowers = 0;

	private User user;

	@PostConstruct
	public void init() {
		this.userFollowList = userService.getUserFollowByUser(loginMB
				.getUser());
	}

	public void calculateNumberOfFollowers() {

		int numberOfFollowers = 0;
		
		List<User> followers = userService.getUserFollowByFollower(this.selectedUser);
		
		numberOfFollowers = followers.size();

		this.numberOfFollowers = numberOfFollowers;
	}

	public LoginView getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginView loginMB) {
		this.loginMB = loginMB;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<User> getUserFollowList() {
		return userFollowList;
	}

	public void setUserFollowList(List<User> userFollowList) {
		this.userFollowList = userFollowList;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Integer getNumberOfFollowers() {
		return numberOfFollowers;
	}

	public void setNumberOfFollowers(Integer numberOfFollowers) {
		this.numberOfFollowers = numberOfFollowers;
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