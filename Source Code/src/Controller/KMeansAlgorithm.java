/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
6.	Class: KMeansAlgorithm
Description:
•	Implements KMeansAlgorithm in terms of cluster
•	Divides the Pixels to k clusters/groups and these groups are passed along the KMeansAlgorithmDistance to get Final Characters
•	Also prints bounding box’s coordinates for each color group 


 */


package Controller;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import Model.Pixel;
import View.HomePage;
import lejos.hardware.Button;

public class KMeansAlgorithm {
	int numberofItems = 25;
	public int numberofCluster = 0;
	ArrayList<Pixel> dataItems;
	ArrayList<Pixel> Centroids;
	ArrayList<Pixel> OldCentroids;
	ArrayList<Float> Distance;
	public static ArrayList<ArrayList<Pixel>> ClusterGroups;

	
	public void ClearAllItems() {
		for (int i=0; i<dataItems.size(); i++){
			dataItems.remove(i);
		}
		for (int i=0; i<Centroids.size(); i++){
			Centroids.remove(i);
		}
		for (int i=0; i<OldCentroids.size(); i++){
			OldCentroids.remove(i);
		}
		for (int i=0; i<ClusterGroups.size(); i++){
			ClusterGroups.remove(i);
		}
		for (int i=0; i<Distance.size(); i++){
			Distance.remove(i);
		}
	}
	
	public KMeansAlgorithm() {
	}

	
	public void PerformClustering() {

		dataItems = new ArrayList<>();
		Centroids = new ArrayList<>();
		OldCentroids = new ArrayList<>();
		ClusterGroups = new ArrayList<>();
		Distance = new ArrayList<>();

		// get board from the HomePage
		HomePage homepage = new HomePage();

		// make number of groups equal to number of clusters
		for (int i = 0; i < numberofCluster; i++) {
			ClusterGroups.add(new ArrayList<Pixel>());
		}

		// get all pixel information from the Board
		for (int i = 0; i < (homepage.board.numberOfItems); i++) {
			dataItems.add(homepage.board.pixel[i]);
			// assign first k elements as centroids
			if (i < numberofCluster) {
				Centroids.add(dataItems.get(i));
			}

		}

		int NumberOfInterations = 1;
		int NumberofCentroidsEqual = 0;

		do {
			for (int i = 0; i < numberofCluster; i++) {
				ClusterGroups.get(i).removeAll(ClusterGroups.get(i));
			}

			NumberofCentroidsEqual = 0;
			System.out.println();
			System.out.println("Iteration Number: " + NumberOfInterations);
			for (int i = 0; i < dataItems.size(); i++) {
				for (int j = 0; j < numberofCluster; j++) {
					Distance.add(abs(Centroids.get(j).ValueOfColor - dataItems.get(i).ValueOfColor));
				}
				ClusterGroups.get(Distance.indexOf(Collections.min(Distance))).add(dataItems.get(i));
				Distance.removeAll(Distance);
			}

			//assign new centroids to old centroids
			//basically update centroids
			for (int i = 0; i < numberofCluster; i++) {
				if (NumberOfInterations == 1) {
					OldCentroids.add(Centroids.get(i));
				} else {
					OldCentroids.set(i, Centroids.get(i));
				}
				if (!ClusterGroups.get(i).isEmpty()) {
					Centroids.set(i, average(ClusterGroups.get(i)));
				}
			}

			//check for convergence
			for (int i = 0; i < numberofCluster; i++) {
				System.out.println(i + ": " + Centroids.get(i).ValueOfColor + " " + OldCentroids.get(i).ValueOfColor);
				if (Centroids.get(i).ValueOfColor != OldCentroids.get(i).ValueOfColor) {
					System.out.println(" Not Equal");
				} else {
					NumberofCentroidsEqual++;
				}
			}
			System.out.println("Numbers of Centroids Equal: " + NumberofCentroidsEqual);
			NumberOfInterations++;
		} while (NumberofCentroidsEqual != numberofCluster);
		
		NumberOfInterations--;
		// Print All the Groups
		clearTheWholeScreen();
		System.out.println();
		System.out.println("Number of iterations till convergence: "+NumberOfInterations);
		Button.ENTER.waitForPressAndRelease();

		// Print All the Groups
		System.out.println();

		System.out.println("--------------Output Group--------------");
		for (int i = 0; i < numberofCluster; i++) {
			System.out.println("Group " + Integer.toString(i+1));
			findMinimum(ClusterGroups.get(i));
			findMaximum(ClusterGroups.get(i));
			for (int j = 0; j < ClusterGroups.get(i).size(); j++) {
				// System.out.println(ClusterGroups.get(i).get(j).X + " " + ClusterGroups.get(i).get(j).Y + " "
						// + ClusterGroups.get(i).get(j).ValueOfColor + " ");
			}
			System.out.println();
		}
		
		

	}
	
	public static void clearTheWholeScreen() {
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
	
	
	private void findMinimum(ArrayList<Pixel> arrayList){
		int minimumX=1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumX>arrayList.get(i).X){
				minimumX=(int) arrayList.get(i).X;
			}
		}
		int minimumY=1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumY>arrayList.get(i).Y && minimumX==arrayList.get(i).X){
				minimumY=(int) arrayList.get(i).Y;
			}
		}
		
		System.out.println("Top Left: "+minimumX+" "+minimumY );
		//Button.ENTER.waitForPressAndRelease();
	}

	private void findMaximum(ArrayList<Pixel> arrayList){
		int minimumX=-1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumX<arrayList.get(i).X){
				minimumX=(int) arrayList.get(i).X;
			}
		}
		int minimumY=-1000;
		for (int i = 0; i < arrayList.size(); i++) {
			if(minimumY<arrayList.get(i).Y && minimumX==arrayList.get(i).X){
				minimumY=(int) arrayList.get(i).Y;
			}
		}
		
		System.out.println("Bottom Right: "+minimumX+" "+minimumY );
		Button.ENTER.waitForPressAndRelease();
	}

	// get Average of the group
	private Pixel average(ArrayList<Pixel> arrayList) {
		// TODO Auto-generated method stub
		Pixel pixel = new Pixel();
		float sum = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			sum = sum + arrayList.get(i).ValueOfColor;
		}
		float average = sum / arrayList.size();
		average = (float) (Math.round(average * 100000.0) / 100000.0);
		pixel.X = 0;
		pixel.Y = 0;
		pixel.ValueOfColor = average;
		// System.out.println("Average: "+pixel.ValueOfColor);

		return pixel;
	}

}
