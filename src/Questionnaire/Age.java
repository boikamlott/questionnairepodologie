package Questionnaire;

public class Age extends DonneeCachee {

	@Override
	public String calculerDonnee() {
		// TODO Auto-generated method stub
		if (this.quest.getIdPersonne() == null) {
			System.err
					.println("Il n'y a pas de personne associé qu questionnaire, comment voulez vous calculer son age?");
			return "-1 idPersonne inconnu";
		} else {
			String[] donnee = GestionDonnee.getInfoPersonne(this.quest
					.getIdPersonne());
			// System.err.println("donnée : "+donnee[0]+" : "+donnee[1]+" : "+donnee[2]);

			Date datenaissance = new Date(donnee[2]);
			// System.out.println("date actuelle : "+new
			// java.sql.Date(System.currentTimeMillis()).toString());
			String temp[] = new java.sql.Date(System.currentTimeMillis())
					.toString().split("-");
			Date dateActuelle = new Date(Integer.parseInt(temp[2]),
					Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
			// System.out.println(dateActuelle.annee);
			// System.out.println("age : "+datenaissance.calculerAge(dateActuelle)+"------------------------------------");
			return datenaissance.calculerAge(dateActuelle).toString();

		}

	}

}
