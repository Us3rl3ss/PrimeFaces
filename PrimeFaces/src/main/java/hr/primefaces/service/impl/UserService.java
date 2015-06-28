package hr.primefaces.service.impl;

import hr.primefaces.dao.IUserDAO;
import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class UserService implements IUserService, Serializable  {

	private static final long serialVersionUID = 1L;
	
	IUserDAO userDAO;

	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
		getUserDAO().addUser(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}

	@Override
	public User getUserById(int id) {
		return getUserDAO().getUserById(id);
	}

	@Override
	public User getUserByDistinctUsername(String username) {
		return getUserDAO().getUserByDistinctUsername(username);
	}
	
	@Override
	public List<User> getUserByUsername(String username) {
		return getUserDAO().getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return getUserDAO().getUsers();
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public List<User> getUserFollow(User loginUser, User currUser) {
		return getUserDAO().getUserFollow(loginUser, currUser);
	}

	@Override
	public List<User> getUserFollowByUser(User loginUser) {
		return getUserDAO().getUserFollowByUser(loginUser);
	}
	
	@Override
	public List<User> getUserFollowByFollower(User currUser) {
		return getUserDAO().getUserFollowByFollower(currUser);
	}
}
