package dk.unf.software.aar2013.gruppe10;

public class RoleVillager extends Role
{

	public RoleVillager() {
		setRoleName("Villager");
		setStandardTeam(0);
		setRoleDescribtion("A normal villager, can't attack but can participate in votes");
		setTeamTurns(true);
		profilePic = R.drawable.icon_vilager;
	}
	
}
