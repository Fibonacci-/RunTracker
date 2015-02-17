package com.helwigdev.runtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Tyler on 2/17/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class LocationReceiver extends BroadcastReceiver {
	private static final String TAG = "LocationReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		//if we have a Location extra, use it
		Location loc = (Location)intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
		if(loc != null){
			onLocationReceived(context,loc);
			return;
		}

		//something else is going on
		if(intent.hasExtra(LocationManager.KEY_PROVIDER_ENABLED)){
			boolean enabled = intent.getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED, false);
			onProviderEnabledChanged(enabled);
		}

	}

	private void onProviderEnabledChanged(boolean enabled) {
		//TODO
	}

	private void onLocationReceived(Context context, Location loc) {
		//TODO
	}
}
