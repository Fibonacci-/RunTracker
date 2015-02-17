package com.helwigdev.runtracker;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by Tyler on 2/17/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class RunManager {
	public static final String TAG = "RunManager";

	public static final String ACTION_LOCATION = "com.helwigdev.runtracker.ACTION_LOCATION";

	private static RunManager sRunManager;
	private Context mAppContext;
	private LocationManager mLocationManager;

	//defeat external instantiation
	private RunManager(Context context){
		mAppContext = context;
		mLocationManager = (LocationManager) mAppContext.getSystemService(Context.LOCATION_SERVICE);
	}

	public static RunManager get(Context c){
		if(sRunManager == null){
			//use app context to avoid leaking activities
			sRunManager = new RunManager(c.getApplicationContext());
		}
		return sRunManager;
	}

	private PendingIntent getLocationPendingIntent(boolean shouldCreate){
		Intent broadcast = new Intent(ACTION_LOCATION);
		int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
		return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
	}

	public void startLocationUpdates() {
		String provider = LocationManager.GPS_PROVIDER;
		PendingIntent pi = getLocationPendingIntent(true);
		mLocationManager.requestLocationUpdates(provider, 0, 0, pi);
	}

	public void stopLocationUpdates(){
		PendingIntent pi = getLocationPendingIntent(false);
		if(pi != null){
			mLocationManager.removeUpdates(pi);
			pi.cancel();
		}
	}

	public boolean isTrackingRun(){
		return getLocationPendingIntent(false) != null;
	}

}
