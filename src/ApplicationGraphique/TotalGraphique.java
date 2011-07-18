package ApplicationGraphique;

import javax.swing.JLabel;
import javax.swing.JRadioButtonMenuItem;

import Questionnaire.*;

public class TotalGraphique extends LigneGraphique {

	String total = "Pas encore calculé";
	JLabel totalAffiche;

	public TotalGraphique(int emplacementLigne, ElementQuestionnaire element,
			Questionnaire quest, int quantiteLigne) {
		super(quest, emplacementLigne, quantiteLigne);
		ligne = (Total) element;
		phrase.setText("<html><center>" + ligne.getTexteQuestion()
				+ "</center></html>");

		totalAffiche = new JLabel();
		totalAffiche.setBounds((width / 2) - 1, 0, (width / 2) - 10, height);
		totalAffiche.setText("<html><center>" + total
				+ "<br /></center></html>");
		this.add(totalAffiche);

	}

	public void refresh() {
		total = ((Total) this.ligne).calculerDonnee();
		if (total != "-1") {
			this.totalAffiche.setText("<html><center>" + this.total
					+ "</center></html>");
		} else {
			total = "Pas encore calculé";
		}
	}

	@Override
	public String getPhrase() {
		// TODO Auto-generated method stub
		return "";
	}

}
