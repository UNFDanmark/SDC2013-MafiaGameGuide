package dk.unf.software.aar2013.gruppe10;

import java.util.ArrayList;

public class Role {
	protected String roleName = "";
	protected int standardTeam;
	protected String roleDescribtion;
	protected boolean wakesFirstNightOnly = false;
	protected boolean teamTurns = false;
	protected boolean active = true;
	public int profilePic = R.drawable.icon_custom;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDescribtion() {
		return roleDescribtion;
	}
	public void setRoleDescribtion(String roleDescribtion) {
		this.roleDescribtion = roleDescribtion;
	}
	
	public int getStandardTeam() {
		return standardTeam;
	}
	public void setStandardTeam(int standardTeam) {
		this.standardTeam = standardTeam;
	}
	
	/**
	 * @return Whether a person should wake the first night or all nights.
	 */
	public boolean isWakesFirstNightOnly() {
		return wakesFirstNightOnly;
	}
	/**
	 * @param Wheter a person should wake the first night or all nights.
	 */
	public void setWakesFirstNightOnly(boolean wakesFirstNightOnly) {
		this.wakesFirstNightOnly = wakesFirstNightOnly;
	}
	/**
	 * @return the teamTurns
	 */
	public boolean isTeamTurns() {
		return teamTurns;
	}
	/**
	 * @param teamTurns the teamTurns to set
	 */
	public void setTeamTurns(boolean teamTurns) {
		this.teamTurns = teamTurns;
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	public Role()
	{
		
	}
	
	public static Role findRoleByName(String name)
	{
		for(int i = 0; i < MainActivity.users.size(); i++)
		{
			if(MainActivity.roles.get(i).getRoleName() == name)
			{
				return MainActivity.roles.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<String> allRoleNames()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < MainActivity.roles.size(); i++)
		{
			temp.add(MainActivity.roles.get(i).getRoleName());
		}
		return temp;
	}
	
	public static int getTotalTurns()
	{
		int totalTurns = 0;
		ArrayList<String> addedTeams = new ArrayList<String>();
		for(int i = 0; i < MainActivity.users.size(); i++)
		{
			if(!MainActivity.users.get(i).getRole().teamTurns)
			{
				totalTurns++;
			}
			else if(!addedTeams.contains(MainActivity.users.get(i).getRole().getRoleName()))
			{
				addedTeams.add(MainActivity.users.get(i).getRole().getRoleName());
				totalTurns++;
			}
		}
		return totalTurns;
	}
	
	public static ArrayList<Role> createTurnList()
	{
		ArrayList<Role> tempList = new ArrayList<Role>();
		ArrayList<String> addedTeams = new ArrayList<String>();
		
		for(int i = 0; i < MainActivity.users.size(); i++)
		{
			if(!MainActivity.users.get(i).getRole().teamTurns)
			{
				tempList.add(MainActivity.users.get(i).getRole());
			}
			else if(!addedTeams.contains(MainActivity.users.get(i).getRole().getRoleName()))
			{
				addedTeams.add(MainActivity.users.get(i).getRole().getRoleName());
				tempList.add(MainActivity.users.get(i).getRole());
			}
		}
		
		return tempList;
	}
}
