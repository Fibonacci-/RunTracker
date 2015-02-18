package com.helwigdev.runtracker;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Tyler on 2/18/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class LocationListCursorLoader extends SQLiteCursorLoader {
	private long mRunId;

	public LocationListCursorLoader(Context c, long runId){
		super(c);
		mRunId = runId;
	}
	@Override
	protected Cursor loadCursor() {
		return RunManager.get(getContext()).queryLocationsForRun(mRunId);
	}
}
