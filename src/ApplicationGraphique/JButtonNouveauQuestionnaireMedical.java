package ApplicationGraphique;

import javax.swing.JButton;

import Questionnaire.Patient;

public class JButtonNouveauQuestionnaireMedical extends JButton {
	public Patient pat;
	public String quest;

	public JButtonNouveauQuestionnaireMedical(Patient pat, String message,
			String quest) {
		super("<html><center>" + message + "<html><center>");
		this.quest = quest;
		this.pat = pat;
	}

}
