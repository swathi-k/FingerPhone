package com.farjahan.android3;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;


public class MainActivity extends TabActivity {
	private SQLiteDatabase dbReader;
	private SQLiteDatabase dbWriter;
	 TabHost tabHost;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tabHost = getTabHost();
        
        // Tab for accountInfo
        TabSpec accountPage = tabHost.newTabSpec("Account SetUp");
        accountPage.setIndicator("Account SetUp");
        Intent accountInfo = new Intent(this, Account_SetUp_Page.class);
        accountPage.setContent(accountInfo);
        
        // Tab for game
        TabSpec gamePage = tabHost.newTabSpec("Game");
        // setting Title and Icon for the Tab
        gamePage.setIndicator("Game");
        Intent game = new Intent(this, GamePage.class);
        gamePage.setContent(game);
        
        // Tab for score
        TabSpec scorePage = tabHost.newTabSpec("Score");
        scorePage.setIndicator("Score");
        Intent score = new Intent(this, ScorePage.class);
        scorePage.setContent(score); 
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(accountPage); // Adding Account SetUp tab
        tabHost.addTab(gamePage); // Adding Game tab
        tabHost.addTab(scorePage); // Adding Score tab
        
      loadDBData();
		   
        
    }
	private void loadDBData() {
		// TODO Auto-generated method stub
		
	
	MyDb zck= new MyDb(this);
    dbReader=zck.getReadableDatabase();
    dbWriter=zck.getWritableDatabase();
    Cursor cursor=dbReader.query("hw2",null,null,null,null,null,null);
    if (cursor != null) {

    	tabHost.setCurrentTab(1);
		
	}else{
		tabHost.setCurrentTab(3);
	}
	
	cursor.close();
    
	}
	}
	

		
	
