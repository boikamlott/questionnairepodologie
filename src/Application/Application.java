package Application;

import Questionnaire.Date;
import Questionnaire.Questionnaire;

public interface Application {

	// cas podo = nom Podologue dans fichier de conf
	// cas serveur => lance l'identification du patient
	void lancerIdentification(String nom, String prenom, Date dateDeNaissance);

	// renvoie serveur ou podo
	// renvoie getClasse de l'objet!
	// Application getTypeApplication();
	// String getTypeApplication();

	// Questionnaire getQuestionnaire(String questionnaire, String idPersonne);

}
