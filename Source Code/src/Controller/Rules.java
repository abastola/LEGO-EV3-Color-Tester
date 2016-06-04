/* 
 Name: Arjun Bastola
 Project: AI Project 5 Scanner
 Class: Rules
 Description:
 1. Gets Text File
 2. Extract all the rules from the text file using array of Statement Object
 
 
 */

package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import Model.Statement;

public class Rules {
	//Create twenty rules (Statement Objects)
	public Statement[] statement = new Statement[20];
	public int statementIndex;

	//Inititalize the rules
	public Rules() throws IOException {
		for (int i = 0; i < 20; i++) {
			statement[i] = new Statement();
		}
		//ExctractRules Function which extracts rules from text file and stores them as statement objects
		ExtractRules();
	}

	
	public void ExtractRules() throws IOException {

		//Get File Using Input Stream and Bufferred Reader
		
		InputStream in = null;
		try {
			in = this.getClass().getResource("rules.txt").openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String mainString;
        statementIndex = 0;
        
        
        //Read all the lines and extract the rules
        while ((mainString = reader.readLine()) != null) {
        		
        	//Delimit each line
        	//Remove unnecessary info like '(', ',' and other non useful chars
			String delimiter1 = "\\(";
			String delimiter2 = "_";
			mainString = mainString.replaceAll(delimiter1, delimiter2);
			delimiter1 = "\\)";
			mainString = mainString.replaceAll(delimiter1, delimiter2);
			delimiter1 = "\\,";
			mainString = mainString.replaceAll(delimiter1, delimiter2);
			// delimiter1 = " ";
			// mainString = mainString.replaceAll(delimiter1, delimiter2);
			mainString = mainString.replace("_", "");
			// System.out.println("Whole Line: "+ mainString);

			String[] splited = mainString.split("\\->");

			/*
			 * for(int i=0; i<splited.length; i++){
			 * 
			 * System.out.println(splited[i]); }
			 */

			String numbers = splitNumbers(splited[0]);
			String letters = splitLetters(splited[0]);

			// System.out.println("Whole Line: " + mainString);
			// System.out.println("Numbers: " + numbers);
			// System.out.println("Letters: " + letters);
			// System.out.println("Result: " + splited[1]);

			String[] SingleRule = new String[50];
			int index = 0;

			SingleRule[index] = splited[1];
			index++;

			// extracted number pairs of left hand side
			// stored in Pairs array of strings
			int numberOfBrackets = numbers.length() / 2;
			String[] Pairs = new String[numberOfBrackets];
			int hello = 0;
			for (int i = 0; i < numberOfBrackets; i++) {
				Pairs[i] = numbers.substring(hello, hello + 2);
				hello = hello + 2;
				SingleRule[index] = Pairs[i];
				index++;

			}

			// extracted letters of left hand side
			// stored in LetterSplit array of strings
			String[] splitedLetters = letters.split(" ");

			int numberOfLetterss = splitedLetters.length;
			String[] LettersSplit = new String[numberOfLetterss];
			hello = 0;
			for (int i = 0; i < numberOfLetterss; i++) {
				// System.out.println("Testing for: "+splitedLetters[i]);
				if (splitedLetters[i].equals("")) {

				} else {
					SingleRule[index] = splitedLetters[i];
					index++;
				}
			}

			SingleRule[0] = SingleRule[0].replaceAll(" ", "");
			statement[statementIndex].TrueList = true;
			statement[statementIndex].Character = SingleRule[0];

			//System.out.print(SingleRule[0] + ": ");

			//Store the rules in SingleRule
			for (int i = 1; i < index; i++) {
				SingleRule[i] = SingleRule[i].replaceAll(" ", "");
				//System.out.print(SingleRule[i] + " ");
				statement[statementIndex].Positions.add(SingleRule[i]);
				//statement[statementIndex].Positions[i - 1] = SingleRule[i];
			}
			//System.out.println();
			statementIndex++;

			// to know if we should go to another number, use the length of
			// characters
			// numbers have length of 2 and char have length of 1
		}

	}

	//Separates Numbers from the String
	public static String splitNumbers(String toSplit) {
		int lengthofString = toSplit.length();
		String abc = "";
		for (int i = 0; i < lengthofString; i++) {
			if (toSplit.charAt(i) == '0' || toSplit.charAt(i) == '1' || toSplit.charAt(i) == '2'
					|| toSplit.charAt(i) == '3' || toSplit.charAt(i) == '4' || toSplit.charAt(i) == '5'
					|| toSplit.charAt(i) == '6' || toSplit.charAt(i) == '7') {
				abc = abc + toSplit.charAt(i);
			}
		}

		return abc;
	}

	//Separate Letters from the String
	public static String splitLetters(String toSplit) {
		// System.out.println(toSplit);
		int lengthofString = toSplit.length();
		String abc = "";
		for (int i = 0; i < lengthofString; i++) {
			if (toSplit.charAt(i) == '0' || toSplit.charAt(i) == '1' || toSplit.charAt(i) == '2'
					|| toSplit.charAt(i) == '3' || toSplit.charAt(i) == '4' || toSplit.charAt(i) == '5'
					|| toSplit.charAt(i) == '6' || toSplit.charAt(i) == '7') {

			} else {
				abc = abc + toSplit.charAt(i);
			}
		}
		return abc;
	}
}
