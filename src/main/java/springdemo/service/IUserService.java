package springdemo.service;

import springdemo.entities.Users;

import java.util.List;


public interface IUserService {

	public Integer regist(Users user);

	public Users login(Users user);

	public List<Users> getAllUsers(Integer pageNum, Integer maxPage);

	public Integer modifyUserinfoById(Users user);

	public Integer delectUserinfoById(Users user);
}
