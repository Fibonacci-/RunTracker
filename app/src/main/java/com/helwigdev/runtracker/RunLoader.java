package com.helwigdev.runtracker;

import android.content.Context;

/**
 * Created by Tyler on 2/17/2015.
 * All code herein copyright Helwig Development 2/17/2015
 */
public class RunLoader extends DataLoader<Run> {
	private long mRunId;

	public RunLoader(Context context, long runId){
		super(context);
		mRunId = runId;
	}

	@Override
	public Run loadInBackground() {
		return RunManager.get(getContext()).getRun(mRunId);
	}
}
