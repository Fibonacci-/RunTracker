package com.helwigdev.runtracker;

import android.content.Context;
import android.location.Location;
import android.util.Log;

/**
 * Created by Tyler on 2/17/2015.
 * All code herein copyright Helwig Development 2/17/2015
 */
public class TrackingLocationReceiver extends LocationReceiver {
	@Override
	protected void onLocationReceived(Context context, Location loc) {
		Log.i("TrackingLocationReceive", "Got location in background OK");
		RunManager.get(context).insertLocation(loc);
	}
}
