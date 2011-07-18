/**
 * 
 */
package Application;

import java.util.ArrayList;
import java.util.List;

import Questionnaire.Date;
import Questionnaire.GestionDonnee;
import Questionnaire.Patient;
import Questionnaire.Questionnaire;

/**
 * @author berger Nicolas
 * 
 */
public class ApplicationPodologue implements Application {

	/**
	 * 
	 **/

	String podo;// nom du podologue sur l'application
	String mode;
	List<Questionnaire> listeQuest;
	Patient patient;// identifiant du patient en cours de traitement

	public ApplicationPodologue() {
		// TODO Auto-generated constructor stub
		podo = Configuration.getInstances().getNomPodologue();
		listeQuest = new ArrayList<Questionnaire>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Application.Application#lancerIdentification() renvoie l'identifiant
	 * du dernier patient qui a rempli un questionnaire :
	 */
	/*
	 * @Override public void lancerIdentification() { // TODO Auto-generated
	 * method stub
	 * 
	 * //a faire
	 * 
	 * //pour chaque questionnaire actif, aller voir la date du dernier
	 * questionnaire et comparer // Dans un premier temps, la derniere ligne du
	 * questionnaire g�n�rale devrait suffir!! this.patient =
	 * GestionDonnee.getDernierPatient();//this.podo);
	 * 
	 * }
	 * 
	 * /* (non-Javadoc)
	 * 
	 * @see Application.Application#getTypeApplication()
	 */
	// @Override
	public String getTypeApplication() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Application.Application#getTypeApplication() Fonction retournant une
	 * liste de tout les questionnaires d'un patient
	 */
	public ArrayList<Questionnaire> getDossier(String idPersonne) {
		// TODO Auto-generated method stub

		// pour chaque questionnaire (pass� et present)
		// recherch� dans le fichier si idPersonne present

		return null;
	}

	// @Override
	public Questionnaire getQuestionnaire(String questionnaire,
			String idPersonne) {
		for (Questionnaire quest : listeQuest) {
			if (quest.getQuest().getQuestionnaire().compareTo(questionnaire) == 0
					& quest.getIdPersonne().compareTo(idPersonne) == 0) {
				return quest;
			}

		}
		return null;
	}

	// main
	public static void main(String[] args) {

	}

	@Override
	public void lancerIdentification(String nom, String prenom,
			Date dateDeNaissance) {
		// TODO Auto-generated method stub

	}

}
