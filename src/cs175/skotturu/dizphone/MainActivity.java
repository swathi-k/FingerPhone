package cs175.skotturu.dizphone;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	Button start1button;
	Button start2button;
	Button start3button;
    Button settingsbutton;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnStartGameButton();
        addListenerOnSettingsButton();
    }
    
	public void addListenerOnStartGameButton() {

		final Context context = this;

		start1button = (Button) findViewById(R.id.start_game1_button);

		start1button.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, StartGameActivity.class);
				startActivity(intent);
				finish();
			}

		});
		
		start2button = (Button) findViewById(R.id.start_game2_button);

		start2button.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				//@TODO change StartGameActivity.class
				Intent intent = new Intent(context, StartGameActivity.class);
				startActivity(intent);
				finish();
			}

		});
		
		start3button = (Button) findViewById(R.id.start_game3_button);

		start3button.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				//@TODO change StartGameActivity.class
				Intent intent = new Intent(context, StartGameActivity.class);
				startActivity(intent);
				finish();
			}

		});

	}
	
	public void addListenerOnSettingsButton() {

		final Context context = this;

		settingsbutton = (Button) findViewById(R.id.settings_button);

		settingsbutton.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, SettingsActivity.class);
				startActivity(intent);
				finish();
			}

		});

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
