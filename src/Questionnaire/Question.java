package Questionnaire;

public abstract class Question extends Ligne// implements ligne
{
	public String nomListeReponse;

	@Override
	public void setNomListeReponse(String texteListeReponse) {
		this.nomListeReponse = texteListeReponse;

	}

	public String getNomListeReponse() {
		return this.nomListeReponse;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNomDeDonnee() + " - " + super.toString() + " - "
				+ getNomListeReponse();
	}

}
