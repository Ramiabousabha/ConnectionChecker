package com.example.lenovo.connectionchecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		context.startService(new Intent(context, CheckConnection.class));
	}

}
