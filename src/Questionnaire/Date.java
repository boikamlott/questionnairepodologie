package Questionnaire;

import java.lang.Math.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date // implements Comparable
{

	public int annee;
	public int mois;
	public int jour;
	public String affiche;

	public Date(int jour, int mois, int annee) {
		this.annee = annee;
		if (mois < 13 && mois > 0) {
			this.mois = mois;
		} else {
			System.err
					.println("Le mois doit etre un chiffre compris entre 1 et 12");
			this.mois = 0;
		}
		if (jour < 32 & jour > 0) {
			this.jour = jour;
		} else {
			System.err
					.println("Le mois doit etre un chiffre compris entre 1 et 12");
			this.jour = 0;
		}
		this.affiche = jour + "/" + mois + "/" + annee;
	}

	public Date(String affiche) {
		// System.out.println("date : "+affiche);
		this.affiche = affiche;
		String temp[] = affiche.split("/");
		this.annee = Integer.parseInt(temp[2]);
		this.mois = Integer.parseInt(temp[1]);
		this.jour = Integer.parseInt(temp[0]);

	}

	public Date() {
		// java.util.Date temp = new
		// java.util.Date(System.getProperties().currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		java.sql.Date temp = new java.sql.Date(System.currentTimeMillis());
		// System.out.println("annaaaaaaaaaaaaaa���������eeeeeeeee"+temp.toString());//cal.;
		String relol[] = temp.toString().split("-");
		this.annee = Integer.parseInt(relol[0]);// temp.getYear();System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+temp.getYear()+" relol : "+relol[0]);
		this.mois = Integer.parseInt(relol[1]);// temp.getMonth();System.out.println(temp.getMonth()+" relol : "+relol[1]);
		this.jour = Integer.parseInt(relol[2]);// temp.getDay();System.out.println(temp.getDay()+" relol : "+relol[2]);
		this.affiche = jour + "/" + mois + "/" + annee;
	}

	public Date(Date temp) {
		this.annee = temp.annee;
		this.mois = temp.mois;
		this.jour = temp.jour;
		this.affiche = temp.affiche;
	}

	public int compareTo(Date date) {
		if (date.annee < this.annee) {
			return -1;
		}
		if (date.annee > this.annee) {
			return 1;
		}
		// meme annee
		if (date.mois < this.mois) {
			return -1;
		}
		if (date.mois > this.mois) {
			return 1;
		}
		// meme annee et mois :
		if (date.jour < this.jour) {
			return -1;
		}
		if (date.jour > this.jour) {
			return 1;
		}
		// meme annee et mois et jour == meme date!!!
		return 0;
	}

	public String toString() {
		return affiche;
	}

	public boolean compare(Date datequest, int limiteEnMoisPrecompletion) {
		// renvoie true si la differences entre les deux dates est inferieur ou
		// egale a la limite, faux sinon
		if (java.lang.Math.abs((this.annee * 12 + this.mois)
				- (datequest.annee * 12 + datequest.mois)) <= limiteEnMoisPrecompletion) {
			return true;
		} else {
			return false;
		}
	}

	public String calculerAge(Date date) {
		// simpliste....
		return Integer.toString(Math.abs(date.annee - this.annee));

	}

}
