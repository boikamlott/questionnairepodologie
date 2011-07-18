package Questionnaire;

//public abstract class DonneeCachee implements ElementQuestionnaire,DonneeCalculee 
public abstract class DonneeCachee extends ElementQuestionnaire implements
		DonneeCalculee

{

	String nomDeDonnee;

	@Override
	public void setNumeroPage(int Numeropage) {
		// TODO Auto-generated method stub
		System.err
				.println("Le numero de page ne devrait pas �tre mis dans une donnee cach��!");
	}

	@Override
	public void setNumeroDeLigne(int NumeroDeLigne) {
		// TODO Auto-generated method stub
		System.err
				.println("Le numero de ligne ne devrait pas �tre mis dans une donnee cach��!");
	}

	@Override
	public void setTexteQuestion(String TexteQuestion) {
		// TODO Auto-generated method stub
		System.err
				.println("Le texte de la question ne devrait pas �tre mis dans une donnee cach��!");
	}

	@Override
	public void setNomListeReponse(String TexteQuestion) {
		// TODO Auto-generated method stub
		System.err
				.println("Le nomListeReponse ne devrait pas �tre mis dans une donnee cach��!");
	}

	@Override
	public void setValeurReponse(DoublonValeurReponse valeurReponse) {
		System.err
				.println("La valeurReponse est calcul� dans une donnee cach�e, elle ne peut donc pas etre fix� arbitrairement !");
	}

	@Override
	public boolean isDonnee() {
		return true;
	}

	@Override
	public void setNomDeDonnee(String nomDeDonnee) {
		this.nomDeDonnee = nomDeDonnee;
	}

	@Override
	public String getNomDeDonnee() {
		return this.nomDeDonnee;
	}

	@Override
	public String getReponse() {
		// TODO Auto-generated method stub
		return (String) this.calculerDonnee();
	}

	@Override
	public void setQuestionnaire(Questionnaire quest) {
		this.quest = quest;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + getNomDeDonnee() + " - " + getReponse()
				+ " - " + getClass() + super.toString();

	}

}
