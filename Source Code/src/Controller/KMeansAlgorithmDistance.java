/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
7.	Class: KMeansAlgorithmDistance
Description:
•	Implements KMeansAlgorithm in terms of distance
•	Divides each group passed from KMeansAlgorithm Class to two groups. 
•	Coordinates of both groups are then passed along NormalizePixels to get Absolute value of coordinates of each Pixel in the groups 
•	Also prints bounding box’s coordinates for each group 


 */


package Controller;

import static java.lang.Math.abs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Model.Pixel;
import lejos.hardware.Button;

public class KMeansAlgorithmDistance {
	int numberofItems = 0;
	int numberofCluster = 2;
	ArrayList<Pixel> dataItems;
	ArrayList<Pixel> Centroids;
	ArrayList<Pixel> OldCentroids;
	ArrayList<Float> Distance;
	public static ArrayList<ArrayList<Pixel>> ClusterGroups;
	public static ArrayList<ArrayList<Pixel>> ClusterGroupsFinal=new ArrayList<>();
	
	public KMeansAlgorithmDistance(){
		
	}
	
	public void clearGroups(){
		//clear cluster Groups
		ClusterGroupsFinal = new ArrayList<>();
		for (int i=0; i<ClusterGroupsFinal.size(); i++){
			for (int j=0; j<ClusterGroupsFinal.get(i).size(); j++){
				ClusterGroupsFinal.get(i).remove(j);
			}
		}
	}
	
	
	public KMeansAlgorithmDistance(ArrayList<Pixel> arrayList) {
		dataItems = new ArrayList<>();
		Centroids = new ArrayList<>();
		OldCentroids = new ArrayList<>();
		ClusterGroups = new ArrayList<>();
		Distance = new ArrayList<>();
		
		//ClusterGroupsFinal = new ArrayList<>();

		// Copy arrayList to DataItems
		dataItems = new ArrayList<Pixel>(arrayList);

		System.out.println("Current Data: ");
		for (int i = 0; i < dataItems.size(); i++) {
			System.out.println(dataItems.get(i).X + " " + dataItems.get(i).Y + " " + dataItems.get(i).ValueOfColor);
		}

		// make number of groups equal to number of clusters
		for (int i = 0; i < numberofCluster; i++) {
			ClusterGroups.add(new ArrayList<Pixel>());
		}

		// get all pixel information from the Board
		for (int i = 0; i < (dataItems.size()); i++) {

			// assign first k elements as centroids
			if (i < numberofCluster) {
				Centroids.add(dataItems.get(i));
			}

		}

		// Print all the Centroids
		System.out.println();
		System.out.println("Current Centroids: ");
		for (int i = 0; i < numberofCluster; i++) {
			System.out.println(Centroids.get(i).ValueOfColor);
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
					float x1 = Centroids.get(j).X;
					float y1 = Centroids.get(j).Y;
					float x2 = dataItems.get(i).X;
					float y2 = dataItems.get(i).Y;
					Distance.add((float) Math.hypot(x1 - x2, y1 - y2)); //find centroid

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
					Centroids.set(i, centroidOfPoints(ClusterGroups.get(i)));
				}
			}

			//check for convergence
			for (int i = 0; i < numberofCluster; i++) {
				// System.out.println(i + ": " + Centroids.get(i).ValueOfColor +
				// " " + OldCentroids.get(i).ValueOfColor);
				if ((Centroids.get(i).X != OldCentroids.get(i).X) && (Centroids.get(i).Y != OldCentroids.get(i).Y)) {
					System.out.println(" Not Equal");
				} else {
					NumberofCentroidsEqual++;
				}
			}
			System.out.println("Numbers of Centroids Equal: " + NumberofCentroidsEqual);
			NumberOfInterations++;
		} while (NumberofCentroidsEqual != numberofCluster);

		

		//print final cluster results
		for (int i = 0; i < numberofCluster; i++) {
			ClusterGroupsFinal.add(ClusterGroups.get(i));
			System.out.println("Group " + i);
			
			for (int j = 0; j < ClusterGroups.get(i).size(); j++) {
				int x = (int) ClusterGroups.get(i).get(j).X;
				int y = (int) ClusterGroups.get(i).get(j).Y;

				//System.out.println(x + " " + y + " " + ClusterGroups.get(i).get(j).ValueOfColor + " ");
			}
			System.out.println();
		}

	}
	
	//clear the screen
	public static void clearTheWholeScreen() {
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
	
	//get average/centroid
	private Pixel centroidOfPoints(ArrayList<Pixel> arrayList) {
		// TODO Auto-generated method stub
		Pixel pixel = new Pixel();
		float x = 0;
		float y = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			x = x + arrayList.get(i).X;
			y = y + arrayList.get(i).Y;
		}

		x = x / arrayList.size();
		y = y / arrayList.size();

		pixel.X = x;
		pixel.Y = y;
		pixel.ValueOfColor = 0;
		// System.out.println("Average: "+pixel.ValueOfColor);

		return pixel;
		//result average
	}

}
