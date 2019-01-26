package boundary;

import java.util.*;

import control.*;
import entity.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This is the main class of the entire system. It handles the initial user
 * interface, the user interface for the customer and loads & flushes all the
 * data from and to the .txt files.
 * 
 * @author SuyashL
 */

public class UIMain {

	public static ArrayList<User> usersArray = new ArrayList<User>();
	public static ArrayList<Movie> moviesArray = new ArrayList<Movie>();
	public static ArrayList<Session> sessionsArray = new ArrayList<Session>();
	public static ArrayList<Booking> bookingsArray = new ArrayList<Booking>();
	public static ArrayList<Booking> userbookingsArray = new ArrayList<Booking>();
	public static ArrayList<Cinema> cinemasArray = new ArrayList<Cinema>();

	/**
	 * The main method of the system which runs the initial interface.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String args[]) throws IOException, ParseException {
		int choice;
		Scanner sc = new Scanner(System.in);
		loadAll();
		do {
			System.out.println("1. User\n2. Admin\n3. Exit");
			System.out.print("Your choice: ");
			choice=0;
		       do{
		           try{
		               choice=sc.nextInt();
		               break;
		            }
		           catch (InputMismatchException e)
		           {
		                System.out.print("Please Re-enter the Choice: ");
		                choice =1;
		                sc.nextLine();
		            }
		        }while(choice!=3);
			if (choice == 1) {
				UIMain user = new UIMain();
				user.customerUI();
			} else if (choice == 2) {
				UIAdmin a1 = new UIAdmin();
				a1.logon();
			} else if (choice == 3) {
				System.out.println("Exiting...");
				System.out.println("Successfully quit.");
			}
		} while (choice != 1 && choice != 2 && choice != 3);
	}

	/**
	 * Loads all the data from the .txt files into the separate ArrayLists
	 * corresponding to the different entity classes.
	 * 
	 * @throws FileNotFoundException
	 */
	static void loadAll() throws FileNotFoundException {
		Loader_Flusher userLoad = new UserController();
		userLoad.load();
		Loader_Flusher movieLoad = new MovieController();
		movieLoad.load();
		Loader_Flusher sessionLoad = new SessionController();
		sessionLoad.load();
		Loader_Flusher bookingLoad = new BookingController();
		bookingLoad.load();
		Loader_Flusher cinemaLoad = new CinemaController();
		cinemaLoad.load();
	}

	/**
	 * Flushes all the data from the ArrayLists into the .txt files
	 * corresponding to the different entity classes.
	 * 
	 * @throws FileNotFoundException
	 */
	static void flushAll() throws FileNotFoundException {
		Loader_Flusher userFlush = new UserController();
		userFlush.flush();
		Loader_Flusher movieFlush = new MovieController();
		movieFlush.flush();
		Loader_Flusher sessionFlush = new SessionController();
		sessionFlush.flush();
		Loader_Flusher bookingFlush = new BookingController();
		bookingFlush.flush();
		Loader_Flusher cinemaFlush = new CinemaController();
		cinemaFlush.flush();
	}

	/**
	 * This method controls the user interface for the customer.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void customerUI() throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		UserController UC = new UserController();
		MovieController MC = new MovieController();
		SessionController SC = new SessionController();
		BookingController BC = new BookingController();
		int userID = UC.logon();
		BC.loadUser(userID);
		UC.flush();
		int movieID = 0;
		int in;
		int mainOption = 0;
		do {
			System.out.println();
			System.out.println("Please choose an option:\n1. View Old Bookings \n2. Make New Booking\n3. Exit");
			System.out.print("Your choice: ");
			int option = 0;
		       do{
		           try{
		               option=sc.nextInt();
		               break;
		            }
		           catch (InputMismatchException e)
		           {
		                System.out.print("Please Re-enter the Choice: ");
		                option =1;
		                sc.nextLine();
		            }
		        }while(option!=3);
			mainOption = option;
			switch (option) {
			case 1:
				ArrayList<String> userMovies = new ArrayList<String>();
				System.out.println();
				System.out.println("---------- Old Bookings ----------");
				for (int i = 0; i < userbookingsArray.size(); i++) {
					String temp = MC.giveTitle(SC.getMovieID(userbookingsArray.get(i).getSessionID()));
					System.out.println("Transaction ID: " + userbookingsArray.get(i).getTransID());
					System.out.println("Movie: " + temp);
					System.out.println("Number of Seats: " + userbookingsArray.get(i).getNum_seats());
					System.out.println("Total Cost: " + userbookingsArray.get(i).getCost());
					System.out.println();
					if (!userMovies.contains(temp))
						userMovies.add(temp);
				}
				sc.nextLine();
				System.out.print("Enter review for a movie (Y/N)? ");
				char ch = sc.nextLine().charAt(0);
				if (ch == 'Y' || ch=='y') {
					for (int i = 0; i < userMovies.size(); i++)
						System.out.println(i + 1 + ". " + userMovies.get(i));
					System.out.print("Choose movie from the list: ");
					int choice = sc.nextInt();
					int index = MC.getMovieIndex(MC.giveID(userMovies.get(choice - 1)));
					MC.setReview(userbookingsArray.get(0).getUserID(), moviesArray.get(index));
				}
				break;
			case 2:
				do {
					System.out.println();
					System.out.println("Please chose an option from the menu below: ");
					System.out.println("1. List of Movies");
					System.out.println("2. List Top 5 Rated Movies");
					System.out.println("3. List Top 5 Sold Movies");
					System.out.println("4. Search Movie by Name");
					System.out.println("5. View Movies by Cineplex");
					System.out.print("Your choice: ");
					in = 0;
				       do{
				           try{
				               in=sc.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                in =1;
				                sc.nextLine();
				            }
				        }while(true);
					if (in == 1 || in == 2 || in == 3 || in == 4 || in == 5)
						break;
					else {
						System.out.println("Please enter an option within the range shown below");
					}
				} while (true);
				int choice;
				ArrayList<Movie> sorted_movies_rating = new ArrayList<Movie>();
				ArrayList<Movie> sorted_movies_sales = new ArrayList<Movie>();
				switch (in) {
				case 1:
					System.out.println();
					MC.ListMovies();
					System.out.print("Enter Movie No. for more details: ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice < moviesArray.size()) {
						movieID = moviesArray.get(choice - 1).getMovieID();
						MC.movieDetails(moviesArray.get(choice - 1).getTitle());
					} else {
						System.out.println("Error! No such movie ID exists");
					}
					break;
				case 2:
					sorted_movies_rating = MovieController.sortByRating();
					for (int i = 0; i < sorted_movies_rating.size(); i++) {
						System.out.println((i + 1) + ". " + sorted_movies_rating.get(i).getTitle());
					}
					System.out.print("Enter Movie No. for more details: ");
					choice = sc.nextInt();
					sc.nextLine();
					movieID = sorted_movies_rating.get(choice - 1).getMovieID();
					MC.movieDetails(sorted_movies_rating.get(choice - 1).getTitle());
					break;
				case 3:
					sorted_movies_sales = MovieController.sortBySales();
					for (int i = 0; i < sorted_movies_rating.size(); i++) {
						System.out.println((i + 1) + ". " + sorted_movies_sales.get(i).getTitle());
					}
					System.out.print("Enter Movie No. for more details: ");
					choice = sc.nextInt();
					sc.nextLine();
					movieID = sorted_movies_sales.get(choice - 1).getMovieID();
					MC.movieDetails(sorted_movies_sales.get(choice - 1).getTitle());
					break;
				case 4:
					sc.nextLine();
					System.out.print("Enter Movie Name: ");
					String name = sc.nextLine();
					movieID = MC.movieDetails(name);
					break;
				case 5:
					CinemaController cineplexMovie = new CinemaController();
					int CineplexID;
					for (int i = 0; i < cinemasArray.size() / 3; i++) {
						System.out.print(i + 1 + ". ");
						System.out.println("Cineplex " + (i + 1));
					}
					System.out.print("Choose Cineplex: ");
					CineplexID = sc.nextInt();
					ArrayList<String> movieTitles = cineplexMovie.printMoviesCineplex(CineplexID);
					for (int z = 0; z < movieTitles.size(); z++)
						System.out.println((z + 1) + ".  " + movieTitles.get(z));
					System.out.print("Choose movie for showtime details: ");
					choice = sc.nextInt();
					int movieid = MC.giveID(movieTitles.get(choice - 1));
					sc.nextLine();
					do {

						Date date = new Date();
						String str2 = String.format("%tD", date);
						String temp = String.valueOf(Integer.valueOf(str2.substring(3, 5)) - 1) + str2.substring(0, 2)
								+ "20" + str2.substring(6);
						if (temp.length() < 8)
							temp = "0" + temp;
						String startDate = temp;
						int startOrg = Integer.valueOf(MC.startDate(movieid).substring(0, 2));
						String endDate = MC.endDate(movieid);
						int end = Integer.valueOf(startDate.substring(0, 2)) + 8;
						if (startOrg > end) {
							System.out.println("Bookings for this movie not open yet!");
							break;
						} else if (startOrg > (Integer.valueOf(startDate.substring(0, 2))))
							startDate = String.valueOf(startOrg) + startDate.substring(2);
						String endTemp = String.valueOf(end) + startDate.substring(2);
						if (Integer.valueOf(endDate.substring(0, 2)) > end)
							endDate = endTemp;
						System.out.println(startDate + " to " + endDate);
						System.out.print("Enter Date for viewing Movie (in format DDMMYYYY): ");
						String DateID = (sc.nextLine());
						SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy");

						if (sdf.parse(startDate).before(sdf.parse(DateID))
								&& sdf.parse(DateID).before(sdf.parse(endDate))) {
							ArrayList<Integer> sessionId = SC.sessionPrint(movieid, DateID, CineplexID);
							System.out.println();
							System.out.print("Choose Showtime: ");
							int showChoose = sc.nextInt();
							int index = SC.getSessionIndex(sessionId.get(showChoose - 1));
							BC.add(sessionsArray.get(index), usersArray.get(userID - 1));
							break;
						} else {
							System.out.print("Enter a date shown within the range: ");
						}
					} while (true);
					break;
				default:
					break;
				}
				if (in == 1 || in == 2 || in == 3 || in == 4) {
					System.out.print("Get showtimes for this movie (Y/N): ");
					char chooseSession = sc.nextLine().charAt(0);
					if (chooseSession == 'Y' || chooseSession == 'y') {
						do {

							Date date = new Date();
							String str2 = String.format("%tD", date);
							String temp = String.valueOf(Integer.valueOf(str2.substring(3, 5)) - 1)
									+ str2.substring(0, 2) + "20" + str2.substring(6);
							if (temp.length() < 8)
								temp = "0" + temp;
							String startDate = temp;
							int startOrg = Integer.valueOf(MC.startDate(movieID).substring(0, 2));
							String endDate = MC.endDate(movieID);
							int end = Integer.valueOf(startDate.substring(0, 2)) + 8;
							if (startOrg > end) {
								System.out.println("Bookings for this movie not open yet!");
								break;
							} else if (startOrg > (Integer.valueOf(startDate.substring(0, 2))))
								startDate = String.valueOf(startOrg) + startDate.substring(2);
							String endTemp = String.valueOf(end) + startDate.substring(2);
							if (Integer.valueOf(endDate.substring(0, 2)) > end)
								endDate = endTemp;
							System.out.println(startDate + " to " + endDate);
							System.out.print("Enter Date for viewing Movie (in format DDMMYYYY): ");
							String DateID = (sc.nextLine());
							SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy");

							if (sdf.parse(startDate).before(sdf.parse(DateID))
									&& sdf.parse(DateID).before(sdf.parse(endDate))) {
								ArrayList<Integer> sessionId = SC.sessionPrint(movieID, DateID, 0);
								System.out.println();
								System.out.print("Choose Showtime: ");
								int showChoose = sc.nextInt();
								int index = SC.getSessionIndex(sessionId.get(showChoose - 1));
								BC.add(sessionsArray.get(index), usersArray.get(userID - 1));
								break;
							} else {
								System.out.print("Enter a date shown within the range: ");
							}
						} while (true);
					} else {
						System.out.println("Exiting...");
						System.out.println("Successfully quit.");
					}
				}
				break;
			default:
				System.out.println("Please enter an option within the shown range");
			}
		} while (mainOption != 3);

		System.out.println("Exiting...");
		flushAll();
		System.out.println("Successfully quit.");
		sc.close();
	}
}
