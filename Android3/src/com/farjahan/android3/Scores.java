package com.farjahan.android3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.content.SharedPreferences;

public class Scores {
	
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String currentscore;
	String livescore;
	String port;
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
		this.port = "7890";
		this.ip = "";	//this ip
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
	
	public String getUserName() {
		return sharedPref.getString(username, "Player 1");
	}
	
	public void setUserName(String name) {
		editor.putString(username, name);
		reset();
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
		
		return callSocket("register:" + name);
	}

	public String sendGameResult(String playername, String gamename) {
		
		return callSocket("result:" + playername + "\t" + gamename + "\t" + getCurrentScore());
	}
	
	public String getStatistics(String playername) {
		return callSocket("statistics:" + playername);
	}
	
	private String callSocket(String socketData) {
		Socket socket = null;
		BufferedWriter writer = null;
		BufferedReader reader =null;
		String output = null;
		
		try{
		
			socket = new Socket(ip, Integer.parseInt(port));
			reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			writer.write(socketData);
			writer.flush();
			output = reader.readLine();
		}
		catch(Exception e){}
			return output;
		}


}
