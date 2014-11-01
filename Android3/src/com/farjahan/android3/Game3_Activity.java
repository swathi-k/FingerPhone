package com.farjahan.android3;

/**
 * 
 * @author Farjahan Hossain*/
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/* Game3_Activity is 3rd game class. Game 3 matches is turn off and on light bulb.
 * 
 *  In this project some code are used from hw2 solution given by
 *  professor Chris Pollett
 */

public class Game3_Activity extends Activity implements OnClickListener {
	final int ON = 0;
	final int OFF = 1;
	private int lives;
	private int score;
	private int highScore;
	private String Title; // 0-Portrait, 1-Landscape
	private int speed; // million second
	private TextView textLives;
	private TextView textScore;
	private TextView textOrientation;
	private boolean hitting; // true: the player click a right Orientation, else

	private boolean imageValue;
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
				Title = getRandomOrientation();
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

		setContentView(R.layout.activity_game_3);
		init();
		gameStart();
	}

	/**
	 * Initialize
	 */
	private void init() {
		textScore = (TextView) findViewById(R.id.game3Score3);
		textLives = (TextView) findViewById(R.id.game3lives3);
		textOrientation = (TextView) findViewById(R.id.game3TextView3);
		Button button1 = (Button) findViewById(R.id.RightButton);
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
		savedscores.setGameName("Game3");
	}

	/**
	 * Start the game
	 */
	private void gameStart() {
		// wait for 0.7 second to start a new game
		handler.postDelayed(gamePlay, 700);
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
	/*
	 * private int getRandomOrientation(){ //0-Portrait, 1-Landscape Random
	 * random=new Random(); return random.nextInt(1000)%2;
	 * 
	 * 
	 * }
	 */

	private String getRandomOrientation() {
		final String[] arr = { "ON", "OFF" };
		Random random = new Random();
		final int select = random.nextInt(arr.length);
		String result = arr[select];
		return result;

	}

	/**
	 * Show the message and orientation on screen
	 */
	private void showMessage() {
		textScore.setText(getString(R.string.text_score3) + " " + score);
		textLives.setText(getString(R.string.text_lives3) + " " + lives);
		ImageView view = (ImageView) findViewById(R.id.offBlub);
		if (hitting) {
			// don't show textOrientation
			textOrientation.setText("");
		} else {
			if (Title.equals("ON")) {
				textOrientation.setText(R.string.text_lightOn);
				view.setImageResource(R.drawable.blub);
				imageValue = true;
			} else {
				textOrientation.setText(R.string.text_lightOff);
				view.setImageResource(R.drawable.trun_on_blub);
				imageValue = true;
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
		case R.id.RightButton:
			if (!clickDone) {
				clickDone = true;
				ImageView view = (ImageView) findViewById(R.id.offBlub);
				TextView blubTitle = (TextView) findViewById(R.id.blubTitle);
				handler.removeCallbacks(gameTimeout);

				if (Title.equals("ON") && imageValue == true) {
					view.setImageResource(R.drawable.trun_on_blub);
					blubTitle.setText(R.string.Off);
					hitting = true;
				} else if (Title.equals("OFF") && imageValue == true) {
					view.setImageResource(R.drawable.blub);
					blubTitle.setText(R.string.On);
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

}