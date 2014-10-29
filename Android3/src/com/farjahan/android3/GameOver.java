package com.farjahan.android3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class GameOver extends Activity {
	private int waiting_time;
	private SQLiteDatabase db;
	private TextView textScore;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        textScore=(TextView)findViewById(R.id.textView2);
        waiting_time=getResources().getInteger(R.integer.game_over_waiting);
        //read the player's score
        MyDb zck=new MyDb(this);
        db=zck.getReadableDatabase();
        Cursor cursor=db.query("hw2", null, null, null, null, null, null);
		if(cursor.moveToNext()){
			int score=cursor.getInt(cursor.getColumnIndex("score"));
			textScore.setText(getString(R.string.text_score)+ " "+score);
		}
		cursor.close();
		db.close();
		zck.close();
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
