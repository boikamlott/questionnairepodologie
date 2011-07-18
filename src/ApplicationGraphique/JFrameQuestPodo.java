package ApplicationGraphique;

import javax.swing.JFrame;

import Application.Configuration;
import Questionnaire.Date;
import Questionnaire.Patient;
import Questionnaire.Questionnaire;
import Questionnaire.Tripplon_questionnaire;

public class JFrameQuestPodo extends JFrame {
	public Questionnaire quest;

	public JFrameQuestPodo() {
		this.setLayout(null);
		this.setSize(Configuration.getInstances().getLargeurApplication(),
				Configuration.getInstances().getHauteurApplication());
		this.setResizable(true);
	}

	public void ajout(Tripplon_questionnaire questTriple, Patient patient,
			Date dateQuestionnaire) {
		quest = new Questionnaire(questTriple, patient, dateQuestionnaire);
		quest.questGraphique.validation.questFrame = this;
		// quest.setPrecompletion(true);
		this.add(quest.questGraphique);
		// this.quest.calculerDonneeToListeTemp();
		// this.quest.questGraphique.setPreSelection(quest);
		this.quest.questGraphique.forcerPrecompletion(quest);
		// enlever message pop up chiant truc deja remple!

		this.setVisible(true);
	}

	public void ajout(String string, Patient pat) {
		quest = new Questionnaire(string, pat.idPersonne);
		quest.questGraphique.validation.questFrame = this;
		this.quest.miseAjour = false;
		this.add(quest.questGraphique);
		this.setVisible(true);

	}
}
