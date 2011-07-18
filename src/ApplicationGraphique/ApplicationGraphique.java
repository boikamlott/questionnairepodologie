package ApplicationGraphique;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Application.*;
import Questionnaire.Date;
import Questionnaire.GestionDonnee;
import Questionnaire.Patient;
import Questionnaire.Questionnaire;

public class ApplicationGraphique extends JFrame implements ActionListener {

	private static ApplicationGraphique instance;
        private Erreur erreur;
	public ApplicationQuestionnaire appli;
	public IdentificationGraphique nouvellePersonne;
	public DoublonTaille taille;
	public boolean run;
	// partie podo
	protected InterfacePodologue podo;
        public Controller controller;

	public static ApplicationGraphique getInstance() {
		// singleton :
		if (instance == null) { // Premier appel
			instance = new ApplicationGraphique();
		}
		return instance;
	}

	private ApplicationGraphique() {
		// partie graphique
		this.setTitle("Questionnaire de suivi du centre de podologie de Podiatech Voiron");
		taille = new DoublonTaille(Configuration.getInstances()
				.getTailleApplication());
		this.setSize(this.taille.getLargeur_application(),
				this.taille.getHauteur_application());
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setAlwaysOnTop(false);
		this.setUndecorated(false);
                this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                controller = new Controller(this);
	}

	public void run() {
		if (Configuration.getInstances().getTypeApplication()
				.equals("podologue"))	{
			this.runPodologue();
		} else {
			appli = new ApplicationQuestionnaire();
			controller.lancerQuestionnaire();
		}
	}

            
        /**
         * Boik's comment
         * A 'retailler' step by step en MVC 
         */
	private void runQuestionnaire() {

                

		while (true) {
			// AjoutquestionnaireGraphique();
			run = false;
			showIdentificationGraphique();
			while (!run) {
				try {
					Thread.sleep(500);
					// System.out.println("tourne!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			// System.out.println("identification finit!!!!!!!!!!!!!!!!!!!  .  .  .  . ,cgsdqj,;sgfesqgfjqsfcsejgfesqhgfeksqjvfesqvgfjsqseqf");
			nouvellePersonne.setVisible(false); // pas necessaire! en faites
												// si!! ?????
			this.remove(nouvellePersonne);
			nouvellePersonne = null;// revient � detruire l'�lement en
									// theorie...
			run = false;

			if (appli.idPersonne == null) {
				// System.out.println("La personne n'as pas voulu que ces donn�es soit enregistr�");
				// nouvellePersonne.setVisible(false);
				// cr�er un label avec petite phrases
				// a faire plus tard : etes vous toujours sur que vous ne voulez
				// pas "preter" des information a caractere personnelle pour des
				// recherche scientifique?
			} else {
				System.out
						.println("pr�sentation et remplissage du questionnaire general!");
				ajoutQuestionnaireGraphique("general");
				//
				System.out
						.println("On cherche a voir si ce n'est pas lapremiere visite du patient : Si dans limite precompletion de quest general alors c visite de controle: ");
				this.appli.QG.precompletion();

				// System.out.println("test de liste reponses de QG apres ca cr�ation dans application graphique : ");
				// this.appli.QG.toStringListeReponse();
				this.appli.QG.questGraphique.verificationEllementActif();

				while (!run) {
					try {
						Thread.sleep(300);
						// System.out.println("encore la!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				run = false;
				this.appli.QG.toStringListeReponse();// );
				System.out
						.println("pr�sentation et remplissage des autres questionnaires si besoin ais!!");
				this.remove(this.appli.QG.questGraphique);
				this.appli.QG.questGraphique.setVisible(false);

				if (((ApplicationQuestionnaire) appli).idPersonne == null) {
					System.out
							.println("La personne n'as pas voulu que ces donn�es soit enregistr�");
					// nouvellePersonne.setVisible(false);
					// cr�er un label avec petite phrases
				} else {
					if (this.appli.creerListeQuestionnaire())
					// renvoie vrai si la fonction a ajuter un element dans la
					// liste!
					{
						// System.out.println("taille liste questionnaire : "+this.appli.liste_questionnaire.size());
						for (Questionnaire quest : this.appli.liste_questionnaire) {
							this.add(quest.questGraphique);
							quest.questGraphique.setVisible(true);
							this.validate();
							// this.repaint();
							run = false;
							while (!run) {
								// on attend que l'utilisateur ais cliquer sur
								// les boutons de fin du questionnaire
								try {
									Thread.sleep(300);
									// System.out.println("encore la!");
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							quest.questGraphique.setVisible(false);
							this.remove(quest.questGraphique);
							// this.c
						}
					}
				}
			}
			this.appli.liste_questionnaire.clear();
		}
	}

	private void runPodologue() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		while (true) {
			this.podo = new InterfacePodologue(this);
			this.add(podo);
			this.setVisible(true);
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ajout du questionnaire g�n�ral :
	public void showIdentificationGraphique() {
		nouvellePersonne = new IdentificationGraphique(this);
		afficher(nouvellePersonne);
	}

	public void ajoutQuestionnaireGraphique(String nomQuest) {
		int pos = ((ApplicationQuestionnaire) this.appli)
				.nouveauQuestionnaire(nomQuest);
		if (pos != -1) {
			this.add(((ApplicationQuestionnaire) this.appli).liste_questionnaire
					.get(pos).questGraphique);
			this.setVisible(true);
		} else {
			this.add(((ApplicationQuestionnaire) this.appli).QG.questGraphique);
			this.setVisible(true);
		}
	}

	/*
	 * private String demandeCheminExport() { //Recuper classe de base pour
	 * demander un chemin! return
	 * "C:\\Documents and Settings\\Owner\\Desktop\\taf voiron"; }
	 */

	/*
	 * private void export(String string) { recup adresse getMotif foreach
	 * personne : get quest general medical old puis quest x
	 * 
	 * }
	 */

	private void lancerIdentificationPodo() {
		/*
		 * run = false; JFrameRecherchePersonne recherchePersonne = new
		 * JFrameRecherchePersonne
		 * ("<html>Interface de recherche d'un patient</html>",this);
		 * while(!run) { System.out.println("Encore et toujours la!"); try {
		 * Thread.sleep(300); } catch (InterruptedException e1) {
		 * e1.printStackTrace(); } } recherchePersonne = null;
		 */run = false;
		this.setVisible(true);
	}

	/*
	 * public void detailPatient(Patient patient) { this.detailPatient= new
	 * DetailPatient(patient); this.add(detailPatient); this.setVisible(true);
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		if (Configuration.getInstances().getTypeApplication()
				.compareTo("podologue") == 0)
		// partie donn�e + deroulement de l'application
		{
			// -------------------------------------- liste bonton dernier
			// patient ---------------------------------------------//

			for (int i = 0; i < podo.listeBoutonDernierPatient.size(); i++) {
				// System.out.println("taille de la liste de bouton : "+podo.listeBoutonDernierPatient.size());
				// System.out.println("taille de quanti� : "+podo.quantiteLigne);
				if (e.getSource() == podo.listeBoutonDernierPatient.get(i)) {
					for (Component comp : podo.getComponents()) {
						this.podo.remove(comp);
						comp = null;
					}
					podo.afficherListeQuestionnairePatient(podo.listeDernierPatient
							.get(i));
				}
			}

			// afficher toujours en dernier donc le numero de ligne est �gale �
			// la quantit� de ligne
			// prevention => index = 0!
			// A faire

			/*
			 * if (e.getSource()==podo.listeExport.get(0)) { String cheminExport
			 * = demandeCheminExport();
			 * GestionDonnee.export("prevention",cheminExport); } //pathologie
			 * => index 1 if (e.getSource()==podo.listeExport.get(1)) { String
			 * cheminExport = demandeCheminExport();
			 * GestionDonnee.export("pathologie",cheminExport); } //recherche =>
			 * index 2 if (e.getSource()==podo.listeExport.get(2)) { String
			 * cheminExport = demandeCheminExport();
			 * GestionDonnee.export("recherche",cheminExport); }
			 */
			if (e.getSource() == podo.recherchePersonne) {
				this.podo.setVisible(false);
				// this.podo = null;
				// this.setVisible(false);
				this.podo.removeAll();
				// this.podo.recherche = new JPannelRecherchePersonne();
				// this.podo.recherche.ajout(this);
				// this.podo.add(this.podo.recherche);
				this.podo.ajoutRecherchePodologue();
				// this.podo.test = new JButton("tesssssssst");
				// this.podo.test.setBounds(33, 33, 133, 233);
				// this.podo.add(this.podo.test);
				this.podo.repaint();
				this.podo.setVisible(true);
				this.repaint();
				this.setVisible(true);
				/*
				 * while(!run) { System.out.println("Encore et toujours la!");
				 * try { Thread.sleep(300); } catch (InterruptedException e1) {
				 * // TODO Auto-generated catch block e1.printStackTrace(); } }
				 */
				System.out.println("cool!!!!!!!");

				/*
				 * for(JButton patient : podo.listeBoutonDernierPatient) {
				 * patient.setVisible(false); } this.podo.setVisible(false);
				 */
				// lancerIdentificationPodo();
			}
			// -------------------------------- cr�er un nouveau questionnaire
			// m�dicale -------------------------------------------//
			if (podo != null) {
				if (podo.nouveauQuestionnaireMedical != null) {
					for (JButtonNouveauQuestionnaireMedical q : podo.nouveauQuestionnaireMedical) {
						if (e.getSource() == q) {
							Date dateLastQuest = GestionDonnee
									.getDateDernierQuestionnaireVersionDonneeActuel(
											q.quest, q.pat.idPersonne);
							// System.err.println("date dernier questionnaire medical : "+dateLastQuestMedical.toString());
							if (dateLastQuest == null) {
								// System.err.println("azertyuiopqsdfghjklmxcvbn,;:");
								this.podo.listeJFrameQuestPodo
										.addLast(new JFrameQuestPodo());
								this.podo.listeJFrameQuestPodo.getLast().ajout(
										q.quest, q.pat);
							} else {
								if (dateLastQuest.compareTo(new Date()) != 0) {

									System.out
											.println("questionnaire "
													+ q.quest
													+ " deja present aveec une date differente!");

									this.podo.listeJFrameQuestPodo
											.addLast(new JFrameQuestPodo());
									this.podo.listeJFrameQuestPodo.getLast()
											.ajout(q.quest, q.pat);
								} else {
									System.out
											.println("questionnaire medicale deja present mais avec une date identique!");

									JOptionPane jop = new JOptionPane();
									jop.showMessageDialog(
											null,
											"<html><center>Un questionnaire "
													+ q.quest
													+ " a d�j� rempli pour ce patient aujourd'hui.<br/> Il n'est pas permis de passer le m�me questionnaire plusieur fois par jour <br/> Veuillez modifi� le questionnaire �xistant. </center></html>",
											"Erreur doublon questionnaire m�dicale",
											JOptionPane.WARNING_MESSAGE);
									this.podo.add(jop);// ncessaire???
									podo.afficherListeQuestionnairePatient(q.pat);
								}
							}
						}
					}
				}
			}

			// ------------------------------------- liste bonton dernier
			// patient --------------------------------------------//
			if (podo.retour != null) {
				if (e.getSource() == podo.retour) {
					this.podo.removeAll();
					podo.retour = null;
					podo.nouveauQuestionnaireMedical = null;
					if (podo.listeJButonInfoQuestExistant != null)
						podo.listeJButonInfoQuestExistant.clear();
					podo.actualiserInterfacePodologue();
				}
			}
			// -------------------------- interface de selection d'un
			// questionnaire ---------------------------------------//
			if (podo.listeJButonInfoQuestExistant != null) {

				for (JButtonInfoQuestExistant button : this.podo.listeJButonInfoQuestExistant) {
					if (e.getSource() == button) {
						this.podo.listeJFrameQuestPodo
								.addLast(new JFrameQuestPodo());
						this.podo.listeJFrameQuestPodo.getLast().ajout(
								button.info.questTriple, button.info.pat,
								button.info.datequest);
						this.podo.listeJFrameQuestPodo.getLast().quest.questGraphique.validation.questFrame = this.podo.listeJFrameQuestPodo
								.getLast();
						/*
						 * for(Component comp : podo.getComponents()) {
						 * this.podo.remove(comp); comp = null; } podo.retour =
						 * null; podo.nouveauQuestionnaireMedical = null;
						 * podo.listeJButonInfoQuestExistant.clear();
						 *//*
							 * for(JButtonInfoQuestExistant jb :
							 * podo.listeJButonInfoQuestExistant) { jb = null; }
							 */
						this.podo.listeJFrameQuestPodo.getLast().setVisible(
								true);
						// podo.actualiserInterfacePodologue();
						break;
					}
				}
			}
			// ------------------------------------- liste bonton rechercge et
			// cr�ation de personne
			// --------------------------------------------//
			if (podo.boutonValider != null) {
				if (e.getSource() == podo.boutonEffacer) {
					// apGraphique.podo = new InterfacePodologue(apGraphique);
					// apGraphique.run = true;
					// ApplicationGraphique.getInstance().podo = new
					// InterfacePodologue(ApplicationGraphique.getInstance());
					// ApplicationGraphique.getInstance().run = true;
					this.podo.removeAll();
					this.podo.actualiserInterfacePodologue();

				}
				if (e.getSource() == podo.boutonValider) {
					//
					// recherche de la personne
					String nom = podo.recupNom.getText().trim();
					String prenom = podo.recupPrenom.getText().trim();
					Integer jour = (Integer) podo.recupJour.getSelectedItem();
					Integer mois = (Integer) podo.recupMois.getSelectedItem();
					Integer annee = (Integer) podo.recupAnnee.getSelectedItem();
					Date dateNaissance = new Date(jour, mois, annee);
					System.out
							.println("vous rechercher la personne dont les info sont : "
									+ nom + " " + prenom + " " + dateNaissance);

					if (!nom.isEmpty() & !prenom.isEmpty()) {
						String idPersonne = GestionDonnee.getIdentifiant(nom,
								prenom, dateNaissance);
						if (idPersonne.compareTo("inconnu") != 0) {
							// Si personne personne trouv� alors initialisation
							// de l'interface de visualisation et cr�ation de
							// questionnaire pour un patient
							this.podo
									.afficherListeQuestionnairePatient(new Patient(
											idPersonne));
						} else {
							// si la personne n'est pas trouv�, alors message
							// comme quoi elle n'as pas �t� trouv�
							JOptionPane jop = new JOptionPane();
							jop.showMessageDialog(
									null,
									"<html><center>La personne que vous rechercher n'existe pas</html>",
									"personne inexistante",
									JOptionPane.WARNING_MESSAGE);
							this.add(jop);
						}
					}

					/*
					 * am�lioration possible si pas trouv� alors je cr� 2 listes
					 * Une pour les personne ayant la m�me date de naissance Une
					 * pour les personnes ayant le m�me nom et prenom Et on
					 * affiche ces personne dans la fentre si les 2 listes sont
					 * non vides
					 */
				}

				if (e.getSource() == podo.boutonCreer) {
					//
					// recherche de la personne
					String nom = podo.recupNom.getText().trim();
					String prenom = podo.recupPrenom.getText().trim();
					Integer jour = (Integer) podo.recupJour.getSelectedItem();
					Integer mois = (Integer) podo.recupMois.getSelectedItem();
					Integer annee = (Integer) podo.recupAnnee.getSelectedItem();
					Date dateNaissance = new Date(jour, mois, annee);
					System.out
							.println("vous rechercher la personne dont les info sont : "
									+ nom + " " + prenom + " " + dateNaissance);

					if (!nom.isEmpty() & !prenom.isEmpty()) {
						String idPersonne = GestionDonnee.getIdentifiant(nom,
								prenom, dateNaissance);
						LinkedList<String> listePersonne = new LinkedList<String>();
						GestionDonnee.getIdentifiants(nom, prenom,
								dateNaissance, listePersonne);
						if (idPersonne.compareTo("inconnu") == 0) {
							// Si personne personne trouv� alors initialisation
							// de l'interface de visualisation et cr�ation de
							// questionnaire pour un patient
							String pat = GestionDonnee.CreerPersonne(nom,
									prenom, dateNaissance);
							this.podo
									.afficherListeQuestionnairePatient(new Patient(
											pat));
						} else {
							JOptionPane jop = new JOptionPane();
							jop.showMessageDialog(
									null,
									"<html><center>La personne que vous chercher a cr�er existe d�j� dans la base de donn�e!</html>",
									"Personne existante",
									JOptionPane.WARNING_MESSAGE);
							this.add(jop);
						}
					} else {
						// si il manque le nom ou prenom de la personne, on
						// revient a la fenetre precedentes
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(
								null,
								"<html><center>Il faut donner un nom et un prenom � la personne que vous voulez cr�er!</html>",
								"personne inexistante",
								JOptionPane.WARNING_MESSAGE);
						this.add(jop);

					}

					/*
					 * am�lioration possible si pas trouv� alors je cr� 2 listes
					 * Une pour les personne ayant la m�me date de naissance Une
					 * pour les personnes ayant le m�me nom et prenom Et on
					 * affiche ces personne dans la fentre si les 2 listes sont
					 * non vides
					 */
				}
			}
		}
	}

    public static void main(String[] args) {
            ApplicationGraphique.getInstance().run();
    }

/**************************************************************************PARTIE AFFICHAGE DES FENETRES**************************************/    
    
    private void afficher(Component co) {
        co.setVisible(true);
        this.repaint();
        this.add(co);
        this.setVisible(true);
        
    }
    
    public void showErreur(String msgErreur) {
        if(erreur == null) {
            erreur = new Erreur(Configuration.getInstances().getLargeurApplication(),
                    Configuration.getInstances().getHauteurApplication());
        }
        erreur.etatInitial();
        erreur.setMessage(msgErreur);
        afficher(erreur);
    }

 /*************************************************************************PARTIE SUPPRESSION DES PANELS*************************************/
    public void unshowIdentification() {
        this.remove(nouvellePersonne);
    }


}
