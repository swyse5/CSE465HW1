/*
 * Stuart Wyse
 * CSE 465 - HW 1
 * Due 9/8/16
 * 
 * This program is meant to read in a z+- file and interpret it.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HW1 {
	public static HashMap<String, Integer> variables = new HashMap();

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			File file = new File("prog1.zpm");
			in = new Scanner(file);
	
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] split = line.split(" ");
				String rightSide, varName;
				
				if(split[1].equals("=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					variables.put(varName, (value));
				}

				// to handle += compound assignment
				else if(split[1].equals("+=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					value += variables.get(varName);
					variables.put(varName, value);
				}
				
				// to handle *= compound assignment
				else if(split[1].equals("*=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					value *= variables.get(varName);
					variables.put(varName, value);
				}
				
				// to handle -= compound assignment
				else if(split[1].equals("-=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					value -= variables.get(varName);
					variables.put(varName, value);
				}

				// print the value of the variable for PRINT statement
				else if(split[0].equals("PRINT")) {
					int value = variables.get(split[1]);
					System.out.println(value);
				}
				
				System.out.println(variables);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	Not currently being used
//	public static boolean isDeclared(String varName) {
//		boolean found = false;
//		if(variables.get(varName) != null) {
//			found = true;
//		}	
//		return found;
//	}

}
