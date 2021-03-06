package com.farjahan.android3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author Farjahan Hossain Account_SetUp_Page class for user to setup player
 *         name and game speed.
 * 
 *         In this project some code are used from hw2 solution given by
 *         professor Chris Pollett
 * 
 * **/

public class Account_SetUp_Page extends Activity implements
		SeekBar.OnSeekBarChangeListener, OnClickListener {
	private int speed; // million second
	private int speedRate; // |max-min|/max_seekbar
	private EditText playerName;
	private TextView speedMessage;
	private SeekBar speedBar;
	Scores score;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accout_setup_layout);
		score = new Scores(getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE).edit());

		init();
		speedBar.setOnSeekBarChangeListener(this);
		View button = this.findViewById(R.id.button1);
		button.setOnClickListener(this);

	}

	/**
	 * Initial the variables
	 */
	private void init() {

		// play name
		playerName = (EditText) findViewById(R.id.editText1);
		// speed message
		speedMessage = (TextView) findViewById(R.id.textView2);
		// load player data from db
		loadDBData();
		// init seekBar
		speedBar = (SeekBar) findViewById(R.id.seekBar1);
		speedBar.setMax(getResources().getInteger(R.integer.max_seekbar));

		int max = getResources().getInteger(R.integer.max_game_speed);
		int min = getResources().getInteger(R.integer.min_game_speed);
		// calculate the rate of speed
		speedRate = (max - min) / speedBar.getMax();
		if (speedRate < 0) {
			speedRate = -speedRate;
		}
		setSpeed(speed);
		showSpeedMessage(speed);
	}

	/**
	 * Get Player Name and speed from database if the date is found else get
	 * data from default
	 */
	private void loadDBData() {

		playerName.setText(score.getUserName());
		speed = score.getGameSpeed();

	}

	/**
	 * Save the player name and speed into database
	 */
	private void saveDBData() {

		score.setUserName(playerName.getText() + "");
		score.setGameSpeed(speed);
	}

	/**
	 * Show the speed
	 * 
	 * @param speed
	 *            a million second integer
	 */
	private void showSpeedMessage(int speed) {
		double second = speed / 1000.0;
		String str = getString(R.string.text_game_speed) + ": " + second + " "
				+ getString(R.string.unit_second);
		speedMessage.setText(str);
	}

	/**
	 * Get the speed from speedBar
	 * 
	 * @return speed million second
	 */
	private int getSpeed() {
		return (speedBar.getMax() - speedBar.getProgress() + 1) * speedRate;
	}

	/**
	 * Set the speedBar progress with value speed
	 * 
	 * @param speed
	 *            million second
	 */
	private void setSpeed(int speed) {
		speedBar.setProgress(speedBar.getMax() - speed / speedRate + 1);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.seekBar1: {
			// get and show the speed
			speed = getSpeed();
			showSpeedMessage(speed);
			break;
		}
		default:
			Log.d("hw3", "wrong seekBar");
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void onClick(View v)
	/**
	 * Handles clicks events for this activity -- new code we added
	 * 
	 * @param v
	 *            - view that event is coming from
	 */
	{
		switch (v.getId()) {
		/*
		 * handle the case coming from thing on our Activity with id button
		 */
		case R.id.button1:
			// show a dialog
			new AlertDialog.Builder(this)
					.setTitle("Save...")
					.setMessage(R.string.text_save_question)
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									saveDBData();
									// finish();
								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											Account_SetUp_Page.this,
											GamePage.class);
									startActivity(intent);
									// finish();

								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
			break;
		}
	}

}