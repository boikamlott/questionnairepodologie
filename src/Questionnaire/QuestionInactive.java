package Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QuestionInactive extends Question {

	public List<DoublonValeurReponse> listeValeurReponse = new ArrayList<DoublonValeurReponse>();
	
	
	
	
	public void setValeurReponse(DoublonValeurReponse valeurReponse) 
	{
		this.listeValeurReponse.add(new DoublonValeurReponse(valeurReponse));
	}
	public List<DoublonValeurReponse> getValeurReponse()
	{
		return this.listeValeurReponse;
	}
	@Override
	public boolean isDonnee() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNomDeDonnee(String nomDeDonnee) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNomDeDonnee() {
		// TODO Auto-generated method stub
		return null;
	}

}
