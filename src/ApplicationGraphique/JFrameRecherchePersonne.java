package ApplicationGraphique;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Application.Configuration;
import Application.DoublonTaille;
import Questionnaire.Date;
import Questionnaire.GestionDonnee;
import Questionnaire.Patient;

public class JFrameRecherchePersonne extends JFrame// implements ActionListener
{
	/*
	 * JPannelRecherchePersonne panel; //s ApplicationGraphique apGraphique;
	 * DoublonTaille taille; public JFrameRecherchePersonne(String
	 * title,ApplicationGraphique appli) { super(title);
	 * //this.setTitle("The podo's questionnairrie! "); taille = new
	 * DoublonTaille(Configuration.getInstances().getTailleApplication());
	 * this.setSize
	 * (this.taille.getLargeur_application(),this.taille.getHauteur_application
	 * ()); this.setLocation(15, 15); this.setResizable(true);
	 * this.setDefaultCloseOperation
	 * (JFrame.EXIT_ON_CLOSE);//DO_NOTHING_ON_CLOSE);
	 * 
	 * this.setUndecorated(false);//true);
	 * 
	 * this.setLayout(null);
	 * 
	 * panel = new JPannelRecherchePersonne(this); panel.setBounds(0, 0,
	 * this.taille
	 * .getLargeur_application(),this.taille.getHauteur_application());
	 * this.add(panel); panel.ajout();
	 * 
	 * this.setAlwaysOnTop(true); this.setVisible(true);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { if(e.getSource()
	 * == panel.boutonEffacer) { // apGraphique.podo = new
	 * InterfacePodologue(apGraphique); // apGraphique.run = true;
	 * ApplicationGraphique.getInstance().podo = new
	 * InterfacePodologue(ApplicationGraphique.getInstance());
	 * ApplicationGraphique.getInstance().run = true;
	 * 
	 * 
	 * 
	 * } if(e.getSource() == panel.boutonValider) { //recherche de la personne
	 * String nom = panel.recupNom.getText().trim(); String prenom =
	 * panel.recupPrenom.getText().trim(); Integer jour =
	 * (Integer)panel.recupJour.getSelectedItem(); Integer mois =
	 * (Integer)panel.recupMois.getSelectedItem(); Integer annee =
	 * (Integer)panel.recupAnnee.getSelectedItem(); Date dateNaissance = new
	 * Date(jour,mois,annee);
	 * System.out.println("vous rechercher la personne dont les info sont : "
	 * +nom+" "+prenom+" "+dateNaissance);
	 * 
	 * if (!nom.isEmpty() & !prenom.isEmpty()) { String idPersonne =
	 * GestionDonnee.getIdentifiant(nom,prenom,dateNaissance); if
	 * (idPersonne.compareTo("inconnu")!=0) { //Si personne personne trouvé
	 * alors initialisation de l'interface de visualisation et création de
	 * questionnaire pour un patient // apGraphique.podo = new
	 * InterfacePodologue(apGraphique); //
	 * apGraphique.podo.afficherListeQuestionnairePatient(new
	 * Patient(idPersonne)); // apGraphique.run = true;
	 * ApplicationGraphique.getInstance().podo = new
	 * InterfacePodologue(ApplicationGraphique.getInstance());
	 * ApplicationGraphique
	 * .getInstance().podo.afficherListeQuestionnairePatient(new
	 * Patient(idPersonne)); ApplicationGraphique.getInstance().run = true; }
	 * else { //si la personne n'est pas trouvé, alors message comme quoi elle
	 * n'as pas été trouvé JOptionPane jop = new JOptionPane();
	 * jop.showMessageDialog(null,
	 * "<html><center>La personne que vous rechercher n'existe pas</html>",
	 * "personne inexistante", JOptionPane.WARNING_MESSAGE); this.add(jop); } }
	 * 
	 * 
	 * /*amélioration possible si pas trouvé alors je cré 2 listes Une pour les
	 * personne ayant la même date de naissance Une pour les personnes ayant le
	 * même nom et prenom Et on affiche ces personne dans la fentre si les 2
	 * listes sont non vides
	 */
	/*
	 * }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) { // JFrameRecherchePersonne j =
	 * new JFrameRecherchePersonne("eeeeeeeeeee"); }
	 */

}
