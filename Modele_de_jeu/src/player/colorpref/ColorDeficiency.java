package player.colorpref;

public enum ColorDeficiency {

	COLORBLIND("Profil 1"), PROFIL2("Profil 2"), PROFIL3("Profil 3");
	
	
	private String intitule;
	
	private ColorDeficiency(String string)
	{
		this.intitule = string;
	}
	
	@Override
	public String toString()
	{
		return intitule;
	}
}
