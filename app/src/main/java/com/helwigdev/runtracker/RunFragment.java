package com.helwigdev.runtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tyler on 2/17/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class RunFragment extends Fragment {
	private Button mStartButton;
	private Button mStopButton;
	private TextView mStartedTextView, mLatitudeTextView, mLongitudeTextView, mAltitudeTextView, mDurationTextView;

	private RunManager mRunManager;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_run, container, false);

		mStartedTextView = (TextView) v.findViewById(R.id.run_startedTextView);
		mLatitudeTextView = (TextView) v.findViewById(R.id.run_latitudeTextView);
		mLongitudeTextView = (TextView) v.findViewById(R.id.run_longitudeTextView);
		mAltitudeTextView = (TextView) v.findViewById(R.id.run_altitudeTextView);
		mDurationTextView = (TextView) v.findViewById(R.id.run_durationTextView);

		mStartButton = (Button) v.findViewById(R.id.run_startButton);
		mStopButton = (Button) v.findViewById(R.id.run_stopButton);

		mStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mRunManager.startLocationUpdates();
				updateUI();
			}
		});
		mStopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mRunManager.stopLocationUpdates();
				updateUI();
			}
		});


		updateUI();
		return v;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mRunManager = RunManager.get(getActivity());
	}

	private void updateUI(){
		boolean started = mRunManager.isTrackingRun();
		mStartButton.setEnabled(!started);
		mStopButton.setEnabled(started);
	}
}