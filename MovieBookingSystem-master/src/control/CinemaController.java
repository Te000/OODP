package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.UIMain;
import entity.Cinema;

/**
 * Control class for cinema data.
 * 
 * @author SuyashL
 */

public class CinemaController implements Loader_Flusher {

	/**
	 * Loads cinema data from Cinemas.txt inside the txt/ directory into the
	 * public ArrayList<Cinema>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		Scanner cinemas = new Scanner(new File("txt/Cinemas.txt"));
		String temp = "";

		while (cinemas.hasNextLine()) {
			temp = cinemas.nextLine();
			String[] s = temp.split(";");
			Cinema c = new Cinema(Integer.valueOf(s[0]), Integer.valueOf(s[1]), s[2].charAt(0));
			UIMain.cinemasArray.add(c);
		}
		cinemas.close();
	}

	/**
	 * @param cineplexID
	 * @return an ArrayList of movie titles playing at that cineplex.
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> printMoviesCineplex(int cineplexID) throws FileNotFoundException {
		MovieController movie = new MovieController();
		ArrayList<String> cineplex1 = new ArrayList<String>();
		ArrayList<String> cineplex2 = new ArrayList<String>();
		ArrayList<String> cineplex3 = new ArrayList<String>();

		for (int i = 0; i < UIMain.sessionsArray.size(); i++) {
			if ((UIMain.sessionsArray.get(i).getCinemaID() / 10) == 1 && cineplexID == 1
					&& !cineplex1.contains(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID())))
				cineplex1.add(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID()));
			else if ((UIMain.sessionsArray.get(i).getCinemaID() / 10) == 2 && cineplexID == 2
					&& !cineplex2.contains(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID())))
				cineplex2.add(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID()));
			else if (cineplexID == 3 && !cineplex1.contains(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID())))
				cineplex2.add(movie.giveTitle(UIMain.sessionsArray.get(i).getMovieID()));
		}
		if (cineplex1.size() != 0)
			return cineplex1;
		else if (!cineplex2.equals(""))
			return cineplex2;
		else if (!cineplex3.equals(""))
			return cineplex3;
		return cineplex3;
	}

	/**
	 * @param cineID
	 * @return a string of the cinema location corresponding to the cinemaID.
	 */
	public static String printCinemaName(int cineID) {
		Cinema c = null;

		for (int i = 0; i < UIMain.cinemasArray.size(); i++) {
			if (UIMain.cinemasArray.get(i).getCinemaID() == cineID) {
				c = UIMain.cinemasArray.get(i);
			}
		}

		return "Cineplex " + c.getCineplexID() + ", Cinema " + c.getCinemaID() % 10;
	}

	/**
	 * @param cineID
	 * @return the cinema type corresponding to the cinemaID.
	 */
	public static String cinemaType(int cineID) {
		Cinema c = null;

		for (int i = 0; i < UIMain.cinemasArray.size(); i++) {
			if (UIMain.cinemasArray.get(i).getCinemaID() == cineID) {
				c = UIMain.cinemasArray.get(i);
			}
		}

		return String.valueOf(c.getCinemaType());
	}

	/**
	 * Loads updated cinema data back into Cinemas.txt inside the txt/ directory
	 * from the public ArrayList<Cinema>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void flush() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("txt/Cinemas.txt");
		for (int i = 0; i < UIMain.cinemasArray.size(); i++) {
			int cinemaID = UIMain.cinemasArray.get(i).getCinemaID();
			int cineplexID = UIMain.cinemasArray.get(i).getCineplexID();
			char cinemaType = UIMain.cinemasArray.get(i).getCinemaType();
			writer.print(String.valueOf(cinemaID) + ";");
			writer.print(String.valueOf(cineplexID) + ";");
			writer.print(cinemaType);
			if (i != UIMain.cinemasArray.size() - 1)
				writer.println();
		}
		writer.close();
	}
}
