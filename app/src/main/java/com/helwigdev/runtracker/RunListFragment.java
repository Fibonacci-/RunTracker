package com.helwigdev.runtracker;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Tyler on 2/17/2015.
 * All code herein copyright Helwig Development 2/17/2015
 */
public class RunListFragment extends ListFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {
	private static final int REQUEST_NEW_RUN = 0;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.run_list_options, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.menu_item_new_run:
				Intent i = new Intent(getActivity(), RunActivity.class);
				startActivityForResult(i, REQUEST_NEW_RUN);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//the id argument will be the Run ID, CursorAdapter gives us this. How kind.
		Intent i = new Intent(getActivity(), RunActivity.class);
		i.putExtra(RunActivity.EXTRA_RUN_ID, id);
		startActivity(i);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_NEW_RUN){
			getLoaderManager().restartLoader(0,null,this);
		}
	}

	@Override
	public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new RunListCursorLoader(getActivity());
	}

	@Override
	public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
		RunCursorAdapter adapter = new RunCursorAdapter(getActivity(), (RunDatabaseHelper.RunCursor)data);
		setListAdapter(adapter);
	}

	@Override
	public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
		setListAdapter(null);
	}


	private static class RunListCursorLoader extends SQLiteCursorLoader{

		public RunListCursorLoader(Context context){
			super(context);
		}

		@Override
		protected Cursor loadCursor() {
			return RunManager.get(getContext()).queryRuns();
		}
	}

	private static class RunCursorAdapter extends CursorAdapter{
		private RunDatabaseHelper.RunCursor mRunCursor;

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public RunCursorAdapter(Context context, RunDatabaseHelper.RunCursor runCursor){
			super(context, runCursor, 0);
			mRunCursor = runCursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			Run run = mRunCursor.getRun();

			TextView startDateTextView = (TextView) view;
			String cellText = context.getString(R.string.cell_text, run.getStartDate());

			startDateTextView.setText(cellText);
		}
	}
}
