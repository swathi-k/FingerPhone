package com.farjahan.android3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class GameOver extends Activity {
	private int waiting_time;
	private TextView textScore;
	Scores score;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        textScore=(TextView)findViewById(R.id.textView2);
        waiting_time=getResources().getInteger(R.integer.game_over_waiting);
        //read the player's score
		score = new Scores(getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE).edit());
		textScore.setText(score.getCurrentScore());
        
        gameEnd();
	}

	/**
	 * Wait for 10 second to return start page
	 */
	private void gameEnd(){
		//init timer
		CountDownTimer timer=new CountDownTimer(waiting_time, 1000){

			@Override
			public void onFinish() {
				// 
				finish();
			}

			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		timer.start();

	}

}
