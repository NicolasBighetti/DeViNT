package musiquizz;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import devintAPI.FenetreAbstraite;

/**
 * La vue de préparation de la partie multijoueur
 * @author Nicolas Bighetti
 *
 */
@SuppressWarnings("serial")
public class MultiPlayerView extends FenetreAbstraite implements ActionListener{

	private JButton commencer;

	private JPanel playercontainer;

	private PanelScore joueur1;

	private PanelScore joueur2;

	private PanelScore joueur3;

	private PanelScore joueur4;

	private int currentPlayer;

	public MultiPlayerView(String title) {
		super(title);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

		commencer = new JButton("Commencer");
		commencer.setEnabled(false);
		commencer.setActionCommand("start");
		commencer.addActionListener(this);
		commencer.setFont(new Font(Font.DIALOG, 1, 35));

		joueur1 = new PanelScore();
		joueur2 = new PanelScore();
		joueur3 = new PanelScore();
		joueur4 = new PanelScore();

		playercontainer = new JPanel(new GridLayout(2,2));
		playercontainer.add(joueur1);
		playercontainer.add(joueur2);
		playercontainer.add(joueur3);
		playercontainer.add(joueur4);

		this.setLayout(new GridLayout(2,1));

		this.add(playercontainer);
		this.add(commencer);

		joueur1.addKeyListener(joueur1);
		joueur1.addKeyListener(this);
		this.pack();
		
		currentPlayer = 1;
		voix.playText("Joueure "+currentPlayer+", choisis ta toucheuh beuzeure");
	}



	@Override
	protected String wavAide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeColor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeSize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String wavAccueil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String wavRegleJeu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("start"))
		{
			this.dispose();
			MultiPlayerGame.setJoueur1(joueur1);
			MultiPlayerGame.setJoueur2(joueur2);
			MultiPlayerGame.setJoueur3(joueur3);
			MultiPlayerGame.setJoueur4(joueur4);
			new MultiPlayerGame("Partie Multijoueur GO");
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		//On controle bien que chaque joueur choisis un buzzer unique
		switch(currentPlayer)
		{
		case 1:
			if(bindKey(joueur1, e))
			{
				currentPlayer++;
				joueur1.getProfilList().setEnabled(false);
				joueur1.refreshPanel();
				voix.playText("Joueure "+currentPlayer+", choisis ta toucheuh beuzeure");
			}
			else
				voix.playText("Choisis une autreuh toucheuh");
			break;
		case 2:
			if(bindKey(joueur2, e))
			{
				if(isUnique(joueur1, joueur2))
				{
					currentPlayer++;
					joueur2.getProfilList().setEnabled(false);
					joueur2.refreshPanel();
					commencer.setEnabled(true);
					voix.playText("Joueure "+currentPlayer+", choisis ta toucheuh beuzeure");
				}
			}
			else
				voix.playText("Choisis une autreuh toucheuh");
			break;
		case 3:
			if(bindKey(joueur3, e))
			{
				if(isUnique(joueur1, joueur2, joueur3))
				{
					currentPlayer++;
					joueur3.getProfilList().setEnabled(false);
					joueur3.refreshPanel();
					voix.playText("Joueure "+currentPlayer+", choisis ta toucheuh beuzeure");
				}
			}
			else
				voix.playText("Choisis une autreuh toucheuh");
			break;
		case 4:
			if(bindKey(joueur4, e))
			{
				if(isUnique(joueur1, joueur2, joueur3, joueur4))
				{
					currentPlayer++;
					joueur4.getProfilList().setEnabled(false);
					joueur4.refreshPanel();
				}
			}
			else
				voix.playText("Choisis une autreuh toucheuh");
			break;
		}
		

	}

	/**
	 * Check if every player have different buzzer
	 * @param ps the different players to test
	 * @return <code> true </code> if they all are unique, <code> false </code> otherwise
	 */
	private boolean isUnique(PanelScore...ps)
	{
		int threshold = 0;

		for (PanelScore panelScore : ps) {
			for (PanelScore panelScore2 : ps) {
				if(panelScore.getPlayer().getBuzzerKey() == panelScore2.getPlayer().getBuzzerKey())
					threshold++;
			}
			if(threshold == 1)
				threshold = 0;
			else
				return false;
		}

		return true;
	}
	
	/**
	 * Bind a key to a player
	 * @param p the playerPanel associated
	 * @param e the Keyevent fired
	 * @return <code>true</code> if the key was binded, <code>false</code> otherwise
	 */
	private boolean bindKey(PanelScore p, KeyEvent e)
	{
		// TODO Auto-generated method stub
		//Si l'on attends pas le buzzer
		int key = e.getKeyCode();
		switch(key)
		{
		//On bloque l'utilisation des touches spécifiques à l'API DeVINt pour empecher les conflits
		case(KeyEvent.VK_ESCAPE):
		case(KeyEvent.VK_F1):
		case(KeyEvent.VK_F2):
		case(KeyEvent.VK_F3):
		case(KeyEvent.VK_F4):
		case(KeyEvent.VK_ENTER):
			return false;
		//Dans les autres cas on bind la touche
		//TODO Gérer les conflits utilisateur
		}

		p.setPlayer(new Player(key));
		p.setWaitingBuzzer(false);
		return true;
	}

}
