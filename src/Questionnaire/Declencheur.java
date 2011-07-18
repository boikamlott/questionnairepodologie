package Questionnaire;

public class Declencheur implements Cloneable {

	String nom_questionnaire = null;
	String nom_donnee = null;
	String valeur_donnee = null;

	public Declencheur(String nom_questionnaire, String nom_donnee,
			String valeur_donnee) {
		super();
		this.nom_questionnaire = nom_questionnaire;
		this.nom_donnee = nom_donnee;
		this.valeur_donnee = valeur_donnee;
	}

	public Declencheur() {
		super();
		nom_questionnaire = null;
		nom_donnee = null;
		valeur_donnee = null;
	}

	public String toString() {
		if (this.valeur_donnee == null) {
			return "nom quest : " + this.nom_questionnaire
					+ " et nom donnée : " + this.nom_donnee
					+ " et variable non initialisé!";
		} else {
			return "nom quest : " + this.nom_questionnaire
					+ " et nom donnée : " + this.nom_donnee + " et variable : "
					+ this.valeur_donnee;
		}
	}

	public Declencheur(Declencheur dec) {
		this.nom_questionnaire = dec.nom_questionnaire.toString();
		this.nom_donnee = dec.nom_donnee.toString();
		this.valeur_donnee = dec.valeur_donnee.toString();
	}

	public Object clone() {
		Object o = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la
			// méthode super.clone()
			o = super.clone();
		} catch (CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}

	/*
	 * public int getIdElement(Questionnaire quest) {
	 * 
	 * 
	 * 
	 * return 0; }
	 */

}
