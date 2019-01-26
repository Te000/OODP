package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import boundary.UIMain;
import entity.Movie;

/**
 * Control class for movie data.
 * 
 * @author SuyashL
 */

public class MovieController implements Loader_Flusher {

	/**
	 * Loads movie data from Movies.txt inside the txt/ directory into the
	 * public ArrayList<Movie>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		Scanner movie_sc = new Scanner(new File("txt/Movies.txt"));

		while (movie_sc.hasNextLine()) {
			String temp[] = (movie_sc.nextLine()).split(";");

			String tempCast[] = temp[5].split(",");
			ArrayList<String> cast = new ArrayList<String>(Arrays.asList(tempCast));

			String tempReview[] = temp[8].split(",");
			ArrayList<String> reviews = new ArrayList<String>(Arrays.asList(tempReview));

			Movie M1 = new Movie((int) Integer.valueOf(temp[0]), temp[1], temp[2], temp[3], temp[4], cast, temp[6],
					(float) Float.valueOf(temp[7]), reviews, temp[9], temp[10], temp[11], temp[12],
					Integer.valueOf(temp[13]), Integer.valueOf(temp[14]), Integer.valueOf(temp[15]));

			UIMain.moviesArray.add(M1);
		}
		movie_sc.close();
	}

	/**
	 * Lists all movies that are currently playing or coming soon.
	 */
	public void ListMovies() {
		for (int i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getStatus().equals("Now Showing")
					|| UIMain.moviesArray.get(i).getStatus().equals("Preview")) {
				System.out.println("MovieID: " + UIMain.moviesArray.get(i).getMovieID() + " Title: "
						+ UIMain.moviesArray.get(i).getTitle());

			}

		}
	}

	/**
	 * Lists all movies in the database.
	 */
	public void AdminListMovies() {
		for (int i = 0; i < UIMain.moviesArray.size(); i++)
			System.out.println("Movie ID: " + UIMain.moviesArray.get(i).getMovieID() + ", Name: "
					+ UIMain.moviesArray.get(i).getTitle());
	}

	/**
	 * Returns the index of a particular movie in the ArrayList<Movie>.
	 * 
	 * @param movieID
	 * @return
	 */
	public int getMovieIndex(int movieID) {
		for (int i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getMovieID() == movieID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Prints the details of a movie looked up using the movie's title.
	 * 
	 * @param movieTitle
	 * @return the movieID
	 */
	public int movieDetails(String movieTitle) {
		for (int i = 0; i < UIMain.moviesArray.size(); i++) {
			if (movieTitle.equalsIgnoreCase(UIMain.moviesArray.get(i).getTitle())) {
				System.out.println();
				System.out.println(UIMain.moviesArray.get(i).getTitle());
				System.out.println("Genre: " + UIMain.moviesArray.get(i).getGenre());
				System.out.println("Censorship: " + UIMain.moviesArray.get(i).getCensorship());
				System.out.println("Type: " + UIMain.moviesArray.get(i).getType());
				System.out.println("Synopsis: " + UIMain.moviesArray.get(i).getSynopsis());
				System.out.println("Director: " + UIMain.moviesArray.get(i).getDirector());
				System.out.println("Rating: " + UIMain.moviesArray.get(i).getAvg_rating());
				System.out.print("Cast: ");
				for (String s : UIMain.moviesArray.get(i).getCast())
					System.out.print(s + ", ");
				System.out.println();
				System.out.println("Reviews: ");
				for (String s : UIMain.moviesArray.get(i).getReviews())
					System.out.println("- " + s);
				return UIMain.moviesArray.get(i).getMovieID();
			}
		}
		return -1;
	}

	/**
	 * Multiple choice selection for censorship field.
	 * 
	 * @return the chosen value for censorship.
	 */
	public String inputCensorship() {
		System.out.println("Select Censorship: ");
		System.out.println("1. G");
		System.out.println("2. PG-13");
		System.out.println("3. R");
		Scanner input = new Scanner(System.in);
		String censorship = "";
		int i = 0;
	       do{
	           try{
	               i=input.nextInt();
	               break;
	            }
	           catch (InputMismatchException e)
	           {
	                System.out.print("Please Re-enter the Choice: ");
	                i =1;
	                input.nextLine();
	            }
	        }while(true);
		switch (i) {
		case 1:
			censorship = "G";
			break;
		case 2:
			censorship = "PG-13";
			break;
		case 3:
			censorship = "R";
			break;
		}

		input.close();

		return censorship;
	}

	/**
	 * Multiple choice selection for status field.
	 * 
	 * @return the chosen value for status.
	 */
	public String inputStatus() {
		System.out.println("Select Status: ");
		System.out.println("1. Preview");
		System.out.println("2. Now Showing");
		System.out.println("3. End of Showing");
		Scanner input = new Scanner(System.in);
		String status = "";
		int i = 0;
	       do{
	           try{
	               i=input.nextInt();
	               break;
	            }
	           catch (InputMismatchException e)
	           {
	                System.out.print("Please Re-enter the Choice: ");
	                i =1;
	                input.nextLine();
	            }
	        }while(true);
		switch (i) {
		case 1:
			status = "Preview";
			break;
		case 2:
			status = "Now Showing";
			break;
		case 3:
			status = "End of Showing";
			break;
		}

		input.close();

		return status;
	}

	/**
	 * Multiple choice selection for movie type field.
	 * 
	 * @return the chosen value for the movie type.
	 */
	public String inputType() {
		System.out.println("Select Movie Type: ");
		System.out.println("1. Normal");
		System.out.println("2. 3D");
		System.out.println("3. Blockbuster");
		Scanner input = new Scanner(System.in);
		String type = "";
		int i = 0;
	       do{
	           try{
	               i=input.nextInt();
	               break;
	            }
	           catch (InputMismatchException e)
	           {
	                System.out.print("Please Re-enter the Choice: ");
	                i =1;
	                input.nextLine();
	            }
	        }while(true);
		switch (i) {
		case 1:
			type = "Normal";
			break;
		case 2:
			type = "3D";
			break;
		case 3:
			type = "BlockBuster";
			break;
		}

		input.close();

		return type;
	}

	/**
	 * Used by the admin to add a new movie.
	 */
	public void addMovie() {
		Scanner input = new Scanner(System.in);
		int movieID;
		String title;
		String genre;
		String type;
		String censorship;
		ArrayList<String> cast;
		String director;
		String status;
		String synopsis;
		String startDate;
		String endDate;
		int hours;
		ArrayList<String> review = new ArrayList<String>();
		if (UIMain.moviesArray.size() == 0)
			movieID = 1;
		else
			movieID = UIMain.moviesArray.get(UIMain.moviesArray.size() - 1).getMovieID() + 1;
		System.out.println("Movie ID: " + movieID);
		System.out.print("Enter Title: ");
		title = input.nextLine();
		System.out.print("Enter Genre: ");
		genre = input.nextLine();
		System.out.print("Enter Type: ");
		type = inputType();
		System.out.print("select Censorship: ");
		censorship = inputCensorship();
		System.out.print("Enter Director: ");
		director = input.nextLine();
		System.out.print("Enter Status: ");
		status = inputStatus();
		System.out.print("Enter Synopsis: ");
		synopsis = input.nextLine();
		System.out.print("Enter Start Date: ");
		startDate = input.nextLine();
		System.out.print("Enter End Date: ");
		endDate = input.nextLine();
		System.out.print("Enter Cast separated by ',': ");
		String castString = input.nextLine();
		String castStr[] = castString.split(",");
		cast = new ArrayList<String>(Arrays.asList(castStr));
		System.out.print("Enter Running Time (in hours): ");
		hours = input.nextInt();
		UIMain.moviesArray.add(new Movie(movieID, title, genre, type, censorship, cast, director, (float) 0, review,
				status, synopsis, startDate, endDate, 0, 0, hours));

		input.close();
	}

	/**
	 * Used by the admin to edit the details of an existing movie.
	 */
	public void editMovie() {
		Scanner input = new Scanner(System.in);
		ListMovies();
		System.out.print("Enter Movie ID to Edit: ");
		int num = input.nextInt();

		System.out.println("Which field do you want to edit :");
		System.out.println(
				"1. Title\n2. Genre\n3. Type\n4. Censorship\n5. Cast\n6. Director\n7. Status\n8. Synopsis\n9. Start Date\n10. End Date\n11. Exit");
		int field = 0;
	       do{
	           try{
	               field=input.nextInt();
	               break;
	            }
	           catch (InputMismatchException e)
	           {
	                System.out.print("Please Re-enter the Choice: ");
	                field =1;
	                input.nextLine();
	            }
	        }while(true);
		do {
			switch (field) {
			case 1:
				input.nextLine();
				System.out.print("Enter new Title: ");
				UIMain.moviesArray.get(num - 1).setTitle(input.nextLine());
				break;
			case 2:
				input.nextLine();
				System.out.print("Enter new Genre: ");
				UIMain.moviesArray.get(num - 1).setGenre(input.nextLine());
				break;
			case 3:
				UIMain.moviesArray.get(num - 1).setType(inputType());
				break;
			case 4:
				UIMain.moviesArray.get(num - 1).setCensorship(inputCensorship());
				break;
			case 5:
				input.nextLine();
				System.out.print("Enter Cast separated by ',' :");
				String castString = input.nextLine();
				String castStr[] = castString.split(",");
				UIMain.moviesArray.get(num - 1).setCast(new ArrayList<String>(Arrays.asList(castStr)));
				break;
			case 6:
				input.nextLine();
				System.out.print("Enter new Director: ");
				UIMain.moviesArray.get(num - 1).setDirector(input.nextLine());
				break;
			case 7:
				UIMain.moviesArray.get(num - 1).setStatus(inputStatus());
				break;
			case 8:
				input.nextLine();
				System.out.print("Enter new Synopsis: ");
				UIMain.moviesArray.get(num - 1).setSynopsis(input.nextLine());
				break;
			case 9:
				input.nextLine();
				System.out.print("Enter new Start Date: ");
				UIMain.moviesArray.get(num - 1).setStartDate(input.nextLine());
				break;
			case 10:
				input.nextLine();
				System.out.print("Enter new End Date: ");
				UIMain.moviesArray.get(num - 1).setEndDate(input.nextLine());
				break;
			case 11:
				System.out.println("Exiting!");
				break;
			default:
				System.out.println("Invalid Input!");
				break;
			}
			System.out.print("Enter Another Field to Enter: ");
			field = input.nextInt();
			if (field >= 11) {
				System.out.println("Exiting");
				break;
			}
		} while (true);

		input.close();
	}

	/**
	 * Used by the admin to change a movie's status to "End of Showing".
	 */
	public void deleteMovie() {
		Scanner input = new Scanner(System.in);
		ListMovies();

		System.out.print("Enter Movie ID to stop showing: ");
		int num = input.nextInt();
		UIMain.moviesArray.get(num - 1).setStatus("End of Showing");

		input.close();
	}

	/**
	 * @param movieID
	 * @return the title of the movie
	 */
	public String giveTitle(int movieID) {
		int i;
		for (i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getMovieID() == movieID)
				return UIMain.moviesArray.get(i).getTitle();
		}
		if (i == UIMain.moviesArray.size())
			return "Not Found";
		return "";
	}

	/**
	 * @param movieTitle
	 * @return the movieID of the movie
	 */
	public int giveID(String movieTitle) {
		int i;
		for (i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getTitle().equals(movieTitle))
				return UIMain.moviesArray.get(i).getMovieID();
		}
		return 0;
	}

	/**
	 * @param movieID
	 * @return the start date of the movie
	 */
	public String startDate(int movieID) {
		for (int i = 0; i < UIMain.moviesArray.size(); i++)
			if (UIMain.moviesArray.get(i).getMovieID() == movieID) {
				return (UIMain.moviesArray.get(i).getStartDate());
			}
		return null;
	}

	/**
	 * @param movieID
	 * @return the end date of the movie
	 */
	public String endDate(int movieID) {
		for (int i = 0; i < UIMain.moviesArray.size(); i++)
			if (UIMain.moviesArray.get(i).getMovieID() == movieID) {
				return (UIMain.moviesArray.get(i).getEndDate());
			}
		return null;
	}

	/**
	 * @param movieID
	 * @return the runtime of the movie
	 */
	public int giveHours(int movieID) {
		int i;
		for (i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getMovieID() == movieID)
				return UIMain.moviesArray.get(i).getHours();
		}
		if (i == UIMain.moviesArray.size())
			return -1;
		return -1;
	}

	/**
	 * @param movieID
	 * @return the type of the movie
	 */
	public String giveType(int movieID) {
		int i;
		for (i = 0; i < UIMain.moviesArray.size(); i++) {
			if (UIMain.moviesArray.get(i).getMovieID() == movieID)
				return UIMain.moviesArray.get(i).getType();
		}
		if (i == UIMain.moviesArray.size())
			return "Not Found";
		return "";
	}

	/**
	 * Adds a new user review to a movie.
	 * 
	 * @param userID
	 * @param mov
	 */
	public void setReview(int userID, Movie mov) {
		int rating, num;
		float final_rating, total;
		String review;
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter rating out of 5 for " + mov.getTitle() + ":");
		rating = sc.nextInt();
		total = mov.getAvg_rating() * mov.getNo_ratings();
		total += rating;
		num = mov.getNo_ratings();
		num++;
		final_rating = (float) (Math.floor((total / num) * 100)) / 100;
		System.out.println("Enter review for the movie: ");
		review = sc.nextLine();
		ArrayList<String> rev;
		rev = mov.getReviews();
		review = sc.nextLine();
		rev.add(review);

		mov.setAvg_rating(final_rating);
		mov.setNo_ratings(num);
		mov.setReviews(rev);
	}

	/**
	 * @return an ArrayList of movies sorted by rating.
	 */
	public static ArrayList<Movie> sortByRating() {
		ArrayList<Movie> _rating = new ArrayList<Movie>();
		float temp;
		int i, j, N;
		for (j = 0; j < UIMain.moviesArray.size(); j++)
			_rating.add(UIMain.moviesArray.get(j));
		N = _rating.size();
		for (i = 1; i < N; i++) {
			temp = _rating.get(i).getAvg_rating();
			j = i - 1;
			while ((j > -1) && (_rating.get(j).getAvg_rating() < temp)) {
				Collections.swap(_rating, j + 1, j);
				j--;
			}
		}
		return _rating;
	}

	/**
	 * @return an ArrayList of movies sorted by sales.
	 */
	public static ArrayList<Movie> sortBySales() {
		ArrayList<Movie> _rating = new ArrayList<Movie>();
		float temp;
		int i, j, N;
		for (j = 0; j < UIMain.moviesArray.size(); j++)
			_rating.add(UIMain.moviesArray.get(j));
		N = _rating.size();
		for (i = 1; i < N; i++) {
			temp = _rating.get(i).getSales();
			j = i - 1;
			while ((j > -1) && (_rating.get(j).getSales() < temp)) {
				Collections.swap(_rating, j + 1, j);
				j--;
			}
		}
		return _rating;
	}

	/**
	 * Loads updated movie data back into Movies.txt inside the txt/ directory
	 * from the public ArrayList<Movie>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void flush() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("txt/Movies.txt");
		for (int i = 0; i < UIMain.moviesArray.size(); i++) {
			int movieID = UIMain.moviesArray.get(i).getMovieID();
			String title = UIMain.moviesArray.get(i).getTitle();
			String genre = UIMain.moviesArray.get(i).getGenre();
			String type = UIMain.moviesArray.get(i).getType();
			String censorship = UIMain.moviesArray.get(i).getCensorship();
			ArrayList<String> cast = UIMain.moviesArray.get(i).getCast();
			String director = UIMain.moviesArray.get(i).getDirector();
			float avg_rating = UIMain.moviesArray.get(i).getAvg_rating();
			ArrayList<String> reviews = UIMain.moviesArray.get(i).getReviews();
			String status = UIMain.moviesArray.get(i).getStatus();
			String synopsis = UIMain.moviesArray.get(i).getSynopsis();
			String startDate = UIMain.moviesArray.get(i).getStartDate();
			String endDate = UIMain.moviesArray.get(i).getEndDate();
			int no_ratings = UIMain.moviesArray.get(i).getNo_ratings();
			int sales = UIMain.moviesArray.get(i).getSales();
			int hours = UIMain.moviesArray.get(i).getHours();
			writer.print(String.valueOf(movieID) + ";");
			writer.print(title + ";");
			writer.print(genre + ";");
			writer.print(type + ";");
			writer.print(censorship + ";");
			for (int j = 0; j < cast.size(); j++) {
				writer.print(cast.get(j) + ",");
			}
			writer.print(";");
			writer.print(director + ";");
			writer.print(String.valueOf(avg_rating) + ";");
			for (int j = 0; j < reviews.size(); j++) {
				writer.print(reviews.get(j) + ",");
			}
			writer.print(";");
			writer.print(status + ";");
			writer.print(synopsis + ";");
			writer.print(startDate + ";");
			writer.print(endDate + ";");
			writer.print(String.valueOf(no_ratings) + ";");
			writer.print(String.valueOf(sales) + ";");
			writer.print(String.valueOf(hours));

			if (i != UIMain.moviesArray.size() - 1)
				writer.println();
		}
		writer.close();
	}
}