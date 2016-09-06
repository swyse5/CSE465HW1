/*
 * Stuart Wyse
 * CSE 465 - HW 1
 * Due 9/8/16
 * 
 * This program is meant to read in a z+- file and interprets it.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HW1 {

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			File file = new File("prog1.zpm");
			in = new Scanner(file);
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
