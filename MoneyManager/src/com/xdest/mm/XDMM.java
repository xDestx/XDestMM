package com.xdest.mm;

import java.util.Scanner;

import com.xdest.mm.exception.UserAlreadyExistsException;
import com.xdest.mm.impl.TextManager;

public class XDMM {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter name: ");
		MMUser usr = null;
		while (usr == null) {
			String name = scanner.nextLine();
			try {
				usr = new MMUser(name);
			} catch (UserAlreadyExistsException e) {
				//System.out.print("\nA user with the name " + e.getOffendingName() + " already exists. Please select a different name.\nName: ");
				usr = null;
			}
			if(usr == null) {
				usr = MMUser.loadUser(name);
			}
		}
		TextManager tm = new TextManager(usr);

		tm.manage(scanner);
		
		//scanner.close();
	}
	
}
