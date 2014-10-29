package cs175.skotturu.dizphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class GamePage extends Activity implements OnClickListener{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);  
        View button1 = this.findViewById(R.id.game1);
        View button2 = this.findViewById(R.id.game2);
        View button3 = this.findViewById(R.id.game3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        
  
  
}

    /**
	Handles clicks events for this activity -- new code we added

	@param v - view that event is coming from
*/
public void onClick(View v)
{
	Intent i;
	switch(v.getId())
	{
    /* handle the case coming from thing on our Activity with id button 
    */
	case R.id.game1: 
      // launch the Game Activity
  	  i = new Intent(this,GameActivity.class); 
  	  startActivity(i);
  	  break;
	case R.id.game2: 
	      // launch the Game Activity
	  	  i = new Intent(this,Game2_Activity.class); 
	  	  startActivity(i);
	  	  break;
  	 
	}
}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        return true;
    }
    return super.onOptionsItemSelected(item);
}
}