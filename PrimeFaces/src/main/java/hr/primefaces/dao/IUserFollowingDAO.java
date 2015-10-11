package hr.primefaces.dao;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFollowing;

public interface IUserFollowingDAO {

	public void addUserFollowing(UserFollowing userFollowing);

	public void updateUserFollowing(UserFollowing userFollowing);

	public void deleteUserFollowing(UserFollowing userFollowing);

	public UserFollowing getUserFriends(User user, User user2);
}
