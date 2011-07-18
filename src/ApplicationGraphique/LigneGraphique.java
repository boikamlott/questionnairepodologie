package ApplicationGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import Application.Configuration;
import Questionnaire.*;

public abstract class LigneGraphique extends JPanel// implements MouseListener
{

	public Questionnaire quest;
	public Ligne ligne;
	int numeroLigne;
	int y;
	int width;
	int height;
	JLabel phrase;

	public abstract String getPhrase();

	public LigneGraphique(Questionnaire q, int i, int quantiteLigne)// , int
																	// quantit�Ligne)
																	// int
																	// numeroLigne)//
	{

		quest = q;
		numeroLigne = i;
		// System.out.println("quest.questGraphique.listePage.size()"+quest.questGraphique.listePage.size()+"ligne.getNumeroPage()"+ligne.getNumeroPage());
		// int quantiteLigne =
		// quest.questGraphique.listePage.get(ligne.getNumeroPage()).quantiteLigne;
		// System.err.println("numero de ligne : "+numeroLigne);

		this.setLayout(null);

		// System.out.println("qqqqqqqqqquuuuuuuessssssssssssssstttttttttte"+q.toString());
		y = (numeroLigne
				* Configuration.getInstances().getHauteurApplication() / (quantiteLigne + 2)) - 30;// (questGraphique.quantiteLigne+2);
		width = Configuration.getInstances().getLargeurApplication();// -10;
		height = Configuration.getInstances().getHauteurApplication()
				/ (quantiteLigne + 2);// questGraphique.quantiteLigne+2);
		// System.err.println("height ligne graphique : "+height);

		// System.out.println("wwwwwwwwwwwwaaaaaaaaaazzzzzzzzzzzzaaaaaaaaaaaaaaa"+height);
		this.setBounds(5, y, width, height);

		// /---------- Phrases presente dans tout les cas -----------------
		// --------------------------------------

		phrase = new JLabel();
		phrase.setFont(new Font(null, Font.PLAIN, 16));
		phrase.setSize((width / 10) * 4, height);
		phrase.setVisible(true);
		this.add(phrase);

	}

}

// public int getNumeroLi
