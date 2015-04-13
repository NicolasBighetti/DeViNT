package musiquizz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import t2s.SIVOXDevint;
import devintAPI.Preferences;

public abstract class PanelChoice extends JPanel implements ActionListener, KeyListener{

	// -------------------------------------------------------
		// les attributs

		// le nom du jeu
		protected String nomJeu = "";

		// les noms des options
		private String[] nomOptions;

		// les boutons associés aux options
		private JButton[] boutonOption;

		// le nombre d'options
		private int nbOption;
		
		private SIVOXDevint voix;

		// attributs des textes et des boutons
		// à redéfinir dans la classe concrète si vous le souhaitez
		// en gardant de forts contrastes et peu de couleurs différentes
		protected Font fonteBouton;
		protected Color foregroundColor;
		protected Color backgroundColor;

		// l'option courante qui est sélectionnée
		private int optionCourante;

		// éléments de placement des composants
		private GridBagLayout placement; // le layout
		private GridBagConstraints regles; // les regles de placement

		// éléments graphiques
		protected JPanel entete;
		protected JLabel lb1;
		protected LineBorder buttonBorder;
		protected LineBorder enteteBorder;
		
		private MultiPlayerGame hook;

		// -------------------------------------------------
		// les méthodes abstraites à définir par héritage

		/**
		 * renvoie le tableau contenant le nom des options méthode abstraite à
		 * définir par héritage
		 */
		protected abstract String[] nomOptions();

		/**
		 * lance l'action associée au bouton n°i méthode abstraite à définir par
		 * héritage PRECOND : i varie entre 0 et le nombre d'options possibles moins
		 * 1
		 */
		private void lancerOption(int i){
			switch(i)
			{
			case 0:
				hook.actionPerformedString(MenuAnswer.titre1);
				break;
			case 1:
				hook.actionPerformedString(MenuAnswer.titre2);
				break;
			case 2:
				hook.actionPerformedString(MenuAnswer.titre3);
				break;
			case 3:
				hook.actionPerformedString(MenuAnswer.titre4);
				break;
			case 4:
				hook.actionPerformedString(MenuAnswer.titre5);
				break;
			default:
				break;
			}
		}


		// -------------------------------------------------------
		/**
		 * constructeur,
		 * 
		 * @param title  : le nom du jeu
		 * @param hook 
		*/
		public PanelChoice(String title, SIVOXDevint voix, MultiPlayerGame hook) {

			this.hook = hook;
			this.voix = voix;
			nomJeu = title;
			optionCourante = -1;
			// méthode à rendre concrète par héritage
			nomOptions = nomOptions(); 
			nbOption = nomOptions.length;
			// on récupère les préférences
			Preferences pref = Preferences.getData();
			foregroundColor = pref.getCurrentForegroundColor();
			backgroundColor = pref.getCurrentBackgroundColor();
			// créé les éléments de la fenêtre
			creerLayout();
			creerEntete();
			creerOption(nomOptions);
		     // visible
	    	this.setVisible(true);
	    	// a le focus
	    	this.requestFocus();
			// lit le message d'accueil
	 
		}

		// -------------------------------------------------------
		// méthodes utilisées par le constructeur

		/**
		 * créé le layout pour placer les composants
		 */
		private void creerLayout() {
			placement = new GridBagLayout();
			regles = new GridBagConstraints();
			setLayout(placement);
			// par défaut on étire les composants horizontalement et verticalement
			regles.fill = GridBagConstraints.HORIZONTAL;
			// par défaut, tous les composants ont un poids de 1
			// on les répartit donc équitablement sur la grille
			regles.weightx = 1;
			regles.weighty = 1;
			// espaces au bord des composants
			regles.insets = new Insets(10, 50, 10, 50);
			// pour placer en haut des zones
			regles.anchor = GridBagConstraints.NORTH;
		}

		/**
		 * créé l'entête avec le nom du jeu
		 */
		public void creerEntete() {

			// panel d'entete de la fenêtre
			entete = new JPanel();
			FlowLayout enteteLayout = new FlowLayout();
			enteteLayout.setAlignment(FlowLayout.CENTER);
			entete.setLayout(enteteLayout);
			enteteBorder = new LineBorder(Color.GRAY, 8);
			entete.setBorder(enteteBorder);

			// le label
			lb1 = new JLabel(nomJeu);
			lb1.setFont(Preferences.getData().getCurrentFont());
			lb1.setForeground(foregroundColor);
			lb1.setBackground(backgroundColor);
			entete.add(lb1);

			// placement de l'entete en 1ère ligne, 1ère colonne
			regles.gridx = 1;
			regles.gridy = 1;
			placement.setConstraints(entete, regles);
			add(entete);
		}

		/**
		 * creer les boutons associés aux noms d'options
		 */
		private void creerOption(String[] noms) {
			// création des boutons
			// panel des boutons
			JPanel boutons = new JPanel();
			boutons.setLayout(new GridLayout(nbOption, 1));
			// les boutons
			fonteBouton = Preferences.getData().getCurrentFont();
			buttonBorder = new LineBorder(Color.BLACK, 8);
			boutonOption = new JButton[nbOption];
			for (int i = 0; i < nbOption; i++) {
				creerBouton(i, noms[i]);
				boutons.add(boutonOption[i]);
			}

			// poids relatif de 4 (i.e 3 fois plus grand que l'entête)
			regles.weighty = 4;
			// placement des boutons
			regles.gridx = 1;
			regles.gridy = 2;
			placement.setConstraints(boutons, regles);
			add(boutons);
		}

		// pour créer un bouton associé à un texte
		private void creerBouton(int i, String texte) {
			boutonOption[i] = new JButton();
			boutonOption[i].setText(texte);
			setPropertiesButton(boutonOption[i]);
		}

		// mettre à jour les propriétés des boutons
		protected void setPropertiesButton(JButton b) {
			b.setFocusable(false);
			b.setBackground(foregroundColor);
			b.setForeground(backgroundColor);
			b.setFont(fonteBouton);
			b.setBorder(buttonBorder);
			b.addActionListener(this);
		}

		// -------------------------------------------------------
		// méthodes pour réagir aux évènements clavier

		// activer l'option si clic sur le bouton
		public void actionPerformed(ActionEvent ae) {
			voix.stop();
			Object source = ae.getSource();
			for (int i = 0; i < nbOption; i++) {
				if (source == boutonOption[i]) {
					if (optionCourante != -1)
						unFocusedButton(optionCourante);
					optionCourante = i;
					setFocusedButton(optionCourante);
					lancerOption(i);
				}
			}
		}

		// mettre le focus sur une option
		private void setFocusedButton(int i) {
			voix.playShortText(boutonOption[i].getText());
			boutonOption[i].setBackground(backgroundColor);
			boutonOption[i].setForeground(foregroundColor);
		}

		// enlever le focus d'une option
		private void unFocusedButton(int i) {
			boutonOption[i].setBackground(foregroundColor);
			boutonOption[i].setForeground(backgroundColor);
		}
		


		/**
		 * Modifie les couleurs de fond et de premier plan pour les menus abstraits
		 * Cette fonction est appelée par la fonction "changeColor" de la classe "Preferences"
		 * à chaque fois que l'on presse F3 
		 * 
		 * Cette fonction peut être réécrite dans les classes filles si besoin
		 * @author LOGRE Ivan, MULLER Stephane, GUYADER Erwan, helen
		 **/
		public void changeColor() {
			// on est passé au jeu de couleurs suivant dans les préférences
			// on le récupère
			Preferences pref = Preferences.getData();
			foregroundColor = pref.getCurrentForegroundColor();
			backgroundColor = pref.getCurrentBackgroundColor();
			
			// et on met à jour le menu avec ces nouvelles couleurs
			//entête
			enteteBorder= new LineBorder(foregroundColor,8);
			entete.setForeground(foregroundColor);
			entete.setBackground(backgroundColor);
			entete.setBorder(enteteBorder);
			//label
			lb1.setForeground(foregroundColor);
			lb1.setBackground(backgroundColor);
			//panel
			this.setForeground(foregroundColor);
			this.setBackground(backgroundColor);
			//boutons
			this.buttonBorder= new LineBorder(backgroundColor,5);
			for (int i=0;i<boutonOption.length;i++) {
				if(i==optionCourante){
					boutonOption[i].setBackground(backgroundColor);
					boutonOption[i].setForeground(foregroundColor);
				}
				else{
					boutonOption[i].setBackground(foregroundColor);
					boutonOption[i].setForeground(backgroundColor);
				}
				boutonOption[i].setBorder(buttonBorder);
			}
		}
	 
		public void changeSize(){
			Font f = Preferences.getData().getCurrentFont();
			lb1.setFont(f);
			for (int i=0;i<boutonOption.length;i++) {
					boutonOption[i].setFont(f);
				}
		}

		/** gestion des touches
	     * ESC fait sortir de la fenêtre courante
	     * F1 invoque l'aide
	     * Cette méthode peut être surchargée par héritage pour réagir à d'autres touches
	     * (voir un exemple dans la classe Jeu)
	     */
	    public void keyPressed(KeyEvent e) {
	    	// toujours arrêter la voix courante quand l'utilisateur fait une action
			// F3 = on passe à la couleur suivante dans le jeu des 
	    	// couleurs défini dans Preferences
			if (e.getKeyCode() == KeyEvent.VK_F3) {
				Preferences.getData().changeColor();
			}
			// F4 = police suivante définie dans Preferences
			if (e.getKeyCode() == KeyEvent.VK_F4) {
				Preferences.getData().changeSize();
			}
			// F5 = voix suivante défini dans Preferences
			if (e.getKeyCode() == KeyEvent.VK_F4) {
				Preferences.getData().changeVoice();
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// méthode à rendre concrète par héritage
				lancerOption(optionCourante);  
			}
			// se déplacer dans les options vers le bas
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (optionCourante == -1) {
					optionCourante = 0;
					setFocusedButton(optionCourante);
				} else {
					unFocusedButton(optionCourante);
					optionCourante = (optionCourante + 1) % nbOption;
					setFocusedButton(optionCourante);
				}
			}
			// se déplacer dans les options vers le haut
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (optionCourante == -1) {
					optionCourante = 0;
					setFocusedButton(optionCourante);
				} else {
					unFocusedButton(optionCourante);
					optionCourante = optionCourante - 1;
					if (optionCourante == -1)
						optionCourante = nbOption - 1;
					setFocusedButton(optionCourante);
				}
			}

	    }
	    
	 public void refresh()
	 {
		 creerOption(nomOptions());
		 entete.validate();
		 entete.repaint();
	 }
}
