package Questionnaire;

public class Patient implements Comparable {

	public String idPersonne;
	public String nom;
	public String prenom;
	public Date dateDeNaissance;

	public Patient(String idPersonne, String nom, String prenom,
			Date dateDeNaissance) {
		super();
		this.idPersonne = idPersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
	}

	public Patient(String idPersonne) {
		this.idPersonne = idPersonne;
		String[] temp = GestionDonnee.getInfoPersonne(idPersonne);
		nom = temp[0];
		prenom = temp[1];
		dateDeNaissance = new Date(temp[2]);
	}

	@Override
	public int compareTo(Object compare) {
		if (compare instanceof Patient)
			return this.idPersonne.compareTo(((Patient) compare).idPersonne);
		return -100;
	}

}
