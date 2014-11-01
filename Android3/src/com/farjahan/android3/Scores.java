package com.farjahan.android3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.content.SharedPreferences;
import android.util.Log;

public class Scores {
	
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String currentscore;
	String livescore;
	int port;
	String ip;
	String username;
	String gamename;
	String gamespeed;
	
	public Scores(SharedPreferences sp, SharedPreferences.Editor e) {
		this.sharedPref = sp;
		this.editor = e;
		this.currentscore = "CurrentScore";
		this.livescore = "LiveScore";
		this.username = "Name";
		this.gamename = "GameName";
		this.gamespeed = "Speed";
		this.port = 7890;
		this.ip = "localhost";	//this ip
	}
	
	public int getCurrentScore() {
		return sharedPref.getInt(currentscore, 0);
	}
	
	public void setCurrentScore(int score) {
	   	editor.putInt(currentscore, score);
   		editor.commit();
   		sendGameResult();
	}

	public int getLives() {
		return sharedPref.getInt(livescore, 3);
	}

	public void setLives(int live) {
	   	editor.putInt(livescore, live);
   		editor.commit();
	}
	
	public String getUserName() {
		return sharedPref.getString(username, "");
	}
	
	public void setUserName(String name) {
		editor.putString(username, name);
		reset();
		registerName(name);
   		editor.commit();
	}
	
	public String getGameName() {
		return sharedPref.getString(gamename, "none");
	}
	
	public void setGameName(String gname) {
		editor.putString(gamename, gname);
		reset();
   		editor.commit();
	}
	
	public int getGameSpeed() {
		return sharedPref.getInt(gamespeed, 1000);
	}
	
	public void setGameSpeed(int speed) {
		editor.putInt(gamespeed, speed);
		reset();
   		editor.commit();
	}

	public void savescores() {
		int current = getCurrentScore();
		
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
	
	public String registerName(String name) {
		String output = callSocket("register:" + name);
		Log.i("calling socket data out was registerd", "calling socket data: output is " + output);
		
		return output;
	}

	public String sendGameResult() {
		Log.i("currentscore in game 3", "currentscore in game 3 " + getCurrentScore());
		return callSocket("result:" + getUserName() + "\t" + getGameName() + "\t" + getCurrentScore());
		
	}
	
	public String getStatistics(String playername) {
		return callSocket("statistics:" + playername);
	}
	
	@SuppressWarnings("resource")
	private String callSocket(String socketData) {
		Socket socket = null;
		BufferedWriter writer = null;
		BufferedReader reader =null;
		String output = null;
		Log.i("calling socket data", "calling socket data" + socketData);
		
		try{
			output="error";
			socket = new Socket(ip, port);
			output="socket error";
			
			reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			output="bufferedreader error";
			writer = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			output="bufferedwriter error";
			
			writer.write(socketData);
			output="write error";
			writer.flush();
			output="flush error";
			
			while ((output = reader.readLine()) != null) {
			    
				return output;
		    }
			
		}
		catch(Exception e){}
			return output;
		}


}