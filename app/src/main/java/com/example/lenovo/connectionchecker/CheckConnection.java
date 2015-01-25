package com.example.lenovo.connectionchecker;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.concurrent.ExecutionException;



public class CheckConnection extends Service {
	private static final int mId=110558;
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	         = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	  
	    NetworkInfo activeNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	    if (activeNetworkInfo !=null){
	    if (activeNetworkInfo.isConnectedOrConnecting()){
	
	    	 return true;
	      }
	    }
	 return false;
	}
	
	public  boolean hasActiveInternetConnection() {
	
	
            MyTask t=new MyTask();
             t.execute("") ;
             try {
				return t.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    return false;
	}
	
	
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      
    	try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e1) {
		
		}
    	if (this.isNetworkAvailable()){
    		  String res="No Internet Connection";
    	        int pic=R.drawable.w;
    	if(hasActiveInternetConnection()){
    	   res="Connected";
    	   pic=R.drawable.c;
    	}
    	 NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(pic)
    	        .setContentTitle("Connection Status")
    	        .setContentText(res);
    	
  
    	mBuilder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0));
    	NotificationManager mNotificationManager =
    	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    	mNotificationManager.notify(mId, mBuilder.build());
    	}
        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // I want to restart this service again in one hour
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
       alarm.set(
        		AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + (1000 * 60 * 15),
              PendingIntent.getService(this, 0, new Intent(this, CheckConnection.class), 0)
        );
    }
}
