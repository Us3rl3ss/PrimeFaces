package hr.primefaces.service;

import hr.primefaces.model.UserFollowing;

public interface IUserFollowingService {

	public void addUserFollowing(UserFollowing userFollowing);

	public void updateUserFollowing(UserFollowing userFollowing);

	public void deleteUserFollowing(UserFollowing userFollowing);
}
