package Application;

public class DoublonTaille {

	private int largeur_application;
	private int hauteur_application;

	public DoublonTaille(int largeur_application, int hauteur_application) {
		this.largeur_application = largeur_application;
		this.hauteur_application = hauteur_application;
	}

	public DoublonTaille(DoublonTaille tailleApplication) {
		this.largeur_application = tailleApplication.getLargeur_application();
		this.hauteur_application = tailleApplication.getHauteur_application();
	}

	public int getLargeur_application() {
		return largeur_application;
	}

	public void setLargeur_application(int largeur_application) {
		this.largeur_application = largeur_application;
	}

	public int getHauteur_application() {
		return hauteur_application;
	}

	public void setHauteur_application(int hauteur_application) {
		this.hauteur_application = hauteur_application;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "largeur application : " + largeur_application
				+ " hauteur application : " + hauteur_application;
	}

}
