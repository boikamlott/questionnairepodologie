package ApplicationGraphique;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;

import Questionnaire.DoublonNomDonneeValeurDonnee;
import Questionnaire.ElementQuestionnaire;
import Questionnaire.QuestionBinaire;
import Questionnaire.Questionnaire;

public class QuestionBinaireGraphique extends LigneGraphique implements
		MouseListener {

	// QuestionBinaire questBinaire; => dans ligne!!
	JButton oui;
	JButton non;

	public QuestionBinaireGraphique(int numeroDeLigne,
			ElementQuestionnaire element, Questionnaire quest, int quantiteLigne) {
		// System.out.println(quest.getnumeneroLigne);
		super(quest, numeroDeLigne, quantiteLigne);
		ligne = (QuestionBinaire) element;
		phrase.setText("<html><center>" + ligne.getTexteQuestion()
				+ "</center></html>");
		// numeroLigne = ligne.getNumeroDeLigne();

		// Mise en place des éléments graphique :

		// ButtonGroup
		ButtonGroup groupeDeBoutons = new ButtonGroup();

		// System.out.println("bbbbbbbbbbbbbbbbbbbbb"+((QuestionBinaire)
		// ligne).getValeurReponse().get(0).valeurDonneeAffichee);

		oui = new JButton(
				((QuestionBinaire) ligne).getValeurReponse().get(0).valeurDonneeAffichee);
		// oui.setBounds(width/2, 0, (width/4), height);
		oui.setBounds((width / 10) * 4, 0, (width / 10) * 3, height);
		oui.addMouseListener(this);
		groupeDeBoutons.add(oui);
		this.add(oui);

		non = new JButton(
				((QuestionBinaire) ligne).getValeurReponse().get(1).valeurDonneeAffichee);
		non.setBounds((width / 10) * 7, 0, (width / 10) * 3, height);
		non.addMouseListener(this);
		groupeDeBoutons.add(non);
		// groupeDeBoutons.
		this.add(non);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == oui) {
			// System.out.println("oui");
			oui.setBorder(BorderFactory.createLineBorder(Color.red));
			non.setBorder(null);
			// this.quest.setReponse(false, new
			// DoublonNomDonneeValeurDonnee(nom_de_donnee, valeurDonnee)))
			// System.out.println("vous avez présser oui et ca devrait enregistré cette donnée : "+((QuestionBinaire)
			// ligne).listeValeurReponse.get(0).valeurDonneeSauvegardee);
			((QuestionBinaire) ligne)
					.setDonnee(((QuestionBinaire) ligne).listeValeurReponse
							.get(0).valeurDonneeSauvegardee);

			quest.questGraphique.verificationEllementActif();

		}
		if (e.getSource() == non) {
			// System.out.println("non");
			// ((QuestionBinaire) ligne).setDonneeTemp(((QuestionBinaire)
			// ligne).listeValeurReponse.get(1).valeurDonneeSauvegardee);
			non.setBorder(BorderFactory.createLineBorder(Color.red));
			oui.setBorder(null);
			// System.out.println("vous avez présser oui et ca devrait enregistré cette donnée : "+((QuestionBinaire)
			// ligne).listeValeurReponse.get(0).valeurDonneeSauvegardee);
			((QuestionBinaire) ligne)
					.setDonnee(((QuestionBinaire) ligne).listeValeurReponse
							.get(1).valeurDonneeSauvegardee);

			quest.questGraphique.verificationEllementActif();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		quest.questGraphique.verificationEllementActif();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}
