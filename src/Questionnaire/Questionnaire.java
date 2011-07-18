package Questionnaire;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import Application.*;

import ApplicationGraphique.*;

public class Questionnaire {

	/**
	 * @param args
	 */
	// variable
	// Application appli;//permet de connaitre son "pere" et de retourner dessus
	// chercher d'autre info!
	// Le grand pere sera toujours ApplicationGraphique.getInstances().appli

	// donn�es de base
	public ArrayList<DoublonNomDonneeValeurDonnee> liste_reponse;
	public ArrayList<ElementQuestionnaire> liste_elementQuestionnaire;
	private Tripplon_questionnaire quest;

	// info de base questionnaire
	public ArrayList<Declencheur> listeDeclencheur;
	// public boolean repondu;
	public Date dateCreactionQuestionnaire;

	// partie podologue : plusieur questionnaire � la fois donc plusieurs
	// patient, donc aucune info doit venir dinamiquement des classes
	// "application"
	private String idPersonne;
	private Date datequest;

	// temporaire fichier xml
	private org.jdom.Document document_jdom;
	private org.jdom.Element element_racine;

	// configuration particuliere
	private boolean precompletion = false;
	private int limiteEnMoisPrecompletion;
	public boolean miseAjour = false;
	public int nombrePage = 1;

	// partie graphique
	public ArrayList<DoublonNomDonneeValeurDonnee> liste_reponse_temp;// = new
																		// ArrayList<DoublonNomDonneeValeurDonnee>();
	public QuestionnaireGraphique questGraphique;

	// constructeur questionnaire ultra vierge, permet juste de recuperer les
	// declencheurs de ce questionnaire pour voir si on va le cr�er!
	public Questionnaire(Tripplon_questionnaire quest) {
		this.quest = quest;
		construireDeclencheur();
	}

	public boolean isDeclenchable() {

		boolean retour = true;

		for (Declencheur dec : listeDeclencheur) {
			// System.out.println(dec.nom_donnee+" valeur donn�e dec : "+dec.valeur_donnee+" compar� a : "+ApplicationGraphique.getInstance().appli.QG.getValeurTemp(dec.nom_donnee.trim()));
			if (ApplicationGraphique.getInstance().appli.QG
					.getValeurTemp(dec.nom_donnee).trim()
					.compareTo(dec.valeur_donnee.trim()) != 0) {
				// System.out.println("on declenche pas le questionnaire : "+quest.getQuestionnaire());
				retour = false;
			}
		}
		return retour;
	}

	private void construireDeclencheur() {
		listeDeclencheur = new ArrayList<Declencheur>();

		SAXBuilder sxb = new SAXBuilder();
		try {
			if (this.document_jdom == null)
				this.document_jdom = sxb.build(new File(this.quest
						.getAdresseQuestionnaire()));
		} catch (Exception e) {
			System.err.println("ggggggggggggggggggggggggggggggggggggg"
					+ this.quest.getAdressedonneeQuestionnaire());// Configuration.getInstances().getAdresseQuestionnaire(quest)
																	// );
			e.printStackTrace();
		}

		element_racine = document_jdom.getRootElement();
		// dom du fichier xml locale cr�er, passage � la r�cuperation des
		// donn�es :
		List liste_noeud_principaux = element_racine.getChildren();
		// On cr�e un Iterator sur notre liste
		Iterator i = liste_noeud_principaux.iterator();
		boolean trouve = false;
		while (i.hasNext() & !trouve) {
			// lecture du premier noeud
			Element courant = (Element) i.next();
			// System.out.println(courant.getName());
			// Si il s'agit du noeud "information" => celui qui nous interresse
			// ici!
			if (courant.getName().trim().compareTo("information") == 0) {
				// on recupere les infos
				List list_info_ligne = courant.getChildren();
				Iterator j = list_info_ligne.iterator();
				// System.out.println("j : "+j.hasNext()+"hhhhh"+list_info_ligne);
				while (j.hasNext()) {
					Element liste_ligne = (Element) j.next();
					// int positionElement = liste_elementQuestionnaire.size();
					// System.out.println("gggggggggggggggggggggggggg : "+positionElement);
					if (liste_ligne.getName().trim().compareTo("declencheur") == 0) {
						listeDeclencheur.add(new Declencheur("general",
								liste_ligne.getAttributeValue("variable"),
								liste_ligne.getValue()));
					}
					if (liste_ligne.getName().trim().compareTo("dateCreation") == 0) {
						dateCreactionQuestionnaire = new Date(
								liste_ligne.getValue());
					}
					if (liste_ligne.getName().trim().compareTo("nombre_page") == 0) {
						this.nombrePage = Integer.parseInt(liste_ligne
								.getValue());
					}
					if (liste_ligne.getName().trim().compareTo("precompletion") == 0) {
						limiteEnMoisPrecompletion = Integer
								.parseInt(liste_ligne
										.getAttributeValue("limite"));
						if (liste_ligne.getValue().compareTo("oui") == 0) {
							this.precompletion = true;
						} else {
							this.precompletion = false;
						}
					}

				}
				trouve = true;
			}
		}
	}

	// constructeur questionnaire vierge
	public Questionnaire(String nom_questionnaire, String idPersonne)// ,
																		// Application
																		// ap)//,
																		// ApplicationQuestionnaire
																		// applicationQuestionnaire)
	{
		this.quest = new Tripplon_questionnaire(nom_questionnaire,
				Configuration.getInstances().getVersionDonneeQuestionnaire(
						nom_questionnaire), Configuration.getInstances()
						.getVersionAffichageQuestionnaire(nom_questionnaire));

		System.out.println("Version donn�e : "
				+ Configuration.getInstances().getVersionDonneeQuestionnaire(
						nom_questionnaire)
				+ " et version affichage : "
				+ Configuration.getInstances()
						.getVersionAffichageQuestionnaire(nom_questionnaire));

		this.idPersonne = idPersonne;
		// repondu = false;
		liste_elementQuestionnaire = new ArrayList<ElementQuestionnaire>();
		datequest = new Date();
		construireListeElement();
		construireDeclencheur();

		// partie donn�e / reponses
		liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();
		liste_reponse_temp = new ArrayList<DoublonNomDonneeValeurDonnee>();
		// => pour liste_reponses, a voir avec les fonction de cr�ation pre
		// rempli!
		// et initialise les lignes quand m�me!
		liste_reponse.addAll(GestionDonnee.getMotif(quest));
		liste_reponse.get(0).valeurDonnee = idPersonne;
		liste_reponse.get(1).valeurDonnee = new Date().affiche;
		liste_reponse_temp.addAll(GestionDonnee.getMotif(quest));
		liste_reponse_temp.get(0).valeurDonnee = idPersonne;
		liste_reponse_temp.get(1).valeurDonnee = new Date().affiche;

		// this.toStringListeReponse();

		// partie graphique
		System.out.println("taille de la liste d�finitive : "
				+ this.liste_reponse.size()
				+ " et taille de la liste de reponse temporaire : "
				+ this.liste_reponse_temp.size());
		questGraphique = new QuestionnaireGraphique(this);
		System.out.println("taille de la liste d�finitive : "
				+ this.liste_reponse.size()
				+ " et taille de la liste de reponse temporaire : "
				+ this.liste_reponse_temp.size());
	}

	public Questionnaire(Tripplon_questionnaire questTriple, Patient pat,
			Date dateQuestionnaire)// , Application ap)//,
									// ApplicationQuestionnaire
									// applicationQuestionnaire)
	{
		this.quest = new Tripplon_questionnaire(questTriple);
		this.idPersonne = pat.idPersonne;
		// repondu = false;
		liste_elementQuestionnaire = new ArrayList<ElementQuestionnaire>();
		datequest = new Date(dateQuestionnaire);
		construireListeElement();
		construireDeclencheur();

		// partie donn�e / reponses
		liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();
		liste_reponse_temp = new ArrayList<DoublonNomDonneeValeurDonnee>();
		// => pour liste_reponses, a voir avec les fonction de cr�ation pre
		// rempli!
		// et initialise les lignes quand m�me!
		liste_reponse.addAll(GestionDonnee.getMotif(quest));
		liste_reponse.get(0).valeurDonnee = idPersonne;
		liste_reponse.get(1).valeurDonnee = new Date().affiche;
		liste_reponse_temp.addAll(GestionDonnee.getMotif(quest));
		liste_reponse_temp.get(0).valeurDonnee = idPersonne;
		liste_reponse_temp.get(1).valeurDonnee = new Date().affiche;

		// this.toStringListeReponse();

		// partie graphique
		System.out.println("taille de la liste d�finitive : "
				+ this.liste_reponse.size()
				+ " et taille de la liste de reponse temporaire : "
				+ this.liste_reponse_temp.size());
		questGraphique = new QuestionnaireGraphique(this);
		System.out.println("taille de la liste d�finitive : "
				+ this.liste_reponse.size()
				+ " et taille de la liste de reponse temporaire : "
				+ this.liste_reponse_temp.size());
	}

	public Questionnaire(String nom_questionnaire, String idPersonne,
			Date dateQuest, int versionDonneeQuestionnaire) {
		this.quest = new Tripplon_questionnaire(nom_questionnaire,
				versionDonneeQuestionnaire, Configuration.getInstances()
						.getLastVersionAffichageQuestionnaire(
								nom_questionnaire, versionDonneeQuestionnaire));
		this.idPersonne = idPersonne;
		this.datequest = dateQuest;
		// this.repondu = false;
		liste_elementQuestionnaire = new ArrayList<ElementQuestionnaire>();
		construireListeElement();

		// partie donn�e / reponses
		liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();
		liste_reponse_temp = new ArrayList<DoublonNomDonneeValeurDonnee>();
		// => pour liste_reponses, a voir avec les fonction de cr�ation pre
		// rempli!
		// et initialise les lignes quand m�me!
		liste_reponse.addAll(GestionDonnee.getMotif(quest));
		liste_reponse_temp.addAll(GestionDonnee.getMotif(quest));

		GestionDonnee.remplirListesReponse(this);// this.quest,idPersonne,dateQuest);

		// this.toStringListeReponse();

		// partie graphique
		questGraphique = new QuestionnaireGraphique(this);
		// questGraphique.setPreSelection(this);
	}

	/*
	 * public Questionnaire(Tripplon_questionnaire questionnaire, String
	 * idPersonne,Date dateDuQuest)//, ApplicationQuestionnaire
	 * applicationQuestionnaire) { this.quest = questionnaire; this.idPersonne =
	 * idPersonne; liste_elementQuestionnaire = new
	 * ArrayList<ElementQuestionnaire>(); liste_reponse = new
	 * ArrayList<DoublonNomDonneeValeurDonnee>(); if (dateDuQuest==null) {
	 * datequest = new Date(); }else{ datequest = dateDuQuest; }
	 * construireListeElement();
	 * 
	 * //partie graphique liste_reponse = new
	 * ArrayList<DoublonNomDonneeValeurDonnee>(this.getListeDonneePrepare());
	 * questGraphique = new QuestionnaireGraphique(this); }
	 */

	/*
	 * public Questionnaire(String nom_questionnaire, String idPersonne,
	 * ArrayList<DoublonNomDonneeValeurDonnee> reponse)//,
	 * ApplicationQuestionnaire applicationQuestionnaire) { this.quest = new
	 * Tripplon_questionnaire(nom_questionnaire,
	 * Configuration.getInstances().getVersionDonneeQuestionnaire
	 * (nom_questionnaire
	 * ),Configuration.getInstances().getVersionAffichageQuestionnaire
	 * (nom_questionnaire)); this.idPersonne = idPersonne;
	 * liste_elementQuestionnaire = new ArrayList<ElementQuestionnaire>();
	 * this.liste_reponse = reponse;
	 * 
	 * construireListeElement(); }
	 */
	// methode
	private void construireListeElement() {
		// System.out.println(Configuration.getInstances().getAdresseQuestionnaire(quest));
		// System.out.println("Crrrrrrrhhhhhhhhhhhhhhhhhhhhrrr");
		SAXBuilder sxb = new SAXBuilder();
		try {
			// System.out.println("Crrrrrrrrrrrrrrrrrrrrrrruu   "+Configuration.getInstances().getAdresseQuestionnaire(quest));
			if (this.document_jdom == null) {
				this.document_jdom = sxb.build(new File(this.quest
						.getAdresseQuestionnaire()));
			}
			// old version :
			// Configuration.getInstances().getAdresseQuestionnaire(quest)));
			// System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		} catch (Exception e) {
			System.err.println("ggggggggggggggggggggggggggggggggggggg"
					+ this.quest.getAdressedonneeQuestionnaire());// Configuration.getInstances().getAdresseQuestionnaire(quest)
																	// );
			e.printStackTrace();
		}
		// System.out.println(Configuration.getInstances().getAdresseQuestionnaire(quest));

		element_racine = document_jdom.getRootElement();
		// dom du fichier xml locale cr�er, passage � la r�cuperation des
		// donn�es :
		List liste_noeud_principaux = element_racine.getChildren();
		// On cr�e un Iterator sur notre liste
		Iterator i = liste_noeud_principaux.iterator();
		while (i.hasNext()) {

			// System.out.println("bndsfjkbfkjgfhj;zfgzhqfvd,qgfdhQ.VFJHQSFVDJH.QFSVHJQFVJHQSFVJHSDN");
			// lecture du premier noeud
			Element courant = (Element) i.next();
			// System.out.println(courant.getName());
			// Si il s'agit du noeud "information.. on fait rien!

			// Si il s'agit des information des differentes ligne, on refait un
			// boucle sur tous les enfants
			// et on cr� un ligne a chaque fois correspondante au info :
			if (courant.getName().trim().compareTo("listeLigne") == 0) {
				List list_info_ligne = courant.getChildren();
				Iterator j = list_info_ligne.iterator();
				// System.out.println("j : "+j.hasNext()+"hhhhh"+list_info_ligne);
				while (j.hasNext()) {
					Element liste_ligne = (Element) j.next();
					int positionElement = liste_elementQuestionnaire.size();

					// System.out.println("gggggggggggggggggggggggggg : "+positionElement);
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("question_binaire") == 0) {
						// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						liste_elementQuestionnaire.add(new QuestionBinaire());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("question_deroulante") == 0) {
						// System.out.println("aaappppppppppppppppppppppppppppppppppppaa");
						liste_elementQuestionnaire
								.add(new QuestionDeroulante());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("question_likerte") == 0) {
						// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						liste_elementQuestionnaire.add(new QuestionLikerte());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("phrase") == 0) {
						// System.out.println("aaaaaaaaaaaaaa  phrase");
						liste_elementQuestionnaire.add(new Phrase());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("imc") == 0) {
						// System.out.println("aaaaaaaaaaaaaaaaa   imc");
						liste_elementQuestionnaire.add(new Imc());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("age") == 0) {
						// System.out.println("aaaaaaaaaaaaaa age");
						liste_elementQuestionnaire.add(new Age());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("temps_renouvellement") == 0) {
						liste_elementQuestionnaire
								.add(new TempsRenouvellement());
						// System.out.println("aaaaaaaaaaaaatps non renouvell�, heureusement!!!!!!");
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
					if (liste_ligne.getAttributeValue("type").trim()
							.compareTo("total") == 0) {
						// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						liste_elementQuestionnaire.add(new Total());
						remplirElementQuestionnaire(liste_ligne.getChildren(),
								positionElement);
					}
				}
			}
			// Si il y a des donn�es "cach�"
			// partit fusionn� avec listeLigne!!!
			/*
			 * if (courant.getName().trim().compareTo("donneeInvisible")==0) {
			 * List list_donneeInvisible = courant.getChildren(); Iterator k =
			 * list_donneeInvisible.iterator(); while(k.hasNext()) { //
			 * System.out.println("jkefgfjsgskdjgfsdhflgksd"); Element quest =
			 * (Element)k.next(); if (quest.getName()=="questionnaire_actif") {
			 * 
			 * } } }
			 */

		}

	}

	private void remplirElementQuestionnaire(List children, int positionElement) {
		// deuxieme partie de mon usine a elementQuestionnaire,
		// On complete les infos necessaire a chaque objet pr�sent dans le
		// fichier xml du questionnaire

		liste_elementQuestionnaire.get(positionElement).setQuestionnaire(this);

		Iterator k = children.iterator();

		while (k.hasNext()) {
			boolean p;
			Element courant = (Element) k.next();

			if (courant.getValue().trim() == "motif_consultation") {
				// System.out.println("motif_consultation : "+courant.getValue().trim());
				p = true;
			} else {
				p = false;
				// System.out.println("genre..." +
				// courant.getName()+" : "+courant.getValue().trim());
			}

			if (courant.getName().trim().compareTo("nomDonnee") == 0) {
				// if (p)
				// System.out.println(courant.getValue().trim());
				liste_elementQuestionnaire.get(positionElement).setNomDeDonnee(
						courant.getValue().trim());
			}
			if (courant.getName().trim().compareTo("texteQuestion") == 0) {
				liste_elementQuestionnaire.get(positionElement)
						.setTexteQuestion(courant.getValue().trim());
			}
			if (courant.getName().trim().compareTo("nomListeReponse") == 0) {
				liste_elementQuestionnaire.get(positionElement)
						.setNomListeReponse(courant.getValue().trim());
			}
			if (courant.getName().trim().compareTo("valeurReponse") == 0) {
				if (courant.getAttributeValue("valeur") != null) {
					// System.out.println("ajout de valeurrrrrrrrrrrrrr reponses : "+courant.getAttributeValue("valeur").trim()+" : "+courant.getValue().trim()+
					// "dans l'objet suivant : "+liste_elementQuestionnaire.get(positionElement).toString());
					liste_elementQuestionnaire.get(positionElement)
							.setValeurReponse(
									new DoublonValeurReponse(
											courant.getAttributeValue("valeur")
													.trim(), courant.getValue()
													.trim()));
				} else {
					// System.out.println("ajout de valeurrrrrrrrrrrrrrr reponses : "+courant.getValue().trim()+" : "+courant.getValue().trim()+
					// "dans l'objet suivant : "+liste_elementQuestionnaire.get(positionElement).toString());
					liste_elementQuestionnaire
							.get(positionElement)
							.setValeurReponse(
									new DoublonValeurReponse(courant.getValue()
											.trim(), courant.getValue().trim()));
				}
			}
			if (courant.getName().trim().compareTo("ligne") == 0) {
				liste_elementQuestionnaire.get(positionElement)
						.setNumeroDeLigne(
								Integer.parseInt(courant.getValue().trim()));
			}
			// ajout du numero de la page
			if (courant.getName().trim().compareTo("page") == 0) {
				liste_elementQuestionnaire.get(positionElement).setNumeroPage(
						Integer.parseInt(courant.getValue().trim()));
			}
			if (courant.getName().trim().compareTo("declencheur") == 0) {

				// System.out.println("Alors tu crois que tu va me passer sous le nez comme ca tranquille? �_� va mourrir pute vierge d'euureur a la conne!");
				// System.out.println(courant.getAttributeValue("questionnaire")+" : "+courant.getAttributeValue("variable")+" : "+courant.getValue().trim());
				liste_elementQuestionnaire.get(positionElement).setDeclencheur(
						courant.getAttributeValue("questionnaire"),
						courant.getAttributeValue("variable"),
						courant.getValue().trim());
			}

		}
		// System.out.println();
	}

	public Date getDateDernierQuestionnaireVersionDonneeActuel(
			String nom_questionnaire)// ,Configuration config)
	{
		// System.out.println("nom questionnaire : "+nom_questionnaire+" idPersonne : "+idPersonne);
		return GestionDonnee.getDateDernierQuestionnaireVersionDonneeActuel(
				nom_questionnaire, idPersonne);
		// return null;
	}

	public Date getDateDernierQuestionnaireToutesVersion(
			String nom_questionnaire)// ,Configuration config)
	{
		return GestionDonnee.getDateDernierQuestionnaireToutesVersion(
				nom_questionnaire, idPersonne);
	}

	public Date getDateDernierQuestionnaireVersionActuel()// Configuration
															// config)
	{
		// /
		// System.out.println(" nom du questionnaire : "+this.quest.getQuestionnaire()
		// +" idPersonne : "+idPersonne);
		return GestionDonnee.getDateDernierQuestionnaireVersionDonneeActuel(
				this.quest.getQuestionnaire(), this.idPersonne);
	}

	public Date getDateDernierQuestionnaireToutesVersion()// Configuration
															// config)
	{
		return GestionDonnee.getDateDernierQuestionnaireToutesVersion(
				quest.getQuestionnaire(), idPersonne);
	}

	/*
	 * void remplirQuestionnaire()//ApplicationQuestionnaire
	 * applicationQuestionnaire) {
	 * 
	 * //a faire
	 * 
	 * //affichage du questionnaire
	 * 
	 * }
	 */

	void remplirQuestionnairePreRempli(Date dateLastQuest)// ApplicationQuestionnaire
															// applicationQuestionnaire)
	{
		Date dateDerniereReponse = getDateDernierQuestionnaireVersionDonneeActuel(this.quest
				.getQuestionnaire());
		if (dateDerniereReponse == null) {
			System.err
					.println("cas ou le questionnaire enregistr� n'est pas du m�me style (version donn�e) que l'actuelle! On pr�rempli avec ce que l'on  peut!");
			ArrayList<DoublonNomDonneeValeurDonnee> liste_reponse_old = new ArrayList<DoublonNomDonneeValeurDonnee>();
			liste_reponse_old.addAll(GestionDonnee.getOldDonnees(
					this.quest.getQuestionnaire(), this.idPersonne,
					dateLastQuest));
			liste_reponse.clear();
			liste_reponse.addAll(GestionDonnee.getMotif(quest));
			for (DoublonNomDonneeValeurDonnee definitif : liste_reponse) {
				for (DoublonNomDonneeValeurDonnee old : liste_reponse_old) {
					if (definitif.nom_de_donnee.compareTo(old.nom_de_donnee) == 0) {
						definitif.valeurDonnee = old.valeurDonnee;
					}
				}
			}

			// fin de la fonction
		} else {
			// n'existe plus!!
			// List<DoublonNomDonneeValeurDonnee> listDonnee = new
			// ArrayList<DoublonNomDonneeValeurDonnee>(GestionDonnee.getDonnees(quest.getQuestionnaire(),this.idPersonne,dateDerniereReponse));

			// deja fait dans constructeur
			// liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();

			// affichage du questionnaire
			liste_reponse.clear();
			liste_reponse.addAll(GestionDonnee.getDonnees(
					this.quest.getQuestionnaire(), this.idPersonne,
					dateLastQuest));
		}

	}

	public String getValeur(String nom_donnee) {
		if (liste_reponse.isEmpty() | liste_reponse.get(0).valeurDonnee == null) {
			Date dateDerniereReponse = getDateDernierQuestionnaireVersionActuel();
			if (dateDerniereReponse == null) {
				System.err
						.println("Aucun questionnaire n'a �t� enregistr� pour vous dans la base de donn�e");
				return null;
			} else {
				return GestionDonnee.getDonnee(this.quest, this.idPersonne,
						dateDerniereReponse, nom_donnee);

			}
		} else {
			for (DoublonNomDonneeValeurDonnee reponse : liste_reponse) {
				if (reponse.nom_de_donnee.compareTo(nom_donnee) == 0) {
					return reponse.valeurDonnee;
				}
			}
		}

		return null;
		// ///////////////// a faire !!!!!!!!!!!!!!!!!!!! //////////////////////
	}

	public void insererReponsesQuestionnaireDansBDD()// ApplicationQuestionnaire
														// applicationQuestionnaire)
	{
		if (liste_reponse.isEmpty() | liste_reponse.get(0).valeurDonnee == null) {
			System.err
					.println("Il 'y a aucun questionnaire a inserer dans la base de donn�es");
		} else {

		}
	}

	public void updateReponseQuestionnaireDansBDD(Date newDate) {
		Date dateDerniereReponse = getDateDernierQuestionnaireVersionActuel();
		if (dateDerniereReponse == null) {
			System.err
					.println("Aucun questionnaire n'a jamais �t� enregistr� pour "
							+ idPersonne
							+ " dans la base de donn�e -- la mise � jour est donc impossible!");
		} else {
			if (liste_reponse.isEmpty()
					| liste_reponse.get(0).valeurDonnee == null) {
				System.err
						.println("Aucune donn�e � inserer -- Une mise � jour n'est pas une suppression! -- la mise � jour est donc impossible!");
			} else {
				GestionDonnee.update(quest, idPersonne, this.datequest,
						liste_reponse);
			}
		}

	}

	public void deleteDonneeQuestionnaireDansBDD(Date vielleDate) {
		if (datequest != null) {
			// Date dateDerniereReponse =
			// getDateDernierQuestionnaireVersionActuel();
			if (getDateDernierQuestionnaireVersionActuel() == null) {
				System.err
						.println("Aucun questionnaire n'a jamais �t� enregistr� pour "
								+ idPersonne
								+ " dans la base de donn�e -- Que voulez supprimer? C'est dur de vider le vide! Mais les archives oui!");
			} else {
				GestionDonnee.deleteLignebdd(quest, idPersonne, datequest);

			}
		} else {
			System.err
					.println("Mais arretez de vouloir vider le vide nom de diou de "
							+ quest.getQuestionnaire() + " de " + idPersonne);
		}
	}

	public void creerBDD(Tripplon_questionnaire Questi) {
		if (new File(quest.getAdressedonneeQuestionnaire()).exists()) {
			System.err
					.println("La base de donn�e existe dej�, cr�ation interrompu pour ne pas supprimer l'existante");
		} else {
			GestionDonnee.createQuestionnaire(Questi);
		}
	}

	public Tripplon_questionnaire getQuest() {
		return quest;
	}

	public void setQuest(Tripplon_questionnaire quest) {
		this.quest = quest;
	}

	public String getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(String idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Date getDatequest() {
		return datequest;
	}

	public void setDatequest(Date datequest) {
		this.datequest = datequest;
	}

	public ArrayList<DoublonNomDonneeValeurDonnee> getListeDonneeRempli() {
		if (liste_reponse == null) {
			System.err
					.println("La liste des reponses n'as m�me pas �t� cr��, c'est mal parti pour ta gueule!!");
			liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();
		}
		if (liste_reponse.isEmpty()) {
			System.err
					.println("Il n'y a aucune donn�e dans la liste des reponses!");
			return liste_reponse;
		} else {
			if (liste_reponse.get(0).valeurDonnee != null) {
				return liste_reponse;
			} else {
				System.err
						.println("La liste �tait incomplete, elle a donc �t� re initialis�");
				liste_reponse.clear();
				liste_reponse.addAll(GestionDonnee.getDonnees(
						this.quest.getQuestionnaire(), idPersonne,
						this.datequest));
				return liste_reponse;
			}
		}
	}

	/*
	 * public ArrayList<DoublonNomDonneeValeurDonnee> getListeDonneePrepare() {
	 * if (liste_reponse==null) { liste_reponse = new
	 * ArrayList<DoublonNomDonneeValeurDonnee>(); liste_reponse.clear();
	 * liste_reponse.addAll(GestionDonnee.getMotif(quest)); return
	 * liste_reponse; } else { if (liste_reponse.isEmpty()) {
	 * liste_reponse.addAll(GestionDonnee.getMotif(quest)); return
	 * liste_reponse; } else { if ((liste_reponse.get(0).nom_de_donnee != null)
	 * & (liste_reponse.get(0).valeurDonnee == null )) { return liste_reponse; }
	 * if (liste_reponse.get(0).valeurDonnee != null ) { System.err.println(
	 * "La liste des reponses est deja complet�... pourquoi en vouuloir une nouvelle?"
	 * ); return null; } } liste_reponse.clear();
	 * liste_reponse.addAll(GestionDonnee.getMotif(quest)); return
	 * liste_reponse; } }
	 */

	public ArrayList<DoublonNomDonneeValeurDonnee> getListDonnee() {
		if (liste_reponse == null) {
			liste_reponse = new ArrayList<DoublonNomDonneeValeurDonnee>();
			return liste_reponse;
		} else {
			return liste_reponse;
		}
	}

	public ArrayList<DoublonNomDonneeValeurDonnee> getListDonneePreRempli() {
		if (!this.precompletion) {
			System.err
					.println("Le questionnaire n'accepte pas d'etre pr�  remplit!");
			return null;
		} else {
			Date dateDerniereReponse = getDateDernierQuestionnaireVersionActuel();
			if (dateDerniereReponse.compare(this.datequest,
					this.limiteEnMoisPrecompletion)) {
				liste_reponse.clear();
				liste_reponse.addAll(GestionDonnee.getDonnees(
						this.quest.getQuestionnaire(), idPersonne,
						dateDerniereReponse));
				return liste_reponse;
			} else {
				System.err
						.println("La limite de precompletion est d�pass�, le questionnaire ne peut pas �tre pr� rempli!!");
				return null;
			}
		}
	}

	public void setReponse(boolean b, DoublonNomDonneeValeurDonnee insertion) {
		// b = true => liste reponses definitive
		// b a false => liste reponses temp!
		if (b) {
			for (DoublonNomDonneeValeurDonnee d : liste_reponse) {
				if (d.nom_de_donnee.compareTo(insertion.nom_de_donnee) == 0) {
					d.valeurDonnee = insertion.valeurDonnee;
				}
			}
		} else {
			for (DoublonNomDonneeValeurDonnee dd_temp : liste_reponse_temp) {
				// System.out.println("on insere : "+insertion.valeurDonnee+" dans "+dd_temp.valeurDonnee);
				// System.out.println("insertion : "+insertion.valeurDonnee);
				// System.out.println("comparaison : "+dd_temp.nom_de_donnee+" : "+insertion.nom_de_donnee);

				if (dd_temp.nom_de_donnee.compareTo(insertion.nom_de_donnee) == 0) {
					// System.err.println("on insere!!!!!!!!!!!!!!! dans liste reponses temp : ");
					dd_temp.valeurDonnee = insertion.valeurDonnee;
				}
			}
		}

	}

	public String getValeurTemp(String nom_donnee) {
		// System.out.println("Nom de donn�e dans get valeur temp : "+
		// nom_donnee);

		if (liste_reponse_temp == null | liste_reponse_temp.isEmpty()) {
			System.err
					.println("liste_temporaire de reponses non initialiser dans getValeurTemp!!!!");
			return "";
		}
		for (DoublonNomDonneeValeurDonnee d : liste_reponse_temp) {
			if (d != null) {
				if (d.nom_de_donnee.compareTo(nom_donnee) == 0) {
					return d.valeurDonnee;
				}
			} else {
				System.err.println("probleme liste reponse temp null!");
			}
		}
		return "";
	}

	public void toStringListeReponse() {
		System.out.println("Liste reponse definitive : ");
		if (liste_reponse == null | liste_reponse.size() == 0) {
			System.err.println("liste vide ou  non rempli!!!!!");
		} else {
			int i = 0;
			for (DoublonNomDonneeValeurDonnee d : liste_reponse) {
				if (d == null) {
					System.out.println("l'element " + i + " est null!");
				} else {
					System.out.println("element " + i + " nom de la donn�e : "
							+ d.nom_de_donnee + " valeur donnee : "
							+ d.valeurDonnee);
				}
				i++;
			}
		}
		System.out.println("Liste reponse temporaire : ");
		if (liste_reponse_temp == null | liste_reponse_temp.size() == 0) {
			System.err.println("liste vide ou  non rempli!!!!!");
		} else {
			int i = 0;
			for (DoublonNomDonneeValeurDonnee d : liste_reponse_temp) {
				if (d == null) {
					System.out.println("l'element " + i + " est null!");
				} else {
					System.out.println("element " + i + " nom de la donn�e : "
							+ d.nom_de_donnee + " valeur donnee : "
							+ d.valeurDonnee);
				}
				i++;
			}
		}

	}

	public void calculerDonneeToListeTemp() {
		for (ElementQuestionnaire el : liste_elementQuestionnaire) {
			// System.err.println("eeeeeeeeeeeeeeeeee : "+el.toString());
			if (el instanceof DonneeCalculee) {// calculerDonnee()
				// System.out.println("Donn�e calcul� : "+ el.toString());
				// System.out.println(" pour : "+ el.getNomDeDonn�e() );
				this.setReponse(false,
						new DoublonNomDonneeValeurDonnee(el.getNomDeDonnee(),
								((DonneeCalculee) el).calculerDonnee()));
			}

		}

	}

	public String listeReponseTempIncomplete() {
		for (DoublonNomDonneeValeurDonnee reponse : this.liste_reponse_temp) {
			if (reponse.valeurDonnee == null) {
				// System.out.println(" donn�e : "+reponse.nom_de_donnee);
				return reponse.nom_de_donnee;
			}
		}
		return null;
	}

	public boolean listeReponseEgale() {
		if (liste_reponse.size() != liste_reponse_temp.size()) {
			return false;
		}
		for (int i = 0; i < liste_reponse_temp.size(); i++) {
			if (liste_reponse.get(i).nom_de_donnee != liste_reponse_temp.get(i).nom_de_donnee
					| liste_reponse.get(i).valeurDonnee != liste_reponse_temp
							.get(i).valeurDonnee) {
				return false;
			}
		}
		return true;
	}

	public void setPrecompletion(boolean precompletion) {
		this.precompletion = precompletion;
	}

	public void precompletion() {
		if (this.precompletion) {
			Date dateLastQuest = GestionDonnee
					.getDateDernierQuestionnaireToutesVersion(
							this.quest.getQuestionnaire(), idPersonne);
			if (dateLastQuest != null) {
				if (new Date().compare(dateLastQuest,
						this.limiteEnMoisPrecompletion)) {
					System.err
							.println("Vous etes dans le cas ou il devriait avoir une precompletion!");
					this.remplirQuestionnairePreRempli(dateLastQuest);
					this.liste_reponse_temp.clear();
					// remplace lign d'apres!
					for (DoublonNomDonneeValeurDonnee aj : liste_reponse) {
						liste_reponse_temp
								.add(new DoublonNomDonneeValeurDonnee(
										aj.nom_de_donnee, aj.valeurDonnee));
					}

					// this.liste_reponse_temp.addAll(liste_reponse);
					// this.toStringListeReponse();
					this.questGraphique.setPreSelection(this);
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(
							null,
							"<html><center>Bienvenue, comme vous �tes venu derni�rement,<br /> nous vous invitons juste � corriger d'eventuelle(s) erreur(s) ou changement(s) dans votre situation.</center></html>",
							"Bonjour!", JOptionPane.INFORMATION_MESSAGE);
					this.questGraphique.add(jop);
					this.miseAjour = true;
				}
			}
		}
		// TODO Auto-generated method stub

	}

}
