package com.example.lenovo.connectionchecker;


import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTask extends AsyncTask<String, Integer, Boolean> {

	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpURLConnection urlc;
		try {
			urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
			 urlc.setRequestProperty("User-Agent", "Test");
		        urlc.setRequestProperty("Connection", "close");
		        urlc.setConnectTimeout(1500); 
		        urlc.connect();
		        return (urlc.getResponseCode() == 200);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return false;
		
	}
   
}
