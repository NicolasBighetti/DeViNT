package player.colorpref;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe empactant les préférences couleurs pour une certaines déficience visuels
 * A compléter
 * Contiendra X champ selon les couleurs à paramètrer (texte, panel, bouton)
 * Dériver plusieurs classes pour accomoder chaque cas, ou sérializer X profils à charger au démarage
 * 
 * Pour obtenir le ColorPack correspondant à une déficience, utiliser la méthode static getColorPack pour récupérer le ColorPack adapté à partir d'une colorDeficiency
 * 
 * @author Nicolas Bighetti
 *
 */
public class ColorPack {
	
	/**
	 * Couleur de fond
	 */
	private Color bgColor;
	
	/**
	 * Couleur de police
	 */
	private Color fontColor;
	
	/**
	 * Taille de police
	 */
	private int fontSize;

	private ColorPack(Color bg, Color font, int size)
	{
		this.bgColor = bg;
		this.fontColor = font;
		this.fontSize = size;
	}
	
	/**
	 * Retourne la bonne instance de ColorPack selon le handicap visuel (Pattern factory)
	 * @param deficiency la déficience visuelle
	 * @return la bonne instance de ColorPack, ou une instance neutre noire et blanche
	 */
	public static ColorPack getColorPack(ColorDeficiency deficiency)
	{
		switch(deficiency)
		{
		
			default:
			case COLORBLIND : return new ColorPack(Color.GREEN, Color.BLACK, 25);
			
		}
	}
	
	/**
	 * Retourne la liste de toute les déficiences
	 * @return une liste contenant toutes les déficiences
	 */
	public static List<ColorDeficiency> getAllDeficiency()
	{
		List<ColorDeficiency> list = new ArrayList<ColorDeficiency>();
		list.add(ColorDeficiency.COLORBLIND);
		
		return list;
	}

	/**
	 * @return the bgColor
	 */
	public Color getBgColor() {
		return bgColor;
	}

	/**
	 * @param bgColor the bgColor to set
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * @return the fontColor
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * @param fontColor the fontColor to set
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * @return the fontSize
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
