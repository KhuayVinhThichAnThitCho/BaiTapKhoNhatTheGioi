package service;

import dao.UserDao;
import dao.UserDaoimpl;
import entity.User;

public class UserServiceImpl implements UserService{
	private UserDao userDAO = new UserDaoimpl();

	@Override
	public User login(String username, String password) {
		return userDAO.login(username, password);
	}
}
