package Questionnaire;

import java.util.Iterator;

//public abstract class Ligne implements ElementQuestionnaire{
//public abstract class Ligne extends ElementQuestionnaire{
public abstract class Ligne extends ElementQuestionnaire {

	int numeroLigne;
	int numeroPage;
	String texte;

	@Override
	public void setNumeroDeLigne(int numeroDeLigne) {
		this.numeroLigne = numeroDeLigne;
	}

	public int getNumeroDeLigne() {
		return this.numeroLigne;
	}

	@Override
	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}

	public int getNumeroPage() {
		return this.numeroPage;
	}

	@Override
	public void setTexteQuestion(String texteQuestion) {
		this.texte = texteQuestion;
	}

	public String getTexteQuestion() {
		return this.texte;

	}

	@Override
	public void setQuestionnaire(Questionnaire quest) {
		this.quest = quest;

	}

	@Override
	public String getReponse() {// Application ap) {
		// TODO Auto-generated method stub
		if (!isDonnee()) {
			return null;
		} else {
			Iterator<DoublonNomDonneeValeurDonnee> i = quest.liste_reponse
					.iterator();
			while (i.hasNext()) {
				DoublonNomDonneeValeurDonnee courant = i.next();
				if (courant.nom_de_donnee.compareTo(this.getNomDeDonnee()) == 0) {
					return courant.valeurDonnee;
				}
			}
			return null;
		}
	}

	@Override
	public String toString() {

		return getNumeroDeLigne() + " - " + getTexteQuestion() + " - "
				+ getClass() + super.toString();
	}

	// a voir lors de la cr�ation de l'interface graphique!!
	// abstract void affiche();

}
