package entity;

/**
 * Represents a booking made by a user. A single booking may be applicable to
 * multiple seats but only to one particular session.
 * 
 * @author SuyashL
 */

public class Booking {
	private String transID;
	private int userID;
	private int sessionID;
	private int num_seats;
	private String booked_seats[] = new String[num_seats];
	private int cost;

	/**
	 * Constructor method for Booking class.
	 * 
	 * @param transID
	 * @param userID
	 * @param sessionID
	 * @param num_seats
	 * @param booked_seats
	 * @param cost
	 */
	public Booking(String transID, int userID, int sessionID, int num_seats, String[] booked_seats, int cost) {
		super();
		this.transID = transID;
		this.userID = userID;
		this.sessionID = sessionID;
		this.num_seats = num_seats;
		this.booked_seats = booked_seats;
		this.cost = cost;
	}

	/**
	 * @return the transID
	 */
	public String getTransID() {
		return transID;
	}

	/**
	 * @param transID
	 *            the transID to set
	 */
	public void setTransID(String transID) {
		this.transID = transID;
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
	 * @return the sessionID
	 */
	public int getSessionID() {
		return sessionID;
	}

	/**
	 * @param sessionID
	 *            the sessionID to set
	 */
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * @return the num_seats
	 */
	public int getNum_seats() {
		return num_seats;
	}

	/**
	 * @param num_seats
	 *            the num_seats to set
	 */
	public void setNum_seats(int num_seats) {
		this.num_seats = num_seats;
	}

	/**
	 * @return the booked_seats
	 */
	public String[] getBooked_seats() {
		return booked_seats;
	}

	/**
	 * @param booked_seats
	 *            the booked_seats to set
	 */
	public void setBooked_seats(String[] booked_seats) {
		this.booked_seats = booked_seats;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
}
