package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

public class ParkingSource<T> implements Source<T> {

	int id;
	String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ParkingSource(int id, String url) {

		this.id = id;
		this.url = url;
	}

	public ParkingSource() {

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

		return id > 0;
	}

	@Override
	public Collection<T> next() {

		List<String> list = new ArrayList<>();
		list.add(this.url);
		this.id--;
		return (Collection<T>) list;
	}

}
