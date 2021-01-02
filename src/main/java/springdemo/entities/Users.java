package springdemo.entities;

import java.io.Serializable;


public class Users implements Serializable {
	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", password=" + password + ", sex=" + sex + "]";
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	// private static final long serialVersionUID = -7283793699677844366L;
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String username;
	private String password;
	private String sex;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(Integer userid, String username, String password, String sex) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.sex = sex;
	}

}
