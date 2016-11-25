package dk.unf.software.aar2013.gruppe10;

public class RoleTerrorist extends Role
{

	public RoleTerrorist() {
		setRoleName("Terrorist");
		setStandardTeam(1);
		setRoleDescribtion("The terroist is passive at night, but can at any time during the day choose to blow himself up, causing any person he choose to die with him.");
		setWakesFirstNightOnly(true);
		setTeamTurns(false);
		profilePic = R.drawable.icon_terrorist;
	}
}
