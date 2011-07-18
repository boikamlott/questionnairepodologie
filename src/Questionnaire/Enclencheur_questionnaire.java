package Questionnaire;

public class Enclencheur_questionnaire implements Enclencheur {

	private String nom_questionnaire;
	Declencheur declencheur;

	// constructeur
	public Enclencheur_questionnaire(String nom_questionnaire_enclenche,
			String nom_questionnaire_declencheur, String nom_donnee,
			String valeur_donnee) {
		super();
		this.nom_questionnaire = nom_questionnaire_enclenche;
		declencheur = new Declencheur(nom_questionnaire_declencheur,
				nom_donnee, valeur_donnee);
	}

	public Enclencheur_questionnaire(String nom_questionnaire_enclenche,
			Declencheur dec) {
		super();
		this.nom_questionnaire = nom_questionnaire_enclenche;
		declencheur = (Declencheur) dec.clone();
	}

	// methodes
	@Override
	public String getQuestionnaire() {
		// TODO Auto-generated method stub
		return this.nom_questionnaire;
	}

	@Override
	public void setQuestionnaire(String nom_questionnaire) {
		// TODO Auto-generated method stub
		this.nom_questionnaire = nom_questionnaire;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Declencheur dec = new Declencheur("general", "nom_donne", "valeur");
		System.out.println(dec.nom_donnee);
		Enclencheur_questionnaire enc = new Enclencheur_questionnaire("quest",
				dec);
		dec.nom_donnee = "jhfhjgevjhfgv";
		System.out.println(enc.declencheur.nom_donnee);
		System.out.println(dec.getClass().getName());// CgetDeclaredClasses().length);
		/*
		 * Class<?>[] classes = dec.getClass().getClasses().length;
		 * System.out.println(classes.length); for (int i=0 ; i < classes.length
		 * ; i++ ) { System.out.println(classes[i].getName());
		 * 
		 * 
		 * }
		 */

	}

}
