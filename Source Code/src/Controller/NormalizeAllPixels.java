/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
8.	Class: NormalizePixels
Description:
•	Each group of pixels passed from KMeansAlgorithmDistance is normalized in this class. 
•	This class finds the lowest value of x and y and subtracts co-ordinates of each Pixel by minimum value of x and y.
•	The normalized groups are then passed to DetectCharacter to get Final Characters.  

 */


package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Pixel;
import View.HomePage;

public class NormalizeAllPixels {

	public HomePage rawData;

	public NormalizeAllPixels() {
		//get Clusters from HomePage Class
		rawData = new HomePage();
		
		//Go through each Cluster and normalize pixels
		for (int i = 0; i < rawData.KMeansDistance.ClusterGroupsFinal.size(); i++) {
			int a = i + 1;
			System.out.println();
			System.out.println("Cluster Group " + a);
			//find minimum X and Y
			int minimumX = findMinimumX(rawData.KMeansDistance.ClusterGroupsFinal.get(i));
			int minimumY = findMinimumY(rawData.KMeansDistance.ClusterGroupsFinal.get(i));
			
			//subtract minimum X and Y from each pixel
			NormalizeCharacters(minimumX, minimumY, rawData.KMeansDistance.ClusterGroupsFinal.get(i));
			System.out.println("Minimum X: " + minimumX + " Minimum Y: " + minimumY);
		}
		PrintNormalizedPixels();
	}

	//print each cluster for checking and testing purpose
	private void PrintNormalizedPixels() {
		System.out.println("Normalized Pixels: ");
		for (int i = 0; i < rawData.KMeansDistance.ClusterGroupsFinal.size(); i++) {
			int a = i + 1;
			System.out.println();
			System.out.println("Cluster Group " + a);
			for (int j = 0; j < rawData.KMeansDistance.ClusterGroupsFinal.get(i).size(); j++) {
				int x = (int) rawData.KMeansDistance.ClusterGroupsFinal.get(i).get(j).X;
				int y = (int) rawData.KMeansDistance.ClusterGroupsFinal.get(i).get(j).Y;
				System.out.println(
						x + " " + y + " " + rawData.KMeansDistance.ClusterGroupsFinal.get(i).get(j).ValueOfColor + " ");
			}
		}

	}

	// get Minimum X
	public int findMinimumX(ArrayList<Pixel> arrayList) {
		int minimum = 100000;
		for (int i = 0; i < arrayList.size(); i++) {
			int abc = (int) arrayList.get(i).X;
			if (abc < minimum) {
				minimum = abc;
			}
		}
		return minimum;
	}

	// get Minimum Y
	public int findMinimumY(ArrayList<Pixel> arrayList) {
		int minimum = 100000;
		for (int i = 0; i < arrayList.size(); i++) {
			int abc = (int) arrayList.get(i).Y;
			if (abc < minimum) {
				minimum = abc;
			}
		}
		return minimum;
	}

	//subtract minimum x and y from each pixel
	public void NormalizeCharacters(int x, int y, ArrayList<Pixel> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i).X = arrayList.get(i).X - x;
			arrayList.get(i).Y = arrayList.get(i).Y - y;
		}

	}

}
