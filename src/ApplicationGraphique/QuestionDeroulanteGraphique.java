package ApplicationGraphique;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;

import Questionnaire.*;

public class QuestionDeroulanteGraphique extends LigneGraphique implements
		MouseListener {

	public JComboBox liste;

	public QuestionDeroulanteGraphique(int emplacementLigne,
			ElementQuestionnaire element, Questionnaire quest, int quantiteLigne) {
		super(quest, emplacementLigne, quantiteLigne);
		UIManager.put("ScrollBar.width", 40);
		ligne = (QuestionDeroulante) element;
		phrase.setText("<html><center>" + ligne.getTexteQuestion()
				+ "</center></html>");
		// phrase.setFont(new Font(null, Font.PLAIN, 20));

		// interface graphique
		Vector liste2 = new Vector<String>();
		liste2.addAll((Collection<String>) ((QuestionDeroulante) ligne)
				.getValeurReponse());
		liste = new JComboBox(liste2);
		liste.setFont(new Font(null, Font.PLAIN, 20));
		// liste.getEditor().
		int hauteur;
		// System.err.println("height question deroulante : "+height);
		if (height < 50) {
			hauteur = height;
		} else {
			hauteur = 25;
		}

		// System.err.println("hauteur"+hauteur);

		// liste.setBounds((width/10)*5, 0, ((width/10)*4), hauteur);
		liste.setBounds((width / 10) * 5, 5, ((width / 10) * 4), hauteur);
		liste.addMouseListener(this);

		// System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee : "+((QuestionDeroulante)
		// ligne).getNomListeReponse());

		// laid mais pas le temps :
		// preselection des liste deriulante
		if (((QuestionDeroulante) ligne).getNomListeReponse().compareTo(
				"deroulante_activité") == 0) {
			liste.setSelectedItem(new String("marche"));
		}
		if (((QuestionDeroulante) ligne).getNomListeReponse().compareTo(
				"deroulante_poids") == 0) {
			liste.setSelectedItem(new String("70"));
		}
		if (((QuestionDeroulante) ligne).getNomListeReponse().compareTo(
				"deroulante_taille") == 0) {
			liste.setSelectedItem(new String("170"));
		}

		this.add(liste);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == liste) {
			// System.out.println("liste");
			// System.out.println(this.liste.getSelectedItem().toString());

			// ((QuestionBinaire) ligne).setDonnee(((QuestionBinaire)
			// ligne).listeValeurReponse.get(0).valeurDonneeSauvegardee);

			quest.questGraphique.verificationEllementActif();

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		/*
		 * if (e.getSource() == liste) { System.out.println("liste");
		 * System.out.println(this.liste.getSelectedItem().toString());
		 * 
		 * // ((QuestionBinaire) ligne).setDonnee(((QuestionBinaire)
		 * ligne).listeValeurReponse.get(0).valeurDonneeSauvegardee); }
		 */
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == liste) {
			// System.out.println("liste");
			// System.out.println(this.liste.getSelectedItem().toString());

			quest.questGraphique.verificationEllementActif();

			// ((QuestionBinaire) ligne).setDonnee(((QuestionBinaire)
			// ligne).listeValeurReponse.get(0).valeurDonneeSauvegardee);
		}

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
