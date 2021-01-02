package springdemo.service.impl;

import com.github.pagehelper.PageHelper;
import springdemo.dao.IUserDao;
import springdemo.entities.Users;
import springdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdemo.utils.MD5;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public Integer regist(Users user) {
		String md5ofStr = new MD5().getMD5ofStr(user.getPassword());
		user.setPassword(md5ofStr);
		return userDao.regist(user);
	}

	@Override
	public Users login(Users user) {
		String md5ofStr = new MD5().getMD5ofStr(user.getPassword());
		user.setPassword(md5ofStr);
		return userDao.login(user);
	}


	@Override
	public List<Users> getAllUsers(Integer pageNum, Integer maxPage) {

		if (pageNum <= 0) {
			pageNum = 1;
		} else if (pageNum >= maxPage) {
			pageNum = maxPage;
		}
		PageHelper.startPage(pageNum, 3);
		return userDao.getAllUsers();
	}

	@Override
	public Integer modifyUserinfoById(Users user) {
		String md5ofStr = new MD5().getMD5ofStr(user.getPassword());
		user.setPassword(md5ofStr);
		return userDao.modifyUserinfoById(user);
	}

	@Override
	public Integer delectUserinfoById(Users user) {
		return userDao.delectUserById(user);
	}

}
