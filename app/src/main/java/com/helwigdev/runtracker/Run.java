package com.helwigdev.runtracker;

import java.util.Date;

/**
 * Created by Tyler on 2/17/2015.
 * Copyright 2015 by Tyler Helwig
 */
public class Run {
	private Date mStartDate;
	private long mId;

	public Run(){
		mId = -1;
		mStartDate = new Date();
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public Date getStartDate() {
		return mStartDate;
	}

	public void setStartDate(Date startDate) {
		mStartDate = startDate;
	}

	public int getDurationSeconds(long endMillis){
		return (int)((endMillis - mStartDate.getTime()) / 1000);
	}

	public static String formatDuration(int durationSeconds){
		int seconds = durationSeconds % 60;
		int minutes = ((durationSeconds - seconds) / 60) % 60;
		int hours = (durationSeconds - (minutes * 60) - seconds) / 3600;

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
