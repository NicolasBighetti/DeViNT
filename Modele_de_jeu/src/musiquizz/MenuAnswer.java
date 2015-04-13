package musiquizz;

import java.awt.event.KeyEvent;

import t2s.SIVOXDevint;

/**
 * Sert à répondre au questions
 * On utilise des attributs static pour passer outre les limitations de MenuAbstrait
 * @author Nicolas Bighetti
 *
 */
@SuppressWarnings("serial")
public class MenuAnswer extends PanelChoice{

	public static String titre1 = "TEST";
	
	public static String titre2 = "TEST";
	
	public static String titre3 = "TEST";
	
	public static String titre4 = "TEST";
	
	public static String titre5 = "TEST";
	
	public MenuAnswer(String title, SIVOXDevint voix, MultiPlayerGame hook) {
		super(title, voix, hook);
		// TODO Auto-generated constructor stub
	}
	
	protected String[] nomOptions() {
		String[] noms = {titre1, titre2, titre3, titre4, titre5};
		return noms;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
