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

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	private User user;
	private User selectedUser;
	private List<User> userFollowList;
	private Integer numberOfFollowers;

	@PostConstruct
	public void init() {

		setNumberOfFollowers(0);
		setUserFollowList(getUserService().getUserFollowByUser(getUserSession().getUser()));
	}

	/**
	 * calculateNumberOfFollowers
	 */
	public void calculateNumberOfFollowers() {

		setNumberOfFollowers(getUserService().getUserFollowByFollower(getSelectedUser()).size());
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userSession
	 */
	public UserSession getUserSession() {
		return userSession;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the userFollowList
	 */
	public List<User> getUserFollowList() {
		return userFollowList;
	}

	/**
	 * @return the selectedUser
	 */
	public User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @return the numberOfFollowers
	 */
	public Integer getNumberOfFollowers() {
		return numberOfFollowers;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param p_userSession the userSession to set
	 */
	public void setUserSession(final UserSession p_userSession) {
		this.userSession = p_userSession;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_userFollowList the userFollowList to set
	 */
	public void setUserFollowList(final List<User> p_userFollowList) {
		this.userFollowList = p_userFollowList;
	}

	/**
	 * @param p_selectedUser the selectedUser to set
	 */
	public void setSelectedUser(final User p_selectedUser) {
		this.selectedUser = p_selectedUser;
	}

	/**
	 * @param p_numberOfFollowers the numberOfFollowers to set
	 */
	public void setNumberOfFollowers(final Integer p_numberOfFollowers) {
		this.numberOfFollowers = p_numberOfFollowers;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

}