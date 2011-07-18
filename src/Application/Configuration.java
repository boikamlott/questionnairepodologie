package Application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

import org.jdom.output.*;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.nio.channels.FileChannel;

import javax.xml.namespace.QName;

import Questionnaire.*;

public class Configuration implements Cloneable {
	// permet de lire les differente info de configuration dans les fichiers xml
	// les contenantss
	// il reste la partie qui permet de bloquer les different fichier lors
	// d'�dition de ceux ci a faire!

	// attributs locaux
	private int largeurApp;
	private int hautAppli;
	private String typAppli;// serveur ou podologue
	private String nomPodo;
	private String podoStrict;

	// attributs generaux
	private List<Tripplon_questionnaire> listQActif;
	private List<Tripplon_questionnaire> listeQInactif;
	private String questBlog;
	private List<String> listPodo;

	// attribut necessaire a la lecture du fichier xml
	private org.jdom.Document docLocal;
	private Element racLocal;
	private String cheminLocalXml = new String(
			"fichier/configurationLocal.xml");

	private org.jdom.Document docPartage;
	private Element racPartage;
	private String cheminPartage;// ne pas marquer le nom du fichier xml ici!!!!!!!!!!!!!!!!!!!!!!!!!!

        private static Configuration instance;
        
	private Configuration() {
		listPodo = new ArrayList<String>();
		listQActif = new ArrayList<Tripplon_questionnaire>();
		listeQInactif = new ArrayList<Tripplon_questionnaire>();

		SAXBuilder sxb = new SAXBuilder();
		try {
			this.docLocal = sxb.build(new File(this.cheminLocalXml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		racLocal = docLocal.getRootElement();
		// dom du fichier xml locale cr�er, passage � la r�cuperation des
		// donn�es :
		recupererConfigLocal();

		//
		cheminPartage += "/fichier/";
		try {
			this.docPartage = sxb.build(new File(this.cheminPartage
					+ "configurationGeneral.xml"));
		} catch (Exception e) {
			System.err
					.println("Probleme lors du chargement du fichier de configuration gen�rale, etes vous sur d'etre bien connect� au reseau?");
			e.printStackTrace();
		}
		racPartage = docPartage.getRootElement();
		// dom du fichier xml locale cr�er
		recupererConfigPartager();

	}

	public DoublonTaille getTailleApplication() {
		return new DoublonTaille(largeurApp, hautAppli);
	}

	
	private void recupererConfigLocal() {
		List<Element> liste_conf = racLocal.getChildren();
		// On cr�e un Iterator sur notre liste
		Element courant;
		Iterator<Element> i = liste_conf.iterator();
		while (i.hasNext()) {
			courant = i.next();

			if(courant.getName().equals("largeur_application")) {
				largeurApp = Integer.parseInt(courant.getValue().trim());
			}	

			if(courant.getName().equals("hauteur_application")) {
				hautAppli = Integer.parseInt(courant.getValue()
						.trim());
			}	

			if(courant.getName().equals("type_application")) {
				typAppli = courant.getValue().trim();
			}	

			if(courant.getName().equals("nom_podologue")) {
				nomPodo = courant.getValue().trim();
			}	

			if(courant.getName().equals("chemin_partage")) {
				cheminPartage = courant.getValue().trim();
			}	

			if(courant.getName().equals("podologue_stricte")) {
				podoStrict = courant.getValue().trim();
			}	
			
		}

	}

	private void recupererConfigPartager() {

		// hzejfghzENGVQBFHJvzejfvj

		// TODO Auto-generated method stub
		List liste_conf = racPartage.getChildren();
		// On cr�e un Iterator sur notre liste
		Iterator i = liste_conf.iterator();
		while (i.hasNext()) {

			Element courant = (Element) i.next();
                        
			if (courant.getName().trim().compareTo("podologue") == 0) {
				if (courant.getAttributeValue("actif").compareTo("oui") == 0) {
					listPodo.add(courant.getValue().trim());
				}
			}
			if (courant.getName().trim().compareTo("questionnaire_bloque") == 0) {
				questBlog = courant.getValue().trim();
			}
			
			if (courant.getName().trim().compareTo("questionnaire") == 0) {
				List list_quest = courant.getChildren();
				Iterator j = list_quest.iterator();
				while (j.hasNext()) {
					Element quest = (Element) j.next();
					if (quest.getName().equals("questionnaire_actif")) {
						int donnee = Integer.parseInt(quest.getAttributeValue(
								"versionDonnee").trim());
						int affich = Integer.parseInt(quest.getAttributeValue(
								"versionAffichage").trim());
						Tripplon_questionnaire tq = new Tripplon_questionnaire(
								quest.getValue().trim(), donnee, affich);
						listQActif.add(tq);
					}
					if (quest.getName().trim().equals("questionnaire_inactif")) {
						Tripplon_questionnaire tq = new Tripplon_questionnaire(
								quest.getValue().trim(), Integer.parseInt(quest
										.getAttributeValue("versionDonnee")
										.trim()), Integer.parseInt(quest
										.getAttributeValue("versionAffichage")
										.trim()));
						listeQInactif.add(tq);
					}
				}
			}
		}
		// System.out.println("fin du chargement de la config general!!!!!!!!!!");
	}

	public void afficheALL() {
		System.out
				.println("---------------- Configuration locale -----------------------");

		// On cr�e une List contenant tous les noeuds "etudiant" de l'Element
		// racine
		List liste_conf = racLocal.getChildren();
		// On cr�e un Iterator sur notre liste
		Iterator i = liste_conf.iterator();
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			System.out.println(courant.getName() + " et ca value : "
					+ courant.getValue());
		}
		System.out
				.println("---------------- Configuration general -----------------------");
		liste_conf = racPartage.getChildren();
		// On cr�e un Iterator sur notre liste
		i = liste_conf.iterator();
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			System.out.println(courant.getName() + " et ca value : "
					+ courant.getValue());
		}
	}

	// Prend l'objet en cours et rajoute les nouveaux �l�mentet supprime ceux
	// plus pr�sent
	private void miseAJourConfiguration(Configuration newConf) {
		// mise a jour de la configuration locale :
		List liste_conf = racLocal.getChildren();
		// On cr�e un Iterator sur notre liste
		Iterator<Element> i = liste_conf.iterator();
		while (i.hasNext()) {
			Element courant = (Element) i.next();

			if (courant.getName() == "largeur_application")
				courant.setText(String.valueOf(newConf.largeurApp));

			if (courant.getName() == "hauteur_application")
				courant.setText(String.valueOf(newConf.hautAppli));

			if (courant.getName() == "type_application")
				courant.setText(newConf.typAppli);

			if (courant.getName() == "nom_podologue")
				courant.setText(newConf.nomPodo);

			if (courant.getName() == "chemin_partage")
				courant.setText(newConf.cheminPartage);

			if (courant.getName() == "podologue_stricte")
				courant.setText(newConf.podoStrict);
			// enregistrement du dom
		}
		try {
			// On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			// Remarquez qu'il suffit simplement de cr�er une instance de
			// FileOutputStream
			// avec en argument le nom du fichier pour effectuer la
			// s�rialisation.
			sortie.output(docLocal, new FileOutputStream(new File(
					cheminLocalXml)));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		// mise a jour de la partie commune
		liste_conf = null;
		liste_conf = newConf.racPartage.getChildren();
		// On cr�e un Iterator sur notre liste
		i = null;
		i = liste_conf.iterator();
		// System.out.println(liste_conf.size());
		for (int ii = 0; ii < liste_conf.size(); ii++) {
			// System.out.println("bgkjsgfzegsuf");
			// System.out.println(i.next().getClass().getName());
			org.jdom.Element courant = (org.jdom.Element) liste_conf.get(ii);// i.next();

			if (courant.getName() == "podologue") {
				boolean podoTrouve = false;
				Iterator j = listPodo.iterator();
				while (j.hasNext()) {
					String podo = (String) j.next();

					if (podo.compareTo(courant.getValue()) == 0) {
						podoTrouve = true;
						courant.setAttribute("actif", "oui");
					}
				}
				if (!podoTrouve) {
					courant.setAttribute("actif", "non");
				}
			}
		}

	}

	private void copierRepertoire(File repertoire, String ancienChemin,
			String nouveauChemin) {
		if (repertoire.isDirectory()) {
			File[] list = repertoire.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					// Appel r�cursif sur les sous-r�pertoires
					copierRepertoire(list[i],
							ancienChemin + repertoire.getName(), nouveauChemin
									+ repertoire.getName());// a voir si il faut
															// mettre un slash
															// entre les 2!!!!!!
				}
			} else {
				System.err.println(repertoire + " : Erreur de lecture.");
			}
		} else {
			FileChannel in = null; // canal d'entr�e
			FileChannel out = null; // canal de sortie

			try {
				// Init
				in = new FileInputStream(repertoire.getAbsolutePath())
						.getChannel();
				out = new FileOutputStream(nouveauChemin + repertoire.getName())
						.getChannel();

				// Copie depuis le in vers le out
				in.transferTo(0, in.size(), out);
			} catch (Exception e) {
				e.printStackTrace(); // n'importe quelle exception
			} finally { // finalement on ferme
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	// singleton permettant d'�tre sur que la configuration est la bonne :
	// On recr� � chaque fois l'instances de la classes, au cas ou il il ais eu
	// des modifications, celui limite extrement
	public static Configuration getInstances() {
		if(instance == null) {
                    instance = new Configuration();
                }
                return instance;
	}

	// methode
	public String getAdresseQuestionnaire(String quest) {

		for (int i = 0; i < this.listQActif.size(); i++) {
			if (this.listQActif.get(i).getQuestionnaire()
					.compareTo(quest) == 0) {

				return cheminPartage
						+ "quest/"
						+ this.listQActif.get(i)
								.getQuestionnaire()
						+ "_V"
						+ this.listQActif.get(i)
								.getVersionDonnee()
						+ "_"
						+ this.listQActif.get(i)
								.getVersionAffichage() + ".xml";
			}
		}
		System.err
				.println("Le questionnaire que vous voulez ouvrir n'as pas/plus de version valide ou n'existe pas!");
		return null;
	}

	public String getAdresseDonneeQuestionnaire(String quest) {
		for (int i = 0; i < this.listQActif.size(); i++) {
			if (this.listQActif.get(i).getQuestionnaire()
					.compareTo(quest) == 0) {
				return cheminPartage
						+ "bdd/"
						+ this.listQActif.get(i)
								.getQuestionnaire()
						+ "_V"
						+ this.listQActif.get(i)
								.getVersionDonnee() + ".csv";
			}
		}
		System.err.println("Le questionnaire que vous voulez ouvrir n'as pas/plus de version valide ou n'existe pas! "
                        + "This is the M!! It's all....!");
		return null;
	}

	public int getLargeurApplication() {
		return largeurApp;
	}

	public void setLargeurApplication(int largeur_application) {
		this.largeurApp = largeur_application;
	}

	public int getHauteurApplication() {
		return hautAppli;
	}

	public void setHauteurApplication(int hauteur_Application) {
		this.hautAppli = hauteur_Application;
	}

	public String getTypeApplication() {
		return typAppli;
	}

	public void setTypeApplication(String type_application) {
		this.typAppli = type_application;
	}

	public String getNomPodologue() {
		return nomPodo;
	}

	public void setNomPodologue(String nom_podologue) {
		this.nomPodo = nom_podologue;
	}

	public String getCheminPartage() {
		return cheminPartage;
	}

	public void setCheminPartage(String chemin_partage) {
		this.cheminPartage = chemin_partage;
	}

	public List<Tripplon_questionnaire> getListeQuestionnaireInactif() {
		return listeQInactif;
	}

	public void setListeQuestionnaireInactif(
			List<Tripplon_questionnaire> liste_questionnaire_inactif) {
		this.listeQInactif = liste_questionnaire_inactif;
	}

	public List<Tripplon_questionnaire> getListeQuestionnaireActif() {
		return listQActif;
	}

	public void setListeQuestionnaireActif(
			List<Tripplon_questionnaire> liste_questionnaire_actif) {
                this.listQActif = liste_questionnaire_actif;
	}

	public List<String> getListePodologue() {
		return listPodo;
	}

	public void setListePodologue(List<String> liste_podologue) {
                this.listPodo = liste_podologue;
	}


	public int getVersionDonneeQuestionnaire(String nom_questionnaire) {

		for (int i = 0; i < this.listQActif.size(); i++)
			if (this.listQActif.get(i).getQuestionnaire()
					.compareTo(nom_questionnaire) == 0) {
				return this.listQActif.get(i).getVersionDonnee();
			}
		System.err
				.println("Le questionnaire que vous voulez ouvrir n'as pas/plus de version valide ou n'existe pas!");
		return 0;
	}

	public String getAdresseQuestionnaire(Tripplon_questionnaire quest) {
		return cheminPartage + "quest/" + quest.getQuestionnaire() + "_V"
				+ quest.getVersionDonnee() + "_" + quest.getVersionAffichage()
				+ ".xml";
	}

	public int getVersionAffichageQuestionnaire(String nom_questionnaire) {
		for (int i = 0; i < this.listQActif.size(); i++) {
			// rajout des parenthese
			if (this.listQActif.get(i).getQuestionnaire()
					.compareTo(nom_questionnaire) == 0) {
				return this.listQActif.get(i)
						.getVersionAffichage();
			}
		}
		System.err.println("Le questionnaire que vous voulez ouvrir n'as pas/plus de version valide ou n'existe pas!");
		return 0;
	}

	public String getAdresseDonneeQuestionnaire(Tripplon_questionnaire questi) {
		return cheminPartage + "bdd/" + questi.getQuestionnaire() + "_V"
				+ questi.getVersionDonnee() + ".csv";
	}

	public int getLastVersionAffichageQuestionnaire(String nom_questionnaire,
			int versionDonneeQuestionnaire) {
		for (Tripplon_questionnaire quest : this.listQActif) {
			if (quest.getQuestionnaire().compareTo(nom_questionnaire) == 0
					& (quest.getVersionDonnee() == versionDonneeQuestionnaire)) {
				return quest.getVersionAffichage();
			}
		}
		System.err.println("Le questionnaire que vous chercher n'as pas de version active... on va renvoyer une des ersions inactive!");
		for (Tripplon_questionnaire quest : this.listQActif) {
			if (quest.getQuestionnaire().compareTo(nom_questionnaire) == 0) {
				return quest.getVersionAffichage();
			}
		}
		System.err.println("Pas d'autre version trouv�! errreeuuur et belllllllaaa!!!!");
		return -1;
	}
}
