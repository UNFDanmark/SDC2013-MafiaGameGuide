package dk.unf.software.aar2013.gruppe10;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuideActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		// Go to new game
		Button B_Guide_GoToSetup = (Button) findViewById(R.id.B_Guide_GoToSetup);
		B_Guide_GoToSetup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
				startActivity(intent);
			}
		});
		
		// Go to character specific
		Button CharBtn = (Button) findViewById(R.id.B_Guide_DiscoverCharacters);
		CharBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),RoleSpecsActivity.class);
				startActivity(intent);
				
			}
		});
	}
}
