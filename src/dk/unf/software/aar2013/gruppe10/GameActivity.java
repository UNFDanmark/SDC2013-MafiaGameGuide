package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GameActivity extends Activity {

	public ListView lv;
    UserAdapter adapter;
    SharedPreferences SP;
    private Context context;
    int currentTurn = 1;
    TextView statusView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		context = this;
		SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		statusView = (TextView) findViewById(R.id.TV_Setup_Game_Status);
		
		
		//MainActivity.users.clear();
		
		lv = (ListView) findViewById(R.id.LV_Setup_Game_Player1);
        adapter = new UserAdapter(this,R.id.LV_Setup_Game_Player1,MainActivity.users);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				GMPopup pop = new GMPopup(context);
				pop.position = position;
				pop.setTitle("Options:");
				pop.show();
			}
		});
        
        final Button continueButton = (Button) findViewById(R.id.B_Setup_Game_Confirm);
        
        continueButton.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				continueButton.setText("Next Turn");
				ArrayList<Role> rolelistTemp = Role.createTurnList();
				if(currentTurn < Role.getTotalTurns())
				{
					if(rolelistTemp.get(currentTurn).getRoleName().equals("Villager"))
					{
						statusView.setText("The whole town");
					}
					else
					{
						if(statusView.getText().equals(rolelistTemp.get(currentTurn).getRoleName()))
						{
							statusView.setText(rolelistTemp.get(currentTurn).getRoleName() + " again");
						}
						else
						{
							statusView.setText(rolelistTemp.get(currentTurn).getRoleName());
						}
					}
					currentTurn++;
				}
				else
				{
					currentTurn = 0;
				}
			}
		});
	}
}
