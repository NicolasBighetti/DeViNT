package player.colorpref;

public enum ColorDeficiency {

	COLORBLIND("Daltonien");
	
	
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
