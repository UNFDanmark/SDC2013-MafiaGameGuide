package dk.unf.software.aar2013.gruppe10;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SetupActivity extends Activity {

	
	int players;
	int mafiaCount;
	int healerCount;
	int detectiveCount;
	int terroristCount;
	
	SharedPreferences SP;
	
	TextView TV_playerCount;
	TextView TV_mafiasCount;
	TextView TV_healerCount;
	TextView TV_detectiveCount;
	TextView TV_terroristCount;
	TextView TV_villagerCount;
	
	SeekBar SB_mafiaCount;
	SeekBar SB_healerCount;
	SeekBar SB_detectiveCount;
	SeekBar SB_terroristCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		// Start game button
		Button B_Setup_Play = (Button) findViewById(R.id.B_Setup_Play);
		B_Setup_Play.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Editor e = SP.edit();
				e.putInt("mafiaCount", mafiaCount);
				e.putInt("healerCount", healerCount);
				e.putInt("detectiveCount", detectiveCount);
				e.putInt("terroristCount", terroristCount);
				e.commit();
				
				MainActivity.users.clear();
				
				MainActivity.soundtrack.stop();
				
				int playersT = SP.getInt("players", 10);
				int mafiaCountT = SP.getInt("mafiaCount", 3);
				int healerCountT = SP.getInt("healerCount", 2);
				int detectiveCountT= SP.getInt("detectiveCount", 1);
				int terroristCountT = SP.getInt("terroristCount", 1);
				
				for(int i = 0; i < playersT-mafiaCountT-healerCountT-detectiveCountT-terroristCountT; i++)
				{
					User u = new User();
					u.setName("Villager " + (i+1));
					u.setRole(new RoleVillager());
					MainActivity.users.add(u);
				}
				for(int i = 0; i < mafiaCountT; i++)
				{
					User u = new User();
					u.setName("Mafia " + (i+1));
					u.setRole(new RoleMafia());
					MainActivity.users.add(u);
				}
				for(int i = 0; i < healerCountT; i++)
				{
					User u = new User();
					u.setName("Healer " + (i+1));
					u.setRole(new RoleHealer());
					MainActivity.users.add(u);
				}
				for(int i = 0; i < detectiveCountT; i++)
				{
					User u = new User();
					u.setName("Detective " + (i+1));
					u.setRole(new RoleDetective());
					MainActivity.users.add(u);
				}
				for(int i = 0; i < terroristCountT; i++)
				{
					User u = new User();
					u.setName("Terrorist " + (i+1));
					u.setRole(new RoleTerrorist());
					MainActivity.users.add(u);
				}
				
				
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				startActivity(intent);	
			}
		});
				
		SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		players = SP.getInt("players", 10);
		TV_playerCount = (TextView) findViewById(R.id.TV_Setup_AmountOfPlayers);
		TV_playerCount.setText("" + players);
				
		Button B_playersUp = (Button) findViewById(R.id.B_Setup_IncreasePlayers);
		Button B_playersDown = (Button) findViewById(R.id.B_Setup_DecreasePlayers);
		Button B_automaticSuggestion = (Button) findViewById(R.id.B_Setup_AutoAssignRoles);
		
		SB_mafiaCount = (SeekBar) findViewById(R.id.SB_Setup_Mafias);
		SB_healerCount = (SeekBar) findViewById(R.id.SB_Setup_Healers);
		SB_detectiveCount = (SeekBar) findViewById(R.id.SB_Setup_Detective);
		SB_terroristCount = (SeekBar) findViewById(R.id.SB_Setup_Terrorist);
		
		players = SP.getInt("players", 10);
		mafiaCount = SP.getInt("mafiaCount", 3);
		healerCount = SP.getInt("healerCount", 2);
		detectiveCount= SP.getInt("detectiveCount", 1);
		terroristCount = SP.getInt("terroristCount", 1);
		
		SB_mafiaCount.setProgress(mafiaCount);
		SB_healerCount.setProgress(healerCount);
		SB_detectiveCount.setProgress(detectiveCount);
		SB_terroristCount.setProgress(terroristCount);
		
		TV_mafiasCount = (TextView) findViewById(R.id.TV_Setup_Mafia);
		TV_mafiasCount.setText(mafiaCount+" Mafias");
		
		TV_healerCount = (TextView) findViewById(R.id.TV_Setup_Healers);
		TV_healerCount.setText(healerCount + " Healers");
		
		TV_detectiveCount = (TextView) findViewById(R.id.TV_Setup_Detective);
		TV_detectiveCount.setText(detectiveCount + " Detectives");
		
		TV_terroristCount = (TextView) findViewById(R.id.TV_Setup_Terrorist);
		TV_healerCount.setText(terroristCount + " Terrorists");
		
		TV_villagerCount = (TextView) findViewById(R.id.TV_Setup_VillagersCount);
		TV_villagerCount.setText(""+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
		
		updateSeekBars();
		
		
		
		B_playersUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SetPlayerCount(1);				
			}
		});
		B_playersDown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				SetPlayerCount(-1);
			}
		});
		B_automaticSuggestion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				spreadPlayers();				
			}
		});
		
		SB_mafiaCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(SB_mafiaCount.getProgress() > mafiaCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount))
				{
					SB_mafiaCount.setProgress(mafiaCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
				}
				updateSeekBars();				
			}
		});
		SB_healerCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(SB_healerCount.getProgress() > healerCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount))
				{
					SB_healerCount.setProgress(healerCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
				}
				updateSeekBars();
				
			}
		});
		SB_detectiveCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(SB_detectiveCount.getProgress() > detectiveCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount))
				{
					SB_detectiveCount.setProgress(detectiveCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
				}
				updateSeekBars();
				
			}
		});
		SB_terroristCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(SB_terroristCount.getProgress() > terroristCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount))
				{
					SB_terroristCount.setProgress(terroristCount+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
				}
				updateSeekBars();
				
			}
		});
	}
	
	private void spreadPlayers() // Sprede spillerne ligeligt over de forskellige roller i forhold til hvor mange spillere der er med.
	{
		SB_mafiaCount.setProgress(0);
		SB_healerCount.setProgress(0);
		SB_detectiveCount.setProgress(0);
		SB_terroristCount.setProgress(0);
		
		if(players < 4)
		{
			SB_mafiaCount.setProgress(1);
			SB_healerCount.setProgress(1);
			SB_detectiveCount.setProgress(0);
			SB_terroristCount.setProgress(0);
		}
		else if(players == 4)
		{
			SB_mafiaCount.setProgress(2);
			SB_healerCount.setProgress(1);
			SB_detectiveCount.setProgress(1);
			SB_terroristCount.setProgress(0);
		}
		else if(players > 4 && players <= 7)
		{
			SB_mafiaCount.setProgress(2);
			SB_healerCount.setProgress(1);
			SB_detectiveCount.setProgress(1);
			SB_terroristCount.setProgress(1);
		}
		else if(players > 7 && players <= 12)
		{
			SB_mafiaCount.setProgress(3);
			SB_healerCount.setProgress(2);
			SB_detectiveCount.setProgress(1);
			SB_terroristCount.setProgress(1);
		}
		else if(players > 12 && players < 20)
		{
			SB_mafiaCount.setProgress(4);
			SB_healerCount.setProgress(3);
			SB_detectiveCount.setProgress(2);
			SB_terroristCount.setProgress(2);
		}
		else
		{
			SB_mafiaCount.setProgress(4 + (int)(((double)(players-20)/8)));
			SB_healerCount.setProgress(3 + (int)(((double)(players-20)/8)));
			SB_detectiveCount.setProgress(2 + (int)(((double)(players-20)/8)));
			SB_terroristCount.setProgress(2 + (int)(((double)(players-20)/8)));
		}
		updateSeekBars();
	}
	
	/**
	 * Will set the player count while still obey the rules and displaying toast's if it gets too low.
	 * @param difference
	 * How much you want to change the player count, can be negative and positive.
	 */
	private void SetPlayerCount(int difference)
	{
		if(players <= 1 && difference < 0)
		{
			players = 1;
		}
		else
		{
			players += difference;
		}
		if(players == 7)
		{
			Toast.makeText(getApplicationContext(), "It is recommended that you are atleast 8 or more players for this game", Toast.LENGTH_LONG).show();
		}
		TV_playerCount.setText("" + players);
		Editor e = SP.edit();
		e.putInt("players", players);
		e.commit();
		updateSeekBars();
		
	}
	
	private void updateSeekBars()
	{
		mafiaCount = SB_mafiaCount.getProgress();
		healerCount = SB_healerCount.getProgress();
		detectiveCount = SB_detectiveCount.getProgress();
		terroristCount = SB_terroristCount.getProgress();
		
		TV_villagerCount.setText(""+(players-mafiaCount-healerCount-detectiveCount-terroristCount));
		
		TV_mafiasCount.setText(mafiaCount + " Mafias");
		TV_healerCount.setText(healerCount + " Healers");
		TV_detectiveCount.setText(detectiveCount + " Detectives");
		TV_terroristCount.setText(terroristCount + " Terrorists");
		
		SB_mafiaCount.setMax(players);
		SB_healerCount.setMax(players);
		SB_detectiveCount.setMax(players);
		SB_terroristCount.setMax(players);
		
		Editor e = SP.edit();
		e.putInt("mafiaCount", mafiaCount);
		e.putInt("healerCount", healerCount);
		e.putInt("detectiveCount", detectiveCount);
		e.putInt("terroristCount", terroristCount);
		e.commit();

		//SB_mafiaCount.setProgress(SP.getInt("mafiasCount", 0));
		//SB_healerCount.setProgress(SP.getInt("healerCount", 0));
		//SB_detectiveCount.setProgress(SP.getInt("detectiveCount", 0));
		//SB_terroristCount.setProgress(SP.getInt("terroristCount", 0));
		
	}
}
