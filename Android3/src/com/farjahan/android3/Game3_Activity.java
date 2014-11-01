package com.farjahan.android3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Game3_Activity extends Activity {
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	final Context context = this;
	private Handler h = new Handler();
	Button right_button;
	Button left_button;
	Scores score;
	String username;
	private Runnable run = new Runnable(){
	    public void run(){
	        //do something
	    	
	    	guess();
	    	playerguess("");
	    }
	};
	    
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		sharedPref = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
		editor = sharedPref.edit();
		
			
		score = new Scores(getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE).edit());
		
		score.setGameName("Game3");

		setContentView(R.layout.start_game_portrait);
			
		start();
		h.postDelayed(run, score.getGameSpeed());
		
	}

	
	public void addListenerOnButton() {

		right_button = (Button) findViewById(R.id.RightButton);
		left_button = (Button) findViewById(R.id.LeftButton);
		right_button.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {


				playerguess("Right");
				guess();	//right button clicked
			}

			
		});
		left_button.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				
				playerguess("Left");
				guess();	//left button clicked
			}
		});
	}

	private void start() {
			addListenerOnButton();
	}

	private void display() {
		//show CurrentScore
		TextView currentScore = (TextView) findViewById(R.id.CurrentScore);
		currentScore.setText(getString(R.string.CurrentScore) + score.getCurrentScore());
		
		//show lives left
		TextView lives = (TextView) findViewById(R.id.LivesLeft);
		lives.setText(getString(R.string.LivesLeft) + score.getLives());
		
		//show game label
		TextView game = (TextView) findViewById(R.id.GameLabel);
   		game.setText(getGameLabel());
	}

	private void playerguess(String guess)
	{
		
		if(getInstruction() && guess.equals("right"))
		{
			score.incCurrentScore();
		}
		else if (!getInstruction() && guess.equals("left"))
		{
			score.incCurrentScore();
		}
		else
			score.decLives();
	}
	
	private boolean getInstruction() {
		//@TODO change "Portrait" to enum
		if(getGameLabel().equals("Click on Right"))
			return true;
		else
			return false;
	}


	private void guess() {
		if(keepgoing())
		{
			changeGameLabel();
			h.postDelayed(run, score.getGameSpeed());
		}
		else
		{
			score.savescores();
			//start intent GameOver
			score.reset();
			Intent intent = new Intent(context, GameOver.class);
			startActivity(intent);
			finish();
		}
	}


	private boolean keepgoing() {
		if(score.getLives() < 1)
			return false;
		else
			return true;
	}

	private String getGameLabel() {
		return sharedPref.getString(getString(R.string.GameLabel1), "Click on Right");
	}
	
	private void changeGameLabel() {
		if( Math.random() < 0.5)
			setGameLabel("Click on Right");
		else
			setGameLabel("Click on Left");
		display();
	}
	
	private void setGameLabel(String orien) {
	   	editor.putString(getString(R.string.GameLabel1), orien);
   		editor.commit();
   		TextView game = (TextView) findViewById(R.id.GameLabel);
   		game.setText(orien);
	}
	
}