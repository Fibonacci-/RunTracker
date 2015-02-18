package com.helwigdev.runtracker;

import android.content.Context;
import android.location.Location;

/**
 * Created by Tyler on 2/17/2015.
 * All code herein copyright Helwig Development 2/17/2015
 */
public class LastLocationLoader extends DataLoader<Location> {
	private long mRunId;

	public LastLocationLoader(Context context, long runId){
		super(context);
		mRunId = runId;
	}

	@Override
	public Location loadInBackground() {
		return null;
	}
}
