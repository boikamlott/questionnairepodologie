package ApplicationGraphique;

import javax.swing.JButton;

import Questionnaire.InfoQuestExistant;

public class JButtonInfoQuestExistant extends JButton {

	public InfoQuestExistant info;

	public JButtonInfoQuestExistant(InfoQuestExistant info, int i) {
		String br = "";
		if (i < 10)
			br = "<br/>";
		this.setText("<html><center>Le patient " + info.pat.nom + " "
				+ info.pat.prenom + " né le "
				+ info.pat.dateDeNaissance.toString() + br
				+ " a passé le questionnaire "
				+ camelCaseToPhrase(info.questTriple.getQuestionnaire())
				+ " le " + info.datequest.toString() + ".</center></html>");
		this.info = info;
	}

	private String camelCaseToPhrase(String phrase) {
		String[] phraseMinus = phrase.split("[A-Z]");
		String[] phraseMajus = phrase.split("[a-z]");
		String retour = phraseMinus[0] + " ";
		int i = 1;
		for (int j = 0; j < phraseMajus.length; j++) {
			if (phraseMajus[j].compareTo("") != 0) {
				retour += phraseMajus[j].toLowerCase() + phraseMinus[i] + " ";
				i++;
			}
		}
		System.out.println("phrase : " + retour);

		return retour;
	}
}