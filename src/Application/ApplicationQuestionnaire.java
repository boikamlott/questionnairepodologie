package Application;

import java.util.ArrayList;
import java.util.List;

import Questionnaire.*;

/**
 * @author berger niclas pour SIDAS
 * 
 */
public class ApplicationQuestionnaire implements Application {

	/**
	 * @param args
	 */
        //Questionnaire general, toujours present
	public Questionnaire QG;
        //Liste
	public ArrayList<Questionnaire> liste_questionnaire = new ArrayList<Questionnaire>();
        //Identifiant de la personne
        public String idPersonne;

	@Override
	public void lancerIdentification(String nom, String prenom,
			Date dateDeNaissance) {
		idPersonne = GestionDonnee.getIdentifiant(nom, prenom, dateDeNaissance);
		if (idPersonne.compareTo("inconnu") == 0) {
			idPersonne = GestionDonnee.CreerPersonne(nom, prenom,
					dateDeNaissance);
		}
	}

	public boolean creerListeQuestionnaire() {
		//Creer la liste des questionnaires que le patient va devoir remplir,
		// a partir des donnees enregistrees
		// dans QG (questionnaire general => obligatoirement remplit) et des
		// declencheurs des differents questionnaires
		if (QG == null) {
			System.err.println("Le questionnaire general est inexistant");
			return false;
		}
		List<Tripplon_questionnaire> listQuestActifTemp = Configuration
				.getInstances().getListeQuestionnaireActif();
		boolean retour = false;
		for (Tripplon_questionnaire questTemp : listQuestActifTemp) {
			if ((questTemp.getQuestionnaire().compareTo("general") != 0)
					& (questTemp.getQuestionnaire().compareTo("medical") != 0)) {
				if (new Questionnaire(questTemp).isDeclenchable()) {
					liste_questionnaire.add(new Questionnaire(questTemp
							.getQuestionnaire(), idPersonne));
					retour = true;
				}
			}
		}
		return retour;
	}

        /**
         *   retour :
	     -1 signifiant questionnaire general
             0-n correspondant a l'identifiant dans liste questionnaire
         * @param type
         * @return 
         */
	public int nouveauQuestionnaire(String type) {
		if (type.compareTo("general") == 0) {
			QG = new Questionnaire(type, idPersonne);
			return -1;
		} else {
			liste_questionnaire.add(new Questionnaire(type, idPersonne));
			return liste_questionnaire.size() - 1;
		}
	}
}
