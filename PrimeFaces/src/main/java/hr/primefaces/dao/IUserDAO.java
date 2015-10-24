package hr.primefaces.dao;

import hr.primefaces.model.User;

import java.util.List;

public interface IUserDAO {

	void addUser(User p_user);

	void updateUser(User p_user);

	void deleteUser(User p_user);

	User getUserById(int p_id);

	User getUserByDistinctUsername(String p_username);

	List<User> getUsers();

	List<User> getUserByUsername(String p_input);

	List<User> getUserFollow(User p_loginUser, User p_currUser);

	List<User> getUserFollowByUser(User p_loginUser);

	List<User> getUserFollowByFollower(User p_currUser);

}
