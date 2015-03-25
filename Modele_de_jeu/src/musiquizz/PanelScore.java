package musiquizz;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.colorpref.ColorDeficiency;
import player.colorpref.ColorPack;


@SuppressWarnings("serial")
public class PanelScore extends JPanel implements KeyListener{

	private Player player;
	
	private ColorPack preferedColor;
	
	private JComboBox<ColorDeficiency> profilList;
	
	private JLabel buzzer;
	
	private JLabel score;
	
	/**
	 * Montre si le prochaine evenement clavier sera enregistré comme touche de référence
	 */
	private boolean waitingBuzzer = false;
	
	public PanelScore()
	{
		super();
		//On rempli la combo box avec toutes les déficiences, et donc profil de couleur
		profilList = new JComboBox<>(ColorPack.getAllDeficiency().toArray(new ColorDeficiency[0]));
		profilList.setFont(new Font(Font.DIALOG, 1,35));
		buzzer = new JLabel("Appuyez sur une touche pour en faire votre buzzer");
		score = new JLabel("Score : 0");
		buzzer.setFont(new Font(Font.DIALOG, 1,25));
		score.setFont(new Font(Font.DIALOG, 1,25));
		this.setLayout(new FlowLayout());
		this.add(profilList);
		this.add(buzzer);
		this.add(score);
	}

	/**
	 * Refresh the panel to apply buzzer modification and color preferences after selection
	 */
	public void refreshPanel()
	{
		if(player == null)
			return;
		
		buzzer.setText("Appuye sur " + KeyEvent.getKeyText(player.getBuzzerKey())+ " pour buzzer!");
		score.setText("Score : " + player.getGoodAnswers());
		ColorPack p = ColorPack.getColorPack(profilList.getItemAt(profilList.getSelectedIndex()));
		
		this.setBackground(p.getBgColor());
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the waitingBuzzer
	 */
	public boolean isWaitingBuzzer() {
		return waitingBuzzer;
	}

	/**
	 * @param waitingBuzzer the waitingBuzzer to set
	 */
	public void setWaitingBuzzer(boolean waitingBuzzer) {
		this.waitingBuzzer = waitingBuzzer;
	}

	/**
	 * @return the preferedColor
	 */
	public ColorPack getPreferedColor() {
		return preferedColor;
	}

	/**
	 * @param preferedColor the preferedColor to set
	 */
	public void setPreferedColor(ColorPack preferedColor) {
		this.preferedColor = preferedColor;
		this.setBackground(preferedColor.getBgColor());
		this.setFont(new Font(Font.DIALOG, 1, preferedColor.getFontSize()));
	}

	/**
	 * @return the profilList
	 */
	public JComboBox<ColorDeficiency> getProfilList() {
		return profilList;
	}

	/**
	 * @param profilList the profilList to set
	 */
	public void setProfilList(JComboBox<ColorDeficiency> profilList) {
		this.profilList = profilList;
	}

	/**
	 * @return the buzzer
	 */
	public JLabel getBuzzer() {
		return buzzer;
	}

	/**
	 * @param buzzer the buzzer to set
	 */
	public void setBuzzer(JLabel buzzer) {
		this.buzzer = buzzer;
	}

	/**
	 * @return the score
	 */
	public JLabel getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(JLabel score) {
		this.score = score;
	}

}
