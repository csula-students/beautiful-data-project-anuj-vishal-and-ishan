package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ParkingCollectorTest {

	@Test
	public void mungee() throws Exception {

		String path = getPath();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<SimpleModel> list = new ArrayList<SimpleModel>();

		try {

			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) { // System.out.println(line);
				// use comma as separator
				String[] viol = line.split(cvsSplitBy);
				if (viol.length >= 40) {

					list.add(new SimpleModel(viol[2], viol[39], viol[5], viol[1], viol[3], viol[19]));

				}

			}

			Assert.assertEquals(list.size(), 41636);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");

	}

	private String getPath() {

		String current = "";
		String path = "\\MockData\\mockdata.csv";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		current += path;
		System.out.println("Current dir:" + current);
		return current;
	}
}