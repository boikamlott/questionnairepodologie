package Questionnaire;

import java.io.File;
import java.util.Scanner;

import Application.*;
import ApplicationGraphique.*;

public class Imc extends DonneeCachee {

	@Override
	public String calculerDonnee() {

		// poids / taille au carré
		float taille = 0;
		float poids = 0;
		for (Declencheur dec : liste_declencheur) {
			// System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttttttt : "+dec.nom_donnee
			// +dec.nom_questionnaire+dec.valeur_donnee);
			// System.out.println("comparaison de taille avec "+dec.nom_donnee);

			if (dec.nom_donnee.compareTo("taille") == 0) {
				//
				if (dec.nom_questionnaire.compareTo("general") == 0) {

					taille = Float.parseFloat(this.quest
							.getValeurTemp(dec.nom_donnee));

					// System.out.println("comparaison de general avec "+dec.nom_questionnaire);

					/*
					 * if (dec.nom_questionnaire.compareTo("general")==0) { //
					 * System
					 * .out.println("1111111122222222223333333333333 "+dec.
					 * valeur_donnee); if
					 * (this.quest.getQuest().getQuestionnaire
					 * ().compareTo("general")==0) { //dans valeur donnee est
					 * enregistré le nom actuelle de la donnée taille! //taille
					 * =
					 * Float.parseFloat(this.quest.getValeurTemp(dec.valeur_donnee
					 * )); taille =
					 * Integer.parseInt(this.quest.getValeurTemp(dec
					 * .valeur_donnee));
					 * 
					 * } else { taille =
					 * Float.parseFloat(ApplicationGraphique.getInstance
					 * ().appli.QG.getValeurTemp(dec.valeur_donnee)); }
					 */
				} else {
					System.out
							.println("jhgfezff quest ce que tu fout la!!!!!!!!!");
					// else inactif pour le moment! peut être dans une future
					// version
				}
				// ps : risque de beugué si taille ou pas est changé par le
				// podo...
			}
			if (dec.nom_donnee.compareTo("poids") == 0) {
				if (dec.nom_questionnaire.compareTo("general") == 0) {

					int p = Integer.parseInt(this.quest
							.getValeurTemp(dec.nom_donnee));
					poids = Float.parseFloat(this.quest
							.getValeurTemp(dec.nom_donnee));
					// System.out.println("int : "+p+" float : "+poids);

					/*
					 * if (dec.nom_questionnaire.compareTo("general")==0) { if
					 * (this
					 * .quest.getQuest().getQuestionnaire().compareTo("general"
					 * )==0) { poids =
					 * Float.parseFloat(this.quest.getValeurTemp(
					 * dec.valeur_donnee)); } else {
					 */
					// poids =
					// Float.parseFloat(ApplicationGraphique.getInstance().appli.QG.getValeurTemp(dec.valeur_donnee));
					// }
				} else {
					// else inactif pour le moment! peut être dans une future
					// version
					System.err.println("qu'est ce que tu fout la!!!!!!!!!");

				}
			}
		}
		// System.err.println("poids : "+poids+" taille : "+taille +
		// ": "+(taille*taille));
		Float tempo = poids / ((taille / 100) * (taille / 100));
		// System.err.println("ttttttttttteeeeeeeeemmmmmmmmmpppppppppoooooo : "+Float.toString(tempo));

		// au secours!

		// ultra laid!!!!!!

		// //mais marre des bugs de cette connasse d'aplication :

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (DoublonNomDonneeValeurDonnee d : this.quest.liste_reponse_temp) {
			// System.err.println("bfkjsdghjksdgvjksdhkjghsdk,ghjkdsgjgsdgkjsdgkjsdqgsqgdvgsdqkjgbvsdqjvgbsdkjbgvkjsdqbv,bsdkjqvlkjbsqdkjvlbsqjlkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
			if (d.nom_de_donnee == "imc") {
				d.valeurDonnee = Float.toString(tempo);
				// System.err.println("merdasse qui marche!!!!!!!");
			}
		}

		return Float.toString(tempo);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " imc : " + this.calculerDonnee();
	}

}
