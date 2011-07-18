package ApplicationGraphique;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Questionnaire.Patient;

public class DetailPatient extends JPanel implements ActionListener {

	Patient pat;

	public DetailPatient(Patient patient) {
		// TODO Auto-generated constructor stub
		System.err
				.println("Partie visualisation de tous les questionnaire d'une personne a faire : 5 questionnaire au max par session (date) => type question likerte avec nom, prenom, date plus 5 boutons vers les quesionnaire de la date en question : ");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
