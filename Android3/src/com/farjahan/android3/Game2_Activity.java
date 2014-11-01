package com.farjahan.android3;

/*@author Farjahan Hossain */
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Game2_Activity is second game class. Game 2 matches phone orientation with
 * display random string. If orientation and display string are same game will
 * display brush or Sipke hair style image. In this project some code are used
 * from hw2 solution given by professor Chris Pollett
 */
public class Game2_Activity extends Activity implements OnClickListener {
	final int PORTRAIT = 0;
	final int LANDSCAPE = 1;
	private int lives;
	private int score;
	private int highScore;
	private int orientation; // 0-Portrait, 1-Landscape
	private int speed; // million second
	private TextView textLives;
	private TextView textScore;
	private TextView textOrientation;
	private boolean hitting; // true: the player click a right Orientation, else
								// false
	private boolean clickDone; // click the "I'm here" button or not
	Scores savedscores;

	private Handler handler = new Handler();

	/**
	 * To start play a game
	 */
	private Runnable gamePlay = new Runnable() {

		@Override
		public void run() {
			if (lives > 0) {
				// reset
				clickDone = false;
				hitting = false;
				orientation = getRandomOrientation();
				showMessage();
				// wait for time out
				handler.postDelayed(gameTimeout, speed);
			} else {
				gameOver();
			}
		}

	};

	/**
	 * Handle the game time out
	 */
	private Runnable gameTimeout = new Runnable() {

		@Override
		public void run() {
			if (!clickDone) {
				// the user doesn't click the button
				clickDone = true; // assume player click since time out
				lives--;
				showMessage();
				gameStart();
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_game_2);
		init();
		gameStart();
	}

	/**
	 * Initialize
	 */
	private void init() {
		textScore = (TextView) findViewById(R.id.game2Score2);
		textLives = (TextView) findViewById(R.id.game2lives2);
		textOrientation = (TextView) findViewById(R.id.game2TextView2);
		Button button1 = (Button) findViewById(R.id.game2button2);
		button1.setOnClickListener(this);
		// initialize variable
		lives = getResources().getInteger(R.integer.max_lives);
		score = 0;
		highScore = 0;
		// orientation=0;
		speed = getResources().getInteger(R.integer.default_game_speed);
		hitting = false;
		// load highScore, speed from database
		savedscores = new Scores(getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE).edit());

		speed = savedscores.getGameSpeed();
		savedscores.setGameName("Game2");
	}

	/**
	 * Start the game
	 */
	private void gameStart() {
		// wait for 0.5 second to start a new game
		handler.postDelayed(gamePlay, 500);
	}

	/**
	 * The game over save data load game_over activity
	 */
	private void gameOver() {

		savedscores.setCurrentScore(score);

		Intent i = new Intent(this, GameOver.class);
		startActivity(i);
		finish();
	}

	/**
	 * Generate a random orientation
	 * 
	 * @return an integer (0-Portrait, 1-Landscape)
	 */
	private int getRandomOrientation() {
		// 0-Portrait, 1-Landscape
		Random random = new Random();
		return random.nextInt(1000) % 2;

	}

	/**
	 * Show the message and orientation on screen
	 */
	private void showMessage() {
		textScore.setText(getString(R.string.text_score2) + " " + score);
		textLives.setText(getString(R.string.text_lives2) + " " + lives);
		if (hitting) {
			// don't show textOrientation
			textOrientation.setText("");
		} else {
			if (orientation == PORTRAIT) {
				textOrientation.setText(R.string.text_Portrait);
			} else {
				textOrientation.setText(R.string.text_landscape);
			}
			if (!clickDone)
				textOrientation.setTextColor(Color.BLACK); // new game
			else
				textOrientation.setTextColor(Color.RED); // miss, warm the
															// player
		}

	}

	/*
	 * handle the case coming from thing on our Activity with id button
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		 * handle the case coming from thing on our Activity with id button
		 */
		case R.id.game2button2:
			if (!clickDone) {
				clickDone = true;
				ImageView view = (ImageView) findViewById(R.id.game2imageView);
				TextView Title = (TextView) findViewById(R.id.title);
				// stop game time out timer
				handler.removeCallbacks(gameTimeout);
				// check if the player hit the right orientation
				int current = getResources().getConfiguration().orientation;
				// Log.d("hw3", ""+current+" or:"+orientation );
				if (current == Configuration.ORIENTATION_PORTRAIT
						&& orientation == PORTRAIT) {
					view.setImageResource(R.drawable.brush_teeth);
					Title.setText(R.string.Upsidedown);
					hitting = true;

				} else if (current == Configuration.ORIENTATION_LANDSCAPE
						&& orientation == LANDSCAPE) {
					view.setImageResource(R.drawable.spike);
					Title.setText(R.string.landscape);
					hitting = true;
				}

				if (hitting) {
					score++;
					if (score > highScore) {
						highScore = score;
					}
				} else {
					lives--;
				}
				showMessage();
				// restart the game
				gameStart();
			}
			break;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Log the change of orientation
		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.i("hw3", "ORIENTATION_PORTRAIT");
		} else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.i("hw3", "ORIENTATION_LANDSCAPE");
		} else {
			Log.i("hw3", "ORIENTATION_OTHERS");
		}

	}

}