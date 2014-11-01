package com.farjahan.android3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ScorePage extends Activity {
	Scores savedscores;
	TextView tv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        savedscores = new Scores(getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE).edit());
        tv = (TextView)findViewById(R.id.resultsStats);
        tv.setText(savedscores.getStatistics(savedscores.getUserName()));
        
    }
    
    
    
    
    
}