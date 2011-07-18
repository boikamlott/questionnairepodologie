package ApplicationGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Application.Configuration;
import Questionnaire.*;

public class QuestionnaireGraphique extends JPanel // implements MouseListener {
{

	public JLabel presentation;
	public ArrayList<ArrayList<LigneGraphique>> listeDePageDeLigneGraphique;
	public int[] nombreDeLigneParPage;
	public JPanelFinQuestionnaire validation;
	// public ApplicationGraphique appliGraphique;
	// public int quantiteLigne;

	// parie page :
	public int pageCourante;

	// public ArrayList<Page> listePage;

	public QuestionnaireGraphique(Questionnaire quest)// ApplicationGraphique
														// appli)
	{
		// jframe
		this.setLayout(null);
		this.setBounds(new Rectangle(ApplicationGraphique.getInstance().taille
				.getLargeur_application(),
				ApplicationGraphique.getInstance().taille
						.getHauteur_application()));
		this.setBorder(BorderFactory.createTitledBorder(null, "Questionnaire "
				+ quest.getQuest().getQuestionnaire(), TitledBorder.CENTER,
				TitledBorder.CENTER));// ("Nom du questionnaire"));

		// this.setFont(new Font(null,Font.PLAIN,44));
		// UIManager.put("TextField.font", new Font(null,Font.PLAIN,44));

		// page
		this.pageCourante = 1;
		nombreDeLigneParPage = new int[13];
		listeDePageDeLigneGraphique = new ArrayList<ArrayList<LigneGraphique>>();

		for (int p = 0; p < 13; p++) {
			nombreDeLigneParPage[p] = 0;
			listeDePageDeLigneGraphique.add(new ArrayList<LigneGraphique>());
			listeDePageDeLigneGraphique.get(p).add(null);
		}

		/*
		 * listePage = new ArrayList<Page>(quest.nombrePage); for(int
		 * i=1;i<quest.nombrePage+2;i++) { listePage.add(new Page(i)); }
		 */

		// partie ligne
		creerListeLigneGraphique(quest);

		/*
		 * for(LigneGraphique ligne : listeLigneGraphique) { if(ligne != null) {
		 * System
		 * .out.println("height"+ligne.height+" width : "+ligne.width+" y : "
		 * +ligne.y); }else { System.err.println("ligne vide"); } }
		 */

		// for (LigneGraphique ligne : listeLigneGraphique) //marche plus a
		// cause de l'initialisation a null!!!!!!!!!!

		/*
		 * for(int ligne : listePage.get(pageCourante).listeLigne) {
		 * this.add(listeLigneGraphique.get(ligne));
		 * System.err.println(listeLigneGraphique.get(ligne).toString());
		 * //this.listeLigneGraphique.get(ligne).setVisible(true); }
		 */
		/*
		 * for(int i =0;i < listeLigneGraphique.size();i++) { if
		 * (listeLigneGraphique.get(i)!=null) {
		 * this.add(listeLigneGraphique.get(i)); }
		 * 
		 * }
		 */

		// partie validation / fin de questionnaire
		// if(Configuration.getInstances().getType_application().compareTo("podologue")==0)

		for (LigneGraphique ligne : listeDePageDeLigneGraphique
				.get(pageCourante)) {
			if (ligne != null) {
				this.add(ligne);
				// System.out.println("La ligne du tpe suivant a �t� ajout� : "+ligne.getClass());
			} else {
				// System.out.println("ligne graphique dans ligne byzarement ligne dans la page : "+pageCourante);
			}
		}

		if (pageCourante == quest.nombrePage) {
			validation = new JPanelFinQuestionnaire(40,
					listeDePageDeLigneGraphique.get(pageCourante).size(), quest);
		} else {
			validation = new JPanelFinQuestionnaire(10,
					listeDePageDeLigneGraphique.get(pageCourante).size(), quest);
		}
		this.add(validation);
		// System.out.println("validation : "+validation.getBounds().toString());

		// quest.

		// this.setPreSelection(quest);

		// this.repaint();
		// this.revalidate();
		this.setVisible(true);
	}

	public void verificationEllementActif() {
		for (LigneGraphique ligneG : this.listeDePageDeLigneGraphique
				.get(pageCourante))
		// for(int i = 0;i<this.listeLigneGraphique.size();i++)
		{
			if (ligneG != null) {
				// System.out.println(ligneG.getClass().getName());
				if (ligneG.ligne != null) {
					if (ligneG instanceof TotalGraphique
							| ligneG.ligne instanceof Total) {
						System.out.println(ligneG.getClass().getName() + ": "
								+ ligneG.ligne.getClass().getName());
						System.out.println(ligneG.ligne.getNomDeDonnee());
						((TotalGraphique) ligneG).refresh();
						ligneG.setVisible(true);
					} else {
						if (ligneG.ligne.elementActif()) {
							ligneG.setVisible(true);
						} else {
							ligneG.setVisible(false);
						}
					}
				} else {
					System.err
							.println("La ligne (donnee) ne devrait pas etre nulle dans ligne graphique!");
				}
			}
		}
	}

	private void creerListeLigneGraphique(Questionnaire quest) {
		// on compte le nombre de ligne, obligatoire pour cr�er chaque ligne
		// ainsi que la liste des ligne graphique
		for (ElementQuestionnaire element : quest.liste_elementQuestionnaire) {
			if (element instanceof Ligne) {
				listeDePageDeLigneGraphique.get(
						((Ligne) element).getNumeroPage()).add(null);
				nombreDeLigneParPage[((Ligne) element).getNumeroPage()]++;
			}
		}
		/*
		 * / //cr�ation de la liste de ligne graphique // if
		 * (listeLigneGraphique == null) // {
		 */
		/*
		 * listeLigneGraphique = new ArrayList<LigneGraphique>(100);//le 100
		 * c'est laid mais �fficace contre les bugs! for(int i =0;i<100;i++) {
		 * listeLigneGraphique.add(null); } //
		 * System.out.println("taille de la ligne graphique : "+
		 * listeLigneGraphique.size()); /* } else { System.err.println(
		 * "Pourquoi vouloir recr�er une chose qui existe deja? ListeLigneGraphique sera ecras�! Mi c pas normal!"
		 * ); listeLigneGraphique = new
		 * ArrayList<LigneGraphique>(quantiteLigne+2); } //
		 * System.out.println("quantit� ligne : "+quantiteLigne);
		 */
		// cr�ation de la page
		for (ElementQuestionnaire element : quest.liste_elementQuestionnaire) {

			if (element instanceof Ligne) {
				/*
				 * for (int j = 1;j<listePage.get(((Ligne)
				 * element).getNumeroPage()).quantiteLigne+1;j++) {
				 * 
				 * } int emplacementLigne = listePage.get(((Ligne)
				 * element).getNumeroPage()).quantiteLigne;
				 * 
				 * System.err.println("emplacementLigne : "+emplacementLigne);
				 */

				if (element instanceof QuestionBinaire) {
					// QuestionBinaireGraphique temp = new
					// QuestionBinaireGraphique(element,quest,quantiteLigne);
					// System.out.println("type element : "+
					// element.getClass().getName());
					// this.add(temp);
					// this.listeLigneGraphique.add(((Ligne)element).getNumeroDeLigne(),new
					// QuestionBinaireGraphique(element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					// this.listeLigneGraphique.add(emplacementLigne,new
					// QuestionBinaireGraphique(emplacementLigne,element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					this.listeDePageDeLigneGraphique.get(
							((Ligne) element).getNumeroPage()).set(
							((Ligne) element).getNumeroDeLigne(),
							new QuestionBinaireGraphique(((Ligne) element)
									.getNumeroDeLigne(), element, quest,
									nombreDeLigneParPage[((Ligne) element)
											.getNumeroPage()]));

				}

				if (element instanceof QuestionLikerte) {

					// this.listeLigneGraphique.add(((Ligne)element).getNumeroDeLigne(),new
					// QuestionLikerteGraphique(element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					// this.listeLigneGraphique.add(emplacementLigne,new
					// QuestionLikerteGraphique(emplacementLigne,element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					this.listeDePageDeLigneGraphique.get(
							((Ligne) element).getNumeroPage()).set(
							((Ligne) element).getNumeroDeLigne(),
							new QuestionLikerteGraphique(((Ligne) element)
									.getNumeroDeLigne(), element, quest,
									nombreDeLigneParPage[((Ligne) element)
											.getNumeroPage()]));
				}
				if (element instanceof QuestionDeroulante) {
					// this.listeLigneGraphique.add(emplacementLigne,new
					// QuestionDeroulanteGraphique(emplacementLigne,element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					this.listeDePageDeLigneGraphique.get(
							((Ligne) element).getNumeroPage()).set(
							((Ligne) element).getNumeroDeLigne(),
							new QuestionDeroulanteGraphique(((Ligne) element)
									.getNumeroDeLigne(), element, quest,
									nombreDeLigneParPage[((Ligne) element)
											.getNumeroPage()]));
				}
				if (element instanceof Total) {
					// this.listeLigneGraphique.add(emplacementLigne,new
					// TotalGraphique(emplacementLigne,element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					this.listeDePageDeLigneGraphique.get(
							((Ligne) element).getNumeroPage()).set(
							((Ligne) element).getNumeroDeLigne(),
							new TotalGraphique(((Ligne) element)
									.getNumeroDeLigne(), element, quest,
									nombreDeLigneParPage[((Ligne) element)
											.getNumeroPage()]));
				}
				if (element instanceof Phrase & !(element instanceof Total)) {
					// this.listeLigneGraphique.add(emplacementLigne,new
					// PhraseGraphique(emplacementLigne,element,quest,listePage.get(((Ligne)
					// element).getNumeroPage()).quantiteLigne));//quantiteLigne));
					this.listeDePageDeLigneGraphique.get(
							((Ligne) element).getNumeroPage()).set(
							((Ligne) element).getNumeroDeLigne(),
							new PhraseGraphique(((Ligne) element)
									.getNumeroDeLigne(), element, quest,
									nombreDeLigneParPage[((Ligne) element)
											.getNumeroPage()]));
				}
			}

			// }

		}
		// quantiteLigne=listeLigneGraphique.size();
		// listeLigneGraphique.sort();

		// /a faire : va chercher la liste des elementQuestionnaire dans
		// questionnaire et les transforme en ligneGraphique
		// pour l'instant, on va juste en avvoir 2 pour le test!
		// this.listeLigneGraphique.add(new questionBinaireGraphique(this,new
		// QuestionBinaire()));//,2));//Color.blue)); 1));//
		// this.listeLigneGraphique.add(new
		// LigneGraphique(this,2));//,2));//Color.black));

		// C'est ici qu'on detecte le nombre de ligne!!!!!!!!!!!

	}

	public void setPreSelection(Questionnaire quest)// , boolean forcage)
	{
		// TODO Auto-generated method stub
		// rempli les differente ligneGraphique avec les donn�es de liste
		// reponse temp
		System.out.println("debut de la preselection ");
		// quest.toStringListeReponse();

		for (LigneGraphique ligneG : listeDePageDeLigneGraphique
				.get(pageCourante)) {
			if (ligneG instanceof QuestionBinaireGraphique) {
				System.err.println(((QuestionBinaire) ligneG.ligne)
						.getValeurReponse().get(0).valeurDonneeSauvegardee);

				if (quest.getValeurTemp(ligneG.ligne.getNomDeDonnee()) != null) {
					if (((QuestionBinaire) ligneG.ligne).getValeurReponse()
							.get(0).valeurDonneeSauvegardee.compareTo(quest
							.getValeurTemp(ligneG.ligne.getNomDeDonnee())) == 0) {
						((QuestionBinaireGraphique) ligneG).oui.isSelected();
						((QuestionBinaireGraphique) ligneG).oui
								.setBorder(BorderFactory
										.createLineBorder(Color.blue));// setBackground(Color.blue);
					} else {
						((QuestionBinaireGraphique) ligneG).non.isSelected();
						((QuestionBinaireGraphique) ligneG).non
								.setBorder(BorderFactory
										.createLineBorder(Color.blue));// setBackground(Color.blue);
					}
				}
			}
			if (ligneG instanceof QuestionLikerteGraphique) {
				for (int i = 0; i < ((QuestionLikerte) ligneG.ligne).listeValeurReponse
						.size(); i++) {
					// System.err.println("ou pas question likerte! : "+
					// ligneG.ligne.getNomDeDonn�e());
					// System.err.println("ou pas question likerte! : "+
					// quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e()));
					// System.err.println("ou pas on compare ca : : "+
					// ((QuestionLikerte)
					// ligneG.ligne).getValeurReponse().get(i).valeurDonneeAffichee);
					// System.err.println("ou pas a ca : : "+
					// quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e()));
					if (quest.getValeurTemp(ligneG.ligne.getNomDeDonnee()) != null) {
						if (((QuestionLikerte) ligneG.ligne).getValeurReponse()
								.get(i).valeurDonneeSauvegardee.compareTo(quest
								.getValeurTemp(ligneG.ligne.getNomDeDonnee())) == 0) {
							((QuestionLikerteGraphique) ligneG).tabJButton[i]
									.isSelected();
							// /*/
							// System.err.println(ligneG.ligne.getNomDeDonn�e());
							// System.err.println(Integer.parseInt(quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e())));
							// ((QuestionLikerteGraphique)
							// ligneG).tabJButton[Integer.parseInt(quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e()))].isSelected();
							((QuestionLikerteGraphique) ligneG).tabJButton[i]
									.isSelected();
							((QuestionLikerteGraphique) ligneG).tabJButton[i]
									.setBackground(Color.blue);
							// System.err.println("On selectionne une questio likerte graphique!");
						}
					}
				}
				// */
			}
			if (ligneG instanceof QuestionDeroulanteGraphique) {
				// System.out.println(quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e()));
				((QuestionDeroulanteGraphique) ligneG).liste
						.setSelectedItem(quest.getValeurTemp(ligneG.ligne
								.getNomDeDonnee()));
			}
			if (ligneG instanceof TotalGraphique) {
				// System.out.println(quest.getValeurTemp(ligneG.ligne.getNomDeDonn�e()));
				// /quest.calculerDonneeToListeTemp();
				((TotalGraphique) ligneG).totalAffiche.setText(quest
						.getValeurTemp(ligneG.ligne.getNomDeDonnee()));
			}

		}
		System.out
				.println("Fin de la pr�selection des elements et d�but de l'afichage des �l�ment acif ! ");
		this.verificationEllementActif();

		this.setVisible(true);
		// this.validate();
	}

	public void changementPage(int changement, Object quest) {
		// this.setVisible(false);
		// JFrameQuestPodo temp = this.validation.questFrame;
		// this.removeAll();
		/*
		 * for(java.awt.Component comp : this.getComponents()) { /*
		 * System.out.println("classe : "+comp.getClass());
		 * System.out.println("bounds : "+comp.getBounds());
		 * System.out.println("nom question "+comp.getDropTarget());
		 * System.out.println("...");* this.remove(comp); comp=null;
		 */

		// }

		/*
		 * for(int ligne : listePage.get(pageCourante).listeLigne) {
		 * this.remove(listeLigneGraphique.get(ligne)); }
		 */
		this.removeAll();

		pageCourante += changement;
		for (LigneGraphique ligne : listeDePageDeLigneGraphique
				.get(pageCourante)) {
			if (ligne != null) {
				this.add(ligne);
				// System.out.println("La ligne du tpe suivant a �t� ajout� : "+ligne.getClass());
			} else {
				System.out
						.println("ligne graphique null dans liste de page de ligne graphique byzarement ligne dans la page : "
								+ pageCourante);
			}
		}
		// bouton final
		// this.remove(validation);

		this.validation = null;
		if (quest instanceof Questionnaire) {
			if (pageCourante == ((Questionnaire) quest).nombrePage) {
				if (pageCourante == 1) {
					validation = new JPanelFinQuestionnaire(40,
							nombreDeLigneParPage[pageCourante], quest);
				} else {
					validation = new JPanelFinQuestionnaire(30,
							nombreDeLigneParPage[pageCourante], quest);
				}
			} else {
				if (pageCourante == 1) {
					validation = new JPanelFinQuestionnaire(10,
							nombreDeLigneParPage[pageCourante], quest);
				} else {
					validation = new JPanelFinQuestionnaire(20,
							nombreDeLigneParPage[pageCourante], quest);
				}
			}
			this.setPreSelection((Questionnaire) quest);
		}
		if (quest instanceof JFrameQuestPodo) {
			if (pageCourante == ((JFrameQuestPodo) quest).quest.nombrePage) {
				if (pageCourante == 1) {
					validation = new JPanelFinQuestionnaire(40,
							nombreDeLigneParPage[pageCourante], quest);
				} else {
					validation = new JPanelFinQuestionnaire(30,
							nombreDeLigneParPage[pageCourante], quest);
				}
			} else {
				if (pageCourante == 1) {
					validation = new JPanelFinQuestionnaire(10,
							nombreDeLigneParPage[pageCourante], quest);
				} else {
					validation = new JPanelFinQuestionnaire(20,
							nombreDeLigneParPage[pageCourante], quest);
				}
			}
			this.setPreSelection(((JFrameQuestPodo) quest).quest);
		}
		this.add(validation);
		this.repaint();
		this.setVisible(true);

		// if(pageCourante==((Questionnaire) quest).nombrePage)
		/*
		 * if(pageCourante==((Questionnaire) quest).nombrePage) { validation =
		 * new
		 * JPanelFinQuestionnaire(30,nombreDeLigneParPage[pageCourante],quest);
		 * }else{ if(pageCourante==1) { validation = new
		 * JPanelFinQuestionnaire(10,nombreDeLigneParPage[pageCourante],quest);
		 * }else{ validation = new
		 * JPanelFinQuestionnaire(20,nombreDeLigneParPage[pageCourante],quest);
		 * } } /*
		 * System.err.println("component pr�sent apres changement de page : ");
		 * for(java.awt.Component comp : this.getComponents()) {
		 * System.out.println("classe : "+comp.getClass());
		 * System.out.println("bounds : "+comp.getBounds());
		 * System.out.println("..."); }*
		 * 
		 * this.add(validation);
		 */

		// this.validate();
		// this.revalidate();
		/*
		 * this.repaint(); if(quest instanceof Questionnaire) {
		 * this.setPreSelection((Questionnaire) quest); }else{
		 * this.setPreSelection(((JFrameQuestPodo) quest).quest); }
		 * 
		 * this.setVisible(true);
		 */
	}

	public void forcerPrecompletion(Questionnaire quest) {
		quest.miseAjour = true;

		quest.liste_reponse_temp.clear();
		// quest.liste_reponse_temp.addAll(GestionDonnee.getMotif(quest.getQuest()));
		// quest.liste_reponse_temp.get(0).valeurDonnee=quest.getIdPersonne();
		quest.liste_reponse_temp.addAll(GestionDonnee.getDonnees(quest
				.getQuest().getQuestionnaire(), quest.getIdPersonne(), quest
				.getDatequest()));

		quest.liste_reponse.clear();
		// quest.liste_reponse.addAll(GestionDonnee.getMotif(quest.getQuest()));
		// quest.liste_reponse.get(0).valeurDonnee=quest.getIdPersonne();
		quest.liste_reponse.addAll(GestionDonnee.getDonnees(quest.getQuest()
				.getQuestionnaire(), quest.getIdPersonne(), quest
				.getDatequest()));
		quest.toStringListeReponse();
		quest.setPrecompletion(true);
		this.pageCourante = 1;
		this.setPreSelection(quest);
		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}

}
