package ApplicationGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Questionnaire.DoublonDateQuest;
import Questionnaire.GestionDonnee;
import Questionnaire.Ligne;
import Questionnaire.Patient;
import Questionnaire.Questionnaire;

public class LigneGraphiquePatient extends JButton// JPanel implements
													// ActionListener
{

	// public ArrayList<DoublonDateQuest> quest;
	// public ArrayList<JButton> boutonQuest;
	public Patient pat;
	int width;
	int height;
	JLabel phrase;

	public LigneGraphiquePatient(Patient pat)// ArrayList<DoublonDateQuest>
												// quest,Patient pat, int width,
												// int height)
	{
		// this.quest = quest;
		this.pat = pat;
		// this.width = width;
		// this.height = height;
		this.setText("<html><center>" + pat.nom + " " + pat.prenom
				+ "<html><center>");

	}

	/*
	 * 
	 * public LigneGraphiquePatient(Patient pat2, int width2, int height2) {
	 * this.pat =pat2; this.width =width2; this.height = height2; this.quest =
	 * new ArrayList<DoublonDateQuest>();
	 * GestionDonnee.getDernierAllQuestionnaireDerniereVersionsPatient
	 * (this.quest,pat);
	 * 
	 * this.phrase = new JLabel(pat.nom+" "+pat.prenom);
	 * this.phrase.setBounds(0, 0, height/10*3, width);
	 * 
	 * for(int i = 0;i<quest.size();i++) { boutonQuest.add(new
	 * JButton("<html>"+quest
	 * .get(i).nomQuestionnaire+"<br/>"+quest.get(i).dateQuest
	 * .toString()+"</html>")); boutonQuest.get(i).addActionListener(this);
	 * boutonQuest.get(i).setBounds(height/10*3 +i*height/10*3,0, height/10*3,
	 * width); this.add(boutonQuest.get(i)); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */
}
