package ApplicationGraphique;

import java.awt.Component;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Application.Configuration;
import Questionnaire.Date;
import Questionnaire.GestionDonnee;
import Questionnaire.Patient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class IdentificationGraphique extends AppSidasPanel implements ActionListener {
	// private DoublonTaille taille;

	private JLabel presentation;
	private JLabel qVisite;
	private JLabel texteNom;
	private TextField recupNom;
	private SelectedButton dejaVenu;
	private SelectedButton jamaisVenu;
	private JLabel textePrenom;
	private TextField recupPrenom;

	private JLabel texteAge;
	private JComboBox recupJour;
	private JComboBox recupMois;
	private JComboBox recupAnnee;

	private JButton boutonValider;// = new JButton("Valider");
	private JButton boutonEffacer;// = new JButton("Effacer");

	ApplicationGraphique apGraphique;


	public IdentificationGraphique(ApplicationGraphique appli) {
                super(Configuration.getInstances().getLargeurApplication(), Configuration.getInstances().getHauteurApplication() );
		if (Configuration.getInstances().getTypeApplication()
				.equals("podologue")) {
			boutonValider = new JButton("Rechercher");
			boutonEffacer = new JButton("Effacer");
		} else {
			boutonValider = new JButton("Valider");
			boutonEffacer = new JButton("Effacer");
		}
		apGraphique = appli;


		this.setLayout(null);
		// agrandisssement des scrolbar
		UIManager.put("ScrollBar.width", 40);

		// partie label de presentation de la page :
		// presentation = new
		// JLabel("<html><center><p1>Bienvenue dans l'interface d'identification. <br> Trouvez-vous byzarre de voir un dromaludaire � 2 bosses sur une fourmis rouge?     </p1></center></html>");
		presentation = new JLabel(
				"<html><center><p1>Bienvenue dans l'interface d'identification.<br/> Merci de n'utiliser que des characteres alphabetiques <br/>" +
				"et le charactere '-' pour vous identifier<br/>Cliquez sur les encadres pour repondre aux questions</p1></center></html>");
		// presentation.setBounds((int) Math.round(largeur*0.10), (int)
		// Math.round(hauteur*0.10),(int) Math.round(largeur*0.80),(int)
		// Math.round(hauteur*0.12));//setBackground(Color.orange);
		ajusterDansFenetre(presentation, 0.1, 0.05, 0.8, 0.12);
		presentation.setHorizontalAlignment(JLabel.CENTER);
		this.add(presentation);
		
		qVisite = new JLabel("Etes-vous deja venu dans ce centre ?");
		this.add(qVisite);
		ajusterDansFenetre(qVisite, 0.10, 0.30, 0.4, 0.06);
		dejaVenu = new SelectedButton("OUI");
		ajusterDansFenetre(dejaVenu, 0.5, 0.3, 0.15, 0.06);
                dejaVenu.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        fireChangeSelected(evt);
                    }
                });
		dejaVenu.setBackground(Color.WHITE);
		this.add(dejaVenu);
		
		jamaisVenu = new SelectedButton("NON");
		ajusterDansFenetre(jamaisVenu, 0.7, 0.3, 0.15, 0.06);
		jamaisVenu.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        fireChangeSelected(evt);
                    }
                });
                jamaisVenu.setSelectedSurChargee(true);
		this.add(jamaisVenu);

		// partie de pr�sentation de recuperation nom et prenom
		texteNom = new JLabel("Nom: ");
		ajusterDansFenetre(texteNom, 0.10, 0.40, 0.40, 0.06);
		this.add(texteNom);
		recupNom = new TextField();
		ajusterDansFenetre(recupNom, 0.50, 0.40, 0.40, 0.06);
		this.add(recupNom);

		textePrenom = new JLabel("Prenom: ");
		ajusterDansFenetre(textePrenom, 0.1, 0.5, 0.4, 0.06);
		this.add(textePrenom);
		recupPrenom = new TextField();
		ajusterDansFenetre(recupPrenom, 0.5, 0.5, 0.4, 0.06);
		this.add(recupPrenom);

		// partie recuperation de la date de naissance :
		texteAge = new JLabel("Date de Naissance: ");
		ajusterDansFenetre(texteAge, 0.10, 0.65, 0.4, 0.06);
		this.add(texteAge);

		// jour :
		List<Integer> jour = new ArrayList<Integer>();
		for (Integer i = 1; i < 32; i++) {
			jour.add(i);
		}
		JLabel texteJour = new JLabel("Jour");
		ajusterDansFenetre(texteJour, 0.45, 0.6, 0.1, 0.04);
		texteJour.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteJour);
		recupJour = new JComboBox(jour.toArray());
		ajusterDansFenetre(recupJour, 0.45, 0.65, 0.1, 0.04);
		recupJour.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupJour);

		// mois :
		List<Integer> mois = new ArrayList<Integer>();
		for (Integer i = 1; i < 13; i++) {
			mois.add(i);
		}
		JLabel texteMois = new JLabel("Mois");
		ajusterDansFenetre(texteMois, 0.6, 0.6, 0.1, 0.04);
		texteMois.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteMois);
		recupMois = new JComboBox(mois.toArray());
		ajusterDansFenetre(recupMois, 0.6, 0.65, 0.1, 0.04);
		recupMois.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupMois);

		// ann�e :
		List<Integer> annee = new ArrayList<Integer>();
		for (Integer i = 1910; i < 2030; i++) {
			annee.add(i);
		}
		JLabel texteAnnee = new JLabel("Annee");
		ajusterDansFenetre(texteAnnee, 0.75, 0.60, 0.13, 0.04);
		texteAnnee.setHorizontalAlignment(JLabel.CENTER);
		this.add(texteAnnee);
		recupAnnee = new JComboBox(annee.toArray());
		ajusterDansFenetre(recupAnnee, 0.75, 0.65, 0.13, 0.04);
		recupAnnee.setSelectedIndex(65);
		recupAnnee.setFont(new Font(null, Font.PLAIN, 20));
		this.add(recupAnnee);
		
		// partie boutons :
		boutonEffacer.addActionListener(this);
		ajusterDansFenetre(boutonEffacer, 0.1, 0.85, 0.3, 0.06);
		this.add(boutonEffacer);
		boutonValider.addActionListener(this);
		ajusterDansFenetre(boutonValider, 0.6, 0.85, 0.3, 0.06);
		this.add(boutonValider);

		boutonEffacer.repaint();
	}


    @Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(!Configuration.getInstances().getTypeApplication()
				.equals("podologue")) {
			if (source == boutonEffacer) {
                                //On "reset" la fenetre
                                recupNom.setText("");
                                recupPrenom.setText("");
                                recupMois.setSelectedIndex(0);
                                recupJour.setSelectedIndex(0);
                                recupAnnee.setSelectedIndex(65);
                                if(dejaVenu.getSelected()) {
                                    fireChangeSelected(e);
                                }
			} else if (source == boutonValider) {
                                // recuperation des donnees
                                String nom = recupNom.getText().trim().toUpperCase();
                                String prenom = recupPrenom.getText().trim().toUpperCase();
                                Date dateNaissance = new Date((Integer) recupJour.getSelectedItem(), 
                                        (Integer) recupMois.getSelectedItem(), (Integer) recupAnnee.getSelectedItem());
                                // Traitement des donnees
                                apGraphique.controller.lanceridentification(nom, prenom, dateNaissance, dejaVenu.getSelected());
			}
			
		} else {
			if (source == boutonEffacer) {
				apGraphique.run = true;
			} else {
				// recuperation des donn�es
				String nom = recupNom.getText().trim();
				String prenom = recupPrenom.getText().trim();
				Integer jour = (Integer) recupJour.getSelectedItem();
				Integer mois = (Integer) recupMois.getSelectedItem();
				Integer annee = (Integer) recupAnnee.getSelectedItem();
				Date dateNaissance = new Date(jour, mois, annee);
				System.out.println("vous avez voulut rechercher la personne dont le nom est : "
								+ nom + " " + prenom + " " + dateNaissance);

				if (!nom.isEmpty() & !prenom.isEmpty()
						& source == boutonValider) {
					String idPersonne = GestionDonnee.getIdentifiant(nom,
							prenom, dateNaissance);
					// System.out.println("identifiant trouv�!");
					if (idPersonne.compareTo("inconnu") == 0) {
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(
								null,
								"<html><center>La personne demand� n'existe pas! </center></html>",
								"Attention!", JOptionPane.WARNING_MESSAGE);
						this.add(jop);// ncessaire???
					} else {
						apGraphique.podo
								.afficherListeQuestionnairePatient(new Patient(
										idPersonne));// detailPatient(new
														// Patient(idPersonne));
					}
				}

			}
		}
	}
        
        private void fireChangeSelected(ActionEvent evt) {
                jamaisVenu.setSelectedSurChargee(!jamaisVenu.getSelected());
                dejaVenu.setSelectedSurChargee(!dejaVenu.getSelected());
                    
        }

    @Override
    public void etatInitial() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
