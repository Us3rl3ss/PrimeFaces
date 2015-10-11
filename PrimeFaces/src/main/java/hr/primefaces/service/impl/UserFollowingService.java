package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFollowing;
import hr.primefaces.service.IUserFollowingService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "UserFollowingService")
@ApplicationScoped
@Transactional(readOnly = true)
public class UserFollowingService implements IUserFollowingService,
		Serializable {

	private static final long serialVersionUID = 1L;

	IUserFollowingDAO userFollowingDAO;
	List<UserFollowing> userFollowingList;

	public UserFollowingService() {
	}

	public List<UserFollowing> getUserFollowingList() {
		return userFollowingList;
	}

	public void setUserFollowingList(List<UserFollowing> userFollowingList) {
		this.userFollowingList = userFollowingList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().addUserFollowing(userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().deleteUserFollowing(userFollowing);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserFollowing(UserFollowing userFollowing) {
		getUserFollowingDAO().updateUserFollowing(userFollowing);
	}
	
	@Override
	public UserFollowing getUserFriends(User user, User user2) {
		return getUserFollowingDAO().getUserFriends(user, user2);
	}

	public IUserFollowingDAO getUserFollowingDAO() {
		return userFollowingDAO;
	}

	public void setUserFollowingDAO(IUserFollowingDAO userFollowingDAO) {
		this.userFollowingDAO = userFollowingDAO;
	}

}
