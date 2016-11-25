package dk.unf.software.aar2013.gruppe10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateRoleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_role);
		
		Button B_CreateRole = (Button) findViewById(R.id.B_Settings_CreateRole_Confirm);
		final EditText  ET_RoleName = (EditText) findViewById(R.id.ET_Settings_CreateRole_RoleName);
		final EditText ET_RoleDesc = (EditText) findViewById(R.id.ET_Settings_CreateRole_RoleDesc);
		final Spinner Spinner_Team = (Spinner) findViewById(R.id.Spinner_Settings_CreateRole_TeamSelect);
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new String[]{"Good","Evil","Other"});
		Spinner_Team.setAdapter(spinnerAdapter);
		
		final CheckBox CBTeamTurns = (CheckBox) findViewById(R.id.CB_Settings_CreateRole_WakeUpSeparately);
		final CheckBox CBFirstNight = (CheckBox) findViewById(R.id.CB_Settings_CreateRole_WakeFirstNightOnly);
		
		B_CreateRole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Role tempRole = new Role();
				tempRole.setRoleName(ET_RoleName.getText().toString());
				tempRole.setRoleDescribtion(ET_RoleDesc.getText().toString());
				
				if(Spinner_Team.getSelectedItem().toString().equals("Good"))
				{
					tempRole.setStandardTeam(0);
				}
				else if(Spinner_Team.getSelectedItem().toString().equals("Evil"))
				{
					tempRole.setStandardTeam(1);
				}
				else
				{
					tempRole.setStandardTeam(2);
				}
				
				tempRole.setTeamTurns(CBTeamTurns.isActivated());
				tempRole.setWakesFirstNightOnly(CBFirstNight.isActivated());
				
				MainActivity.roles.add(tempRole);
				finish();
			}
		});
		
	}

}
