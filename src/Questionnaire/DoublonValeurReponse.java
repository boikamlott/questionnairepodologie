package Questionnaire;

public class DoublonValeurReponse {

	public String valeurDonneeSauvegardee;
	public String valeurDonneeAffichee;

	public DoublonValeurReponse(DoublonValeurReponse valeurReponse) {
		this.valeurDonneeAffichee = valeurReponse.valeurDonneeAffichee
				.toString();
		this.valeurDonneeSauvegardee = valeurReponse.valeurDonneeSauvegardee
				.toString();
	}

	public DoublonValeurReponse(String valeurDonneeSauvegardee,
			String valeurDonneeAffichee) {
		this.valeurDonneeAffichee = valeurDonneeAffichee;
		this.valeurDonneeSauvegardee = valeurDonneeSauvegardee;
	}

	public DoublonValeurReponse(String memeValeurDonnee) {
		this.valeurDonneeAffichee = memeValeurDonnee;
		this.valeurDonneeSauvegardee = memeValeurDonnee;
	}
}
