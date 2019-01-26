package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import boundary.UIMain;

import entity.User;

/**
 * Control class for user data.
 * 
 * @author SuyashL
 */

public class UserController implements Loader_Flusher {

	/**
	 * Loads user data from Users.txt inside the txt/ directory into the public
	 * ArrayList<User>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		Scanner user_sc = new Scanner(new File("txt/Users.txt"));
		String record;
		String fields[];

		while (user_sc.hasNextLine()) {
			record = user_sc.nextLine();
			fields = record.split(";");
			UIMain.usersArray.add(new User(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3],
					Integer.parseInt(fields[4]), fields[5]));
		}

		user_sc.close();
	}

	/**
	 * Login method for the user. If user is new or not found, user is requested
	 * to register.
	 * 
	 * @return userID This user's unique UserID.
	 */
	public int logon() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Are you an existing user (Y/N)? ");
		char check = (sc.nextLine().charAt(0));
		int i;

		if (check == 'Y' || check == 'y') {
			while (true) {
				System.out.print("Enter User ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				for (i = 0; i < UIMain.usersArray.size(); i++) {
					User u1 = UIMain.usersArray.get(i);
					int ID = u1.getUserID();

					if (id == ID)
						while (true) {
							System.out.print("Enter password: ");
							String password = sc.nextLine();
							if (password.equals(u1.getPassword())) {
								return ID;
							} else {
								System.out.println("Incorrect password. Please try again.");
							}
						}
				}
				if (i == UIMain.usersArray.size()) {
					System.out.println("User ID not found!\n1. Enter Again\n2. Make New Account");
					int choice = 0;
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
				        }while(true);
					if (choice == 2) {
						check = 'N';
						break;
					}
				}
			}
		}

		if (check == 'N' || check == 'n') {
			System.out.print("Enter Name: ");
			String Name = sc.nextLine();
			System.out.print("Enter Mobile No.: ");
			String Mobile = sc.nextLine();
			System.out.print("Enter E-Mail ID: ");
			String Email = sc.nextLine();
			System.out.print("Enter Age: ");
			int Age = sc.nextInt();
			System.out.print("Enter Password: ");
			sc.nextLine();
			String Password = sc.nextLine();
			User lastUser = UIMain.usersArray.get(UIMain.usersArray.size() - 1);
			int userID = lastUser.getUserID() + 1;
			User u1 = new User(userID, Name, Mobile, Email, Age, Password);
			UIMain.usersArray.add(u1);
			System.out.println("Your User ID is " + userID + ".");
			return userID;
		}
		return 0;
	}

	/**
	 * Loads updated user data back into Users.txt inside the txt/ directory
	 * from the public ArrayList<User>.
	 * 
	 * @throws FileNotFoundException
	 */
	public void flush() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("txt/Users.txt");
		for (int i = 0; i < UIMain.usersArray.size(); i++) {
			int userID = UIMain.usersArray.get(i).getUserID();
			String name = UIMain.usersArray.get(i).getName();
			String mobile_no = UIMain.usersArray.get(i).getMobile_no();
			String email = UIMain.usersArray.get(i).getEmail();
			int age = UIMain.usersArray.get(i).getAge();
			String password = UIMain.usersArray.get(i).getPassword();
			writer.print(String.valueOf(userID) + ";");
			writer.print(name + ";");
			writer.print(mobile_no + ";");
			writer.print(email + ";");
			writer.print(String.valueOf(age) + ";");
			writer.print(password);

			if (i != UIMain.usersArray.size() - 1)
				writer.println();
		}
		writer.close();
	}
}