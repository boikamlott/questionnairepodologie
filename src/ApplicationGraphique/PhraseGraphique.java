package ApplicationGraphique;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Questionnaire.*;

public class PhraseGraphique extends LigneGraphique {

	public PhraseGraphique(int emplacementLigne, ElementQuestionnaire element,
			Questionnaire quest, int quantiteLigne) {
		super(quest, emplacementLigne, quantiteLigne);
		ligne = (Phrase) element;
		// System.err.println("phrase graphique : "+ligne.getTexteQuestion());
		phrase.setText("<html><center>" + ligne.getTexteQuestion()
				+ "</center></html>");
		// phrase.set
		phrase.setSize(width, height);
	}

	@Override
	public String getPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}