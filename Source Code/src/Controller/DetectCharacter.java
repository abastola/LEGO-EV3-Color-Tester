/*
 * Name: Arjun Bastola 
 * Project: 5 / AI

9.	Class: DetectCharacter
Description:
•	Each group of pixels passed from NormalizePixels are checked against the provided rule base to detect characters.
•	 Uses Forward Reasoning AI algorithm to detect Characters. 
•	Prints out each detected Character. 

 */

package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Pixel;

public class DetectCharacter {
	public static Rules rules;

	public DetectCharacter(ArrayList<Pixel> arrayList) {

		//get the Rules from the Text File
		try {
			rules = new Rules();
		} catch (IOException e) {
			throw new RuntimeException();
		}

		
		//rule base system
		for (int i = 0; i < arrayList.size(); i++) {
			String ValueToPass = Integer.toString((int) arrayList.get(i).X)
					+ Integer.toString((int) arrayList.get(i).Y);
			for (int index = 0; index < 20; index++) {
				if (rules.statement[index].TrueList) {
					
					//check if given Pixel is in the rule
					boolean found = LookUpNestedRule(rules.statement[index].Character, ValueToPass);
					if (found) {

					} else {
						rules.statement[index].TrueList = false;

					}

				}

			}
		}

		//print out possible candidate
		for (int index = 0; index < rules.statementIndex; index++) {
			if (rules.statement[index].TrueList && arrayList.size()>=rules.statement[index].Positions.size()) {
				System.out.print(rules.statement[index].Character+" ");
			}

		}

		// ClearTheTree();

	}

	/*
	 * public void ClearTheTree(){ for (int i=0; i<20; i++){
	 * rules.statement[i].TrueList=true; } }
	 */
	
	//look up for a certain coordinate in a rule base
	public static boolean LookUpNestedRule(String a, String passed) {
		int i;
		boolean found = false;
		for (i = 0; i < 20; i++) {

			if (rules.statement[i].Character.equals(a)) {
				break;
			}
		}

		for (int j = 0; j < rules.statement[i].Positions.size(); j++) {
			if (rules.statement[i].Positions.get(j) != null) {
				String abc = rules.statement[i].Positions.get(j);
				if (abc.equals("00") || abc.equals("01") || abc.equals("02") || abc.equals("03") || abc.equals("04")
						|| abc.equals("05") || abc.equals("10") || abc.equals("11") || abc.equals("12")
						|| abc.equals("13") || abc.equals("14") || abc.equals("15") || abc.equals("20")
						|| abc.equals("21") || abc.equals("22") || abc.equals("23") || abc.equals("24")
						|| abc.equals("25") || abc.equals("30") || abc.equals("31") || abc.equals("32")
						|| abc.equals("33") || abc.equals("34") || abc.equals("35") || abc.equals("40")
						|| abc.equals("41") || abc.equals("42") || abc.equals("43") || abc.equals("44")
						|| abc.equals("45") || abc.equals("50") || abc.equals("51") || abc.equals("52")
						|| abc.equals("53") || abc.equals("54") || abc.equals("55") || abc.equals("60")
						|| abc.equals("61") || abc.equals("62") || abc.equals("63") || abc.equals("64")
						|| abc.equals("65") || abc.equals("70") || abc.equals("71") || abc.equals("72")
						|| abc.equals("73") || abc.equals("74") || abc.equals("75")) {
					if (rules.statement[i].Positions.get(j).equals(passed)) {
						// System.out.println(" Match Found: " +
						// rules.statement[i].Character);
						found = true;
						break;
					}

				} else {
					found = LookUpNestedRule(abc, passed);

				}
				if (found) {
					break;
				}

			}
		}

		return found;
	}

}
