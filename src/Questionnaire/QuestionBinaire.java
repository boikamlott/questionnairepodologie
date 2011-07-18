package Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QuestionBinaire extends Question {

	String nomDeDonnee;
	public List<DoublonValeurReponse> listeValeurReponse = new ArrayList<DoublonValeurReponse>();

	public QuestionBinaire() {
		super();
	}

	public void setValeurReponse(DoublonValeurReponse valeurReponse) {
		this.listeValeurReponse.add(new DoublonValeurReponse(valeurReponse));
	}

	public List<DoublonValeurReponse> getValeurReponse() {
		return this.listeValeurReponse;
	}

	@Override
	public void setNomDeDonnee(String nomDeDonnee) {
		// TODO Auto-generated method stub

		this.nomDeDonnee = nomDeDonnee;
	}

	@Override
	public String getNomDeDonnee() {
		// TODO Auto-generated method stub
		return nomDeDonnee;
	}

	@Override
	public boolean isDonnee() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setDonnee(String valeurDonnee) {
		this.quest.setReponse(false,
				new DoublonNomDonneeValeurDonnee(this.getNomDeDonnee(),
						valeurDonnee));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " Nom de la donn�e : " + nomDeDonnee
				+ " taile liste valeur reponses : " + listeValeurReponse.size();
	}

}
