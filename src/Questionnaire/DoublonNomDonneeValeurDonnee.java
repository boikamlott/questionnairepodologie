package Questionnaire;

public class DoublonNomDonneeValeurDonnee {

	public String nom_de_donnee;
	public String valeurDonnee;

	public DoublonNomDonneeValeurDonnee(String nom_de_donnee,
			String valeurDonnee) {
		super();
		this.nom_de_donnee = nom_de_donnee;
		this.valeurDonnee = valeurDonnee;
	}

	public DoublonNomDonneeValeurDonnee(String nom_de_donnee) {
		super();
		this.nom_de_donnee = nom_de_donnee;
		this.valeurDonnee = null;
	}

}
