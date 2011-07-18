package Questionnaire;

import Application.Configuration;

public class Tripplon_questionnaire {

	// attribut => les 2 doublons d'objet pour permettre de réutilisez cette
	// classe
	private String nom_questionnaire;
	private int version_donnee;
	private int version_affichage;

	public Tripplon_questionnaire(String nom_questionnaire, int version_donnee,
			int version_affichage) {
		this.nom_questionnaire = nom_questionnaire;
		this.version_donnee = version_donnee;
		this.version_affichage = version_affichage;
	}

	public Tripplon_questionnaire(Tripplon_questionnaire questTriple) {
		nom_questionnaire = questTriple.getQuestionnaire();
		version_donnee = questTriple.getVersionDonnee();
		version_affichage = questTriple.getVersionAffichage();
	}

	// methode :
	public String getQuestionnaire() {
		return nom_questionnaire;
	}

	public int getVersionDonnee() {
		return version_donnee;
	}

	public int getVersionAffichage() {
		return version_affichage;
	}

	public void setQuestionnaire(String nom_questionnaire) {
		this.nom_questionnaire = nom_questionnaire;
	}

	public void setVersionDonnee(int version) {
		version_donnee = version;
	}

	public void setVersionAffichage(int version) {
		version_affichage = version;
	}

	public String getAdresseQuestionnaire() {
		return Configuration.getInstances().getAdresseQuestionnaire(this);
		// return
		// this.nom_questionnaire+"V_"+this.version_donnee+"_"+this.version_affichage+".xml";
	}

	public String getAdressedonneeQuestionnaire() {
		// TODO Auto-generated method stub
		return Configuration.getInstances().getAdresseDonneeQuestionnaire(this);
	}

}
