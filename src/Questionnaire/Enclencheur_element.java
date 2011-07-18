package Questionnaire;

import java.util.Arrays;

public class Enclencheur_element implements Enclencheur{

	private String nom_questionnaire;
	int id_element;
	
	
	
	
	public Enclencheur_element(String nom_questionnaire, int id_element) {
		super();
		this.nom_questionnaire = nom_questionnaire;
		this.id_element = id_element;
	}
	public Enclencheur_element(String nom_questionnaire, Declencheur dec) {
		super();
		this.nom_questionnaire = nom_questionnaire;
	//	this.id_element = dec.getIdElement(null);
	

	}

	@Override
	public String getQuestionnaire() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setQuestionnaire(String nom_questionnaire) {
		// TODO Auto-generated method stub
		
	}
}
