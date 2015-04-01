package musiquizz;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import util.MP3Gatherer;
import util.MyMP3Player;
import devintAPI.FenetreAbstraite;

/**
 * Vu servant de support de jeu à la partie
 * Contient les cases joueurs ainsi que leurs scores
 * Un thread timer fait tourner les musiques et les temps de réponses
 * @author Nicolas Bighetti
 *
 */
@SuppressWarnings("serial")
public class MultiPlayerGame extends FenetreAbstraite implements ActionListener{

	private static PanelScore joueur1;

	private static PanelScore joueur2;

	private static PanelScore joueur3;

	private static PanelScore joueur4;

	private boolean isAnswering;

	private Player activePlayer;

	private JPanel playerContainer;

	private JComboBox<String> answerWheel;

	private JProgressBar timer;

	private JButton anwserButton;

	private MyMP3Player mp3player;

	private List<String> mp3Path;

	private CountDownThread cdt;

	public MultiPlayerGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		playerContainer = new JPanel(new GridLayout(2,2));

		if(joueur1.getPlayer() != null)
			playerContainer.add(joueur1);

		if(joueur2.getPlayer() != null)
			playerContainer.add(joueur2);

		if(joueur3.getPlayer() != null)
			playerContainer.add(joueur3);

		if(joueur4.getPlayer() != null)
			playerContainer.add(joueur4);

		answerWheel = new JComboBox<String>();
		answerWheel.setFont(new Font(Font.DIALOG, 1, 35));
		answerWheel.setVisible(true);

		timer = new JProgressBar(SwingConstants.HORIZONTAL,0,300);
		timer.setValue(300);


		anwserButton = new JButton("Valider");
		anwserButton.setFont(new Font(Font.DIALOG, 1, 35));
		anwserButton.setActionCommand("OK");
		anwserButton.addActionListener(this);

		MP3Gatherer g = new MP3Gatherer("../ressources/musique/");
		g.gatherMP3();
		mp3Path = g.getMp3Path();
		setNewRandomSong();
		startThread();
		mp3player.play();

		answerWheel = new JComboBox<String>(g.getMp3Name().toArray(new String[0]));
		answerWheel.setFont(new Font(Font.DIALOG, 1, 35));
		answerWheel.setVisible(true);

		this.setLayout(new GridLayout(4,1));
		this.add(playerContainer);
		this.add(answerWheel);
		this.add(timer);
		this.add(anwserButton);

		isAnswering = false;

	}


	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(isABuzzer(e.getKeyCode()))
		{
			cdt.stop();
			mp3player.pause();

			if(joueur1.getPlayer().getBuzzerKey() == e.getKeyCode())
				activePlayer = joueur1.getPlayer();
			else if(joueur2.getPlayer().getBuzzerKey() == e.getKeyCode())
				activePlayer = joueur2.getPlayer();
			else if(joueur3.getPlayer().getBuzzerKey() == e.getKeyCode())
				activePlayer = joueur3.getPlayer();
			else
				activePlayer = joueur4.getPlayer();

		}
	}

	private boolean isABuzzer(int keyCode) {
		// TODO Auto-generated method stub

		if(joueur1.getPlayer() != null)
			if(joueur1.getPlayer().getBuzzerKey() == keyCode)
				return true;
		if(joueur2.getPlayer() != null)
			if(joueur2.getPlayer().getBuzzerKey() == keyCode)
				return true;
		if(joueur3.getPlayer() != null)
			if(joueur3.getPlayer().getBuzzerKey() == keyCode)
				return true;
		if(joueur4.getPlayer() != null)
			if(joueur4.getPlayer().getBuzzerKey() == keyCode)
				return true;

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("OK"))
		{
			if(answerWheel.getItemAt(answerWheel.getSelectedIndex()).equals(mp3player.getTitle()))
			{
				activePlayer.setGoodAnswers(activePlayer.getGoodAnswers()+1);
				addOneTotalQuestion();

				if(activePlayer.equals(joueur1.getPlayer()))
				{
					joueur1.refreshPanel();
					joueur1.getPlayer().setReactionTime(joueur1.getPlayer().getReactionTime()+(30000 - cdt.getTempsMS()));
					joueur2.getPlayer().setReactionTime(joueur2.getPlayer().getReactionTime() + 30000);
					if(joueur3.getPlayer() != null)
						joueur3.getPlayer().setReactionTime(joueur3.getPlayer().getReactionTime() + 30000);
					if(joueur4.getPlayer() != null)
						joueur4.getPlayer().setReactionTime(joueur4.getPlayer().getReactionTime() + 30000);
				}
				else if(activePlayer.equals(joueur2.getPlayer()))
				{
					joueur2.refreshPanel();
					joueur2.getPlayer().setReactionTime(joueur2.getPlayer().getReactionTime()+(30000 - cdt.getTempsMS()));
					joueur1.getPlayer().setReactionTime(joueur1.getPlayer().getReactionTime() + 30000);
					if(joueur3.getPlayer() != null)
						joueur3.getPlayer().setReactionTime(joueur3.getPlayer().getReactionTime() + 30000);
					if(joueur4.getPlayer() != null)
						joueur4.getPlayer().setReactionTime(joueur4.getPlayer().getReactionTime() + 30000);
				}
				else if(joueur3.getPlayer() != null && activePlayer.equals(joueur3.getPlayer()))
				{
					joueur3.refreshPanel();
					joueur3.getPlayer().setReactionTime(joueur3.getPlayer().getReactionTime()+(30000 - cdt.getTempsMS()));
					joueur2.getPlayer().setReactionTime(joueur2.getPlayer().getReactionTime() + 30000);
					joueur1.getPlayer().setReactionTime(joueur1.getPlayer().getReactionTime() + 30000);
					if(joueur4.getPlayer() != null)
						joueur4.getPlayer().setReactionTime(joueur4.getPlayer().getReactionTime() + 30000);
				}
				else
				{
					if(joueur4.getPlayer() != null)
					{
						joueur4.refreshPanel();
						joueur4.getPlayer().setReactionTime(joueur4.getPlayer().getReactionTime()+(30000 - cdt.getTempsMS()));
						joueur2.getPlayer().setReactionTime(joueur2.getPlayer().getReactionTime() + 30000);
						joueur3.getPlayer().setReactionTime(joueur3.getPlayer().getReactionTime() + 30000);
						joueur1.getPlayer().setReactionTime(joueur1.getPlayer().getReactionTime() + 30000);
					}
				}

				softReset();

			}
			else
			{
				activePlayer = null;
				cdt.resume();
				if(mp3player.getMp().isPaused())
					mp3player.unpause();
			}
		}

		this.requestFocus();
	}

	/**
	 * Reset le contexte lorsque le round se finit par une bonne réponse
	 */
	private void softReset() {
		cdt.setTempsMS(30000);
		cdt.resume();
		
		joueur1.getPlayer().setReactionTime(joueur1.getPlayer().getReactionTime() + 30000);
		joueur2.getPlayer().setReactionTime(joueur2.getPlayer().getReactionTime() + 30000);
		if(joueur3.getPlayer() != null)
			joueur3.getPlayer().setReactionTime(joueur3.getPlayer().getReactionTime() + 30000);
		if(joueur4.getPlayer() != null)
			joueur4.getPlayer().setReactionTime(joueur4.getPlayer().getReactionTime() + 30000);

		timer.setValue(300);
		mp3player.stop();
		setNewRandomSong();
		cdt.setTempsMS(30000);
		cdt.resume();
		mp3player.play();
	}

	/**
	 * Redémarre la partie après un timeout
	 * appelé par le thread timer lorsque celui ci est fini
	 */
	public void hardReset()
	{
		addOneTotalQuestion();
		
		mp3player.stop();
		timer.setValue(300);
		setNewRandomSong();
		mp3player.play();
		startThread();
	}
	
	private void addOneTotalQuestion() {
		joueur1.getPlayer().setTotalQuestions(joueur1.getPlayer().getTotalQuestions()+1);
		joueur2.getPlayer().setTotalQuestions(joueur2.getPlayer().getTotalQuestions()+1);
		if(joueur3.getPlayer() != null)
			joueur3.getPlayer().setTotalQuestions(joueur3.getPlayer().getTotalQuestions()+1);
		if(joueur4.getPlayer() != null)
			joueur4.getPlayer().setTotalQuestions(joueur4.getPlayer().getTotalQuestions()+1);
	}

	/**
	 * choose a new song randomly selected from the source folder
	 */
	private void setNewRandomSong()
	{
		Collections.shuffle(mp3Path);
		
		mp3player = new MyMP3Player(mp3Path.iterator().next());
		System.out.println(mp3player.getTitle());
	}

	/**
	 * Démarrer le thread chargé de limiter le temps des parties et gèrer le décroissement de la barre de progret
	 */
	private void startThread()
	{
		cdt = new CountDownThread(this);
		Thread t = new Thread(cdt);
		t.start();
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

	/**
	 * @return the joueur1
	 */
	public PanelScore getJoueur1() {
		return joueur1;
	}

	/**
	 * @param joueur1 the joueur1 to set
	 */
	public static void setJoueur1(PanelScore joueur1) {
		MultiPlayerGame.joueur1 = joueur1;
	}

	/**
	 * @return the joueur2
	 */
	public PanelScore getJoueur2() {
		return joueur2;
	}

	/**
	 * @param joueur2 the joueur2 to set
	 */
	public static void setJoueur2(PanelScore joueur2) {
		MultiPlayerGame.joueur2 = joueur2;
	}

	/**
	 * @return the joueur3
	 */
	public PanelScore getJoueur3() {
		return joueur3;
	}

	/**
	 * @param joueur3 the joueur3 to set
	 */
	public static void setJoueur3(PanelScore joueur3) {
		MultiPlayerGame.joueur3 = joueur3;
	}

	/**
	 * @return the joueur4
	 */
	public PanelScore getJoueur4() {
		return joueur4;
	}

	/**
	 * @param joueur4 the joueur4 to set
	 */
	public static void setJoueur4(PanelScore joueur4) {
		MultiPlayerGame.joueur4 = joueur4;
	}

	/**
	 * @return the anwserWheel
	 */
	public JComboBox<String> getAnswerWheel() {
		return answerWheel;
	}

	/**
	 * @param anwserWheel the anwserWheel to set
	 */
	public void setAnswerWheel(JComboBox<String> answerWheel) {
		this.answerWheel = answerWheel;
	}

	/**
	 * @return the timer
	 */
	public JProgressBar getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(JProgressBar timer) {
		this.timer = timer;
	}

	/**
	 * @return the anwserButton
	 */
	public JButton getAnwserButton() {
		return anwserButton;
	}

	/**
	 * @param anwserButton the anwserButton to set
	 */
	public void setAnwserButton(JButton anwserButton) {
		this.anwserButton = anwserButton;
	}



	/**
	 * @return the playerContainer
	 */
	public JPanel getPlayerContainer() {
		return playerContainer;
	}

	/**
	 * @param playerContainer the playerContainer to set
	 */
	public void setPlayerContainer(JPanel playerContainer) {
		this.playerContainer = playerContainer;
	}

	/**
	 * @return the mp3player
	 */
	public MyMP3Player getMp3player() {
		return mp3player;
	}

	/**
	 * @param mp3player the mp3player to set
	 */
	public void setMp3player(MyMP3Player mp3player) {
		this.mp3player = mp3player;
	}

	/**
	 * @return the mp3Path
	 */
	public List<String> getMp3Path() {
		return mp3Path;
	}

	/**
	 * @param mp3Path the mp3Path to set
	 */
	public void setMp3Path(List<String> mp3Path) {
		this.mp3Path = mp3Path;
	}

	/**
	 * @return the activePlayer
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer the activePlayer to set
	 */
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	/**
	 * @return the isAnswering
	 */
	public boolean isAnswering() {
		return isAnswering;
	}

	/**
	 * @param isAnswering the isAnswering to set
	 */
	public void setAnswering(boolean isAnswering) {
		this.isAnswering = isAnswering;
	}

}
