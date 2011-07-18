package Questionnaire;

import java.util.ArrayList;

import Application.*;
import ApplicationGraphique.*;

public abstract class ElementQuestionnaire {

	/**
	 * Cette classe est la classe mere d'un �l�ment d'un questionnaire Tout les
	 * �l�ments du questionnaire h�rite de cette classe.
	 * 
	 * @param declencheur
	 *            objet contenant les infos sur la valeur de la donn�e
	 *            declenchant l'apparition de cet �l�ment dans le questionnaire,
	 *            si il y a en as pas, alors ce parametre prend comme valeur
	 *            null
	 * @param enclencheur
	 *            booleen permettant de lancer (ou pas) une recherche pour
	 *            mettre a jour les objets qui change suivant
	 */
	public Questionnaire quest;

	public ArrayList<Declencheur> liste_declencheur = new ArrayList<Declencheur>();

	public void setDeclencheur(Declencheur dec) {
		liste_declencheur.add(new Declencheur(dec));
	}

	public void setDeclencheur(String quest, String nomDeDonnee,
			String valeurDonnee) {
		liste_declencheur
				.add(new Declencheur(quest, nomDeDonnee, valeurDonnee));
	}

	public void setDeclencheur(String quest, String nomDeDonnee) {
		liste_declencheur.add(new Declencheur(quest, nomDeDonnee, null));
	}

	public boolean elementActif() {

		// dynamique!!!!!!!!!!!!!!!!!!!
		// On le fait a partir de la liste de reponses temporaire et on appel la
		// methode a chaque fois que le clic sur un bouton!!!!!!!!!!!!!

		// test de faire un jLabel qui implements implements
		// MouseListener!!!!!!!!!!!!!!!!!!!!!!

		if (liste_declencheur.isEmpty()) {
			// System.out.println("Liste declencheur vide??$ C'st surement a faire!!!!!!!!!");
			return true;
		} else {

			boolean retour = false;
			for (Declencheur declen : liste_declencheur) {
				if (declen == null) {
					System.err
							.println("le declencheur ne devrait pas etre vide dans element actif!");
					return true;
				}
				if (declen.nom_questionnaire == "this") {
					declen.nom_questionnaire = this.quest.getQuest()
							.getQuestionnaire();
				}
				if (declen.nom_donnee == null) {
					System.err
							.println("Le nom de donn�e du declencheur ne devrait pas �tre vide!");
					return true;
				}
				if (declen.nom_questionnaire.compareTo(this.quest.getQuest()
						.getQuestionnaire()) == 0
						| declen.nom_questionnaire == "this") {

					if (this.quest.getValeurTemp(declen.nom_donnee) == null) {
						if (declen.valeur_donnee == null) {
							System.err
									.println("la veleur de la donn�e d'un declencheur non null ne devrait pas etre null!");
							return false;
						} else {
							// on attend que la donn�e est une valeur pour
							// decid� si oui ou non on l'affiche, par efautc non
							// (le cas ici prresent!)
							return false;
						}
					}
					if (this.quest.getValeurTemp(declen.nom_donnee).compareTo(
							declen.valeur_donnee) != 0) {
						// on est dans le cas du m�me questionnaire ou l'element
						// ne va pas etre declench�
						return false;
					} else {
						retour = true;
					}
				} else {
					// pas de else generique, un active pas un propri�t� a
					// partir d'une donn�e presente dans un autre questionnaire
					// ici on on active ce cas que si on �tait pas dans QG et
					// que la ddonn�e recherch� est dans qg!
					if (declen.nom_questionnaire == "general"
							| declen.nom_questionnaire == "this") {

						if (ApplicationGraphique.getInstance().appli.QG == null) {
							System.err
									.println("Le QG ne devrait pas etre null dans element actif");
						}
						if (ApplicationGraphique.getInstance().appli.QG
								.getValeurTemp(declen.nom_donnee).compareTo(
										declen.valeur_donnee) != 0) {
							// on est dans le cas du m�me questionnaire ou
							// l'element ne va pas etre declench�
							return false;
						} else {
							retour = true;
						}
					} else {
						// System.out.println(this.getClass().getSimpleName());
						System.err
								.println("Aucune ligne ou donn�e ne devrait etre activ� a partir d'un questionnaire autre que le general ou soit m�me!");
					}
					/*
					 * Questionnaire questCible =
					 * ApplicationGraphique.getInstance
					 * ().appli.getQuestionnaire(
					 * this.quest.getQuest().getQuestionnaire
					 * (),this.quest.getIdPersonne());
					 * //this.quest.appli.getQuestionnaire
					 * (this.quest.getQuest().
					 * getQuestionnaire(),this.quest.getIdPersonne()); if
					 * (questCible == null) { return false; }
					 * //if(declen.nom_questionnaire
					 * .compareTo(questCible.quest.getQuestionnaire())==0) //{
					 * else { if
					 * (declen.valeur_donnee.compareTo(questCible.getValeur
					 * (declen.valeur_donnee))!=0) { return false; } else {
					 * retour = true; } } //truc a faire icic? return false????
					 * ou pas!
					 */
				}
			}
			return retour; // equivalent a return true; obliatoirement
		}

	}

	// ArrayList<Enclencheur> enclencheur = new ArrayList<Enclencheur>();

	// int id_element;

	// methode

	// fonction necessaire a la cr�ation des objets
	public abstract void setNumeroDeLigne(int NumeroDeLigne);

	public abstract void setNumeroPage(int NumeroPage);

	public abstract void setTexteQuestion(String TexteQuestion);

	public abstract void setNomListeReponse(String TexteQuestion);

	public abstract void setValeurReponse(DoublonValeurReponse valeurReponse);

	// Fonction necessaire au traitement des donn�es
	public abstract boolean isDonnee();

	public abstract void setNomDeDonnee(String nomDeDonnee);

	public abstract String getNomDeDonnee();

	// necessaire a la r�cuperation des resultats
	// Application necessaire puisque chaque �lement ne connait pas ses
	// congeneres!
	public abstract String getReponse();// Application ap);
	// maintenant il le connait :

	public abstract void setQuestionnaire(Questionnaire quest);

	// necesssaire a l'interface graphique :
	// public abstract boolean isLigne

	public String toString() {
		if (liste_declencheur.isEmpty()) {
			return super.toString() + " salope!!!!!!!";
		} else {
			return "declencheur 1 : "
					+ liste_declencheur.get(0).nom_questionnaire
					+ liste_declencheur.get(0).nom_donnee
					+ liste_declencheur.get(0).valeur_donnee;
		}
	}

}

/*
 * 
 * package Questionnaire;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import Application.Application;
 * 
 * 
 * public interface ElementQuestionnaire {
 * 
 * /** Cette classe est la classe mere d'un �l�ment d'un questionnaire Tout les
 * �l�ments du questionnaire h�rite de cette classe.
 * 
 * @param declencheur objet contenant les infos sur la valeur de la donn�e
 * declenchant l'apparition de cet �l�ment dans le questionnaire, si il y a en
 * as pas, alors ce parametre prend comme valeur null
 * 
 * @param enclencheur booleen permettant de lancer (ou pas) une recherche pour
 * mettre a jour les objets qui change suivant
 * 
 * 
 * //ArrayList<Declencheur> declencheur = new ArrayList<Declencheur>();
 * //ArrayList<Enclencheur> enclencheur = new ArrayList<Enclencheur>();
 * 
 * //int id_element;
 * 
 * 
 * 
 * //methode
 * 
 * // fonction necessaire a la cr�ation des objets public abstract void
 * setNunmeroDeLigne(int NumeroDeLigne); public abstract void
 * setTexteQuestion(String TexteQuestion); public abstract void
 * setNomListeReponse(String TexteQuestion); public abstract void
 * setValeurReponse(DoublonValeurReponse valeurReponse);
 * 
 * //Fonction necessaire au traitement des donn�es public abstract boolean
 * isDonnee(); public abstract void setNomDeDonnee(String nomDeDonnee); public
 * abstract String getNomDeDonn�e();
 * 
 * //necessaire a la r�cuperation des resultats //Application necessaire puisque
 * chaque �lement ne connait pas ses congeneres! public String
 * getReponse();//Application ap); //maintenant il le connait : public abstract
 * void setQuestionnaire(Questionnaire quest);
 * 
 * 
 * public abstract String toString();
 * 
 * }
 * 
 * 
 * 
 * /* //constructeur public element(boolean enclencheur) { super();
 * this.enclencheur = enclencheur; } public element() { super(); } public
 * element(declencheur declencheur) { super(); this.declencheur = declencheur; }
 * public element(boolean enclencheur,declencheur declencheur) { super();
 * this.declencheur = declencheur; this.enclencheur = enclencheur; }
 */

