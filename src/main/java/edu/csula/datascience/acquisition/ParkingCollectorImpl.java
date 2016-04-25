package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class ParkingCollectorImpl implements Collector<List, List> {

	MongoClient mongoClient;
	DB database;
	DBCollection collection;

	public ParkingCollectorImpl() {
		/*
		 * // establish database connection to MongoDB mongoClient = new
		 * MongoClient();
		 * 
		 * // select `bd-example` as testing database database =
		 * mongoClient.getDatabase("bd-example");
		 * 
		 * // select collection by name `tweets` collection =
		 * database.getCollection("tweets");
		 */

		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("parking");
		collection = database.getCollection("parkingViolation");
	}

	public Collection<List> mungee(@SuppressWarnings("rawtypes") Collection<List> src) {

		String URL = "";
		Object obj = new Object();

		Iterator i = src.iterator();

		while (i.hasNext()) {
			obj = i.next();
		}

		URL = (String) obj;
		System.out.println(URL);
		// String URL =
		// "C:/Users/Vishal/Desktop/test/Parking_Violations_Issued_-_Fiscal_Year_2016.csv";

		// Collection<T> list = Lists.newArrayList();

		BufferedReader br = null;
		String line, line2, line3, line4 = "";
		String cvsSplitBy = ",";
		int count = 0;
		ParkingCollectorImpl collector = new ParkingCollectorImpl();
		List<List<String>> Parent_List = new ArrayList<List<String>>();

		try {

			br = new BufferedReader(new FileReader(URL));
			System.out.println(URL);

			while ((line = br.readLine()) != null) { // System.out.println(line);
				// use comma as separator
				String[] viol = line.split(cvsSplitBy);
				count++;
				List<String> subList = new ArrayList<String>();
				// changes
				if (viol.length >= 40) {
					subList = new ArrayList<String>();
					subList.add(viol[2]);
					subList.add(viol[39]);
					subList.add(viol[5]);
					subList.add(viol[1]);
					subList.add(viol[3]);
					subList.add(viol[19]);
				}
				if (subList.size() == 6)
					Parent_List.add(subList);
				if (Parent_List.size() > 250000) {

					collector.saveData(Parent_List);

					Parent_List.clear();

				}

			}

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

		return null;

	}

	private void saveData(List<List<String>> parent_List) {

		// System.out.println(parent_List.size());

		System.out.println("save " + parent_List.size());
		for (List<String> temp : parent_List) {
			BasicDBObject document = new BasicDBObject();
			document.put("Registration state", temp.get(0));
			document.put("Description", temp.get(1));
			document.put("code", temp.get(2));
			document.put("Plate Id", temp.get(3));
			document.put("Plate Type", temp.get(4));
			document.put("Violation Time", temp.get(5));
			collection.insert(document);
		}

	}

	@Override
	public void save(Collection<List> data) {

	}

}
