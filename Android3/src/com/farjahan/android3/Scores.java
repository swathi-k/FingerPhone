package com.farjahan.android3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.SharedPreferences;
import android.util.Log;

public class Scores {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String currentscore;
	String livescore;
	int port;
	String ip = "192.168.15.9"; // this ip
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
		editor.commit();
		registerName();
		reset();
		
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
		editor.putInt(currentscore, 0);
		editor.commit();
	}

	public String registerName() {
		Log.i("calling socket data", "client calling socket data registerName(): " + getUserName());
		String ans = callSocket("register:" + getUserName());
		Log.i("calling socket data", "server returns calling socket data registerName(): " + ans);
		return ans;
		
	}

	public String sendGameResult() {
		Log.i("calling socket data", "client returns calling socket data sendGameResult(): " + getUserName() + " gameName: " + getGameName());
		String ans = callSocket("result:" + getUserName() + "," + getGameName() + ","
				+ getCurrentScore()); 
		Log.i("calling socket data", "server returns calling socket data sendGameResult(): " + ans);
		return ans;
	}

	public String getStatistics(String playername) {
		Log.i("calling socket data", "client calling socket data getStatistics(): " + playername);
		String ans = callSocket("statistics:" + playername); 
		Log.i("calling socket data", "server returns calling socket data getStatistics(): " + ans);
		return ans;
	}

	@SuppressWarnings("resource")
	private String callSocket(String socketData) {
	Socket socket = null;
	BufferedWriter writer = null;
	BufferedReader reader =null;
	String output = "";
	
	try{
		socket = new Socket(ip, port);
		
		
		reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));
		
		writer.write(socketData);
		writer.newLine();
		writer.flush();
		
		output = "socket trying";
		while (true) {
			
			if((output = reader.readLine()) != null) {
		    
			return output;
			}
		}
//		writer.close();
//		reader.close();
//		socket.close();
	}
	catch(Exception e){
		output = "error";
		return e.getMessage();
	
	}
		
	}


}