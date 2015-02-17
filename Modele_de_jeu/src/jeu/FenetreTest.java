package jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import devintAPI.FenetreAbstraite;

public class FenetreTest extends FenetreAbstraite implements ActionListener{

	private JLabel labelT;
	
	private JTextField textZone;
	
	private JPanel panel;
	
	private JButton flemme;
	
	public FenetreTest(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		voix.playText(textZone.getText());
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		
		 panel = new JPanel();
		 panel.setBackground(Color.blue);
		 panel.setLayout(new GridLayout(3,1));
		 textZone = new JTextField();
		 textZone.addActionListener(this);
		 
		 labelT = new JLabel("Ceci est un texte");
		 panel.add(textZone);
		 panel.add(labelT);
		 
		 flemme = new JButton("Bouton wav");
		 flemme.addActionListener(this);
		 
		 panel.add(flemme);
		 
		 this.add(panel);
		 
	}

	@Override
	protected String wavAide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		Random r = new Random();
		panel.setBackground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
	}

	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
		Random r = new Random();
		labelT.setFont(new Font("Comis sans ms", 1, r.nextInt(50)));
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
	

}
