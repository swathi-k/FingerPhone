<<<<<<< HEAD
package com.farjahan.android3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/*** ScorePage display user game score statistics ****/
public class ScorePage extends Activity {
	Scores socket, ascoket;
	Scores userName;
	TextView showStatistics;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.score_layout);

		showStatistics = (TextView) findViewById(R.id.textView1);
		socket = new Scores(getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE), getSharedPreferences("MyPREFERENCES",
				Context.MODE_PRIVATE).edit());
		String user = socket.getUserName();
		/********* Display user name ********/
		showStatistics.setText(user);

		// String gameStatistics = socket.getStatistics(user);
		// showStatistics.setText(gameStatistics);
		// Log.i("Details of ", "game " + gameStatistics);
	}
=======
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
    
    
    
    
    
>>>>>>> origin/master
}