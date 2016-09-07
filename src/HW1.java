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
	public static HashMap<String, Object> variables = new HashMap();

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			File file = new File("prog2.zpm");
			in = new Scanner(file);
	
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] split = line.split(" ");
				String rightSide, varName;
				
				// to handle variable declarations
				if(split[1].equals("=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					
					if(rightSide.contains("\"")) {
						variables.put(varName, rightSide);
					}
					
					// check if the right side of argument is a variable or a value
					else if(variables.get(rightSide) != null) {
						value = (int) variables.get(rightSide);
						variables.put(varName, value);
					} else {
						value = Integer.parseInt(rightSide);
						variables.put(varName, value);
					}
				}

				// to handle += compound assignment
				else if(split[1].equals("+=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						if(variables.get(rightSide) instanceof String) {
							String strValue = (String) variables.get(rightSide);
							strValue += variables.get(varName);
							variables.put(varName, strValue);
						}
						else if(variables.get(rightSide) instanceof Integer) {
							value = (int) variables.get(rightSide);
							value += (int) variables.get(varName);
							variables.put(varName, value);
						}
					} else {
						value = Integer.parseInt(rightSide);
						value += (int) variables.get(varName);
						variables.put(varName, value);
					}
				}
				
				// to handle *= compound assignment
				else if(split[1].equals("*=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = (int) variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					value *= (int) variables.get(varName);
					variables.put(varName, value);
				}
				
				// to handle -= compound assignment
				else if(split[1].equals("-=")) {
					varName = split[0];
					rightSide = split[2];
					int value;
					// check if the right side of argument is a variable or a value
					if(variables.get(rightSide) != null) {
						value = (int) variables.get(rightSide);
					} else value = Integer.parseInt(rightSide);
					value -= (int) variables.get(varName);
					variables.put(varName, value);
				}

				// print the value of the variable for PRINT statement
				else if(split[0].equals("PRINT")) {
					rightSide = split[1];
					//ensure variable has been declared before trying to print it
					if(variables.get(rightSide) != null) {
						if(variables.get(rightSide) instanceof String) {
							String strValue = (String) variables.get(rightSide);
							System.out.println(strValue);
						} else if(variables.get(rightSide) instanceof Integer) {
							int value = (int) variables.get(split[1]);
							System.out.println(value);
						}
					} else System.out.println("Error: Variable not declared. Cannot call \'PRINT\' method.");
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
