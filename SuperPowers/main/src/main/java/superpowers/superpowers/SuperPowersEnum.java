package superpowers.superpowers;

public enum SuperPowersEnum {
	
	KNOCKOUT("KnockOut"),
	TELEPORT("Teleport"),
	LASEREYES("LaserEyes"),
	FLIGHT("Flight"),
	INVISIBILITY("Invisibility");
	
	String name;
	
	SuperPowersEnum(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
}
