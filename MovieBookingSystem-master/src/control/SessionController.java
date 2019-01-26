package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import boundary.UIMain;
import entity.Session;

/**
 * Control class for session data.
 * 
 * @author SuyashL
 */

public class SessionController implements Loader_Flusher {

	/**
	 * Loads session data from Sessions.txt inside the txt/ directory into the
	 * public ArrayList<User>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		Scanner session = new Scanner(new File("txt/Sessions.txt"));
		String record;
		String fields[];
		char seatPlan_char[];
		int k;

		while (session.hasNextLine()) {
			boolean seatPlan[][] = new boolean[10][20];
			k = 0;
			record = session.nextLine();
			fields = record.split(";");

			seatPlan_char = fields[6].toCharArray();

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 20; j++) {
					if (seatPlan_char[k] == '1') {
						seatPlan[i][j] = true;
					} else {
						seatPlan[i][j] = false;
					}
					k++;
				}
			}

			Session s = new Session(Integer.valueOf(fields[0]), Integer.valueOf(fields[1]), Integer.valueOf(fields[2]),
					fields[3], fields[4], fields[5], seatPlan);
			s.setSeatPlan(seatPlan);
			UIMain.sessionsArray.add(s);
		}
		session.close();
	}

	/**
	 * Prints the movies playing in a particular cineplex for a particular date
	 * as specified by the user.
	 * 
	 * @param movieID
	 * @param sessionDate
	 * @param cineplexID
	 * @return sessionIDArray An array of the sessionID values corresponding to
	 *         the user's query.
	 * @throws FileNotFoundException
	 */
	public ArrayList<Integer> sessionPrint(int movieID, String sessionDate, int cineplexID)
			throws FileNotFoundException {
		ArrayList<Integer> sessionIDArray = new ArrayList<Integer>();
		int k = 0;
		if (cineplexID == 0) {
			for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
				if (UIMain.sessionsArray.get(i).getDate().equals(sessionDate)
						&& UIMain.sessionsArray.get(i).getMovieID() == movieID) {
					System.out.print(k + 1 + ". ");
					System.out.println(
							"\t" + CinemaController.printCinemaName(UIMain.sessionsArray.get(i).getCinemaID()));
					if ((UIMain.sessionsArray.get(i).getCinemaID()) % 10 == 3)
						System.out.println("\tType: Platinum");
					else
						System.out.println("\tType: Normal");
					System.out.print("\tStart Time: " + UIMain.sessionsArray.get(i).getStartTime());
					System.out.println(" End Time: " + UIMain.sessionsArray.get(i).getEndTime());
					k++;
					sessionIDArray.add(UIMain.sessionsArray.get(i).getSessionID());
				}
			}
		} else {
			for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
				for (int j = 0; j < UIMain.cinemasArray.size(); j++) {
					if (UIMain.cinemasArray.get(j).getCinemaID() == UIMain.sessionsArray.get(i).getCinemaID()) {
						if (UIMain.cinemasArray.get(j).getCineplexID() == cineplexID) {
							if (UIMain.sessionsArray.get(i).getDate().equals(sessionDate)
									&& UIMain.sessionsArray.get(i).getMovieID() == movieID) {
								System.out.print(k + 1 + ". ");
								System.out.println("\t"
										+ CinemaController.printCinemaName(UIMain.sessionsArray.get(i).getCinemaID()));
								if ((UIMain.sessionsArray.get(i).getCinemaID()) % 10 == 3)
									System.out.println("\tType: Platinum");
								else
									System.out.println("\tType: Normal");
								System.out.print("\tStart Time: " + UIMain.sessionsArray.get(i).getStartTime());
								System.out.println(" End Time: " + UIMain.sessionsArray.get(i).getEndTime());
								k++;
								sessionIDArray.add(UIMain.sessionsArray.get(i).getSessionID());
							}
						}
					}
				}

			}
		}
		return sessionIDArray;
	}

	/**
	 * @param sessionID
	 * @return movieID of the movie playing during the particular session.
	 * @throws FileNotFoundException
	 */
	public int getMovieID(int sessionID) throws FileNotFoundException {
		for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
			if (UIMain.sessionsArray.get(i).getSessionID() == sessionID)
				return UIMain.sessionsArray.get(i).getMovieID();
		}
		return 0;
	}

	/**
	 * Used by the admin to add a single session to the system.
	 */
	public void add() {
		Scanner sc = new Scanner(System.in);

		MovieController mc = new MovieController();
		int hours;
		int sessionID;
		int movieID;
		int cinemaID;
		String date;
		String start_time;
		String end_time;
		boolean seatPlan[][] = new boolean[10][20];

		sessionID = UIMain.sessionsArray.get((UIMain.sessionsArray.size()) - 1).getSessionID() + 1;
		System.out.print("Movie ID: ");
		movieID = sc.nextInt();
		System.out.print("Enter a Cinema ID from the following: \n" + "11, 12, 13, 21, 22, 23, 31, 32, 33");
		cinemaID = sc.nextInt();
		System.out.print("Date (DDMMYYYY): ");
		sc.nextLine();
		date = sc.nextLine();
		System.out.print("Start Time (HH:MM): ");
		start_time = sc.nextLine();
		System.out.print("End Time (HH:MM): ");
		end_time = sc.nextLine();

		hours = mc.giveHours(movieID);

		int start_hour = (Integer.valueOf(start_time.substring(0, 2))) % 24;
		end_time = Integer.toString(start_hour + hours) + start_time.substring(2);

		UIMain.sessionsArray.add(new Session(sessionID, movieID, cinemaID, date, start_time, end_time, seatPlan));

		sc.close();
	}

	/**
	 * Used by the admin to add a daily repeated session for a week.
	 * 
	 * @throws ParseException
	 */
	public void addWeek() throws ParseException {
		Scanner sc = new Scanner(System.in);

		MovieController mc = new MovieController();
		int hours;
		int sessionID;
		int movieID;
		int cinemaID;
		String date;
		String start_time;
		String end_time;
		boolean seatPlan[][] = new boolean[10][20];

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date_d = new Date();
		Calendar c = Calendar.getInstance();

		if (UIMain.sessionsArray.size() == 0) {
			sessionID = 0;
		} else {
			sessionID = UIMain.sessionsArray.get((UIMain.sessionsArray.size()) - 1).getSessionID() + 1;
		}
		System.out.print("Movie ID: ");
		movieID = sc.nextInt();
		System.out.print("Enter a Cinema ID from the following: \n" + "11, 12, 13, 21, 22, 23, 31, 32, 33");
		cinemaID = sc.nextInt();
		sc.nextLine();
		System.out.print("Starting Date (DDMMYYY): ");
		date = sc.nextLine();
		date_d = dateFormat.parse(date);
		c.setTime(date_d);
		System.out.print("Time (HH:MM): ");
		start_time = sc.nextLine();

		hours = mc.giveHours(movieID);

		int start_hour = (Integer.valueOf(start_time.substring(0, 2))) % 24;
		end_time = Integer.toString(start_hour + hours) + start_time.substring(2);

		UIMain.sessionsArray.add(new Session(sessionID, movieID, cinemaID, date, start_time, end_time, seatPlan));

		for (int i = 0; i < 7; i++) {
			c.add(Calendar.DATE, 1);
			UIMain.sessionsArray.add(new Session(++sessionID, movieID, cinemaID, dateFormat.format(c.getTime()),
					start_time, end_time, seatPlan));
		}

		sc.close();
	}

	/**
	 * Allows the admin to edit a particular session looked up using the
	 * sessionID.
	 * 
	 * @param sessionID
	 */
	public void edit(int sessionID) {
		int hours;
		String start_time, end_time;
		MovieController mc = new MovieController();
		Scanner sc = new Scanner(System.in);

		System.out.println("Edit Session ID: " + sessionID);
		for (int k = 0; k < UIMain.sessionsArray.size(); k++) {
			if (UIMain.sessionsArray.get(k).getSessionID() == sessionID) {
				System.out.print("Date (DDMMYYYY): ");
				UIMain.sessionsArray.get(k).setDate(sc.nextLine());
				System.out.print("Start Time (HH:MM): ");
				start_time = sc.next();
				UIMain.sessionsArray.get(k).setStartTime(start_time);

				hours = mc.giveHours(UIMain.sessionsArray.get(k).getMovieID());

				int start_hour = (Integer.valueOf(start_time.substring(0, 2))) % 24;
				end_time = Integer.toString(start_hour + hours) + start_time.substring(2);

				UIMain.sessionsArray.get(k).setEndTime(end_time);
				System.out.println("Record updated.");
				break;
			}
		}

		sc.close();
	}

	/**
	 * Allows the admin to delete a particular session looked up using the
	 * sessionID.
	 * 
	 * @param sessionID
	 */
	public void delete(int sessionID) {
		for (int k = 0; k < UIMain.sessionsArray.size(); k++) {
			if (UIMain.sessionsArray.get(k).getSessionID() == sessionID) {
				UIMain.sessionsArray.remove(k);
				System.out.println("Record deleted.");
			}
		}
	}

	/**
	 * Gets the prices for a particular session according to whether it's a
	 * weekday/holiday/weekend and whether the movie is normal/blockbuster/3D.
	 * 
	 * @param session
	 * @return prices[] An array of prices for the different age groups for a
	 *         particular session.
	 * @throws FileNotFoundException
	 */
	public int[] getPrices(Session session) throws FileNotFoundException {
		Scanner admin = new Scanner(new FileReader("txt/SystemSettings.txt"));
		MovieController movie = new MovieController();
		admin.nextLine();
		String AdultPrices[] = admin.nextLine().split(",");
		String ChildPrices[] = admin.nextLine().split(",");
		String SeniorPrices[] = admin.nextLine().split(",");
		String Holidays[] = admin.nextLine().split(",");
		String type = movie.giveType(session.getMovieID());
		Date date = new Date();
		int dayNum = 0;
		String str = String.format("%tA", date);
		String str2 = String.format("%tD", date);
		String temp = str2.substring(3, 5) + str2.substring(0, 2) + "20" + str2.substring(6);
		if (str.equals("Monday"))
			dayNum = 1;
		else if (str.equals("Tuesday"))
			dayNum = 2;
		else if (str.equals("Wednesday"))
			dayNum = 3;
		else if (str.equals("Thursday"))
			dayNum = 4;
		else if (str.equals("Friday"))
			dayNum = 5;
		else if (str.equals("Saturday"))
			dayNum = 6;
		else
			dayNum = 7;
		String bookDate = session.getDate();
		dayNum = ((Integer.valueOf(bookDate.substring(0, 2)) - Integer.valueOf(temp.substring(0, 2))) + dayNum) % 7;
		for (int i = 0; i < Holidays.length; i++)
			if (Holidays[i].equals(bookDate)) {
				dayNum = 7;
				break;
			}
		if (dayNum < 5)
			str2 = "Weekday " + str2;
		else
			str2 = "Weekend " + str2;
		int index = 0;
		if (type.equals("3D")) {
			if ((str2.substring(0, str2.indexOf(' '))).equals("Weekday"))
				if ((session.getCinemaID() % 10) == 3)
					index = 1;
				else
					index = 0;
			else if ((session.getCinemaID() % 10) == 3)
				index = 3;
			else
				index = 2;
		} else if (type.equals("BlockBuster")) {
			if ((str2.substring(0, str2.indexOf(' '))).equals("Weekday"))
				if ((session.getCinemaID() % 10) == 3)
					index = 5;
				else
					index = 4;
			else if ((session.getCinemaID() % 10) == 3)
				index = 7;
			else
				index = 6;
		} else {
			if ((str2.substring(0, str2.indexOf(' '))).equals("Weekday"))
				if ((session.getCinemaID() % 10) == 3)
					index = 9;
				else
					index = 8;
			else if ((session.getCinemaID() % 10) == 3)
				index = 11;
			else
				index = 10;
		}

		admin.close();

		int prices[] = { Integer.valueOf(AdultPrices[index]), Integer.valueOf(ChildPrices[index]),
				Integer.valueOf(SeniorPrices[index]) };
		return prices;

		/*
		 * NOTE: Prices stored in text file in this order:
		 * ADU(3D(WD(N,P),WE(N,P)),BB(WD(N,P),WE(N,P)),NOR(WD(N,P),WE(N,P)))
		 * CHI(3D(WD(N,P),WE(N,P)),BB(WD(N,P),WE(N,P)),NOR(WD(N,P),WE(N,P)))
		 */
	}

	/**
	 * Used to book seats for a particular session and update the seatPlan for
	 * that session.
	 * 
	 * @param session
	 * @param booked_seats
	 * @return whether the booking was successful or not.
	 */
	public boolean bookSeats(Session session, String booked_seats) {
		boolean[][] seatPlan = session.getSeatPlan();

		int cinemaId = session.getCinemaID() % 10;
		String s_array[] = booked_seats.split(",");
		int r = -1;
		int c[] = new int[2];
		if (cinemaId == 3) {
			for (int i = 0; i < 2; i++) {
				char column = (s_array[i]).charAt(1);
				char row = (s_array[i]).charAt(0);
				if (column == 'A' || column == 'B') {
					c[i] = (column - 62);
				} else if (column == 'C' || column == 'D') {
					c[i] = (column - 60);
				} else if (column == 'E' || column == 'F') {
					c[i] = (column - 58);
				} else if (column == 'G' || column == 'H') {
					c[i] = (column - 56);
				} else
					c[i] = (column - 54);
				r = row - 48;

			}
		} else {
			for (int i = 0; i < 2; i++) {
				char column = (s_array[i]).charAt(1);
				char row = (s_array[i]).charAt(0);
				if (column - 64 < 5) {
					c[i] = (column - 64);
				} else if ((column - 64) >= 5 && (column - 64) < 16) {
					c[i] = (column - 64) + 1;
				} else if ((column - 64) >= 16 && (column - 64) <= 18) {
					c[i] = (column - 64) + 2;
				}
				r = row - 48;
			}
		}
		for (int k = c[0] - 1; k <= (c[1] - 1); k++) {
			if (!seatPlan[r - 1][k]) {
				seatPlan[r - 1][k] = true;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the index of a particular session in the ArrayList<Session>.
	 * 
	 * @param sessionID
	 * @return
	 */
	public int getSessionIndex(int sessionID) {
		for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
			if (UIMain.sessionsArray.get(i).getSessionID() == sessionID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Loads updated session data back into Sessions.txt inside the txt/
	 * directory from the public ArrayList<Session>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void flush() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("txt/Sessions.txt");
		boolean seatPlan[][] = new boolean[10][20];
		String s = "";

		for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
			s = "";
			seatPlan = UIMain.sessionsArray.get(i).getSeatPlan();

			for (int k = 0; k < 10; k++) {
				for (int l = 0; l < 20; l++) {
					if (seatPlan[k][l] == false) {
						s += "0";
					} else {
						s += "1";
					}
				}
			}

			writer.print(Integer.toString(UIMain.sessionsArray.get(i).getSessionID()) + ";"
					+ Integer.toString(UIMain.sessionsArray.get(i).getMovieID()) + ";"
					+ Integer.toString(UIMain.sessionsArray.get(i).getCinemaID()) + ";"
					+ UIMain.sessionsArray.get(i).getDate() + ";" + UIMain.sessionsArray.get(i).getStartTime() + ";"
					+ UIMain.sessionsArray.get(i).getEndTime() + ";" + s);

			if (i != UIMain.sessionsArray.size() - 1)
				writer.println();
		}
		writer.close();
	}
}