/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
1.	Class: HomePage
Description:
•	Provides user Menu to select between “Scan Board” and “Start K Means Algorithm.”
•	If the user selects K Means, this class displays an option to select value for k. 

 */


package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controller.Board;
import Controller.DetectCharacter;
import Controller.KMeansAlgorithm;
import Controller.KMeansAlgorithmDistance;
import Controller.NormalizeAllPixels;
import Controller.Rules;
import Model.Pixel;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.utility.TextMenu;

public class HomePage {
	public static Board board;
	public static KMeansAlgorithmDistance KMeansDistance;
	public static int testsNumber;
	public static int numberOfClusters;
	

	public static void main(String[] args) {

		//Display the Menu
		DisplayMenu();

		
	}

	//clear the screen
	public static void clearTheWholeScreen() {
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}

	public static void DisplayMenu() {
		LCD.clear();
		clearTheWholeScreen();
		
		//Diplay User Options
		while (!Button.ENTER.isDown()) {
			String[] testsList = { "Scan Board", "Start KMeans"};
			TextMenu testsMenu = new TextMenu(testsList, 1, "Menu: ");
			testsNumber = testsMenu.select();

			Delay.msDelay(300);

		}
		//Scan Board
		if (testsNumber == 0) {
			LCD.clear();
			System.out.println("Scanning Board");
			board = new Board(); 
			board.GetDataFromBoard(0);
			Button.ENTER.waitForPressAndRelease();
			DisplayMenu();
		} 
		//Start K Means Algorithm
		else if (testsNumber == 1) {
			//Select Value of k
			LCD.clear();
			Button.ENTER.waitForPressAndRelease();
			while (!Button.ENTER.isDown()) {
				String[] testsList = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
				TextMenu testsMenu = new TextMenu(testsList, 1, "Select k:");
				numberOfClusters = testsMenu.select() + 1;

				Delay.msDelay(300);

			}
			LCD.clear();
			board.GetDataFromBoard(1);
			PerformKMeansClustering();
			
			//Print Clusters
			PrintClusters();
			
			// Detect All the Characters
			NormalizeAllPixels normalize = new NormalizeAllPixels();

			// Detect Character
			// make DetecTCharacter Object and print out the results
			
			//detect and print
			System.out.println();
			for (int i = 0; i < KMeansDistance.ClusterGroupsFinal.size(); i++) {
				System.out.print(Integer.toString(i + 1) + ": ");
				DetectCharacter detect = new DetectCharacter(KMeansDistance.ClusterGroupsFinal.get(i));
				Button.ENTER.waitForPressAndRelease();
				System.out.println();
			}

			Button.ENTER.waitForPressAndRelease();
			
			KMeansDistance.clearGroups();
			DisplayMenu();
			
		} 
	}

	//print clusters
	public static void PrintClusters() {
		System.out.println("Distance Cluster");
		for (int i = 0; i < KMeansDistance.ClusterGroupsFinal.size(); i++) {
			int a = i + 1;
			System.out.println("Group " + a);
			findMinimum(KMeansDistance.ClusterGroupsFinal.get(i));
			findMaximum(KMeansDistance.ClusterGroupsFinal.get(i));
			for (int j = 0; j < KMeansDistance.ClusterGroupsFinal.get(i).size(); j++) {
				int x = (int) KMeansDistance.ClusterGroupsFinal.get(i).get(j).X;
				int y = (int) KMeansDistance.ClusterGroupsFinal.get(i).get(j).Y;

			}
			System.out.println();
		}
	}

	//find minimum and maximum x and y for bounding box
	private static void findMinimum(ArrayList<Pixel> arrayList){
		int minimumX=1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumX>arrayList.get(i).X){
				minimumX=(int) arrayList.get(i).X;
			}
		}
		int minimumY=1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumY>arrayList.get(i).Y ){
				minimumY=(int) arrayList.get(i).Y;
			}
		}
		
		System.out.println("Top Left: "+minimumX+" "+minimumY );
		//Button.ENTER.waitForPressAndRelease();
	}
	
	
	//find minimum and maximum x and y for bounding box
	private static void findMaximum(ArrayList<Pixel> arrayList){
		int minimumX=-1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumX<arrayList.get(i).X){
				minimumX=(int) arrayList.get(i).X;
			}
		}
		int minimumY=-1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumY<arrayList.get(i).Y){
				minimumY=(int) arrayList.get(i).Y;
			}
		}
		
		System.out.println("Bottom Right: "+minimumX+" "+minimumY );
		Button.ENTER.waitForPressAndRelease();
	}

	public static void PerformKMeansClustering() {
		// Use K-Means Algorithm to divide the Clusters According to Color
		KMeansAlgorithm KMeans = new KMeansAlgorithm();
		
		KMeans.numberofCluster = numberOfClusters;
		KMeans.PerformClustering();

		// Use K-Means Algorithm to divide the Clusters According to Distance
		System.out.println();
		for (int i = 0; i < KMeans.ClusterGroups.size(); i++) {
			System.out.println("Performing Clustering for Group: " + i);
			if (KMeans.ClusterGroups.get(i).size() > 1) {
				KMeansDistance = new KMeansAlgorithmDistance(KMeans.ClusterGroups.get(i));
			}
		}
		
		KMeans.ClearAllItems();
		
	}

}
