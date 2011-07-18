package Questionnaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


import Application.Configuration;

public class GestionDonnee {

	// public static String chemin;

	public GestionDonnee() {
		super();
		// chemin = Configuration.getInstances().getChemin_partage();
	}

	public static String getIdentifiant(String nom, String prenom,
			Date dateDeNaissance) {
		File bdd;
		String retour = new String();
		boolean trouve = false;
		try {
			bdd = new File(Configuration.getInstances().getCheminPartage()
					+ "bdd/personne.csv");
			BufferedReader buff = new BufferedReader(new FileReader(bdd));
			String ligne = buff.readLine();

			while (ligne != null) {
				// System.out.println("gerer ces putains de dates!!!!!!!!!!!!!");

				String[] elementLigne = ligne.split(";");
				// element 0 est la date
				if (elementLigne[1].trim().toLowerCase()
						.compareTo(nom.trim().toLowerCase()) == 0) {
					// System.out.println("!!!!!!!!!!!!!");
					if (elementLigne[2].trim().toLowerCase()
							.compareTo(prenom.trim().toLowerCase()) == 0) {
						// System.err.println("eeeeeeeeeeeeeeee");

						if (elementLigne[3].trim().toLowerCase()
								.compareTo(dateDeNaissance.affiche) == 0) {
							trouve = true;
							ligne = null;
							retour = elementLigne[0];
						} else {
							ligne = buff.readLine();
						}
					} else {
						ligne = buff.readLine();
					}

				} else {
					ligne = buff.readLine();
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		if (trouve) {
			return retour;
		} else {
			return "inconnu";
		}
	}

	/*
	 * element 0 : nom element 1 prenom element 2 : date de naissance
	 */
	public static String[] getInfoPersonne(String idPersonne) {
		File bdd;
		String[] retour = new String[3];
		boolean trouve = false;
		try {
			bdd = new File(Configuration.getInstances().getCheminPartage()
					+ "bdd/personne.csv");
			BufferedReader buff = new BufferedReader(new FileReader(bdd));
			String ligne = buff.readLine();

			while (ligne != null) {
				String[] elementLigne = ligne.split(";");
				if (elementLigne[0].compareTo(idPersonne) == 0) {
					retour[0] = elementLigne[1];// nom
					retour[1] = elementLigne[2];// prenom
					retour[2] = elementLigne[3];// date_de_naissance
					trouve = true;
					ligne = null;
				} else {
					ligne = buff.readLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (trouve) {
			// System.out.println("trouv�����������!!!!!!!!");
			return retour;
		} else {
			// System.out.println("Pas trouv�!!!!!!!!");
			return null;
		}
	}

	public static String CreerPersonne(String nom, String prenom,
			Date dateDeNaissance) {
		System.out.println(Configuration.getInstances().getCheminPartage());

		// sysdateDeNaissance.toString()
		String idPersonne = "";// = "" pour eclipse qui fait "chier"
		boolean pasNouveau = true;
		while (pasNouveau) {
			idPersonne = "";
			idPersonne = stringAleatoire5lettres();
			// System.out.println(idPersonne);
			System.out.println(idPersonne);
			if (getInfoPersonne(idPersonne) == null) {
				// System.out.println("boucle");

				pasNouveau = false;
				List<DoublonNomDonneeValeurDonnee> listPersonne = new ArrayList<DoublonNomDonneeValeurDonnee>();
				listPersonne.add(new DoublonNomDonneeValeurDonnee("idPersonne",
						idPersonne));
				listPersonne.add(new DoublonNomDonneeValeurDonnee("nom", nom));
				listPersonne.add(new DoublonNomDonneeValeurDonnee("prenom",
						prenom));
				listPersonne.add(new DoublonNomDonneeValeurDonnee(
						"date_de_naissance", dateDeNaissance.affiche));
				insertInto(Configuration.getInstances().getCheminPartage()
						+ "bdd/personne.csv", idPersonne, new Date(),
						listPersonne);
			}
		}
		return idPersonne;

		// TODO Auto-generated method stub

	}

	public static void insertInto(String chemin, String idPersonne,
			Date dateQuest, List<DoublonNomDonneeValeurDonnee> listInsertion) {
		// modif : remplace nombdd par chemin, le chemin dependant de la
		// derniere version... ou pas!

		// on ouvre le fichier
		File bdd;
		// System.out.println("chemin de la bdd : "+chemin);
		try {
			bdd = new File(chemin);

			if (!bdd.exists() & bdd.length() == 0) {
				// Cas fichier inexisant!
				System.err.println("Cas fichier bdd inexistantes!!!!!!");

			}
			// fin d'utilisation de bdd
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// on insert les donn�es dans le fichier correspondant :
		int tailleTable = 0;
		List<String> formatBDD = new ArrayList<String>(10);
		String line = "";
		try {
			line = new BufferedReader(new FileReader(chemin)).readLine();
		} catch (FileNotFoundException e) {
			creerFichierBdd(chemin, listInsertion);
			System.out
					.println("Le fichier n'as pas �t� trouv�, il vient donc d'�tre cr�er!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] ligne = line.split(";");
		// on cr� le string que l'on va inserer en fonction des donn�es
		// recuillit :
		// Il commence toujour par les "cles primaires" des questionnaire : la
		// date du jour et l'identifiant de la personne
		String insertion = "";
		if (chemin.contains("personne")) {
			// System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			for (DoublonNomDonneeValeurDonnee d : listInsertion) {
				// System.out.println(d.valeurDonnee);
				insertion += d.valeurDonnee + ";";
			}
		} else {
			// System.out.println("eeeeeeeeeee : "+chemin+" : eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

			insertion = creerLigneDonnee(idPersonne, dateQuest, listInsertion,
					ligne);

		}

		FileWriter writer = null;
		// System.out.println("configuration X-X-X : "+Configuration.getInstances().getChemin_partage());
		// System.out.println("debut de l'eeeeeeeeeecriture sur : "+Configuration.getInstances().getAdresseDonneeQuestionnaire(nombdd));
		try {
			writer = new FileWriter(chemin, true);
			writer.write(System.getProperty("line.separator") + insertion, 0,
					insertion.length() + 2);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// eclipse le veut absolument... pas du tout logique ca!
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("erreur byzarre eclipse!");
				e.printStackTrace();
			}
		}
		// System.out.println("ffffffffffffffffffffffffffffffffff");

	}

	private static void creerFichierBdd(String chemin,
			List<DoublonNomDonneeValeurDonnee> listInsertion) {
		// TODO Auto-generated method stub
		// on cr� les fichiers de base de donn�es si il n'existe pas
		File bdd = new File(chemin);
		FileWriter writer = null;
		// bdd = new File(chemin);
		try {
			bdd.createNewFile();

			String insertion = "";
			// fichier en unique exemplaire... donc pas de cr�ation automatique
			// ici!
			/*
			 * if (nombdd=="personne") { insertion =
			 * "idPersonne;nom;prenom;date_de_naissance;"; } else {
			 */
			insertion = "idPersonne;date;";
			// }
			for (DoublonNomDonneeValeurDonnee i : listInsertion) {
				if (i.nom_de_donnee != "nom" & i.nom_de_donnee != "prenom"
						& i.nom_de_donnee != "date"
						& i.nom_de_donnee != "idPersonne")
					insertion += i.nom_de_donnee + ";";
			}
			try {
				writer = new FileWriter(bdd);
				// writer.append(System.getProperty("line.separator"));
				writer.write(insertion, 0, insertion.length());
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String stringAleatoire5lettres() {
		String consonnes = "bcdfghjklmnpqrstvwxz";
		String voyelles = "aeiouy";
		int number_voyelles = 5;
		int number_consonnes = 19;
		Random random = new Random(System.currentTimeMillis());
		String idPersonne = "";

		idPersonne += consonnes.charAt(random.nextInt(number_consonnes));
		idPersonne += voyelles.charAt(random.nextInt(number_voyelles));
		idPersonne += consonnes.charAt(random.nextInt(number_consonnes));
		idPersonne += voyelles.charAt(random.nextInt(number_voyelles));
		idPersonne += consonnes.charAt(random.nextInt(number_consonnes));
		return idPersonne;
	}

	// public static Date getDateDernierQuestionnaireVersionActuel()
	public static Date getDateDernierQuestionnaireVersionDonneeActuel(
			String nom_questionnaire, String idPersonne) {
		// TODO Auto-generated method stub
		return getDateDernierQuestionnaire(Configuration.getInstances()
				.getAdresseDonneeQuestionnaire(nom_questionnaire), idPersonne);
	}

	public static Date getDateDernierQuestionnaire(String chemin,
			String idPersonne) {
		// TODO Auto-generated method stub
		FileReader bdd = null;
		Date retour = null;
		try {
			// System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb chemin : "+chemin
			// );
			bdd = new FileReader(chemin);
			BufferedReader buff = new BufferedReader(bdd);
			String ligne = buff.readLine();
			while (ligne != null) {
				String[] kk = ligne.split(";");
				if (kk[0].compareTo(idPersonne) == 0) {
					if (retour == null) {
						retour = new Date(kk[1]);
					} else {
						Date temp = new Date(kk[1]);
						if (retour.compareTo(temp) == 1) {
							retour = new Date(temp);
						}
					}
				}
				ligne = buff.readLine();
			}
			buff.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static Date getDateDernierQuestionnaireToutesVersion(
			String nom_questionnaire, String idPersonne) {
		// TODO Auto-generated method stub
		int version = Configuration.getInstances()
				.getVersionDonneeQuestionnaire(nom_questionnaire);
		Date retour = null;
		for (int i = 1; i < version + 1; i++) {
			if (retour == null) {
				retour = getDateDernierQuestionnaire(Configuration
						.getInstances().getCheminPartage()
						+ "/bdd/"
						+ nom_questionnaire + "_V" + i + ".csv", idPersonne);
			} else {
				Date temp = getDateDernierQuestionnaire(Configuration
						.getInstances().getCheminPartage()
						+ "/bdd/"
						+ nom_questionnaire + "_V" + i + ".csv", idPersonne);

				if (temp != null) {
					// System.err.println(temp.affiche);
					// System.err.println(retour.affiche);
					if (retour.compareTo(temp) == 1) {
						retour = new Date(temp);
					}
				}
			}

		}
		return retour;
	}

	/*
	 * @return : Une liste contenant les donn�es enregistr� dans la bdd a
	 * finir!!!!!!!
	 */
	public static ArrayList<DoublonNomDonneeValeurDonnee> getDonnees(
			String nom_questionnaire, String idPersonne, Date dateQuest) {
		// TODO Auto-generated method stub
		ArrayList<DoublonNomDonneeValeurDonnee> retour = new ArrayList<DoublonNomDonneeValeurDonnee>();

		// on ouvre le fichier
		FileReader bdd = null;
		String tableauNomDonnee[];
		String tableauValeurDonnee[];

		try {
			bdd = new FileReader(Configuration.getInstances()
					.getAdresseDonneeQuestionnaire(nom_questionnaire));
			BufferedReader buff = new BufferedReader(bdd);
			String ligne = buff.readLine();
			tableauNomDonnee = ligne.split(";");
			ligne = buff.readLine();
			while (ligne != null) {
				tableauValeurDonnee = ligne.split(";");
				if ((tableauValeurDonnee[0].compareTo(idPersonne) == 0 & tableauValeurDonnee[1]
						.compareTo(dateQuest.affiche) == 0)) {
					// on stocke la ligne correspondante a "idPersonne" et a
					// "dateQuest"
					// puis on remplit directement la liste qu'on retourne
					for (int i = 0; i < tableauNomDonnee.length; i++) {
						if (tableauValeurDonnee[i] != "")// ou null, a voir ce
															// que renvoie la
															// mehode de parsing
															// avec ;;
						{
							if (tableauNomDonnee[i] == "") {
								retour.add(new DoublonNomDonneeValeurDonnee(
										tableauNomDonnee[i], null));
							} else {
								retour.add(new DoublonNomDonneeValeurDonnee(
										tableauNomDonnee[i],
										tableauValeurDonnee[i]));
							}
						}
					}
					buff.close();
					return retour;
				} else {
					ligne = buff.readLine();
				}
			}
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<DoublonNomDonneeValeurDonnee> getOldDonnees(
			String nom_questionnaire, String idPersonne, Date dateQuest) {
		// TODO Auto-generated method stub
		ArrayList<DoublonNomDonneeValeurDonnee> retour = new ArrayList<DoublonNomDonneeValeurDonnee>();

		// on ouvre le fichier
		FileReader bdd = null;
		String tableauNomDonnee[];
		String tableauValeurDonnee[];

		try {
			int versionMax = Configuration.getInstances()
					.getVersionDonneeQuestionnaire(nom_questionnaire);
			for (int j = 1; j < versionMax; j++) {
				bdd = new FileReader(Configuration.getInstances()
						.getCheminPartage()
						+ "/bdd/"
						+ nom_questionnaire
						+ "_V" + j + ".csv");
				BufferedReader buff = new BufferedReader(bdd);
				String ligne = buff.readLine();
				tableauNomDonnee = ligne.split(";");
				ligne = buff.readLine();
				while (ligne != null) {
					tableauValeurDonnee = ligne.split(";");
					if ((tableauValeurDonnee[0].compareTo(idPersonne) == 0 & tableauValeurDonnee[1]
							.compareTo(dateQuest.affiche) == 0)) {
						// on stocke la ligne correspondante a "idPersonne" et a
						// "dateQuest"
						// puis on remplit directement la liste qu'on retourne
						for (int i = 0; i < tableauNomDonnee.length; i++) {
							if (tableauValeurDonnee[i] != "")// ou null, a voir
																// ce que
																// renvoie la
																// mehode de
																// parsing avec
																// ;;
							{
								if (tableauNomDonnee[i] == "") {
									retour.add(new DoublonNomDonneeValeurDonnee(
											tableauNomDonnee[i], null));
								} else {
									retour.add(new DoublonNomDonneeValeurDonnee(
											tableauNomDonnee[i],
											tableauValeurDonnee[i]));
								}
							}
						}
						buff.close();
						return retour;
					} else {
						ligne = buff.readLine();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getDerniersPatients(
			ArrayList<Patient> listeDernierPatient, int nbPatient)// String
																	// podo) {
																	// ==>>>
																	// cot� podo
																	// � prendre
																	// plus tard
																	// en
																	// compte!!
	{
		// TODO Auto-generated method stub
		FileReader bdd = null;
		// ArrayList<Patient> listeRetour = new ArrayList<Patient>();
		// String exligne = null;
		try {
			bdd = new FileReader(Configuration.getInstances()
					.getAdresseDonneeQuestionnaire("general"));
			BufferedReader buff = new BufferedReader(bdd);
			String ligne = buff.readLine();
			ligne = buff.readLine();
			int i = -1;
			ArrayList<String> listeLigne = new ArrayList<String>();
			while (ligne != null) {
				i++;
				listeLigne.add(ligne.toString());
				System.out.println(ligne + " + i : " + i);
				ligne = buff.readLine();
			}
			buff.close();
			bdd.close();
			buff = null;
			bdd = null;
			// if (exligne != null)

			// /
			// System.err.println("gggggggggggggg : "+listeLigne.size()+" et a a la valeur suivante : "+i);

			String temp[] = listeLigne.get(i).split(";");
			listeDernierPatient.add(new Patient(temp[0]));
			// test
			i--;

			int arret = i - nbPatient;

			for (int j = i; j > arret; j--) {

				if (j >= 0) {
					temp = listeLigne.get(j).split(";");
					Patient patTemp = new Patient(temp[0]);
					boolean trouve = false;
					for (Patient pat : listeDernierPatient) {
						if (pat.compareTo(patTemp) == 0) {
							trouve = true;
							if (arret > 0) {
								arret--;
							}
						}
					}
					if (!trouve) {
						listeDernierPatient.add(new Patient(temp[0]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Patient getDernierPatient()// String podo) { ==>>> cot� podo �
												// prendre plus tard en compte!!
	{
		// TODO Auto-generated method stub
		FileReader bdd = null;
		String exligne = null;
		try {
			bdd = new FileReader(Configuration.getInstances()
					.getAdresseDonneeQuestionnaire("general"));
			BufferedReader buff = new BufferedReader(bdd);
			String ligne = buff.readLine();
			ligne = buff.readLine();
			while (ligne != null) {
				exligne = ligne.toString();
				ligne = buff.readLine();
			}
			buff.close();
			bdd.close();
			if (exligne != null) {
				String temp[] = exligne.split(";");
				buff.close();
				return new Patient(temp[0]);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void update(Tripplon_questionnaire quest, String idPersonne,
			Date dateQuest, List<DoublonNomDonneeValeurDonnee> listInsertion) {
		// seul moyen de changer une ligne d'un fichier en java... le copier
		// entierement dans un autre
		// puis supprimer l'ancien et copier le nouveau � la place!
		File bdd;
		File newBDD;
		try {
			boolean miseAJour = false;

			bdd = new File(Configuration.getInstances()
					.getAdresseDonneeQuestionnaire(quest));
			// FileReader bdd_read = new FileReader(bdd);
			// BufferedReader buffin = new BufferedReader(bdd_read);
			BufferedReader buffin = new BufferedReader(new FileReader(bdd));

			newBDD = new File("temp.csv");
			FileWriter bdd_write = new FileWriter(newBDD);
			BufferedWriter buffout = new BufferedWriter(bdd_write);

			String ligne = buffin.readLine();

			String nouvelleLigne = creerLigneDonnee(idPersonne, dateQuest,
					listInsertion, ligne.split(";"));

			System.out.println(nouvelleLigne);
			// on insere la premier ligne
			buffout.append(ligne);
			ligne = buffin.readLine();
			while (ligne != null) {
				// on commence par inserer le marqueur de passages a la ligne :
				buffout.append(System.getProperty("line.separator"));
				// System.out.println(" ligne : "+ligne);
				// puis suivant si on est sur la bonne ligne on pas on recopie
				// ou copie la nouvelle ligne!
				String[] temp = ligne.split(";");
				if ((temp[0].compareTo(idPersonne) == 0 & temp[1]
						.compareTo(dateQuest.toString()) == 0)) {
					System.err.println(nouvelleLigne);
					buffout.append(nouvelleLigne);
					miseAJour = true;
				} else {
					buffout.append(ligne);
				}

				ligne = buffin.readLine();
			}
			if (!miseAJour) {
				buffout.append(System.getProperty("line.separator"));
				buffout.append(nouvelleLigne);
				System.out
						.println("transfert questionnaire entre version donn�es!");
			}
			buffout.flush();
			buffin.close();
			buffout.close();
			buffin = null;
			buffout = null;
			ligne = null;
			// bdd.delete();
			boolean enregistre = false;
			int fin = 0;

			// technique qui attend 4 secondes au cas ou le fichier soit en
			// cours d'utilisation et donc bloqu� :
			while (fin < 40) {
				enregistre = bdd.delete();
				if (enregistre) {
					fin = 40;
				} else {
					fin++;
					Thread.sleep(100);
					System.out
							.println("En attente de supresion du fichier suivant : "
									+ bdd.getAbsolutePath());
				}
			}
			enregistre = false;
			fin = 0;
			while (fin < 40) {
				enregistre = newBDD.renameTo(bdd);
				if (enregistre) {
					fin = 40;
				} else {
					fin++;
					Thread.sleep(100);
					System.out
							.println("En attente de supresion du fichier suivant : "
									+ bdd.getAbsolutePath());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String creerLigneDonnee(String idPersonne, Date dateQuest,
			List<DoublonNomDonneeValeurDonnee> listInsertion, String[] motif) {
		// TODO Auto-generated method stub
		String retour = idPersonne + ";" + dateQuest.toString() + ";";

		for (String element : motif) {
			// System.err.println("ligne motif : "+element);
			if (element.compareTo("idPersonne") != 0
					& element.compareTo("dateQuestionnaire") != 0) {
				for (DoublonNomDonneeValeurDonnee i : listInsertion) {
					if (element.compareTo(i.nom_de_donnee) == 0)

					{

						// System.err.println(i.nom_de_donnee+" et sa valeur de donn�es : "+i.valeurDonnee);
						if (i.valeurDonnee != null) {
							retour += i.valeurDonnee + ";";
						} else {
							retour += ";";
						}
					}
				}
			}
		}
		return retour;
	}

	@SuppressWarnings("finally")
	public static String getDonnee(Tripplon_questionnaire quest,
			String idPersonne, Date dateQuest, String nom_donnee) {
		File bdd;
		try {
			bdd = new File(quest.getAdressedonneeQuestionnaire());
			BufferedReader buff = new BufferedReader(new FileReader(bdd));
			String ligne;
			String motif[] = buff.readLine().split(";");
			if (motif != null) {
				ligne = buff.readLine();
			} else {
				ligne = null;
			}
			while (ligne != null) {
				String[] elementLigne = ligne.split(";");
				// element 0 est la date
				if (elementLigne[1].compareTo(idPersonne) == 0) {
					if (new Date(elementLigne[2]).compareTo(dateQuest) == 0) {
						for (int i = 2; i < motif.length; i++) {
							if (motif[i].compareTo(nom_donnee) == 0) {
								buff.close();
								return elementLigne[i];
							}
						}
					}
				}
			}
			buff.close();
			System.err
					.println("La donn�e recherch�e "
							+ nom_donnee
							+ " n'existe pas! Cette fonction va donc renvoiyez un beau neant -- va t'il se transformer en chaos?");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bdd = null;
			return null;
		}
	}

	public static void deleteLignebdd(Tripplon_questionnaire quest,
			String idPersonne, Date dateQuest) {
		// seul moyen de changer une ligne d'un fichier en java... le copier
		// entierement dans un autre
		// puis supprimer l'ancien et copier le nouveau � la place!
		File bdd;
		File newBDD;
		try {
			bdd = new File(quest.getAdressedonneeQuestionnaire());
			BufferedReader buffin = new BufferedReader(new FileReader(bdd));

			newBDD = new File("temp.csv");
			FileWriter bdd_write = new FileWriter(newBDD);
			BufferedWriter buffout = new BufferedWriter(bdd_write);

			String ligne = buffin.readLine();
			buffout.append(ligne);
			ligne = buffin.readLine();
			while (ligne != null) {

				String[] temp = ligne.split(";");
				if ((temp[0].compareTo(idPersonne) != 0 & temp[1]
						.compareTo(dateQuest.toString()) != 0)) {
					buffout.append(System.getProperty("line.separator"));
					buffout.append(ligne);

				}
				ligne = buffin.readLine();

			}
			buffin.close();
			buffout.close();

			boolean enregistre = false;
			int fin = 0;

			// technique qui attend 4 secondes au cas ou le fichier soit en
			// cours d'utilisation et donc bloqu� :
			while (fin < 40) {
				enregistre = bdd.delete();
				if (enregistre) {
					fin = 40;
				} else {
					fin++;
					Thread.sleep(100);
					System.out
							.println("En attente de supresion du fichier suivant : "
									+ bdd.getAbsolutePath());
				}
			}
			enregistre = false;
			fin = 0;

			// technique qui attend 4 secondes au cas ou le fichier soit en
			// cours d'utilisation et donc bloqu� :
			while (fin < 40) {
				enregistre = newBDD.renameTo(bdd);
				if (enregistre) {
					fin = 40;
				} else {
					fin++;
					Thread.sleep(100);
					System.out
							.println("En attente d'ecrasage du fichier suivant : "
									+ bdd.getAbsolutePath()
									+ " dans le fichier suivant "
									+ newBDD.getAbsolutePath());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createQuestionnaire(Tripplon_questionnaire questi) {
		// TODO Auto-generated method stub

		// va permettre de cr�er le fichier xml le permettant

		// a faire mais plus tard!!!!!!!!!!!!!

	}

	public static List<DoublonNomDonneeValeurDonnee> getMotif(
			Tripplon_questionnaire quest) {
		ArrayList<DoublonNomDonneeValeurDonnee> retour = new ArrayList<DoublonNomDonneeValeurDonnee>();
		File bdd;
		// System.out.println("adresse quest : "+quest.getAdressedonneeQuestionnaire());
		try {
			bdd = new File(quest.getAdressedonneeQuestionnaire());
			BufferedReader buffin = new BufferedReader(new FileReader(bdd));
			String[] ligne = buffin.readLine().split(";");
			for (String i : ligne) {
				retour.add(new DoublonNomDonneeValeurDonnee(i));
			}
			buffin.close();
			return retour;
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			return retour;
		}
	}

	public static void remplirListesReponse(Questionnaire quest)// Tripplon_questionnaire
																// quest, String
																// idPersonne,
																// Date
																// dateQuest)
	{
		// TODO Auto-generated method stub
		// les2 listes (liste reponses temp et liste reponse) ont deja le
		// "motif" du questionnaire inserer en eux!

		File bdd;
		try {
			bdd = new File(quest.getQuest().getAdressedonneeQuestionnaire());
			BufferedReader buff = new BufferedReader(new FileReader(bdd));
			String ligne;
			String motif[] = buff.readLine().split(";");
			if (motif != null) {
				ligne = buff.readLine();
			} else {
				ligne = null;
			}
			boolean trouve = false;
			while (ligne != null & !trouve) {
				String[] elementLigne = ligne.split(";");
				// element 0 est la date
				if (elementLigne[1].compareTo(quest.getIdPersonne()) == 0) {
					if (new Date(elementLigne[2]).compareTo(quest
							.getDatequest()) == 0) {
						for (int i = 2; i < motif.length; i++) {
							for (DoublonNomDonneeValeurDonnee element : quest.liste_reponse) {
								if (motif[i].compareTo(element.nom_de_donnee) == 0) {
									element.valeurDonnee = elementLigne[i];
								}
							}
						}
						trouve = true;
						quest.liste_reponse_temp.clear();
						for (DoublonNomDonneeValeurDonnee aj : quest.liste_reponse) {
							quest.liste_reponse_temp
									.add(new DoublonNomDonneeValeurDonnee(
											aj.nom_de_donnee, aj.valeurDonnee));
						}
					}
				}
			}
			buff.close();
			if (!trouve) {
				System.err.println("L'enregistrement du questionnaire "
						+ quest.getQuest().getQuestionnaire() + " de "
						+ quest.getIdPersonne() + " pour la date "
						+ quest.getDatequest().toString() + " n'existe pas!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertInto(Questionnaire quest, String nomListe) {
		if (nomListe == "liste_reponse_temp") {
			insertInto(quest.getQuest().getAdressedonneeQuestionnaire(),
					quest.getIdPersonne(), quest.getDatequest(),
					quest.liste_reponse_temp);
		} else {
			insertInto(quest.getQuest().getAdressedonneeQuestionnaire(),
					quest.getIdPersonne(), quest.getDatequest(),
					quest.liste_reponse);
		}
	}

	public static boolean precompletion(String nomQuest, String idPersonne) {
		Date dateLastQuest = getDateDernierQuestionnaireToutesVersion(nomQuest,
				idPersonne);
		// TODO Auto-generated method stub
		// a afire!
		return false;
	}

	public static void getQuestionnaires(
			LinkedList<InfoQuestExistant> listQuest, Patient pat) {
		List<Tripplon_questionnaire> list = Configuration.getInstances()
				.getListeQuestionnaireInactif();
		list.addAll(Configuration.getInstances().getListeQuestionnaireActif());
		for (Tripplon_questionnaire quest : list) {
			File bdd;
			try {
				bdd = new File(quest.getAdressedonneeQuestionnaire());
				BufferedReader buff = new BufferedReader(new FileReader(bdd));
				String ligne = buff.readLine();
				// DoublonDateQuest retour = null;
				while (ligne != null) {
					// System.err.println(pat.idPersonne+" : ddddddddddddddddddddddddd ; "+ligne);
					String[] elementLigne = ligne.split(";");
					// element 1 est la date
					// element 0 est l'idPersonne
					if (elementLigne[0].compareTo(pat.idPersonne) == 0) {
						listQuest.add(new InfoQuestExistant(pat, quest,
								new Date(elementLigne[1])));
					}
					ligne = buff.readLine();
				}
				buff.close();
				// System.err.println("La donn�e recherch�e "+nom_donnee+" n'existe pas! Cette fonction va donc renvoiyez un beau neant -- va t'il se transformer en chaos?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// return listeRetour;

	}

	public static void export(String string, String cheminExport) {
		// a faire
	}

	// public static ArrayList<DoublonDateQuest>
	// getDernierAllQuestionnaireDerniereVersionsPatient(ArrayList<DoublonDateQuest>
	// listeRetour,Patient pat)
	public static void getDernierAllQuestionnaireDerniereVersionsPatient(
			ArrayList<DoublonDateQuest> listeRetour, Patient pat) {
		for (Tripplon_questionnaire quest : Configuration.getInstances()
				.getListeQuestionnaireActif()) {
			File bdd;
			try {
				bdd = new File(quest.getAdressedonneeQuestionnaire());
				BufferedReader buff = new BufferedReader(new FileReader(bdd));
				String ligne = buff.readLine();
				DoublonDateQuest retour = null;
				while (ligne != null) {
					// System.err.println("ddddddddddddddddddddddddd");
					String[] elementLigne = ligne.split(";");
					// element 0 est la date
					if (elementLigne[1].compareTo(pat.idPersonne) == 0) {
						retour = new DoublonDateQuest(
								new Date(elementLigne[1]),
								quest.getQuestionnaire());
					}
					ligne = buff.readLine();
				}
				if (retour != null) {
					listeRetour.add(new DoublonDateQuest(retour.dateQuest,
							retour.nomQuestionnaire));
				}
				buff.close();
				// System.err.println("La donn�e recherch�e "+nom_donnee+" n'existe pas! Cette fonction va donc renvoiyez un beau neant -- va t'il se transformer en chaos?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// return listeRetour;
	}

	public static void main(String[] args) {
		Tripplon_questionnaire quest = new Tripplon_questionnaire(
				"satisfaction", 1, 0);
		String idPersonne = "ludid";
		Date dateQuest = new Date("29/10/2010");
		LinkedList<DoublonNomDonneeValeurDonnee> listInsertion = new LinkedList<DoublonNomDonneeValeurDonnee>();
		listInsertion.add(new DoublonNomDonneeValeurDonnee("idPersonne",
				"ludid"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee("dateQuestionnaire",
				"29/10/2010"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"port_entrainement_sport", "0"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"port_entrainement_competition", "1"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee("port_ville", "2"));
		listInsertion
				.add(new DoublonNomDonneeValeurDonnee("port_travail", "3"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"total_port_semelle", "6"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"satisfaction_confort", "0"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"satisfaction_transpiration", "1"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"satisfaction_poids", "2"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"satisfaction_stabilite_pied", "3"));
		listInsertion.add(new DoublonNomDonneeValeurDonnee(
				"total_port_semelle", "6"));

		;
		;
		;
		;
		;
		;
		;
		;
		;

		update(quest, idPersonne, dateQuest, listInsertion);

	}

	public static void getIdentifiants(String nom, String prenom,
			Date dateNaissance, LinkedList<String> listePersonne) {
/*		// Comme d'hab, on considere que le fichier est bon et qu'il n'y a pas
		// de probleme pour l'ouvrir ni de double acc�s!
		// preparation des �lement, pour qu'il n'y ais pas d'erreur dessus en
		// cas de minuscule, majuscule ou espaces different
		String nomComplet;
		if (dateNaissance == null) {
			nomComplet = nom.trim().toLowerCase() + " "
					+ prenom.trim().toLowerCase();
		} else {
			nomComplet = nom.trim().toLowerCase() + " "
					+ prenom.trim().toLowerCase() + " "
					+ dateNaissance.toString();
		}
		// preparation de la metrics pour tester la difference entre les
		// �l�ments
		//NE DEVRAIT PAS ETRE EXPLOITEE
		//EuclideanDistance metric = new EuclideanDistance();
		// ouverture du fichier
		File bdd;
		try {
			bdd = new File(Configuration.getInstances().getChemin_partage()
					+ "bdd/personne.csv");
			BufferedReader buff = new BufferedReader(new FileReader(bdd));
			String ligne = buff.readLine();

			while (ligne != null) {
				String[] elementLigne = ligne.split(";");
				// element 0 est l'identifiant => le retour
				// 1 est le nom
				// element 2 est le prenom
				// element 3 est la date de naissance
				if (dateNaissance == null) {
					if (metric.getSimilarity(elementLigne[1].trim()
							.toLowerCase()
							+ " "
							+ elementLigne[2].trim().toLowerCase(), nomComplet) > 0.4) {
						listePersonne.add(elementLigne[0]);
					} else {
						ligne = buff.readLine();
					}
				} else {
					if (metric.getSimilarity(elementLigne[1].trim()
							.toLowerCase()
							+ " "
							+ elementLigne[2].trim().toLowerCase()
							+ " "
							+ elementLigne[2].trim().toLowerCase(), nomComplet) > 0.4) {
						listePersonne.add(elementLigne[0]);
					} else {
						ligne = buff.readLine();
					}
				}
			}
			buff.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
}
/*
 * try { // load the driver into memory
 * Class.forName("org.relique.jdbc.csv.CsvDriver"); Properties props = new
 * java.util.Properties(); props.put("separator",";"); // separator is a point
 * virgule // create a connection. The first command line parameter is assumed
 * to // be the directory in which the .csv files are held Connection conn =
 * DriverManager.getConnection("jdbc:relique:csv:./fichier/bdd/",props); //
 * create a Statement object to execute the query with Statement stmt =
 * conn.createStatement(); String table = "personne"; // Select the ID and NAME
 * columns from sample.csv ResultSet results =
 * stmt.executeQuery("SELECT idPersonne FROM "+table +" where nom = '"+nom+"'");
 * 
 * //ResultSet results =
 * stmt.executeQuery("SELECT idPersonne FROM "+table+" where nom = '"
 * +nom+"' and prenom ='"+prenom+";");//' and date ='"+dateDeNaissance+"';");
 * 
 * //fin de la partie sql // dump out the results idPersonne="inconnu"; while
 * (results.next()) { idPersonne = results.getString("idPersonne");
 * System.out.println("wwwwwwwwwwwwwwwwwwwwwww"+idPersonne); } if
 * (idPersonne.compareTo("inconnu")==0) { System.out.println(idPersonne);
 * boolean pasNouveau = true; while(pasNouveau) { idPersonne =""; idPersonne =
 * stringAleatoire5lettres(); System.out.println(idPersonne); pasNouveau=false;
 * //attention � la date, elle risque de possr probleme!!!!!!!!! ResultSet
 * doublon =
 * stmt.executeQuery("SELECT idPersonne,nom,prenom,date_de_naissance FROM "
 * +table+" where nom = 'kanade'"); int i = 0; while ((doublon.next()) &
 * (i<10)); {
 * System.out.println("gbdsfjgkjsdgfskdgkljeg       "+doublon.getString
 * (1)+"     "+doublon.getString(2)); i++; pasNouveau=true;
 * 
 * } } //equivalent si on continue en sql : //ResultSet insert =
 * stmt.executeQuery
 * ("insert into personne (idPersonne,nom,prenom) values ("+idPersonne
 * +","+nom+","+prenom+");"); List<InsertionBDD> listPersonne = new
 * ArrayList<InsertionBDD>(); listPersonne.add(new InsertionBDD("idPersonne",
 * idPersonne)); listPersonne.add(new InsertionBDD("nom", nom));
 * listPersonne.add(new InsertionBDD("prenom", prenom)); listPersonne.add(new
 * InsertionBDD("date_de_naissance", dateDeNaissance.toString()));
 * insertInto("personne",listPersonne); }
 * 
 * 
 * //int result2 =
 * stmt.executeUpdate("insert into test2 (,MASCCC) values (date,sexe");
 * 
 * 
 * // clean up results.close(); stmt.close(); conn.close(); } catch(Exception e)
 * { System.out.println("Oops-> " + e); e.printStackTrace(); }
 */