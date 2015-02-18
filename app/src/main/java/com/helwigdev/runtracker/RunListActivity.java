package com.helwigdev.runtracker;

import android.support.v4.app.Fragment;

/**
 * Created by Tyler on 2/17/2015.
 * All code herein copyright Helwig Development 2/17/2015
 */
public class RunListActivity extends SingleFragmentActivity {



	@Override
	protected Fragment createFragment() {
		return new RunListFragment();
	}
}
