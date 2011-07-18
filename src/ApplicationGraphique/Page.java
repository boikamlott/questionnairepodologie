package ApplicationGraphique;

import java.util.ArrayList;

public class Page {
	public int numeroDePage;
	// public int nombreDePage;
	public ArrayList<Integer> listeLigne;
	public int quantiteLigne;

	public Page(int numeroPage) {
		this.numeroDePage = numeroPage;
		listeLigne = new ArrayList<Integer>();
		quantiteLigne = 0;
	}
}
