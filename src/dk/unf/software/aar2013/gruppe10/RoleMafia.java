package dk.unf.software.aar2013.gruppe10;

public class RoleMafia extends Role
{

	public RoleMafia() {
		setRoleName("Mafia");
		setStandardTeam(1);
		setRoleDescribtion("The mafia is the evil part of town. It attacks during the night and the whole mafia team can choose 1 victim, each night, who they wanna kill.");
		setTeamTurns(true);
		profilePic = R.drawable.icon_mafia;
	}
	
}
