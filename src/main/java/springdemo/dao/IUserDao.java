package springdemo.dao;

import org.apache.ibatis.annotations.*;
import springdemo.entities.Users;

import java.util.List;


@Mapper
public interface IUserDao {
	@Insert("insert into users (username,password,sex) values(#{username},#{password},#{sex})")
	public Integer regist(Users user);

	@Select("select * from users where username=#{username} and password=#{password}")
	public Users login(Users user);

	@Select("select * from users")
	public List<Users> getAllUsers();

	@Update("update users set password=#{password} where userid=#{userid}")
	public Integer modifyUserinfoById(Users user);

	@Delete("delete from users where userid=#{userid}")
	public Integer delectUserById(Users user);
}
