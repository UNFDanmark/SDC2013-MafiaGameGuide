package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapter extends ArrayAdapter<User>
{

	private int position;
    private Context context;
    private UserAdapter currentAdapter;
    
	public UserAdapter(Context context, int resource, ArrayList<User> objects) {
		super(context, resource, objects);
        this.context = context;
        currentAdapter = this;
	}
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.user_list_layout, null);
	            
	            this.position = position;
	 
	            TextView TV_role = (TextView) v.findViewById(R.id.UserListLayout_Role);
	            final TextView TV_name = (TextView) v.findViewById(R.id.UserListLayout_Name);
	            final TextView TV_status = (TextView) v.findViewById(R.id.TV_UserList_Status);
	            ImageView profileImage = (ImageView) v.findViewById(R.id.imageView1);
	            //final Button B_Kill = (Button) v.findViewById(R.id.B_Setup_Game_Kill);
	            //final Button B_Change = (Button) v.findViewById(R.id.B_UserListLayout_Change);
	            
	            //final ImageView Status = (ImageView) v.findViewById(R.id.Image_UserListLayout_Status);
	            
	            /*if(MainActivity.users.get(position).isStatus())
	            {
	            	//B_Kill.setText("Kill");
	            	Status.setColorFilter(Color.GREEN);
	            }
	            else
	            {
	            	//B_Kill.setText("Revive");
	            	Status.setColorFilter(Color.RED);
	            }*/
	            
	            TV_name.setText(MainActivity.users.get(position).getName());
	            TV_role.setText(MainActivity.users.get(position).getRole().getRoleName());
				
		            if(MainActivity.users.get(position).isStatus())
		            {
		            	TV_status.setText("Alive");
		            	TV_status.setTextColor(Color.rgb(22, 191, 00));
		            }
		            else
		            {
		            	TV_status.setText("Dead");
		            	TV_status.setTextColor(Color.rgb(224, 00, 00));
		            }
		            
		            if(MainActivity.users.get(position).getRole().getStandardTeam() == 0)
		            {
		            	v.setBackgroundColor(Color.WHITE);
		            }
		            else if(MainActivity.users.get(position).getRole().getStandardTeam() == 1)
		            {
		            	v.setBackgroundColor(Color.LTGRAY);
		            }
		            else
		            {
		            	v.setBackgroundColor(Color.YELLOW);
		            }
		            
		        profileImage.setImageResource(MainActivity.users.get(position).getRole().profilePic);
	            
	            /*B_Change.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
					}
				});*/
	            
	            /*B_Kill.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {						
						MainActivity.users.get(position).setStatus(!MainActivity.users.get(position).isStatus());
			            if(MainActivity.users.get(position).isStatus())
			            {
			            	B_Kill.setText("Kill");
			            	Status.setColorFilter(Color.GREEN);
			            }
			            else
			            {
			            	B_Kill.setText("Revive");
			            	Status.setColorFilter(Color.RED);
			            }
					}
				});*/
	            
	        return v;
	      
	    }
}
