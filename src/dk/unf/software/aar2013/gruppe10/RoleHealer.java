package dk.unf.software.aar2013.gruppe10;

public class RoleHealer extends Role
{

	public RoleHealer() {
		setRoleName("Healer");
		setStandardTeam(0);
		setRoleDescribtion("The healer is a protecter and can choose 1 person each night, who he wishes to protect from all harm done during that night. He can also choose himself.");
		profilePic = R.drawable.icon_healer;
	}
	
}
