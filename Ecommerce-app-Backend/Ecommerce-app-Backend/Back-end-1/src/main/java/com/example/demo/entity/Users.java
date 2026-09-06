package com.example.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class Users {

//	@Column(name="userid")
//	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long userId;
	
	@Column(name="username")
	private String userName;
	
	@Id
	@Column(name="useremail")
	private String email;
	
	@Column(name="userpassword")
	private String password;
	
	@Column(name="userRepassword")
	private String reEnterPassword;

	@Override
	public String toString() {
		return "Users [ userName=" + userName + ", email=" + email + ", password=" + password
				+ ", reEnterPassword=" + reEnterPassword + "]";
	}

	public Users(Long userId, String userName, String email, String password, String reEnterPassword) {
		super();
//		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.reEnterPassword = reEnterPassword;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}
	
	
	
	
}

