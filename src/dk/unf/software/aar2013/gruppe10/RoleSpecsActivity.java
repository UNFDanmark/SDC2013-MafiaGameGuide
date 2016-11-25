package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.widget.ExpandableListView;

public class RoleSpecsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_role_specs);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		ExpandableListView EL_Guide_RoleSpecs_ListContainer = (ExpandableListView)findViewById(R.id.EL_Guide_RoleSpecs_ListContainer);
		 
        ArrayList<rolespecs_parent> arrayParents = new ArrayList<rolespecs_parent>();
        
        // Mafia
        rolespecs_parent Mafia = new rolespecs_parent();
        Mafia.setTitle("Mafia");
        ArrayList<String> MafiaDes = new ArrayList<String>();
        MafiaDes.add("All mafia wakes up together and must agree on one player to kill that night. They win when there are no players on any other team. They can accidentally shoot a terrorist, even though they're on the same team and win together. If they want to, they can choose to kill one of themselves, as some kind of strategy, to mess with other players' heads.\n\nMafia members are usually assigned with black J, Q & K, from a standard deck of cards.");
        Mafia.setArrayChildren(MafiaDes);
        arrayParents.add(Mafia);
    
        // Healer
        rolespecs_parent Healer = new rolespecs_parent();
        Healer.setTitle("Healer");
        ArrayList<String> HealerDes = new ArrayList<String>();
        HealerDes.add("Healers wake up seperately and decide which player they want to protect by pointing. Healers are allowed to point at themselves.  They win along with other villagers, detectives etc. on the good team, when there are no players left on any other team. If a healer protects the player who the mafia wanted to shoot, the player will not die. However if any player is protected by more than 1 healer, that person will die. This means that it can backfire, to ask for protection, but it can also be used offensively.\n\nHealers are usually assigned with red/hearts of (J,) Q & K. So they can be awakened with \"Healer-Queen awakes\".\n\nHealers protect against anything that kills during the night, fx Romeo and Juliet.");
        Healer.setArrayChildren(HealerDes);
        arrayParents.add(Healer);
        
        // Detective
        rolespecs_parent Detective = new rolespecs_parent();
        Detective.setTitle("Detective");
        ArrayList<String> DetectiveDes = new ArrayList<String>();
        DetectiveDes.add("Detectives (usually there's just 1) wakes up seperately and points at whoever they want to \"search\". Gamemaster then returns a thumb up or down representing the selected players' status as good or bad, this is from a villagers point of view, so any player on another team will be represented as bad, even though they may not be on the bad team (mafia/terrorist).\n\nA detective is usually assigned with the Ace of diamonds, but if there are going to be more than one detective, players must agree what cards represent a detective.");
        Detective.setArrayChildren(DetectiveDes);
        arrayParents.add(Detective);
        
        // Terrorist
        rolespecs_parent Terrorist = new rolespecs_parent();
        Terrorist.setTitle("Terrorist");
        ArrayList<String> TerroristDes = new ArrayList<String>();
        TerroristDes.add("Terrorists are suicide bombers. They only wake during the first night. He's special ability is to instantaneously kill another player during day, doing so the terrorist also kills himself.");
        Terrorist.setArrayChildren(TerroristDes);
        arrayParents.add(Terrorist);
        
        // Villager
        rolespecs_parent Villager = new rolespecs_parent();
        Villager.setTitle("Villager");
        ArrayList<String> VillagerDes = new ArrayList<String>();
        VillagerDes.add("Villagers are only active during the day. They have no abilities but to participate in voting on who to kill.");
        Villager.setArrayChildren(VillagerDes);
        arrayParents.add(Villager);
        
        //sets the adapter that provides data to the list.
        EL_Guide_RoleSpecs_ListContainer.setAdapter(new rolespecs_adapter(RoleSpecsActivity.this,arrayParents));
		
	}
}
