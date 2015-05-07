package com.example.modernartui;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;

@SuppressLint({ "ValidFragment", "NewApi" })
public class MainActivity extends Activity {
	
	protected static final String TAG = "MainActivity";
	protected TextView    colorValue;
	protected TextView[]  colorBlocks;
	protected ColorTube[] colorTubes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SeekBar sb = (SeekBar) findViewById(R.id.seekBar1);
		
		// Don't display the color value
		colorValue = (TextView) findViewById(R.id.textView1);
		colorValue.setTextColor(Color.TRANSPARENT);
		initialColors();
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, 
					int progress, boolean fromUser) {
				updateColors(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
		});
		initialColors();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		switch(item.getItemId()) {
			case R.id.more_info:
				Log.i(TAG, "More info selected.");
					DialogFragment df = AlertDialogFragment.getInstance();
					df.show(getFragmentManager(), "Alert");
				
				
				return true;
			default:
				return false;
		}
	}
	
	public static class AlertDialogFragment extends DialogFragment {

		public static AlertDialogFragment getInstance() {
			return new AlertDialogFragment();
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(getActivity())
			.setMessage("Inspired by the works of artists such " +
					"as Piet Mondrian and Ben Nichoison\n" +
					"Click below to learn more!")
			.setCancelable(true)
			.setPositiveButton(R.string.visit_moma,	
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.i(MainActivity.TAG, "Dialog YES clicked");
						Intent intent = new Intent(getActivity(), WebActivity.class);
						startActivity(intent);
					}
				}	
				)
			.setNegativeButton(R.string.not_now, 
				new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.i(MainActivity.TAG, "Dialog NO clicked");
				
					}
				}
			)
			.create();
		}
		
	}

	void initialColors() {
		// Create references
		colorBlocks = new TextView[] {
				(TextView) findViewById(R.id.textView2),
				(TextView) findViewById(R.id.textView3),
				(TextView) findViewById(R.id.textView4),
				(TextView) findViewById(R.id.textView5),
				(TextView) findViewById(R.id.textView6),			
			};
		colorBlocks[0].getRootView().setBackgroundColor(Color.BLACK);
		
		// Define color ranges
		colorTubes = new ColorTube[] {
			ColorTube.create(),
			ColorTube.create(),
			ColorTube.create(),
			null,
			ColorTube.create()		
		};
		
		// Do a startup update
		updateColors(0);
	}
	
	void updateColors(int level) {
		// Update colors
		if(colorValue != null)
		colorValue.setText(level+"");
		for (int i = 0; i < 5; i ++) {
			if(colorTubes[i] != null)
				colorBlocks[i].setBackgroundColor(colorTubes[i].at(level));
			else
				colorBlocks[i].setBackgroundColor(Color.WHITE);
		}
	}
}