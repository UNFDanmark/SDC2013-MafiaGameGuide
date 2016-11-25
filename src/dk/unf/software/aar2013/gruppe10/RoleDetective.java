package dk.unf.software.aar2013.gruppe10;

public class RoleDetective extends Role
{

	public RoleDetective() {
		setRoleName("Detective");
		setStandardTeam(0);
		setRoleDescribtion("The detective can choose 1 person each night, the game master will then tell him wheter the chosen person is good or bad.");
		profilePic = R.drawable.icon_detective;
	}
}
