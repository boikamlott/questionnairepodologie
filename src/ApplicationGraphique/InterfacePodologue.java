package ApplicationGraphique;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Application.Configuration;
import Questionnaire.Date;
import Questionnaire.GestionDonnee;
import Questionnaire.InfoQuestExistant;
import Questionnaire.Patient;
import Questionnaire.Tripplon_questionnaire;

public class InterfacePodologue extends JPanel {
	// public JPannelRecherchePersonne recherche;
	// public JButton test;
	// partie recherche podologue
	private JLabel presentation;

	private JLabel texteNom;
	public JTextField recupNom;

	private JLabel textePrenom;
	public JTextField recupPrenom;

	private JLabel texteAge;
	public JComboBox recupJour;
	public JComboBox recupMois;
	public JComboBox recupAnnee;

	public JButton boutonValider;// = new JButton("Valider");
	public JButton boutonEffacer;// = new JButton("Effacer");
	public JButton boutonCreer;

	int largeur, hauteur;

	private ApplicationGraphique ap;
	public JButton recherchePersonne;// ,prevention,pathologie,recherche;
	public ArrayList<Patient> listeDernierPatient = new ArrayList<Patient>();
	// public ArrayList<LigneGraphiquePatient> listeBoutonDernierPatient
	// public ArrayList<JButton> listeBoutonDernierPatient = new
	// ArrayList<JButton>();
	public ArrayList<LigneGraphiquePatient> listeBoutonDernierPatient;// = new
																		// ArrayList<LigneGraphique>();
	public ArrayList<JButton> listeExport;
	// private Map<String,JButton> listeExport;

	// partie positionnement graphique
	public int quantiteLigne;
	int y;// =
			// (ApplicationGraphique.getInstance().taille.getHauteur_application()/(quantiteLigne+2))-5;//(questGraphique.quantiteLigne+2);
	int width;// =
				// ApplicationGraphique.getInstance().taille.getLargeur_application()-10;
	int height;// =
				// ApplicationGraphique.getInstance().taille.getHauteur_application()/(quantiteLigne+2);//questGraphique.quantiteLigne+2);

	// partie details patient
	private LinkedList<InfoQuestExistant> listInfoQuestExistant;
	public LinkedList<JFrameQuestPodo> listeQuestGraphPatient;// = new
																// LinkedList<JFrameQuestPodo>();
	public LinkedList<JButtonNouveauQuestionnaireMedical> nouveauQuestionnaireMedical;
	public LinkedList<JButtonInfoQuestExistant> listeJButonInfoQuestExistant;
	public JButton retour;

	// /liste des questionnaire ouvert en dhors de la frame principale :
	public LinkedList<JFrameQuestPodo> listeJFrameQuestPodo = new LinkedList<JFrameQuestPodo>();

	public InterfacePodologue(ApplicationGraphique appli) {
		this.setLayout(null);
		this.ap = appli;
		quantiteLigne = 6;

		y = (Configuration.getInstances().getHauteurApplication() / (quantiteLigne + 2)) - 5;// (questGraphique.quantiteLigne+2);
		width = Configuration.getInstances().getLargeurApplication() - 10;
		height = Configuration.getInstances().getHauteurApplication()
				/ (quantiteLigne + 2);// questGraphique.quantiteLigne+2);

		listeDernierPatient = new ArrayList<Patient>();
		// ArrayList<LigneGraphiquePatient>
		listeBoutonDernierPatient = new ArrayList<LigneGraphiquePatient>();
		listeExport = new ArrayList<JButton>();

		// partie patient
		GestionDonnee.getDerniersPatients(listeDernierPatient, quantiteLigne);
		// System.out.println("taille de la liste des dernier patient : "+listeDernierPatient.size());
		afficherListeDernierPatient();

		// partie recherche d'un patient
		afficherLienRecherchePersonne();

		// partie Export
		afficherListeExportEtBoutonActualiser();

	}

	private void afficherLienRecherchePersonne() {

		recherchePersonne = new JButton("Recherche manuelle de personne : ");// phrase.setText("<html>"+ligne.getTexteQuestion()+"</html>");
		recherchePersonne.setBounds(2, (y * quantiteLigne + 1), width, height);
		// System.out.println(recherchePersonne.getBounds());
		recherchePersonne.setVisible(true);
		recherchePersonne.addActionListener(ap);
		this.add(recherchePersonne);
	}

	private void afficherListeExportEtBoutonActualiser() {
		int nombreElement = 4;
		// afficher toujours en dernier donc le numero de ligne est �gale � la
		// quantit� de ligne
		// prevention => index = 0!
		listeExport.add(new JButton("Exporter donn�e pr�vention"));
		listeExport.get(0).setBounds(2, y * (quantiteLigne + 1) + 8,
				width / nombreElement, height);
		listeExport.get(0).addActionListener(ap);
		listeExport.get(0).setVisible(true);
		this.add(listeExport.get(0));

		// pathologie => index 1
		listeExport.add(new JButton("Exporter donn�e pathologie"));
		listeExport.get(1).setBounds((width / nombreElement) + 3,
				y * (quantiteLigne + 1) + 8, width / nombreElement, height);
		// System.out.println(listeExport.get(1).getBounds());
		listeExport.get(1).addActionListener(ap);
		listeExport.get(1).setVisible(true);
		this.add(listeExport.get(1));

		// recherche => index 2
		listeExport.add(new JButton("Exporter donn�e recherche"));
		listeExport.get(2).setBounds(((width / nombreElement) + 2) * 2,
				8 + (quantiteLigne + 1) * y, width / nombreElement, height);
		listeExport.get(2).addActionListener(ap);
		listeExport.get(2).setVisible(true);
		this.add(listeExport.get(2));

		// bouton actualiser :
		retour = new JButton("Actualiser");
		retour.setBounds((((width / nombreElement)) * 3) + 5, 8
				+ (quantiteLigne + 1) * y, (width / nombreElement) - 1, height);
		retour.addActionListener(ap);
		retour.setVisible(true);
		this.add(retour);

	}

	public void ajoutRecherchePodologue() {
		largeur = Configuration.getInstances().getLargeurApplication();// frame.apGraphique.taille.getLargeur_application();
		hauteur = Configuration.getInstances().getHauteurApplication();// apGraphique.taille.getHauteur_application();

		// partie label de presentation de la page :
		presentation = new JLabel(
				"<html><center><p1>Bienvenue dans l'interface de recherche ou de cr�ation d'une personne. <br/>Tous les champs sont obligatoire pour la cr�ation d'une nouvelle personne<br/>Les champs nom et pr�nom suffisent pour la recherche</p1></center></html>");
		// presentation.setBounds((int) Math.round(largeur*0.10), (int)
		// Math.round(hauteur*0.10),(int) Math.round(largeur*0.80),(int)
		// Math.round(hauteur*0.12));//setBackground(Color.orange);
		presentation.setBounds((int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.10),
				(int) Math.round(largeur * 0.80),
				(int) Math.round(hauteur * 0.12));// setBackground(Color.orange);
		presentation.setHorizontalAlignment(JLabel.CENTER);
		this.add(presentation);

		// partie de pr�sentation de recuperation nom et prenom
		texteNom = new JLabel("Quel est le nom du patient? ");
		// texteNom.setBounds((int) Math.round(largeur*0.10), (int)
		// Math.round(hauteur*0.30),(int) Math.round(largeur*0.40),(int)
		// Math.round(hauteur*0.06));//setBackground(Color.orange);
		texteNom.setBounds((int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.30),
				(int) Math.round(largeur * 0.40),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		this.add(texteNom);
		recupNom = new JTextField();
		// recupNom.setBounds((int) Math.round(largeur*0.45), (int)
		// Math.round(hauteur*0.30),(int) Math.round(largeur*0.40),(int)
		// Math.round(hauteur*0.06));//setBackground(Color.orange);
		recupNom.setBounds((int) Math.round(largeur * 0.45),
				(int) Math.round(hauteur * 0.30),
				(int) Math.round(largeur * 0.40),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		this.add(recupNom);

		textePrenom = new JLabel("Quel est le prenom du patient? ");
		// textePrenom.setBounds((int) Math.round(largeur*0.10), (int)
		// Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
		// Math.round(hauteur*0.06));//setBackground(Color.orange);
		textePrenom.setBounds((int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.4),
				(int) Math.round(largeur * 0.40),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		this.add(textePrenom);
		recupPrenom = new JTextField();
		// recupPrenom.setBounds((int) Math.round(largeur*0.45), (int)
		// Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
		// Math.round(hauteur*0.06));//setBackground(Color.orange);
		recupPrenom.setBounds((int) Math.round(largeur * 0.45),
				(int) Math.round(hauteur * 0.4),
				(int) Math.round(largeur * 0.40),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		this.add(recupPrenom);

		// partie r�cuperation de la date de naissance :
		texteAge = new JLabel("Quel est votre date de naissance ? ");
		// texteAge.setBounds((int) Math.round(largeur*0.10), (int)
		// Math.round(hauteur*0.55),(int) Math.round(largeur*0.40),(int)
		// Math.round(hauteur*0.06));//setBackground(Color.orange);
		texteAge.setBounds((int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.55),
				(int) Math.round(largeur * 0.40),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		this.add(texteAge);

		// jour :
		Vector<Integer> jour = new Vector<Integer>();
		for (Integer i = 1; i < 32; i++) {
			jour.add(i);
		}
		JLabel texteJour = new JLabel("Jour");
		texteJour.setBounds((int) Math.round(largeur * 0.45),
				(int) Math.round(hauteur * 0.50),
				(int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		texteJour.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteJour);
		recupJour = new JComboBox(jour);
		recupJour.setBounds((int) Math.round(largeur * 0.45),
				(int) Math.round(hauteur * 0.55),
				(int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		recupJour.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupJour);

		// mois :
		Vector<Integer> mois = new Vector<Integer>();
		for (Integer i = 1; i < 13; i++) {
			mois.add(i);
		}
		JLabel texteMois = new JLabel("Mois");
		texteMois.setBounds((int) Math.round(largeur * 0.60),
				(int) Math.round(hauteur * 0.50),
				(int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		texteMois.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteMois);
		recupMois = new JComboBox(mois);
		recupMois.setBounds((int) Math.round(largeur * 0.60),
				(int) Math.round(hauteur * 0.55),
				(int) Math.round(largeur * 0.10),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		recupMois.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupMois);

		// ann�e :
		Vector<Integer> annee = new Vector<Integer>();
		for (Integer i = 1910; i < 2015; i++) {
			annee.add(i);
		}
		JLabel texteAnnee = new JLabel("Annee");
		texteAnnee.setBounds((int) Math.round(largeur * 0.75),
				(int) Math.round(hauteur * 0.50),
				(int) Math.round(largeur * 0.13),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		texteAnnee.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteAnnee);
		recupAnnee = new JComboBox(annee);
		recupAnnee.setBounds((int) Math.round(largeur * 0.75),
				(int) Math.round(hauteur * 0.55),
				(int) Math.round(largeur * 0.13),
				(int) Math.round(hauteur * 0.04));// setBackground(Color.orange);
		recupAnnee.setSelectedIndex(65);
		recupAnnee.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupAnnee);

		// partie boutons :
		boutonEffacer = new JButton("Annuler");
		boutonEffacer.setBounds((int) Math.round(largeur * 0.06),
				(int) Math.round(hauteur * 0.80),
				(int) Math.round(largeur * 0.25),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		boutonEffacer.addActionListener(ap);
		this.add(boutonEffacer);

		boutonCreer = new JButton("Cr�er");
		boutonCreer.setBounds((int) Math.round(largeur * 0.37),
				(int) Math.round(hauteur * 0.80),
				(int) Math.round(largeur * 0.25),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		boutonCreer.addActionListener(ap);
		this.add(boutonCreer);

		boutonValider = new JButton("Rechercher");
		boutonValider.setBounds((int) Math.round(largeur * 0.68),
				(int) Math.round(hauteur * 0.80),
				(int) Math.round(largeur * 0.25),
				(int) Math.round(hauteur * 0.06));// setBackground(Color.orange);
		boutonValider.addActionListener(ap);
		this.add(boutonValider);

	}

	private void afficherListeDernierPatient()// , int quantiteLigne)
	{
		System.out
				.println("debut de l'Affichage de la liste des derniers patient! ");
		// listeBoutonDernierPatient = new ArrayList<LigneGraphiquePatient>();
		for (int i = 0; i < quantiteLigne; i++) {
			Patient pat = listeDernierPatient.get(i);
			// System.out.println("hello + "+pat.idPersonne+" i : "+i);
			// System.out.println("hello"+listeBoutonDernierPatient.size());

			listeBoutonDernierPatient.add(new LigneGraphiquePatient(pat));// ,width,height));//QuestionLikerteGraphique()//new
																			// JButton(pat.nom+" de son prenom "+pat.prenom+" n� le "+pat.dateDeNaissance.affiche));
			listeBoutonDernierPatient.get(i).setBounds(2, y * i, width, height);
			listeBoutonDernierPatient.get(i).addActionListener(ap);// listeBoutonDernierPatient.get(i));
			this.add(listeBoutonDernierPatient.get(i));
			listeBoutonDernierPatient.get(i).setVisible(true);
		}

	}

	public void afficherListeQuestionnairePatient(Patient patient) {

		for (Component comp : this.getComponents()) {
			this.remove(comp);
			comp = null;
		}
		// r�cuperation des diff�rents questionnaires remplit par le patient
		listInfoQuestExistant = new LinkedList<InfoQuestExistant>();
		GestionDonnee.getQuestionnaires(listInfoQuestExistant, patient);
		quantiteLigne = listInfoQuestExistant.size() + 2;// 2 car +1 pour
															// nouveau
															// questionnaire
															// medical et +1
															// pour le bouton
															// retour.

		// information remplacant les layout merdique de java
		y = (Configuration.getInstances().getHauteurApplication() / (quantiteLigne)) - 5;// (questGraphique.quantiteLigne+2);
		width = Configuration.getInstances().getLargeurApplication() - 10;
		height = Configuration.getInstances().getHauteurApplication()
				/ (quantiteLigne);

		// bouton nouveau questionnaire

		LinkedList<Tripplon_questionnaire> questionnaireExistant = null;
		questionnaireExistant = new LinkedList<Tripplon_questionnaire>();
		questionnaireExistant.addAll(Configuration.getInstances()
				.getListeQuestionnaireActif());
		int nombreQuest = questionnaireExistant.size();
		nouveauQuestionnaireMedical = null;
		nouveauQuestionnaireMedical = new LinkedList<JButtonNouveauQuestionnaireMedical>();

		for (Tripplon_questionnaire t : questionnaireExistant) {
			this.nouveauQuestionnaireMedical
					.addLast(new JButtonNouveauQuestionnaireMedical(patient,
							camelCaseToPhrase(t.getQuestionnaire()), t
									.getQuestionnaire()));
			nouveauQuestionnaireMedical.getLast().setBounds(
					(width * (nouveauQuestionnaireMedical.size() - 1))
							/ nombreQuest, 0, width / nombreQuest, height);
			// System.out.println(recherchePersonne.getBounds());
			nouveauQuestionnaireMedical.getLast().setVisible(true);
			nouveauQuestionnaireMedical.getLast().addActionListener(ap);
			this.add(nouveauQuestionnaireMedical.getLast());
		}
		// System.out.println("taille de la liste des quest existant : "+listInfoQuestExistant.size());

		// ajout des liens des diff�rents questionnaire existant a la vol�:
		listeJButonInfoQuestExistant = new LinkedList<JButtonInfoQuestExistant>();
		for (int i = 0; i < listInfoQuestExistant.size(); i++) {
			// System.err.println("nouveau bouton questionnaire !");
			listeJButonInfoQuestExistant
					.addLast(new JButtonInfoQuestExistant(listInfoQuestExistant
							.get(i), listInfoQuestExistant.size()));
			listeJButonInfoQuestExistant.getLast().setBounds(0, y * (i + 1),
					width, height);
			listeJButonInfoQuestExistant.getLast().addActionListener(ap);
			this.add(listeJButonInfoQuestExistant.getLast());
			listeJButonInfoQuestExistant.getLast().setVisible(true);
		}

		// enfin du lien retour qui fait au passage un raffraichissement de
		// l'interface initiale des podologues :
		this.retour = new JButton("retour");// phrase.setText("<html>"+ligne.getTexteQuestion()+"</html>");
		retour.setBounds(0, (y * (quantiteLigne - 1)), width, height);
		// System.out.println(recherchePersonne.getBounds());
		retour.setVisible(true);
		retour.addActionListener(ap);
		this.add(retour);

		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}

	private void afficherQuestionnairesPatient(Patient patient,
			Tripplon_questionnaire questTriple, Date dateQuestionnaire) {
		// GestionDonnee.getQuestionnaires(patient);// TODO Auto-generated
		// method stub

		listeQuestGraphPatient.addLast(new JFrameQuestPodo());
		listeQuestGraphPatient.getLast().setSize(
				Configuration.getInstances().getLargeurApplication(),
				Configuration.getInstances().getHauteurApplication());
		listeQuestGraphPatient.getLast().setLocation(
				10 * listeQuestGraphPatient.size(),
				10 * listeQuestGraphPatient.size());
		// listeQuestGraphPatient.getLast().setLocationRelativeTo(null);
		// listeQuestGraphPatient.getLast().setDefaultCloseOperation(JFrame.EXIT_);
		listeQuestGraphPatient.getLast().setResizable(false);
		listeQuestGraphPatient.getLast().setAlwaysOnTop(false);// true);
		listeQuestGraphPatient.getLast().setUndecorated(false);// true);

		// listeQuestGraphPatient.getLast().quest = new
		// Questionnaire(questTriple,patient,dateQuestionnaire);
		listeQuestGraphPatient.getLast().ajout(questTriple, patient,
				dateQuestionnaire);
		listeQuestGraphPatient.getLast().setVisible(true);

		// Questionnaire questMedical = new Questionnaire("medical", patient);

		/*
		 * Date dateDerniereReponse =
		 * GestionDonnee.getDateDernierQuestionnaireVersionDonneeActuel
		 * ("medical", patient.idPersonne); if (dateDerniereReponse != null) {
		 * System.err.println("date : "+dateDerniereReponse);
		 * questMedical.liste_reponse.clear();
		 * questMedical.liste_reponse.addAll(GestionDonnee.getDonnees("medical",
		 * patient.idPersonne, dateDerniereReponse));
		 * for(DoublonNomDonneeValeurDonnee li_temp :
		 * questMedical.liste_reponse_temp) { for(DoublonNomDonneeValeurDonnee
		 * li : questMedical.liste_reponse) { if
		 * (li_temp.nom_de_donnee.compareTo(li.nom_de_donnee)==0) {
		 * li_temp.valeurDonnee = li.valeurDonnee; } } } // questMedical.
		 * questMedical.toStringListeReponse();
		 * 
		 * questMedical.questGraphique.setPreSelection(questMedical);//,true); }
		 */
		// listeQuestGraphPatient.getLast().add(questMedical.questGraphique);
		// questMedical.questGraphique.setVisible(true);
		// java.awt.Dimension screenSize =
		// java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		// listeQuestGraphPatient.getLast().setVisible(true);
		/*
		 * � la vol�e : new JFrame() affiche questpre rempli button enregistreur
		 * modif! premier as : quest medical seulement!<
		 */

	}

	public void actualiserInterfacePodologue() {
		for (Component comp : this.getComponents()) {
			remove(comp);
			comp = null;
		}
		this.setLayout(null);
		quantiteLigne = 6;

		y = (Configuration.getInstances().getHauteurApplication() / (quantiteLigne + 2)) - 5;// (questGraphique.quantiteLigne+2);
		width = Configuration.getInstances().getLargeurApplication() - 10;
		height = Configuration.getInstances().getHauteurApplication()
				/ (quantiteLigne + 2);// questGraphique.quantiteLigne+2);

		listeDernierPatient.clear();
		// ArrayList<LigneGraphiquePatient>
		listeBoutonDernierPatient.clear();
		listeExport.clear();

		// partie patient
		GestionDonnee.getDerniersPatients(listeDernierPatient, quantiteLigne);
		// System.out.println("taille de la liste des dernier patient : "+listeDernierPatient.size());
		afficherListeDernierPatient();

		// partie recherche d'un patient
		afficherLienRecherchePersonne();

		// partie Export
		afficherListeExportEtBoutonActualiser();

		this.repaint();
		this.setVisible(true);
	}

	private String camelCaseToPhrase(String phrase) {
		String[] phraseMinus = phrase.split("[A-Z]");
		String[] phraseMajus = phrase.split("[a-z]");
		String retour = phraseMinus[0] + " ";
		int i = 1;
		for (int j = 0; j < phraseMajus.length; j++) {
			if (phraseMajus[j].compareTo("") != 0) {
				retour += phraseMajus[j].toLowerCase() + phraseMinus[i] + " ";
				i++;
			}
		}
		System.out.println("phrase : " + retour);

		return retour;
	}

}
