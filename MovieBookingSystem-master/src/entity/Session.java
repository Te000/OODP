package entity;

import control.CinemaController;

/**
 * Represents a particular showtime in a particular cinema. Each showtime has
 * it's own unique sessionID.
 * 
 * @author SuyashL
 */

public class Session {
	private int sessionID;
	private int movieID;
	private int cinemaID;
	private String date;
	private String start_time;
	private String end_time;
	private boolean seatPlan[][] = new boolean[10][20];

	/**
	 * Constructor method for Session class.
	 * 
	 * @param sessionID
	 * @param movieID
	 * @param cinemaID
	 * @param date
	 * @param start_time
	 * @param end_time
	 * @param seatPlan
	 */
	public Session(int sessionID, int movieID, int cinemaID, String date, String start_time, String end_time,
			boolean[][] seatPlan) {
		super();
		this.sessionID = sessionID;
		this.movieID = movieID;
		this.cinemaID = cinemaID;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.seatPlan = seatPlan;
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
	 * @return the movieID
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * @param movieID
	 *            the movieID to set
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * @return the cinemaID
	 */
	public int getCinemaID() {
		return cinemaID;
	}

	/**
	 * @param cinemaID
	 *            the cinemaID to set
	 */
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the start_time
	 */
	public String getStartTime() {
		return start_time;
	}

	/**
	 * @param start_time
	 *            the start_time to set
	 */
	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return the end_time
	 */
	public String getEndTime() {
		return end_time;
	}

	/**
	 * @param end_time
	 *            the end_time to set
	 */
	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}

	/**
	 * @return the seatPlan
	 */
	public boolean[][] getSeatPlan() {
		return seatPlan;
	}

	/**
	 * 
	 * @param seatPlan
	 *            the seatPlan to set
	 */
	public void setSeatPlan(boolean seatPlan[][]) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				this.seatPlan[i][j] = seatPlan[i][j];
			}
		}
	}

	/**
	 * Calculates the total seats & seats available in this particular session.
	 * 
	 * @return the seats available in "X/Y" format
	 */
	public String seatsAvailable() {
		String x, y;
		int a, z = 0;

		if (CinemaController.cinemaType(this.cinemaID) == "N") {
			y = "180";
		} else {
			y = "30";
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if (this.seatPlan[i][j] == true) {
					z++;
				}
			}
		}

		a = Integer.parseInt(y) - z;
		x = Integer.toString(a);

		return x + "/" + y;
	}

	/**
	 * Calcuates the total seats & seats booked in this particular session.
	 * 
	 * @return the seats booked in "X/Y" format
	 */
	public String seatsBooked() {
		String a, y;
		int z = 0;

		if (CinemaController.cinemaType(this.cinemaID).equals("N")) {
			y = "180";
		} else {
			y = "30";
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if (this.seatPlan[i][j] == true) {
					z++;
				}
			}
		}

		a = String.valueOf(z);
		return a + "/" + y;
	}
}
