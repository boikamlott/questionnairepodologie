package ApplicationGraphique;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import Questionnaire.*;

public class QuestionLikerteGraphique extends LigneGraphique implements
		MouseListener, ActionListener {

	// ArrayList<testLikerte_old> listeBouton;
	ButtonGroup groupeDeBoutons = new ButtonGroup();
	JButton[] tabJButton = new JButton[5];

	/*
	 * JButton b0; JButton b1; JButton b2; JButton b3; JButton b4;
	 */

	public QuestionLikerteGraphique(int emplacementLigne,
			ElementQuestionnaire element, Questionnaire quest, int quantiteLigne) {
		super(quest, emplacementLigne, quantiteLigne);
		ligne = (QuestionLikerte) element;
		phrase.setText("<html><center>" + ligne.getTexteQuestion()
				+ "</center></html>");

		for (int i = 0; i < 5; i++) {
			// partie graphique
			// System.out.println("azertyuiopazertyuiop"+ligne.getTexteQuestion());
			// checkHorizontalKey

			// donnee passage a la ligne :
			int taillePolice = 12;
			// int largeurJButton = (width/10-2);
			int largeurJButton = (((width / 10) * 6) / 5) - 3;
			int taille_limite = (largeurJButton / taillePolice) * 2;
			String insertion = "";

			String texte_original = ((QuestionLikerte) ligne)
					.getValeurReponse().get(i).valeurDonneeAffichee.trim();// "Moyennement original";//

			insertion += "<html><center>";
			if (texte_original.length() < taille_limite) {
				insertion += texte_original;// +"</center></html>";
			} else {
				String[] texte1 = texte_original.split(" ");
				int nbCaractere = taille_limite;
				for (String mot : texte1) {
					if (mot.length() > taille_limite) {
						System.err.println("Le mot : " + mot
								+ "est trop long pour votre Jbutton!!");
						// on l'insere quand même, entre le reste de la division
						// entiere de taille_limite et l'intelligence humaine,
						// il devrait être possible de reconnaitre le mot!
						if (nbCaractere != taille_limite) {
							insertion += "<br>" + mot + "<br>";
						} else {
							insertion += "<br>" + mot + "<br>";
						}
						nbCaractere = taille_limite;
					} else {
						if (mot.length() < nbCaractere) {
							insertion += mot + " ";
							nbCaractere -= mot.length() + 1;
						} else {
							if (mot.length() == nbCaractere) {
								insertion += mot + "<br>";
								nbCaractere = taille_limite;
							} else {
								insertion += "<br>" + mot + " ";
								nbCaractere = taille_limite - 1 - mot.length();
							}
						}
					}
				}
				insertion += "</center></html>";
			}

			// System.out.println("insertion : "+insertion);
			// System.out.println("taille jbutton : "+largeurJButton+" taille JButton/taille police : "+largeurJButton/12);

			tabJButton[i] = new JButton();
			tabJButton[i].setText(insertion);// "<html>dixxxxxxxx<br>douzeeefefee<br>quatorzeeeeeee</html>");
			tabJButton[i].setBounds((width / 10) * 4 + ((largeurJButton) * i),
					0, largeurJButton, height);
			tabJButton[i].setBorder(null);
			tabJButton[i].addMouseListener(this);
			tabJButton[i].addActionListener(this);
			tabJButton[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN,
					taillePolice));
			groupeDeBoutons.add(tabJButton[i]);
			this.add(tabJButton[i]);
		}// fin for

		/*
		 * b0 = new JButton(); b0.setText(insertion);//
		 * "<html>dixxxxxxxx<br>douzeeefefee<br>quatorzeeeeeee</html>");
		 * b0.setBounds((width/2), 0, largeurJButton, height);
		 * b0.setBorder(null); b0.addMouseListener(this); b0.setFont(new
		 * Font(Font.SANS_SERIF,Font.PLAIN,12)); groupeDeBoutons.add(b0);
		 * this.add(b0);
		 * 
		 * 
		 * b1 = new JButton(((QuestionLikerte)
		 * ligne).getValeurReponse().get(1).valeurDonneeAffichee);
		 * b1.setBounds(width/2 + largeurJButton, 0, largeurJButton, height);
		 * b1.setBorder(null); b1.addMouseListener(this); b1.setFont(new
		 * Font(Font.SANS_SERIF,Font.PLAIN,10)); groupeDeBoutons.add(b2);
		 * this.add(b1);
		 * 
		 * b2 = new JButton(((QuestionLikerte)
		 * ligne).getValeurReponse().get(2).valeurDonneeAffichee);
		 * b2.setBounds(width/2 + largeurJButton*2, 0, largeurJButton, height);
		 * b2.setBorder(null); b2.setFont(new
		 * Font(Font.SANS_SERIF,Font.PLAIN,7)); b2.addMouseListener(this);
		 * groupeDeBoutons.add(b2); this.add(b2);
		 * 
		 * b3 = new JButton(((QuestionLikerte)
		 * ligne).getValeurReponse().get(3).valeurDonneeAffichee);
		 * b3.setBounds(width/2 + (width/10-3)*3, 0, (width/10-3), height);
		 * b3.setBorder(null); b3.addMouseListener(this);
		 * groupeDeBoutons.add(b3); this.add(b3);
		 * 
		 * b4 = new JButton(((QuestionLikerte)
		 * ligne).getValeurReponse().get(4).valeurDonneeAffichee);
		 * b4.setBounds(width/2 + (width/10-3)*4, 0, (width/10-3), height);
		 * b4.setBorder(null); b4.addMouseListener(this);
		 * groupeDeBoutons.add(b4); this.add(b4);
		 */
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		quest.questGraphique.verificationEllementActif();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		// System.out.println("azertyuiopazertyuiopaztyuiop");
		// this.setBackground(Color.RED);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("aaaaaaaaaazzzzzzzzzzeeeeeeeeeeerrrrrttttttyyyyyyyyyyuiopazertyuiopaztyuiop");

		quest.questGraphique.verificationEllementActif();
		for (int i = 0; i < 5; i++) {
			if (tabJButton[i] == e.getSource())// groupeDeBoutons.isSelected(tabJButton[i]));
			{
				// System.out.println("bouton selectionner : "+i);
				tabJButton[i].setBorderPainted(true);
				tabJButton[i].setBackground(Color.RED);
				((QuestionLikerte) ligne).setDonnee(((QuestionLikerte) ligne)
						.getValeurReponse().get(i).valeurDonneeSauvegardee
						.trim());
			} else {
				tabJButton[i].setBackground(null);
			}
		}

	}

}
