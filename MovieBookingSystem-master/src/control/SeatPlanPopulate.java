package control;

import entity.Session;

/**
 * Used to populate the seat plan for a session on CLI.
 * 
 * @author SuyashL
 */

public class SeatPlanPopulate {
	public void seatPopulate(Session thisSession) {
		if ((thisSession.getCinemaID() % 10 == 1) || thisSession.getCinemaID() % 10 == 2) {
			String booked = "x";
			String free = ".";
			String _ne = " ";
			System.out.println("  A B C D   E F G H I J K L M N   O P Q R");
			for (int i = 0; i < 10; i++) {
				if (i != 9)
					System.out.print(i + 1 + " ");
				else
					System.out.print(i + 1);
				for (int j = 0; j < 20; j++) {
					if (j == 4 || j == 15) {
						System.out.print(_ne + ' ');
					} else if (thisSession.getSeatPlan()[i][j] == true) {
						System.out.print(booked + ' ');
					} else {
						System.out.print(free + ' ');
					}
				}
				System.out.println();
			}
		} else {
			String booked = "x";
			String free = ".";
			String _ne = " ";
			System.out.println("      A B     C D     E F     G H     I J ");
			for (int i = 0; i < 10; i++) {
				if (i != 9)
					System.out.print(i + 1 + " ");
				else
					System.out.print(i + 1);
				for (int j = 0; j < 20; j++) {
					if (j == 0 || j == 1 || j == 4 || j == 5 || j == 8 || j == 9 || j == 12 || j == 13
							|| j == 16 | j == 17)
						System.out.print(_ne + ' ');
					else if (thisSession.getSeatPlan()[i][j] == true) {
						System.out.print(booked + ' ');
					} else {
						System.out.print(free + ' ');
					}
				}
				System.out.println();
			}
		}
		System.out.println("_________________________________________");
		System.out.println("\t\t  Screen");
		System.out.println("Legend:  x : booked  . : available");
	}
}
