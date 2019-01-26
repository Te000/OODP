package entity;

import java.util.*;

/**
 * Represents a movie that can be either currently showing, coming soon or not
 * playing in cinemas anymore.
 * 
 * @author SuyashL
 */

public class Movie {
	private int movieID;
	private String title;
	private String genre;
	private String type;
	private String censorship;
	private ArrayList<String> cast;
	private String director;
	private float avg_rating;
	private ArrayList<String> reviews;
	private String status;
	private String synopsis;
	private String startDate;
	private String endDate;
	private int no_ratings;
	private int sales;
	private int hours;

	/**
	 * Constructor method for Booking class.
	 * 
	 * @param movieID
	 * @param title
	 * @param genre
	 * @param type
	 * @param censorship
	 * @param cast
	 * @param director
	 * @param avg_rating
	 * @param reviews
	 * @param status
	 * @param synopsis
	 * @param startDate
	 * @param endDate
	 * @param no_ratings
	 * @param sales
	 * @param hours
	 */
	public Movie(int movieID, String title, String genre, String type, String censorship, ArrayList<String> cast,
			String director, float avg_rating, ArrayList<String> reviews, String status, String synopsis,
			String startDate, String endDate, int no_ratings, int sales, int hours) {
		super();
		this.movieID = movieID;
		this.title = title;
		this.genre = genre;
		this.type = type;
		this.censorship = censorship;
		this.cast = cast;
		this.director = director;
		this.avg_rating = avg_rating;
		this.reviews = reviews;
		this.status = status;
		this.synopsis = synopsis;
		this.startDate = startDate;
		this.endDate = endDate;
		this.no_ratings = no_ratings;
		this.sales = sales;
		this.hours = hours;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the censorship
	 */
	public String getCensorship() {
		return censorship;
	}

	/**
	 * @param censorship
	 *            the censorship to set
	 */
	public void setCensorship(String censorship) {
		this.censorship = censorship;
	}

	/**
	 * @return the cast
	 */
	public ArrayList<String> getCast() {
		return cast;
	}

	/**
	 * @param cast
	 *            the cast to set
	 */
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director
	 *            the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the avg_rating
	 */
	public float getAvg_rating() {
		return avg_rating;
	}

	/**
	 * @param avg_rating
	 *            the avg_rating to set
	 */
	public void setAvg_rating(float avg_rating) {
		this.avg_rating = avg_rating;
	}

	/**
	 * @return the reviews
	 */
	public ArrayList<String> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews
	 *            the reviews to set
	 */
	public void setReviews(ArrayList<String> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis
	 *            the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the no_ratings
	 */
	public int getNo_ratings() {
		return no_ratings;
	}

	/**
	 * @param no_ratings
	 *            the no_ratings to set
	 */
	public void setNo_ratings(int no_ratings) {
		this.no_ratings = no_ratings;
	}

	/**
	 * @return the sales
	 */
	public int getSales() {
		return sales;
	}

	/**
	 * @param sales
	 *            the sales to set
	 */
	public void setSales(int sales) {
		this.sales = sales;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}
}
