package com.helwigdev.runtracker;

import android.support.v4.app.Fragment;

/**
 * Created by Tyler on 2/18/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class RunMapActivity extends SingleFragmentActivity {
	public static final String EXTRA_RUN_ID = "com.helwigdev.runtracker.run_id";

	@Override
	protected Fragment createFragment() {
		long runid = getIntent().getLongExtra(EXTRA_RUN_ID, -1);
		if (runid != -1) {
			return RunMapFragment.newInstance(runid);
		} else {
			return new RunMapFragment();
		}

	}
}
