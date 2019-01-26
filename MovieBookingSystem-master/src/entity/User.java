package entity;

/**
 * Represents a user that has been registered with the movie booking system. The
 * user's login & contact details are stored in the system.
 * 
 * @author SuyashL
 */

public class User {
	private int userID;
	private String name;
	private String mobile_no;
	private String email;
	private int age;
	private String password;

	/**
	 * Constructor method for User class.
	 * 
	 * @param userID
	 * @param name
	 * @param mobile_no
	 * @param email
	 * @param age
	 * @param password
	 */
	public User(int userID, String name, String mobile_no, String email, int age, String password) {
		super();
		this.userID = userID;
		this.name = name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.age = age;
		this.password = password;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile_no
	 */
	public String getMobile_no() {
		return mobile_no;
	}

	/**
	 * @param mobile_no
	 *            the mobile_no to set
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
