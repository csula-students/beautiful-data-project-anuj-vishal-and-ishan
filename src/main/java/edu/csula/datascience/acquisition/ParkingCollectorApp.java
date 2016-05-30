package edu.csula.datascience.acquisition;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

// "http://api.openweathermap.org/data/2.5/forecast/daily?appid=c5c75d5f24085e2c0211eed791afa04c&q=London&units=metric&cnt=7";

public class ParkingCollectorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int id = 0;

		String[] resourse = {
				"\\src\\main\\resources\\org\\gradle\\Parking_Violations_Issued_-_Fiscal_Year_2014__August_2013___June_2014_(1).csv",
				"\\src\\main\\resources\\org\\gradle\\Parking_Violations_Issued_-_Fiscal_Year_2016.csv",
				"\\src\\main\\resources\\org\\gradle\\Parking_Violations_Issued_-_Fiscal_Year_2015.csv",
				"\\src\\main\\resources\\org\\gradle\\parkinggeocoded.csv" };

		/*
		 * String[] URL = {
		 * "C:/Users/Ishan/Downloads/Parking_Violations_Issued_-_Fiscal_Year_2014__August_2013___June_2014_(1).csv",
		 * "C:/Users/Ishan/Downloads/Parking_Violations_Issued_-_Fiscal_Year_2016.csv",
		 * "C:/Users/Ishan/Downloads/Parking_Violations_Issued_-_Fiscal_Year_2015.csv",
		 * "C:/Users/Ishan/Downloads/parkinggeocoded.csv"
		 * 
		 * };
		 */

		String[] URL = new String[4];
		for (int i = 0; i < resourse.length; i++) {
			URL[i] = getPath(resourse[i]);
			System.out.println(URL[i]);
		}

		// "C:/Users/Vishal/Desktop/test/Parking_Violations_Issued_-_Fiscal_Year_2014__August_2013___June_2014_.csv",

		@SuppressWarnings("rawtypes")
		ParkingSource parkingSource = null;

		ParkingCollectorImpl collector = new ParkingCollectorImpl();

		for (int i = 0; i < URL.length; i++) {
			parkingSource = new ParkingSource(1, URL[i]);

			while (parkingSource.hasNext()) {
				Collection<List> parkingViolation = parkingSource.next();
				System.out.println("************** " + parkingViolation);
				Collection<List> cleaneddata = collector.mungee(parkingViolation);

				/*
				 * collector.save(cleaneddata);
				 */ // System.out.println(parkingViolation.toString());

			}

		}

	}

	private static String getPath(String path) {
		// TODO Auto-generated method stub

		String current = "";
		// String path = "\\MockData\\mockdata.csv";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		current += path;
		System.out.println("Current dir:" + current);
		return current;
//Test
	}

}
