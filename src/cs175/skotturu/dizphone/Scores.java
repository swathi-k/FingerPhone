package cs175.skotturu.dizphone;

import android.content.Context;
import android.content.SharedPreferences;

public class Scores {
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String highscore;
	String currentscore;
	String livescore;
	
	
	public Scores(SharedPreferences sp, SharedPreferences.Editor e, String h, String c, String l) {
		this.sharedPref = sp;
		this.editor = e;
		this.highscore = h;
		this.currentscore = c;
		this.livescore = l;
	}
	
	
	public int getHighScore() {
		return sharedPref.getInt(highscore, 0);
	}
	
	public void setHighScore(int score) {
	   	editor.putInt(highscore, score);
   		editor.commit();
	}
	
	public int getCurrentScore() {
		return sharedPref.getInt(currentscore, 0);
	}
	
	public void setCurrentScore(int score) {
	   	editor.putInt(currentscore, score);
   		editor.commit();
	}

	public int getLives() {
		return sharedPref.getInt(livescore, 3);
	}

	public void setLives(int live) {
	   	editor.putInt(livescore, live);
   		editor.commit();
	}

	public void savescores() {
		int current = getCurrentScore();
		
		if(current > getHighScore())
    	   	setHighScore(current);
	}

	public void incCurrentScore() {
		int current = getCurrentScore();
		current++;
		setCurrentScore(current);
	}

	public void decLives() {
		int live = getLives();
		live--;
		setLives(live);
	}
	
	public void reset() {
		setLives(3);
		setCurrentScore(0);
	}

}
