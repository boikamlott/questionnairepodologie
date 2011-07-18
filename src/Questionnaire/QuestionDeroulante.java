package Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QuestionDeroulante extends Question {

	String nomDeDonnee;
	public List<String> listeValeurReponse = new ArrayList<String>();

	@Override
	public void setValeurReponse(DoublonValeurReponse valeurReponse) {
		this.listeValeurReponse.add(valeurReponse.valeurDonneeSauvegardee);
	}

	public List<String> getValeurReponse() {
		return this.listeValeurReponse;
	}

	@Override
	public boolean isDonnee() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setNomDeDonnee(String nomDeDonnee) {
		this.nomDeDonnee = nomDeDonnee;

	}

	@Override
	public String getNomDeDonnee() {
		// TODO Auto-generated method stub
		return nomDeDonnee;
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
