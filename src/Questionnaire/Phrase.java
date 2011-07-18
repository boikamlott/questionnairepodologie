/**
 * 
 */
package Questionnaire;

/**
 * @author yassine
 * 
 */
public class Phrase extends Ligne {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Questionnaire.ElementQuestionnaire#setNomListeReponse(java.lang.String)
	 * Ne fait rien, permet juste de construire les elements du questionnaire
	 * sans connaitre leur classes
	 */
	@Override
	public void setNomListeReponse(String TexteQuestion) {
		// TODO Auto-generated method stub
		System.err
				.println("t'es pas pret d'�tre le chez des dollars toi!!! Ce message ne devrait jamais arriv�, va donc perfectionn� tout ca!!!!");

	}

	@Override
	public void setValeurReponse(DoublonValeurReponse valeurReponse) {
		// TODO Auto-generated method stub
		System.err
				.println("t'as envie haruhi detruise le monde ou bien? Ce message ne devrait jamais arriv�, va donc perfectionn� tout ca!!!!");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Questionnaire.ElementQuestionnaire#isDonnee()
	 */
	@Override
	public boolean isDonnee() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Questionnaire.ElementQuestionnaire#setNomDeDonnee(java.lang.String)
	 */
	@Override
	public void setNomDeDonnee(String nomDeDonnee) {
		// TODO Auto-generated method stub
		System.err
				.println("t'as envie de te suicider ou quoi? Ce message ne devrait jamais arriv�, va donc perfectionn� tout ca!!!!");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Questionnaire.ElementQuestionnaire#getNomDeDonn�e()
	 */
	@Override
	public String getNomDeDonnee() {
		// TODO Auto-generated method stub
		System.err
				.println("t'as envie que sarko regagne les ellections ou quoi? Ce message ne devrait jamais arriv�, va donc perfectionn� tout ca!!!!");

		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pas de nom de donn�e car phrase - " + super.toString();
	}

}
