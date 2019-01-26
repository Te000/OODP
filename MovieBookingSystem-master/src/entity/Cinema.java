package entity;

/**
 * Represents a cinema inside a cineplex. Each cinema has it's own unique
 * cinemaID. cinemaType stores whether the cinema is a normal or a platinum
 * cinema.
 * 
 * @author SuyashL
 */

public class Cinema {
	private int cinemaID;
	private int cineplexID;
	private char cinemaType;

	/**
	 * Constructor method for Cinema class.
	 * 
	 * @param cinemaID
	 * @param cineplexID
	 * @param cinemaType
	 */
	public Cinema(int cinemaID, int cineplexID, char cinemaType) {
		super();
		this.cinemaID = cinemaID;
		this.cineplexID = cineplexID;
		this.cinemaType = cinemaType;
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
	 * @return the cineplexID
	 */
	public int getCineplexID() {
		return cineplexID;
	}

	/**
	 * @param cineplexID
	 *            the cineplexID to set
	 */
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}

	/**
	 * @return the cinemaType
	 */
	public char getCinemaType() {
		return cinemaType;
	}

	/**
	 * @param cinemaType
	 *            the cinemaType to set
	 */
	public void setCinemaType(char cinemaType) {
		this.cinemaType = cinemaType;
	}
}
