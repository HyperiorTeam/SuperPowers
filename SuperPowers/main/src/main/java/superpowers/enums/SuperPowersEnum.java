package superpowers.enums;

public enum SuperPowersEnum {
	
	KNOCKOUT("KnockOut");
	
	String name;
	
	SuperPowersEnum(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
}
