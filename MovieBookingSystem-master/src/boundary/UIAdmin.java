package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.MovieController;
import control.SessionController;
import entity.Movie;

/**
 * This class handles the user interface for the admin i.e. the cinema staff. It
 * allows the admin to logon, access & edit movie data, access & edit session
 * data, add holidays & change the admin password.
 * 
 * @author SuyashL
 */

public class UIAdmin {

	/**
	 * This is the main method that handles the user interface from login to
	 * logout.
	 * 
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void logon() throws FileNotFoundException, ParseException {
		Scanner admin = new Scanner(new FileReader("txt/SystemSettings.txt"));
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Password: ");
		String input_password = input.next();
		String real_password = admin.nextLine();
		MovieController MC = new MovieController();

		while (!input_password.equals(real_password)) {
			System.out.print("Password Incorrect, please re-enter: ");
			input_password = input.next();
		}

		String AdultPrices = admin.nextLine();
		String ChildPrices = admin.nextLine();
		String SeniorPrices = admin.nextLine();
		String temp = admin.nextLine();
		String Holidays[] = temp.split(",");
		ArrayList<String> Holiday = new ArrayList<String>(Arrays.asList(Holidays));
		int ch = 1;

		do {
			System.out.println("1. Showtimes \n2. Movies\n3. Edit Prices\n4. Add Holiday\n5. Change Password\n6. Exit");
			ch = 0;
		       do{
		           try{
		               ch=input.nextInt();
		               break;
		            }
		           catch (InputMismatchException e)
		           {
		                System.out.print("Please Re-enter the Choice: ");
		                ch =1;
		                input.nextLine();
		            }
		        }while(ch!=6);
			switch (ch) {
			case 1:
				System.out.println("1. List showtimes\n2. Add new showtimes\n3. Edit showtimes\n4. Delete showtimes");
				System.out.print("Your choice: ");
				int ch_show = 0;
			       do{
			           try{
			               ch_show=input.nextInt();
			               break;
			            }
			           catch (InputMismatchException e)
			           {
			                System.out.print("Please Re-enter the Choice: ");
			                ch_show =1;
			                input.nextLine();
			            }
			        }while(true);
				switch (ch_show) {
				case 1:
					int list_c;
					System.out.println("1.By Movie \n2.By Cinema ID \n3.By Date");
					System.out.print("Your choice: ");
					list_c = 0;
				       do{
				           try{
				               list_c=input.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                list_c =1;
				                input.nextLine();
				            }
				        }while(ch!=6);
					input.nextLine();
					switch (list_c) {
					case 1:
						MC.ListMovies();
						System.out.print("Enter Movie No. for show times: ");
						int choice = input.nextInt();
						int movieID = UIMain.moviesArray.get(choice - 1).getMovieID();
						int valid_movie = displayShowtimeByMovieID(movieID);
						if (valid_movie == 0) {
							System.out.println("There are noe showtimes for this movie");
						}
						break;
					case 2:
						System.out.println(
								"Enter a Cinema ID from the following: \n" + "11, 12, 13, 21, 22, 23, 31, 32, 33");
						System.out.print("Your choice: ");
						int cinemaIDD = input.nextInt();
						input.nextLine();
						int valid_cinema = displayShowtimeByCinemaID(cinemaIDD, MC);
						if (valid_cinema == 0) {
							System.out.println("There are no showtimes for this cinema");
						}
						break;
					case 3:
						System.out.print("Enter Date (DDMMYYYY): ");
						String dateIDD = input.nextLine();
						int valid_date = displayShowtimeByDate(dateIDD, MC);
						if (valid_date == 0) {
							System.out.println("There are no showtimes on this date");
						}
						break;
					}

					break;
				case 2:
					System.out.println("Would you like to add:");
					System.out.println("1. Add a Single Showtime");
					System.out.println("2. Add a Showtime for a Week");
					System.out.print("Your choice: ");
					ch_show = 0;
				       do{
				           try{
				               ch_show=input.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                ch_show =1;
				                input.nextLine();
				            }
				        }while(true);
					SessionController sc3 = new SessionController();
					MC.ListMovies();
					if (ch_show == 1) {
						sc3.add();
					} else {
						sc3.addWeek();
					}
					System.out.println("Showtime added successfully");
					break;
				case 3:
					System.out.println("Edit Showtimes:");
					System.out.println("1. By Movie \n2. By Cinema ID \n3. By Date");
					SessionController SC = new SessionController();
					int edit = 0;
				       do{
				           try{
				               edit=input.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                edit =1;
				                input.nextLine();
				            }
				        }while(true);
					switch (edit) {
					case 1:
						System.out.print("Enter Movie Title: ");
						input.nextLine();
						String mtitle = input.nextLine();
						int movieIDD = MC.giveID(mtitle);
						int valid_movie = displayShowtimeByMovieID(movieIDD);
						if (valid_movie == 1) {
							System.out.print("Input Session ID to edit: ");
							int sessionIDD = input.nextInt();
							SC.edit(sessionIDD);
						} else {
							System.out.println("There are no sessions for this movie");
						}
						break;
					case 2:
						System.out.println(
								"Enter a Cinema ID from the following: \n" + "11, 12, 13, 21, 22, 23, 31, 32, 33");
						System.out.print("Your choice: ");
						int cinemaIDD = input.nextInt();
						int valid_cinema = displayShowtimeByCinemaID(cinemaIDD, MC);
						if (valid_cinema == 1) {
							System.out.print("Input Session ID to edit: ");
							int sessionIDD = input.nextInt();
							SC.edit(sessionIDD);
						} else {
							System.out.println("There are no sessions for this cinemaID");
						}
						break;
					case 3:
						System.out.print("Enter Date (DDMMYYYY): ");
						input.nextLine();
						String dateIDD = input.nextLine();
						int valid_date = displayShowtimeByDate(dateIDD, MC);
						if (valid_date == 1) {
							System.out.print("Input Session ID to edit: ");
							int sessionIDD = input.nextInt();
							input.nextLine();
							SC.edit(sessionIDD);
						} else {
							System.out.println("There are no sessions on this date");
						}
						break;
					}
					break;
				case 4:
					System.out.println("Delete Showtimes:");
					System.out.println("1. By Movie \n2. By Cinema \n3. By Date");
					SessionController SC2 = new SessionController();
					int edit1 = 0;
				       do{
				           try{
				               edit1=input.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                edit1 =1;
				                input.nextLine();
				            }
				        }while(true);
					switch (edit1) {
					case 1:
						System.out.print("Enter Movie Title: ");
						input.nextLine();
						String mtitle = input.nextLine();
						int movieIDD = MC.giveID(mtitle);
						int valid_movie = displayShowtimeByMovieID(movieIDD);
						if (valid_movie == 1) {
							System.out.print("Input Session ID to delete: ");
							int sessionIDD = input.nextInt();
							SC2.delete(sessionIDD);
						} else {
							System.out.println("There are no sessions for this movie");
						}
						break;
					case 2:
						System.out.print(
								"Enter a Cinema ID from the following: \n" + "11, 12, 13, 21, 22, 23, 31, 32, 33");
						int cinemaIDD = input.nextInt();
						int valid_cinema = displayShowtimeByCinemaID(cinemaIDD, MC);
						if (valid_cinema == 1) {
							System.out.print("Input Session ID to delete: ");
							int sessionIDD = input.nextInt();
							SC2.delete(sessionIDD);
						} else {
							System.out.println("There are no sessions for this cinema");
						}
						break;
					case 3:
						System.out.print("Enter Date (DDMMYYYY): ");
						String dateIDD = input.nextLine();
						int valid_date = displayShowtimeByDate(dateIDD, MC);
						if (valid_date == 1) {
							System.out.print("Input Session ID to delete: ");
							int sessionIDD = input.nextInt();
							SC2.delete(sessionIDD);
							break;
						} else {
							System.out.println("There are no sessions on this date");
						}
					}
					break;
				default:
					System.out.println("Exiting Showtimes Mode...");
					break;
				}
				break;

			case 2:
				ArrayList<Movie> sorted_movies_rating = new ArrayList<Movie>();
				ArrayList<Movie> sorted_movies_sales = new ArrayList<Movie>();

				System.out.println("1. List Movies\n2. Add New Movie\n3. Edit Movie\n4. Delete Movie");
				System.out.print("Your choice: ");
				int ch_movie = 0;
			       do{
			           try{
			               ch_movie=input.nextInt();
			               break;
			            }
			           catch (InputMismatchException e)
			           {
			                System.out.print("Please Re-enter the Choice: ");
			                ch_movie =1;
			                input.nextLine();
			            }
			        }while(true);
				switch (ch_movie) {
				case 1:
					System.out.println("1. List All Movies\n2. List Top 5 Rated Movies\n3. List Top 5 Sold Movies");
					System.out.print("Your choice: ");
					int ch_list_movie = 0;
				       do{
				           try{
				               ch_list_movie=input.nextInt();
				               break;
				            }
				           catch (InputMismatchException e)
				           {
				                System.out.print("Please Re-enter the Choice: ");
				                ch_list_movie =1;
				                input.nextLine();
				            }
				        }while(true);
					switch (ch_list_movie) {
					case 1:
						MC.AdminListMovies();
						break;
					case 2:
						sorted_movies_rating = MovieController.sortByRating();
						for (int i = 0; i < sorted_movies_rating.size(); i++) {
							System.out.println("Name: " + sorted_movies_rating.get(i).getTitle() + ",  Overall Rating: "
									+ sorted_movies_rating.get(i).getAvg_rating());
						}
						break;
					case 3:
						sorted_movies_sales = MovieController.sortBySales();
						for (int i = 0; i < sorted_movies_sales.size(); i++) {
							System.out.println("Name: " + sorted_movies_sales.get(i).getTitle() + ",  Total Sales: "
									+ sorted_movies_sales.get(i).getSales());
						}
						break;
					}
					break;
				case 2:
					MC.addMovie();
					break;
				case 3:
					MC.editMovie();
					break;
				case 4:
					MC.deleteMovie();
					break;
				default:
					System.out.println("Exiting Movies Mode...");
				}
				break;
			case 3:
				char choice = '1';
				while (choice != '4') {
					input.nextLine();
					System.out.println("Edit Prices for 1. Adult\n2. Child\n3. Senior Citizen\n4. Exit");
					choice = input.nextLine().charAt(0);
					switch (choice) {
					case '1':
						AdultPrices = "";
						System.out.println("Enter Prices for 3D Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						AdultPrices = input.next() + ",";
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						AdultPrices += (input.next() + ",");
						System.out.println("Enter Prices for Blockbuster Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						AdultPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						AdultPrices += (input.next() + ",");
						System.out.println("Enter Prices for Normal Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						AdultPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						AdultPrices += input.next();
						break;
					case '2':
						ChildPrices = "";
						System.out.println("Enter Prices for 3D Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						ChildPrices = input.next() + ",";
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						ChildPrices += (input.next() + ",");
						System.out.println("Enter Prices for Blockbuster Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						ChildPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						ChildPrices += (input.next() + ",");
						System.out.println("Enter Prices for Normal Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						ChildPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						ChildPrices += input.next();
						break;
					case '3':
						SeniorPrices = "";
						System.out.println("Enter Prices for 3D Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						SeniorPrices = input.next() + ",";
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						SeniorPrices += (input.next() + ",");
						System.out.println("Enter Prices for Blockbuster Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						SeniorPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						SeniorPrices += (input.next() + ",");
						System.out.println("Enter Prices for Normal Movie:");
						System.out.print("For Weekday (Normal,Platinum): ");
						SeniorPrices += (input.next() + ",");
						System.out.print("For Weekend/Holidays (Normal,Platinum): ");
						SeniorPrices += input.next();
						break;
					default:
						System.out.println("Exiting Price Mode...");
						break;
					}
				}
				break;
			case 4:
				while (true) {
					input.nextLine();
					System.out.print("Add a Holiday (Y/N)? ");
					choice = input.nextLine().charAt(0);
					if (choice == 'Y') {
						System.out.print("Enter Date (DDMMYYYY): ");
						Holiday.add(input.next());
						System.out.println("");
					} else {
						System.out.println("Exiting Holiday Selection Mode...");
						break;
					}
				}
				break;
			case 5:
				input.nextLine();
				System.out.print("Enter New Password: ");
				String passNew = input.next();
				System.out.print("Re-Enter Password: ");
				if (passNew.equals(input.next())) {
					System.out.println("Password Changed!");
					real_password = passNew;
				} else {
					System.out.println("Passwords don't match!");
				}
				break;
			case 6:
				System.out.println("Exiting...");
				UIMain.flushAll();
				System.out.println("You have successfully quit the system.");
				break;
			default:
				System.out.println("Invalid Input!");
				break;
			}
		} while (ch != 6);

		PrintWriter writer = new PrintWriter("txt/SystemSettings.txt");
		writer.println(real_password);
		writer.println(AdultPrices);
		writer.println(ChildPrices);
		writer.println(SeniorPrices);
		for (int i = 0; i < Holiday.size(); i++) {
			writer.print(Holiday.get(i) + ",");
		}
		writer.println();
		writer.close();
		admin.close();
		input.close();
	}

	/**
	 * The following method searches through all the sessions and gets the
	 * sessions for a particular movie.
	 * 
	 * @param movieID	Movie ID to be searched for.
	 * @return ret - Flag whether any sessions were found.
	 */
	public int displayShowtimeByMovieID(int movieID) {
		int ret = 0;
		for (int x = 0; x < UIMain.sessionsArray.size(); x++) {
			if (UIMain.sessionsArray.get(x).getMovieID() == movieID) {
				System.out.print("Session ID: ");
				System.out.println(UIMain.sessionsArray.get(x).getSessionID());
				System.out.print("Cinema ID: ");
				System.out.println(UIMain.sessionsArray.get(x).getCinemaID());
				System.out.print("Date: ");
				System.out.println(UIMain.sessionsArray.get(x).getDate());
				System.out.print("Show Timings: ");
				System.out.print(UIMain.sessionsArray.get(x).getStartTime());
				System.out.print(" to ");
				System.out.println(UIMain.sessionsArray.get(x).getEndTime());
				System.out.print("No. of seats booked: ");
				System.out.println(UIMain.sessionsArray.get(x).seatsBooked());
				ret = 1;
			}
		}
		return ret;
	}

	/**
	 * The following method searches through all the sessions and gets the
	 * sessions for a particular cinema.
	 * 
	 * @param cinemaID	Cinema ID to be searched for.
	 * @param MC		MovieController object to use in search.
	 * @return ret - Flag whether any sessions were found.
	 */
	public int displayShowtimeByCinemaID(int cinemaID, MovieController MC) {
		int ret = 0;
		for (int x = 0; x < UIMain.sessionsArray.size(); x++) {
			if (UIMain.sessionsArray.get(x).getCinemaID() == cinemaID) {
				System.out.print("Session ID: ");
				System.out.println(UIMain.sessionsArray.get(x).getSessionID());
				System.out.print("Movie title: ");
				System.out.println(MC.giveTitle(UIMain.sessionsArray.get(x).getMovieID()));
				System.out.print("Movie ID: ");
				System.out.println(UIMain.sessionsArray.get(x).getMovieID());
				System.out.print("Date: ");
				System.out.println(UIMain.sessionsArray.get(x).getDate());
				System.out.print("Show Timings: ");
				System.out.print(UIMain.sessionsArray.get(x).getStartTime());
				System.out.print(" to ");
				System.out.println(UIMain.sessionsArray.get(x).getEndTime());
				System.out.print("No. of seats booked: ");
				System.out.println(UIMain.sessionsArray.get(x).seatsBooked());
				ret = 1;
			}
		}
		return ret;
	}

	/**
	 * The following method searches through all the sessions and gets the
	 * sessions on a particular date.
	 * 
	 * @param date	Date to be searched for.
	 * @param MC	MovieController object to use in search.
	 * @return ret - Flag whether any sessions were found.
	 */
	public int displayShowtimeByDate(String date, MovieController MC) {
		int ret = 0;
		for (int x = 0; x < UIMain.sessionsArray.size(); x++) {
			if (UIMain.sessionsArray.get(x).getDate().equals(date)) {
				System.out.print("Session ID : ");
				System.out.println(UIMain.sessionsArray.get(x).getSessionID());
				System.out.print("Movie title: ");
				System.out.println(MC.giveTitle(UIMain.sessionsArray.get(x).getMovieID()));
				System.out.print("Movie ID : ");
				System.out.println(UIMain.sessionsArray.get(x).getMovieID());
				System.out.print("Cinema ID: ");
				System.out.println(UIMain.sessionsArray.get(x).getCinemaID());
				System.out.print("Show Timings : ");
				System.out.print(UIMain.sessionsArray.get(x).getStartTime());
				System.out.print(" to ");
				System.out.println(UIMain.sessionsArray.get(x).getEndTime());
				System.out.print("No. of seats booked : ");
				System.out.println(UIMain.sessionsArray.get(x).seatsBooked());
				ret = 1;
			}
		}
		return ret;
	}
}
