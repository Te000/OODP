package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import boundary.UIMain;
import entity.Booking;
import entity.Session;
import control.SeatPlanPopulate;
import entity.User;

/**
 * Control class for booking data.
 * 
 * @author SuyashL
 */

public class BookingController implements Loader_Flusher {

	/**
	 * Loads all booking data from Bookings.txt inside the txt/ directory into
	 * the public ArrayList<Booking>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		Scanner booking = new Scanner(new File("txt/Bookings.txt"));
		String temp = "";

		while (booking.hasNextLine()) {
			temp = booking.nextLine();
			String[] s = temp.split(";");
			String[] booked_seats = new String[Integer.valueOf(s[3])];
			booked_seats = s[4].split(",");
			Booking b = new Booking(s[0], Integer.valueOf(s[1]), Integer.valueOf(s[2]), Integer.valueOf(s[3]),
					booked_seats, Integer.valueOf(s[5]));
			UIMain.bookingsArray.add(b);
		}
		booking.close();
	}

	/**
	 * Loads the booking data for a particular user according to the current
	 * logged in user.
	 * 
	 * @param userID
	 * @throws FileNotFoundException
	 */
	public void loadUser(int userID) throws FileNotFoundException {
		for (int i = 0; i < UIMain.bookingsArray.size(); i++) {
			if (UIMain.bookingsArray.get(i).getUserID() == userID)
				UIMain.userbookingsArray.add(UIMain.bookingsArray.get(i));
		}
	}

	/**
	 * Adds a new booking for a session by a particular user.
	 * 
	 * @param thisSession
	 * @param user_this
	 * @throws FileNotFoundException
	 */
	public void add(Session thisSession, User user_this) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		SeatPlanPopulate SPP = new SeatPlanPopulate();
		SessionController SC = new SessionController();
		MovieController movie = new MovieController();
		int priceArr[] = SC.getPrices(thisSession);
		int price, numSeats, counter = 0;
		boolean flag = true;
		int seats = 0;
		SPP.seatPopulate(thisSession);

		System.out.print("How many seats would you like to book (max 8)? ");
		numSeats = input.nextInt();
		seats = numSeats;
		String selection = "";
		input.nextLine();
		counter = 0;

		while (seats != 0) {
			if (seats == 1) {
				System.out.print("Enter a valid seat number (eg. 1B): ");
				String temp4 = input.nextLine();
				flag = SC.bookSeats(thisSession, (temp4 + "," + temp4));
				if (flag == true) {
					selection += (temp4 + ",");
					seats--;
				}
			} else {
				if (counter == 0) {
					System.out.print("Enter a valid seat range (eg. 1B,1C): ");
					counter++;
				} else
					System.out.print("Enter a valid seat range (eg. 1B,1C) for remaining seats: ");
				String temp1 = input.nextLine();
				String temp[] = temp1.split(",");
				flag = SC.bookSeats(thisSession, temp1);
				if (flag == true) {
					seats -= (temp[1].charAt(1) - temp[0].charAt(1) + 1);
					for (int i = 0; i < (temp[1].charAt(1) - temp[0].charAt(1) + 1); i++)
						selection += ((String
								.valueOf((char) (temp[0].charAt(0)) + String.valueOf((char) (temp[0].charAt(1) + i))))
								+ ",");
				}
			}
			if (seats == 0) {
				System.out.println(
						"Are these the seats you want (Y/N)?\n" + selection.substring(0, selection.length() - 1));
				char choice = input.nextLine().charAt(0);
				if (choice == 'N') {
					seats = numSeats;
					counter = 0;
					selection = "";
				}
			}
		}
		System.out.println("Adult: S$" + priceArr[0] + ", Child: S$" + priceArr[1] + ", Senior: S$" + priceArr[2] + "\n"
				+ "1. Book everyone as adults" + "\n2. Enter ages for all seats");
		int choice = 0;
	       do{
	           try{
	               choice=input.nextInt();
	               break;
	            }
	           catch (InputMismatchException e)
	           {
	                System.out.print("Please Re-enter the Choice: ");
	                choice =1;
	                input.nextLine();
	            }
	        }while(true);
		selection = selection.substring(0, selection.length() - 1);
		if (choice == 2) {
			System.out.print("Enter no. of Adults: ");
			price = priceArr[0] * (input.nextInt());
			System.out.print("Enter no. of Children: ");
			price += priceArr[1] * (input.nextInt());
			System.out.print("Enter no. of Senior Citizens: ");
			price += priceArr[2] * (input.nextInt());
		} else
			price = priceArr[0] * numSeats;
		System.out.println("\nBooking Summary:\n--------------------------------");
		System.out.println("UserID: " + user_this.getUserID());
		System.out.println("Name: " + user_this.getName());
		System.out.println("Movie Title: " + movie.giveTitle(thisSession.getMovieID()));
		System.out.println("Date: " + thisSession.getDate());
		System.out.println("Cinema: " + thisSession.getCinemaID() % 10);
		if ((thisSession.getCinemaID()) % 10 == 3)
			System.out.println("Type: Platinum");
		else
			System.out.println("Type: Normal");
		System.out.println("No. of Tickets: " + numSeats);
		System.out.println("Seats: " + selection);
		System.out.println("Total Cost: $" + price);
		UIMain.userbookingsArray.add(new Booking(
				Integer.toString(thisSession.getCinemaID()) + "X" + thisSession.getDate()
						+ thisSession.getStartTime().substring(0, 2) + thisSession.getStartTime().substring(3),
				user_this.getUserID(), thisSession.getSessionID(), numSeats, selection.split(","), price));
		UIMain.bookingsArray.add(new Booking(
				Integer.toString(thisSession.getCinemaID()) + "X" + thisSession.getDate()
						+ thisSession.getStartTime().substring(0, 2) + thisSession.getStartTime().substring(3),
				user_this.getUserID(), thisSession.getSessionID(), numSeats, selection.split(","), price));
		
		input.close();
	}

	/**
	 * Loads updated booking data back into Bookings.txt inside the txt/
	 * directory from the public ArrayList<Booking>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void flush() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("txt/Bookings.txt");
		for (int i = 0; i < UIMain.bookingsArray.size(); i++) {
			String transID = UIMain.bookingsArray.get(i).getTransID();
			int userID = UIMain.bookingsArray.get(i).getUserID();
			int sessionID = UIMain.bookingsArray.get(i).getSessionID();
			int num_seats = UIMain.bookingsArray.get(i).getNum_seats();
			String booked_seats[] = UIMain.bookingsArray.get(i).getBooked_seats();
			int cost = UIMain.bookingsArray.get(i).getCost();

			writer.print(transID + ";");
			writer.print(String.valueOf(userID) + ";");
			writer.print(sessionID + ";");
			writer.print(String.valueOf(num_seats) + ";");
			for (int j = 0; j < booked_seats.length; j++) {
				writer.print(booked_seats[j] + ",");
			}
			writer.print(";");
			writer.print(String.valueOf(cost));

			if (i != UIMain.bookingsArray.size() - 1)
				writer.println();
		}
		writer.close();
	}
}
