package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	SharedPreferences SP;
	public int players;
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Role> roles = new ArrayList<Role>();
	public static MediaPlayer soundtrack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		SP = PreferenceManager.getDefaultSharedPreferences(this);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		roles.add(new RoleVillager());
		roles.add(new RoleMafia());
		roles.add(new RoleHealer());
		roles.add(new RoleDetective());
		roles.add(new RoleTerrorist());
		
		players = SP.getInt("players", 10); // Læser antal spillere fra SP
		
		Button B_Main_NewGame = (Button) findViewById(R.id.B_Main_NewGame);
		Log.d("derp", (B_Main_NewGame==null ? "den er null" : "intet prob her"));
		B_Main_NewGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),SetupActivity.class);
				startActivity(intent);	
			}
		});
		
		// Go to about page
		Button B_main_GoToAbout = (Button) findViewById(R.id.B_main_GoToAbout);
		B_main_GoToAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
				startActivity(intent);
				
			}
		});
		
		Button B_Main_Pref = (Button) findViewById(R.id.B_Main_Preferences);
		B_Main_Pref.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), RoleListActivity.class);
				startActivity(intent);
			}
		});
		
		
		// BACKGROUND SOUNDTRACK
		soundtrack = MediaPlayer.create(getApplicationContext(), R.raw.mgg);
		soundtrack.stop();
		soundtrack.setLooping(true);
		soundtrack.start();
	
		
		
		Button B_Main_HowToPlay = (Button) findViewById(R.id.B_Main_HowToPlay);
		B_Main_HowToPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
				startActivity(intent);
			}
		});
		
		Button B_Main_Settings = (Button) findViewById(R.id.B_Main_Preferences);
		B_Main_Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), RoleListActivity.class);
				startActivity(intent);
			}
		});
	}
}
