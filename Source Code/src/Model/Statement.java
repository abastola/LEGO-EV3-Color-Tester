/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
3.	Class: Statement
Description:
•	Stores each rule of Text File
•	Used for Backward and Forward Mode


 */


package Model;

import java.util.ArrayList;


public class Statement {
	public String Character; //store right hand side charatcer
	public boolean TrueList; //to see if present in tree or not
	public ArrayList<String> Positions; //coordinates of the rules
	
	
public Statement() {
	Character=null;
	TrueList=false;
	Positions = new ArrayList<>();
	
}
	

}
