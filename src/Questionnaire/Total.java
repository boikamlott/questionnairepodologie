package Questionnaire;

import ApplicationGraphique.ApplicationGraphique;
import ApplicationGraphique.LigneGraphique;
import ApplicationGraphique.QuestionLikerteGraphique;

public class Total extends Phrase implements DonneeCalculee {

	String nomDeDonnee;

	@Override
	public void setNomDeDonnee(String nomDeDonnee) {

		this.nomDeDonnee = nomDeDonnee;
	}

	@Override
	public String getNomDeDonnee() {
		return nomDeDonnee;
	}

	@Override
	public boolean isDonnee() {
		return true;
	}

	@Override
	public String getReponse() {
		return this.calculerDonnee().toString();
	}

	@Override
	public String calculerDonnee() {
		Integer total = 0;
		if (liste_declencheur.get(0).nom_donnee.compareTo("all") == 0) {
			for (int l = 1; l < quest.nombrePage + 1; l++) {
				for (LigneGraphique ligne : this.quest.questGraphique.listeDePageDeLigneGraphique
						.get(l)) {
					if (ligne instanceof QuestionLikerteGraphique) {
						try {
							int temp = Integer
									.parseInt(this.quest
											.getValeurTemp(ligne.ligne
													.getNomDeDonnee()));
							total += temp;
						} catch (java.lang.NumberFormatException e) {
							// System.out.println("hello");
						}
					}
				}
			}
		} else {
			for (Declencheur dec : liste_declencheur) {
				// System.out.println("azerty");
				if (dec.nom_questionnaire.compareTo("general") != 0) {
					try {
						total += Integer.parseInt(this.quest
								.getValeurTemp(dec.nom_donnee));
					} catch (Exception e) {
						return "-1";
						// e.printStackTrace();
					}
					// System.out.println(dec.toString());
				} else {
					try {
						total += Integer.parseInt(ApplicationGraphique
								.getInstance().appli.QG
								.getValeurTemp(dec.nom_donnee));
					} catch (Exception e) {
						// System.err.println("liste reponse temp de questionnaire genera incomplete : erreur chop� et total inachev�!");
						return "-1";
					}
				}
			}
		}
		// System.out.println("Total calcul� : "+total);
		return total.toString();
	}

	@Override
	public String toString() {
		return getNomDeDonnee() + " - " + super.toString();
	}

}
