package hr.primefaces.service;

import hr.primefaces.model.User;

import java.util.List;

public interface IUserService {
	
	public void addUser(User user);
	
	public void updateUser(User user);

	public void deleteUser(User user);
	
	public User getUserById(int id);
	
	public User getUserByDistinctUsername(String username);
	
	public List<User> getUsers();
	
	public List<User> getUserByUsername(String input);
	
	public List<User> getUserFollow(User loginUser, User currUser);
	
	public List<User> getUserFollowByUser(User loginUser);
	
	public List<User> getUserFollowByFollower(User currUser);
}
