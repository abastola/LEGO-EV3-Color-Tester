/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
Class: Board
Description:
•	Scans the 10*10 board automatically (without support)
•	Store’s each pixel’s details: (x-axis, y-axis and color value) as Pixel Object

 */

package Controller;

import java.util.Arrays;

import Model.Pixel;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;
import lejos.hardware.Sound;

public class Board {
	public static int numberOfItems = 100; //total number of Pixels
	public static Pixel[] pixel = new Pixel[numberOfItems]; //to store details of each pixel
	public static Pixel[] backuppixel = new Pixel[numberOfItems]; //backup for next run
	private static Port colorSensorPort = SensorPort.S4; //Get colorsensor
	private static EV3ColorSensor colorSensor;
	
	
	public static double travelDistance = 0.6299;

	public Board() {

	}

	
	public void GetDataFromBoard(int a) {

		//move the scanner or ev3
		if (a == 0) {
			// Initialize the Color Sensor
			colorSensor = new EV3ColorSensor(colorSensorPort);
			colorSensor.setCurrentMode("RGB");
			float[] sample = new float[3];
			
			// Move Motor Forward
			DifferentialPilot pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.C, false); // parameters
			pilot.setTravelSpeed(1);
			colorSensor.fetchSample(sample, 0);
			
			Button.ENTER.waitForPressAndRelease();
			
			
			pilot.forward();
			int index = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					
					//pilot.travel(0.6299);
					//Delay.msDelay((long) (650));
					
										
					// get color
					colorSensor.fetchSample(sample, 0);
					
					//get blue value, green value and get value
					//add them for better results
					float value = sample[0] + sample[1] + sample[2];

					//store the x, y and color value as Pixel Object
					pixel[index] = new Pixel(value, i, j);
					backuppixel[index] = new Pixel(value, i, j);
					System.out.println(value + " " + i + " " + j);
					index++;
					Button.ENTER.waitForPressAndRelease();
				}
				// Move Back and Rotate to next Row
				pilot.travel(-10 * travelDistance);
				pilot.setRotateSpeed(20);
				pilot.rotate(90);
				pilot.travel(travelDistance);
				pilot.rotate(-90);

				Button.ENTER.waitForPressAndRelease();
				// Button.ENTER.waitForPressAndRelease();
				clearTheWholeScreen();
				//pilot.forward();
			}
			colorSensor.close();
			//pilot.stop();
			Button.ENTER.waitForPressAndRelease();

		} else  if(a==1){
			//get back up data
			for (int i=0; i<numberOfItems; i++){
				pixel[i].X=backuppixel[i].X;
				pixel[i].Y=backuppixel[i].Y;
				pixel[i].ValueOfColor=backuppixel[i].ValueOfColor;
				
			}
			

		}else{
			
			//sample data
			pixel[0] = new Pixel(0.006862745, 0, 0);
			pixel[1] = new Pixel(0.006862745, 0, 1);
			pixel[2] = new Pixel(0.9999, 0, 2);
			pixel[3] = new Pixel(0.20686275, 0, 3);
			pixel[4] = new Pixel(0.20196079, 0, 4);

			pixel[5] = new Pixel(0.006862745, 1, 0);
			pixel[6] = new Pixel(0.006862745, 1, 1);
			pixel[7] = new Pixel(0.9999, 1, 2);
			pixel[8] = new Pixel(0.20686275, 1, 3);
			pixel[9] = new Pixel(0.21686275, 1, 4);

			pixel[10] = new Pixel(0.006862745, 2, 0);
			pixel[11] = new Pixel(0.006862745, 2, 1);
			pixel[12] = new Pixel(0.9999, 2, 2);
			pixel[13] = new Pixel(0.9999, 2, 3);
			pixel[14] = new Pixel(0.9999, 2, 4);

			pixel[15] = new Pixel(0.20686275, 3, 0);
			pixel[16] = new Pixel(0.21686275, 3, 1);
			pixel[17] = new Pixel(0.19676275, 3, 2);
			pixel[18] = new Pixel(0.9999, 3, 3);
			pixel[19] = new Pixel(0.9999, 3, 4);

			pixel[20] = new Pixel(0.20676275, 4, 0);
			pixel[21] = new Pixel(0.9999, 4, 1);
			pixel[22] = new Pixel(0.9999, 4, 2);
			pixel[23] = new Pixel(0.00762745, 4, 3);
			pixel[24] = new Pixel(0.006862745, 4, 4);
			
			backuppixel[0] = new Pixel(0.006862745, 0, 0);
			backuppixel[1] = new Pixel(0.006862745, 0, 1);
			backuppixel[2] = new Pixel(0.9999, 0, 2);
			backuppixel[3] = new Pixel(0.20686275, 0, 3);
			backuppixel[4] = new Pixel(0.20196079, 0, 4);

			backuppixel[5] = new Pixel(0.006862745, 1, 0);
			backuppixel[6] = new Pixel(0.006862745, 1, 1);
			backuppixel[7] = new Pixel(0.9999, 1, 2);
			backuppixel[8] = new Pixel(0.20686275, 1, 3);
			backuppixel[9] = new Pixel(0.21686275, 1, 4);

			backuppixel[10] = new Pixel(0.006862745, 2, 0);
			backuppixel[11] = new Pixel(0.006862745, 2, 1);
			backuppixel[12] = new Pixel(0.9999, 2, 2);
			backuppixel[13] = new Pixel(0.9999, 2, 3);
			backuppixel[14] = new Pixel(0.9999, 2, 4);

			backuppixel[15] = new Pixel(0.20686275, 3, 0);
			backuppixel[16] = new Pixel(0.21686275, 3, 1);
			backuppixel[17] = new Pixel(0.19676275, 3, 2);
			backuppixel[18] = new Pixel(0.9999, 3, 3);
			backuppixel[19] = new Pixel(0.9999, 3, 4);

			backuppixel[20] = new Pixel(0.20676275, 4, 0);
			backuppixel[21] = new Pixel(0.9999, 4, 1);
			backuppixel[22] = new Pixel(0.9999, 4, 2);
			backuppixel[23] = new Pixel(0.00762745, 4, 3);
			backuppixel[24] = new Pixel(0.006862745, 4, 4);
		}
	}

	//clear whole screen
	public static void clearTheWholeScreen() {
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}

}
