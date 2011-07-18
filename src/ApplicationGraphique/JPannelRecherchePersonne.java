package ApplicationGraphique;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Application.Configuration;

public class JPannelRecherchePersonne extends JPanel {
	/*
	 * // private DoublonTaille taille; public JButton test;
	 * 
	 * 
	 * private JLabel presentation;
	 * 
	 * private JLabel texteNom; public JTextField recupNom;
	 * 
	 * private JLabel textePrenom; public JTextField recupPrenom;
	 * 
	 * private JLabel texteAge; public JComboBox recupJour; public JComboBox
	 * recupMois; public JComboBox recupAnnee;
	 * 
	 * public JButton boutonValider;// = new JButton("Valider"); public JButton
	 * boutonEffacer;// = new JButton("Effacer");
	 * 
	 * // ApplicationGraphique apGraphique;
	 * 
	 * int largeur; int hauteur; // JFrameRecherchePersonne frame;
	 * 
	 * public JPannelRecherchePersonne() { super(); this.setLayout(null);
	 * largeur =
	 * Configuration.getInstances().getLargeur_application();//frame.apGraphique
	 * .taille.getLargeur_application(); hauteur =
	 * Configuration.getInstances().getHauteur_Application
	 * ();//apGraphique.taille.getHauteur_application(); }
	 * 
	 * 
	 * public JPannelRecherchePersonne(JFrameRecherchePersonne frame) { super();
	 * this.setLayout(null); largeur =
	 * frame.taille.getLargeur_application();//frame
	 * .apGraphique.taille.getLargeur_application(); hauteur =
	 * frame.taille.getHauteur_application
	 * ();//apGraphique.taille.getHauteur_application(); }
	 * 
	 * public void ajout(ApplicationGraphique apGraphique) {
	 * 
	 * //partie label de presentation de la page : presentation = new JLabel(
	 * "<html><center><p1>Bienvenue dans l'interface de recherche d'une personne. <br/>Veuillez remplir tous les champs suivants pour trouver les questionnaires d'une personne</p1></center></html>"
	 * ); // presentation.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.10),(int) Math.round(largeur*0.80),(int)
	 * Math.round(hauteur*0.12));//setBackground(Color.orange);
	 * presentation.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.10),(int) Math.round(largeur*0.80),(int)
	 * Math.round(hauteur*0.12));//setBackground(Color.orange);
	 * presentation.setHorizontalAlignment(JLabel.CENTER);
	 * this.add(presentation);
	 * 
	 * 
	 * //partie de présentation de recuperation nom et prenom texteNom = new
	 * JLabel("Quel est votre nom ? "); // texteNom.setBounds((int)
	 * Math.round(largeur*0.10), (int) Math.round(hauteur*0.30),(int)
	 * Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * texteNom.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.30),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * this.add(texteNom); recupNom = new JTextField(); //
	 * recupNom.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.30),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * recupNom.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.30),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * this.add(recupNom);
	 * 
	 * 
	 * textePrenom = new JLabel("Quel est votre prenom ? "); //
	 * textePrenom.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * textePrenom.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * this.add(textePrenom); recupPrenom = new JTextField(); //
	 * recupPrenom.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * recupPrenom.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.4),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * this.add(recupPrenom);
	 * 
	 * 
	 * //partie récuperation de la date de naissance : texteAge = new
	 * JLabel("Quel est votre date de naissance ? "); //
	 * texteAge.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.55),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * texteAge.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.55),(int) Math.round(largeur*0.40),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * this.add(texteAge);
	 * 
	 * 
	 * 
	 * //jour : Vector<Integer> jour = new Vector<Integer>(); for(Integer i =
	 * 1;i<32;i++) { jour.add(i); } JLabel texteJour = new JLabel("Jour"); //
	 * texteJour.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.50),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * texteJour.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.50),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * texteJour.setHorizontalAlignment(JLabel.CENTER); this.add(texteJour);
	 * recupJour = new JComboBox(jour); // recupJour.setBounds((int)
	 * Math.round(largeur*0.45), (int) Math.round(hauteur*0.55),(int)
	 * Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * recupJour.setBounds((int) Math.round(largeur*0.45), (int)
	 * Math.round(hauteur*0.55),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * recupJour.setFont(new Font(null, Font.PLAIN, 20)); this.add(recupJour);
	 * 
	 * 
	 * //mois : Vector<Integer> mois = new Vector<Integer>(); for(Integer i =
	 * 1;i<13;i++) { mois.add(i); } JLabel texteMois = new JLabel("Mois"); //
	 * texteMois.setBounds((int) Math.round(largeur*0.60), (int)
	 * Math.round(hauteur*0.50),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * texteMois.setBounds((int) Math.round(largeur*0.60), (int)
	 * Math.round(hauteur*0.50),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * texteMois.setHorizontalAlignment(JLabel.CENTER); this.add(texteMois);
	 * recupMois = new JComboBox(mois); // recupMois.setBounds((int)
	 * Math.round(largeur*0.60), (int) Math.round(hauteur*0.55),(int)
	 * Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * recupMois.setBounds((int) Math.round(largeur*0.60), (int)
	 * Math.round(hauteur*0.55),(int) Math.round(largeur*0.10),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * recupMois.setFont(new Font(null, Font.PLAIN, 20)); this.add(recupMois);
	 * 
	 * 
	 * //année : Vector<Integer> annee = new Vector<Integer>(); for(Integer i =
	 * 1910 ;i<2015;i++) { annee.add(i); } JLabel texteAnnee = new
	 * JLabel("Annee"); texteAnnee.setBounds((int) Math.round(largeur*0.75),
	 * (int) Math.round(hauteur*0.50),(int) Math.round(largeur*0.13),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * texteAnnee.setHorizontalAlignment(JLabel.CENTER); this.add(texteAnnee);
	 * recupAnnee = new JComboBox(annee); recupAnnee.setBounds((int)
	 * Math.round(largeur*0.75), (int) Math.round(hauteur*0.55),(int)
	 * Math.round(largeur*0.13),(int)
	 * Math.round(hauteur*0.04));//setBackground(Color.orange);
	 * recupAnnee.setSelectedIndex(65); recupAnnee.setFont(new Font(null,
	 * Font.PLAIN, 20)); this.add(recupAnnee);
	 * 
	 * 
	 * 
	 * //partie boutons : boutonEffacer = new JButton("Annuler");
	 * boutonEffacer.setBounds((int) Math.round(largeur*0.10), (int)
	 * Math.round(hauteur*0.80),(int) Math.round(largeur*0.30),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * boutonEffacer.addActionListener(apGraphique); this.add(boutonEffacer);
	 * 
	 * boutonValider = new JButton("Rechercher"); boutonValider.setBounds((int)
	 * Math.round(largeur*0.60), (int) Math.round(hauteur*0.80),(int)
	 * Math.round(largeur*0.30),(int)
	 * Math.round(hauteur*0.06));//setBackground(Color.orange);
	 * boutonValider.addActionListener(apGraphique); this.add(boutonValider);
	 * 
	 * }
	 */
}
