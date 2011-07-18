package Questionnaire;

public class Patient_complement {

	public String idPersonne;
	public String nom;
	public String prenom;
	public Date dateDeNaissance;

	// ajout informations adresse + medecin
	public String adresseRue;
	public String adresseVille;
	public String telFixe;
	public String telMobile;
	public String medecin;

	public Patient_complement(String idPersonne, String nom, String prenom,
			Date dateDeNaissance) {
		super();
		this.idPersonne = idPersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;

		adresseRue = null;
		adresseVille = null;
		telFixe = null;
		telMobile = null;
		medecin = null;
	}

	/*
	 * public Patient(String nom, String prenom, Date dateDeNaissance) {
	 * super(); this.nom = nom; this.prenom = prenom; this.dateDeNaissance =
	 * dateDeNaissance; }
	 */

	public Patient_complement(String idPersonne) {
		this.idPersonne = idPersonne;
		String[] temp = GestionDonnee.getInfoPersonne(idPersonne);
		nom = temp[0];
		prenom = temp[1];
		dateDeNaissance = new Date(temp[2]);

		adresseRue = temp[3];
		adresseVille = temp[4];
		telFixe = temp[5];
		telMobile = temp[6];
		medecin = temp[7];

	}

}
