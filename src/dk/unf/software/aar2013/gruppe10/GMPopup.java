package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class GMPopup extends Dialog {

	public int position;
	private Context context;
	
	public GMPopup(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamemasterview);
		
		final Spinner RoleChanger = (Spinner) findViewById(R.id.Spinner_GMView_RoleChanger);
		ArrayList<String> Roles = new ArrayList<String>();
		for(int i = 0; i < MainActivity.roles.size(); i++)
		{
			Roles.add(MainActivity.roles.get(i).getRoleName());
		}
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, Roles);
		RoleChanger.setAdapter(spinnerAdapter);
		RoleChanger.setSelection(Roles.indexOf(MainActivity.users.get(position).getRole().getRoleName()));
		
		RoleChanger.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				MainActivity.users.get(position).setRole(Role.findRoleByName(RoleChanger.getItemAtPosition(arg2).toString()));
				((GameActivity)context).adapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button GMChangeName = (Button) findViewById(R.id.B_GMView_ChangeName);
		GMChangeName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(context);

				alert.setTitle("Enter name");
				alert.setMessage("Please enter a new name for the person below:");

				// Set an EditText view to get user input 
				final EditText input = new EditText(context);
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				  String value = input.getText().toString();
				  	MainActivity.users.get(position).setName(value);
					((GameActivity)context).adapter.notifyDataSetChanged();
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				  }
				  
				});
				alert.show();
			}
		});
		
		final Button GMKill = (Button) findViewById(R.id.B_GMView_Kill);
		
		if(MainActivity.users.get(position).isStatus())
        {
        	GMKill.setText("Kill");
        	//Status.setColorFilter(Color.GREEN);
        }
        else
        {
        	GMKill.setText("Revive");
        	//Status.setColorFilter(Color.RED);
        }
		
		GMKill.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {						
				MainActivity.users.get(position).setStatus(!MainActivity.users.get(position).isStatus());
	            if(MainActivity.users.get(position).isStatus())
	            {
	            	GMKill.setText("Kill");
	            	//Status.setColorFilter(Color.GREEN);
	            }
	            else
	            {
	            	GMKill.setText("Revive");
	            	//Status.setColorFilter(Color.RED);
	            }
	            ((GameActivity)context).adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	
	
	
	
}
