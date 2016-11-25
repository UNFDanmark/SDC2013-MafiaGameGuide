package dk.unf.software.aar2013.gruppe10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class RoleListActivity extends Activity {

	Role currentRole;
	int position;
	ArrayList<String> rolelist;
	ArrayAdapter<String> AA;
	ListView LV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_role_list);
		
		Button B_NewRole = (Button) findViewById(R.id.B_RoleList_AddRole);
		Button B_RemoveRole = (Button) findViewById(R.id.B_RoleList_RemoveRole);
		Button B_EditRole = (Button) findViewById(R.id.B_RoleList_EditRole);
		Button B_MoveUp = (Button) findViewById(R.id.B_RoleList_MoveUp);
		Button B_MoveDown = (Button) findViewById(R.id.B_RoleList_MoveDown);
		
		rolelist = Role.allRoleNames();
		
		LV = (ListView) findViewById(R.id.ListView_RoleList);
		AA = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item,rolelist);
		LV.setAdapter(AA);
		AA.notifyDataSetChanged();
		
		LV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				((TextView)findViewById(R.id.TESTTEXTVIEW)).setText(Role.allRoleNames().get((int) arg3));
				currentRole = Role.findRoleByName((rolelist.get((int) arg3)));
				position = (int) arg3;
			}
		});
		
		B_NewRole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RoleListActivity.this,CreateRoleActivity.class);
				startActivity(intent);
				AA.notifyDataSetChanged();
			}
		});
		
		B_RemoveRole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.roles.remove(position);
				//rolelist.clear();
				rolelist.remove(position);
				//AA.addAll(rolelist);
				AA.notifyDataSetChanged();
			}
		});
		
		B_EditRole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		B_MoveUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.swap(MainActivity.roles, position, position-1);
				//rolelist.clear();
				Collections.swap(rolelist, position, position-1);
				AA.notifyDataSetChanged();
			}
		});
		
		B_MoveDown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.swap(MainActivity.roles, position, position+1);
				//rolelist.clear();
				Collections.swap(rolelist, position, position+1);
				AA.notifyDataSetChanged();
			}
		});
		
		
	}
}
