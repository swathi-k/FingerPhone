package cs175.skotturu.dizphone;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec accountPage = tabHost.newTabSpec("Account SetUp");
        accountPage.setIndicator("Account SetUp");
        Intent accountInfo = new Intent(this, Account_SetUp_Page.class);
        accountPage.setContent(accountInfo);
        
        // Tab for Songs
        TabSpec gamePage = tabHost.newTabSpec("Game");
        // setting Title and Icon for the Tab
        gamePage.setIndicator("Game");
        Intent songsIntent = new Intent(this, GamePage.class);
        gamePage.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec scorePage = tabHost.newTabSpec("Score");
        scorePage.setIndicator("Score");
        Intent videosIntent = new Intent(this, ScorePage.class);
        scorePage.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(accountPage); // Adding photos tab
        tabHost.addTab(gamePage); // Adding songs tab
        tabHost.addTab(scorePage); // Adding videos tab
        
     
        
        
        
    }
}