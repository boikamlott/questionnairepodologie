package ApplicationGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;

import Application.Configuration;
import Questionnaire.Date;
import Questionnaire.DoublonNomDonneeValeurDonnee;
import Questionnaire.GestionDonnee;
import Questionnaire.QuestionBinaire;
import Questionnaire.QuestionDeroulante;
import Questionnaire.QuestionLikerte;
import Questionnaire.Questionnaire;

public class JPanelFinQuestionnaire extends JPanel implements ActionListener {

	private JButton boutonEnregistrer;// valider le questionnaire et
										// l'enregistrer
	private JButton boutonQuitter;// quitter sans enregistrer
	private JButton boutonPrecedent;
	private JButton boutonSuivant;
	private Questionnaire quest;
	public JFrameQuestPodo questFrame;
	int hauteurBouton;
	int largeurBouton;

	int y;
	int weight;
	int height;

	private Questionnaire getQuest() {
		if (questFrame == null) {
			System.out.println("Quest frame null");
			return quest;
		} else {
			return questFrame.quest;
		}
	}

	public JPanelFinQuestionnaire(int cas, int quantiteLigne, Object q) {
		/*
		 * @param cas podologue_debut et quetionnaire_debut : 10
		 * podologue_suivant et questionnaire_suivant : 20 podologue_fin et
		 * questionnaire_fin : 30
		 */
		if (q instanceof Questionnaire) {
			this.quest = (Questionnaire) q;// ApplicationGraphique.getInstance().questGeneralGraphique;
		} else {
			if (q instanceof JFrameQuestPodo) {
				this.questFrame = (JFrameQuestPodo) q;
			}
		}
		this.setLayout(null);
		y = ((quantiteLigne + 1)
				* Configuration.getInstances().getHauteurApplication() / (quantiteLigne + 2)) - 30;
		weight = Configuration.getInstances().getLargeurApplication() - 10;
		// height =
		// ApplicationGraphique.getInstance().taille.getHauteur_application()/4;//-5);
		height = Configuration.getInstances().getHauteurApplication()
				/ (quantiteLigne + 2) - 10;// questGraphique.quantiteLigne+2);
		this.setBounds(0, y, weight, height);
		int nombreBouton;
		hauteurBouton = 50;
		largeurBouton = 180;

		switch (cas) {
		case (10):
			boutonQuitter(2, "Annuler", 0);
			boutonSuivant(2, "Suite", 1);
			break;
		case (20):
			boutonPrecedent(3, "Precedent", 0);
			boutonQuitter(3, "Annuler", 1);
			boutonSuivant(3, "Suite", 2);
			break;
		case (30):
			boutonPrecedent(3, "Precedent", 0);
			boutonQuitter(3, "Annuler", 1);
			boutonEnregistrer(3, "Fin", 2);
			break;
		case (40):
			boutonQuitter(2, "Annuler", 0);
			boutonEnregistrer(2, "Fin", 1);
			break;
		}
	}

	/*
	 * 
	 * 
	 * if (cas.compareTo("podologue_suivant")==0) {
	 * //this.setBorder(BorderFactory.createTitledBorder(
	 * "Fin du questionnaire. 'Quitter' pour ne pas sauvegarder les modif, 'valider' sinon!"
	 * ));
	 * 
	 * } else { // this.setBorder(BorderFactory.createEtchedBorder(Color.red,
	 * Color.blue));
	 * 
	 * nombreBouton = 2 ;
	 * 
	 * //ajout du bouton quitter!!!!!!!
	 * 
	 * 
	 * 
	 * //ajout du bouton valider/ enregister boutonEnregistrer = new
	 * JButton("Valider"); if (weight/nombreBouton>130) { largeur = 130;
	 * positionX = (((weight/nombreBouton)-130)/2)+weight/(nombreBouton); }else
	 * { largeur = weight/nombreBouton; positionX = weight/(nombreBouton); } if
	 * (height>33) { hauteur = 33; positionY = (height-33)/2; }else { hauteur =
	 * height; positionY = 0; } boutonEnregistrer.setBounds(positionX,
	 * positionY, largeur, hauteur); boutonEnregistrer.addActionListener(this);
	 * this.add(boutonEnregistrer); /* //
	 * System.out.println("quitter : "+boutonQuitter.getBounds().toString());
	 * 
	 * boutonEnregistrer = new JButton("Valider"); //
	 * this.boutonEnregistrer.setPreferredSize(new Dimension(130,33)); //
	 * this.boutonEnregistrer.setMaximumSize(new Dimension(130,33)); //
	 * this.boutonEnregistrer.setMinimumSize(new Dimension(130,33)); positionX =
	 * (
	 * (weight/nombreBouton)/2);//4)*3;//Math.round(((this.getWidth()/nombreBoouton
	 * )-130)/nombreBoouton)+this.getWidth()/nombreBoouton; positionY =
	 * (y);///4;//Math.round((this.getHeight()-33)/2); //
	 * if(position<0)position=0; boutonEnregistrer.setBounds(positionX,
	 * positionY,weight, 33); boutonEnregistrer.addActionListener(this);
	 * System.out
	 * .println("ttttttttttttttttttttttt"+positionX+" : "+positionY+" : "
	 * +weight+" : "+33);
	 * 
	 * this.add(boutonEnregistrer);
	 * 
	 * 
	 * System.out.println(boutonEnregistrer.getBounds().toString());
	 * System.out.println("this : "+this.getBounds().toString());
	 * System.out.println(this.getHeight());
	 * 
	 * //this.getX()
	 * 
	 * System.out.println("position x puis y "+positionX+" : "+positionY);
	 * 
	 * this.revalidate();
	 * 
	 * //this.setVisible(true);
	 * 
	 * } }
	 */

	private void boutonPrecedent(int nombreBouton, String string, int position) {
		boutonPrecedent = new JButton(string);
		int positionX;// = 0;//(weight/nombreBouton)/4;///=
						// Math.round(((this.getWidth()/nombreBoouton)-130)/nombreBoouton);
		int positionY;// =
						// 5;//(height-y);///4;//Math.round((this.getHeight()-33)/2);
		int largeur;
		int hauteur;
		if (weight / nombreBouton > largeurBouton) {
			largeur = largeurBouton;
			positionX = ((weight / nombreBouton) - largeurBouton) / 2
					+ position * (weight / nombreBouton);
		} else {
			largeur = weight / nombreBouton;
			positionX = position * (weight / nombreBouton);
		}
		if (height > hauteurBouton) {
			hauteur = hauteurBouton;
			positionY = (height - hauteurBouton) / 2;
		} else {
			hauteur = height;
			positionY = 0;
		}
		boutonPrecedent.setBounds(positionX, positionY, largeur, hauteur);
		boutonPrecedent.addActionListener(this);
		this.add(boutonPrecedent);
	}

	private void boutonEnregistrer(int nombreBouton, String string, int position) {
		boutonEnregistrer = new JButton(string);
		int positionX;// = 0;//(weight/nombreBouton)/4;///=
						// Math.round(((this.getWidth()/nombreBoouton)-130)/nombreBoouton);
		int positionY;// =
						// 5;//(height-y);///4;//Math.round((this.getHeight()-33)/2);
		int largeur;
		int hauteur;
		if (weight / nombreBouton > largeurBouton) {
			largeur = largeurBouton;
			positionX = ((weight / nombreBouton) - largeurBouton) / 2
					+ position * (weight / nombreBouton);
		} else {
			largeur = weight / nombreBouton;
			positionX = position * (weight / nombreBouton);
		}
		if (height > hauteurBouton) {
			hauteur = hauteurBouton;
			positionY = (height - hauteurBouton) / 2;
		} else {
			hauteur = height;
			positionY = 0;
		}
		boutonEnregistrer.setBounds(positionX, positionY, largeur, hauteur);
		boutonEnregistrer.addActionListener(this);
		this.add(boutonEnregistrer);
	}

	private void boutonQuitter(int nombreBouton, String string, int position) {
		boutonQuitter = new JButton(string);
		int positionX;// = 0;//(weight/nombreBouton)/4;///=
						// Math.round(((this.getWidth()/nombreBoouton)-130)/nombreBoouton);
		int positionY;// =
						// 5;//(height-y);///4;//Math.round((this.getHeight()-33)/2);
		int largeur;
		int hauteur;
		if (weight / nombreBouton > largeurBouton) {
			largeur = largeurBouton;
			positionX = (((weight / nombreBouton) - largeurBouton) / 2)
					+ position * (weight / nombreBouton);
		} else {
			largeur = weight / nombreBouton;
			positionX = position * (weight / nombreBouton);
		}
		if (height > hauteurBouton) {
			hauteur = hauteurBouton;
			positionY = (height - hauteurBouton) / 2;
		} else {
			hauteur = height;
			positionY = 0;
		}
		boutonQuitter.setBounds(positionX, positionY, largeur, hauteur);
		boutonQuitter.addActionListener(this);
		this.add(boutonQuitter);
	}

	private void boutonSuivant(int nombreBouton, String string, int position) {
		boutonSuivant = new JButton(string);
		int positionX;// = 0;//(weight/nombreBouton)/4;///=
						// Math.round(((this.getWidth()/nombreBoouton)-130)/nombreBoouton);
		int positionY;// =
						// 5;//(height-y);///4;//Math.round((this.getHeight()-33)/2);
		int largeur;
		int hauteur;
		if (weight / nombreBouton > largeurBouton) {
			largeur = largeurBouton;
			positionX = (((weight / nombreBouton) - largeurBouton) / 2)
					+ position * (weight / nombreBouton);
		} else {
			largeur = weight / nombreBouton;
			positionX = position * (weight / nombreBouton);
		}
		if (height > hauteurBouton) {
			hauteur = hauteurBouton;
			positionY = (height - hauteurBouton) / 2;
		} else {
			hauteur = height;
			positionY = 0;
		}
		boutonSuivant.setBounds(positionX, positionY, largeur, hauteur);
		boutonSuivant.addActionListener(this);
		this.add(boutonSuivant);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boutonEnregistrer) {
			for (LigneGraphique ligne : this.getQuest().questGraphique.listeDePageDeLigneGraphique
					.get(this.getQuest().questGraphique.pageCourante)) {
				if (ligne instanceof QuestionDeroulanteGraphique) {
					try {
						// System.out.println(((QuestionDeroulanteGraphique)
						// ligne).liste.getSelectedItem().toString());
						((QuestionDeroulante) ((QuestionDeroulanteGraphique) ligne).ligne)
								.setDonnee(((QuestionDeroulanteGraphique) ligne).liste
										.getSelectedItem().toString());
					} catch (java.lang.NullPointerException ExeceptionNull) {
						((QuestionDeroulante) ((QuestionDeroulanteGraphique) ligne).ligne)
								.setDonnee("");
					}
				}
			}
			this.getQuest().calculerDonneeToListeTemp();
			// on cherche a voir si il y a des donn�es non selectionn�
			String hh = this.getQuest().listeReponseTempIncomplete();
			if (hh != null) {
				// on cr�er un pannel de messages pour dire qu'il faut
				// selectionner une occurence pour chaque question
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(
						null,
						"<html><center>Il manque des reponses!<br/> Il faut que pour chaque question <br/> une reponses soit selectionn�!<br/>Il manque : "
								+ hh + " </center></html>", "Attention!",
						JOptionPane.WARNING_MESSAGE);
				this.getQuest().questGraphique.add(jop);// ncessaire???
			} else {
				// System.err.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+this.quest.listeReponseTempIncomplete());
				if (this.getQuest().listeReponseEgale()) {
					if (Configuration.getInstances().getTypeApplication()
							.compareTo("podologue") == 0) {
						System.out
								.println("Fin de la jframe sans modification de donn�es!");
						questFrame.dispose();
					} else {
						System.out
								.println("pas de modification dans les listes!");
						this.getQuest().questGraphique.removeAll();
						ApplicationGraphique.getInstance().run = true;
					}
				} else {
					// si questionnaire
					// alors gestion de la sauvegarde du questionnaire et
					// passage au questionnaire suivant
					if (Configuration.getInstances().getTypeApplication()
							.compareTo("podologue") != 0) {
						if (this.getQuest().miseAjour) {
							System.out.println("mise a jour des donn�es : ");
							GestionDonnee.update(this.getQuest().getQuest(),
									this.getQuest().getIdPersonne(), this
											.getQuest().getDatequest(), this
											.getQuest().liste_reponse_temp);
						} else {
							System.out
									.println("cr�ation d'une nouvelle ligne de donn�es : ");
							GestionDonnee.insertInto(this.getQuest(),
									"liste_reponse_temp");
						}
						this.getQuest().questGraphique.removeAll();
						ApplicationGraphique.getInstance().run = true;
					} else
					// si cot� podologue
					// alors gestion de la questFrame
					{
						if (this.getQuest().miseAjour) {
							System.out.println("mise a jour des donn�es : ");
							GestionDonnee.update(this.getQuest().getQuest(),
									this.getQuest().getIdPersonne(), this
											.getQuest().getDatequest(), this
											.getQuest().liste_reponse_temp);
						} else {
							System.out
									.println("cr�ation d'une nouvelle ligne de donn�es : ");
							GestionDonnee.insertInto(this.getQuest(),
									"liste_reponse_temp");
						}
						questFrame.dispose();

					}
				}
			}
		}

		/*
		 * if (this.getQuest().controle) {
		 * System.out.println("mise a jour des don�es : ");
		 * GestionDonnee.update(this.getQuest().getQuest(),
		 * this.getQuest().getIdPersonne(),
		 * this.getQuest().getDatequest(),this.getQuest().liste_reponse_temp);
		 * }else{
		 * System.out.println("cr�ation d'une nouvelle ligne de donn�es : ");
		 * GestionDonnee.insertInto(this.getQuest(),"liste_reponse_temp"); }
		 * 
		 * } if
		 * (Configuration.getInstances().getType_application().compareTo("podologue"
		 * )==0) { if (this.getQuest().controle) {
		 * System.out.println("mise a jour des don�es : ");
		 * GestionDonnee.update(this.getQuest().getQuest(),
		 * this.getQuest().getIdPersonne(),
		 * this.getQuest().getDatequest(),this.getQuest().liste_reponse_temp);
		 * }else{
		 * System.out.println("cr�ation d'une nouvelle ligne de donn�es : ");
		 * GestionDonnee.insertInto(this.getQuest(),"liste_reponse_temp"); } //
		 * for(JFrameQuestPodo frame : ;
		 * ApplicationGraphique.getInstance().podo.
		 * listeJFrameQuestPodo.remove(questFrame); questFrame.dispose();
		 * 
		 * // questFrame.setVisible(false);// = null; } else {
		 * ApplicationGraphique.getInstance().run = true; }
		 */

		// this.quest.toStringListeReponse();

		if (e.getSource() == boutonQuitter) {

			if (Configuration.getInstances().getTypeApplication()
					.compareTo("podologue") == 0) {
				for (int jiji = 0; jiji < ApplicationGraphique.getInstance().podo.listeJFrameQuestPodo
						.size(); jiji++) {
					if (questFrame == ApplicationGraphique.getInstance().podo.listeJFrameQuestPodo
							.get(jiji)) {

						System.out
								.println("On vient de perdre un questionnaire! Vous inqui�tez pas rien n'est d�finitife en informatique! (ou presque!)");
						ApplicationGraphique.getInstance().podo.listeJFrameQuestPodo
								.remove(jiji);
						// questFrame.removeAll();
						questFrame.dispose();
					}
				}
			} else {

				// ApplicationGraphique.getInstance().
				// this.quest.questGraphique.setVisible(false);
				System.out
						.println("passage au questionnaire suivant ou plutot fin des questionnaire pour une personne non?");
				ApplicationGraphique.getInstance().appli.idPersonne = null;
				ApplicationGraphique.getInstance().appli.QG.setIdPersonne(null);
				ApplicationGraphique.getInstance().run = true;
			}
		}
		if (e.getSource() == boutonSuivant) {
			for (LigneGraphique ligne : this.getQuest().questGraphique.listeDePageDeLigneGraphique
					.get(this.getQuest().questGraphique.pageCourante)) {
				if (ligne instanceof QuestionDeroulanteGraphique) {
					// System.out.println(((QuestionDeroulanteGraphique)
					// ligne).liste.getSelectedItem().toString());
					((QuestionDeroulante) ((QuestionDeroulanteGraphique) ligne).ligne)
							.setDonnee(((QuestionDeroulanteGraphique) ligne).liste
									.getSelectedItem().toString());
				}
			}
			if (Configuration.getInstances().getTypeApplication()
					.compareTo("podologue") == 0) {
				this.getQuest().questGraphique.changementPage(1,
						this.questFrame);
			} else {
				this.getQuest().questGraphique.changementPage(1,
						this.getQuest());
			}

			/*
			 * JFrameQuestPodo temp = this.questFrame; // questFrame.dispose();
			 * System
			 * .out.println("questFrame : "+questFrame.getClass().hashCode());
			 * System
			 * .out.println("questFrame temporaire : "+temp.getClass().hashCode
			 * ());
			 * 
			 * this.getQuest().questGraphique.changementPage(1,this.getQuest());/
			 * * System.err.println(
			 * "vjfeeeeee ca devrait pas arriv� ca! vhjfv,hjnfv,hjbcvghc,hc,hfchxcfh"
			 * );
			 * System.out.println("questFrame apres changement pages : "+questFrame
			 * .getClass().hashCode());
			 * System.out.println("questFrame temporaire : "
			 * +temp.getClass().hashCode()); // this.questFrame = temp;
			 * System.out
			 * .println("questFrame finallement: "+questFrame.getClass(
			 * ).hashCode());
			 */
		}
		if (e.getSource() == boutonPrecedent) {
			for (LigneGraphique ligne : this.getQuest().questGraphique.listeDePageDeLigneGraphique
					.get(this.getQuest().questGraphique.pageCourante)) {
				if (ligne instanceof QuestionDeroulanteGraphique) {
					try {
						((QuestionDeroulante) ((QuestionDeroulanteGraphique) ligne).ligne)
								.setDonnee(((QuestionDeroulanteGraphique) ligne).liste
										.getSelectedItem().toString());
					} catch (java.lang.NullPointerException ExeceptionNull) {
						((QuestionDeroulante) ((QuestionDeroulanteGraphique) ligne).ligne)
								.setDonnee("");
					}
				}
			}
			if (Configuration.getInstances().getTypeApplication()
					.compareTo("podologue") == 0) {
				this.getQuest().questGraphique.changementPage(-1,
						this.questFrame);
			} else {
				this.getQuest().questGraphique.changementPage(-1,
						this.getQuest());
			}
		}
		/*
		 * p a s s a g e
		 * 
		 * a u
		 * 
		 * q u e s t i o n n a i r e
		 * 
		 * s u i v a n t !
		 */
	}

	/*
	 * if(source == boutonEnregistrer) {
	 * 
	 * //for(QuestionLikerte (QuestionLikerteGraphique) ligne :
	 * quest.questGraphique.listeLigneGraphique) for (int j
	 * =0;j<quest.questGraphique.listeLigneGraphique.size();j++) {
	 * QuestionLikerteGraphique ligne =
	 * (QuestionLikerteGraphique)quest.questGraphique
	 * .listeLigneGraphique.get(j); //testLikerte bouton = (testLikerte)
	 * ligne.groupeDeBouton; //System.out.println(bouton.donneeRecup); for(int i
	 * =0;i<ligne.listeBouton.size();i++)//(testLikerte test : (testLikerte)
	 * ligne.listeBouton) {
	 * 
	 * testLikerte bouton = (testLikerte) ligne.listeBouton.get(i);
	 * System.out.println(bouton.isSelected()); }
	 * 
	 * //System.out.println("pppppppppppppppppppppppppppppp : "+
	 * ligne.listeBouton.size());
	 * //ligne.listeBouton.size());//get(0).getClass()
	 * .getName());//.donneeRecup);
	 * 
	 * //ligne.groupeDeBoutons.getSelection().getClass().getName());//.getMnemonic
	 * ()); } }
	 */
}

/*
 * recupPrenom.setText(""); recupNom.setText("");
 * System.out.println("Vous avez vouler annuler et effacer les resultats!."); }
 * else if(source == boutonValider){ String nom = recupNom.getText(); String
 * prenom = recupPrenom.getText(); Integer jour =
 * (Integer)recupJour.getSelectedItem(); Integer mois =
 * (Integer)recupMois.getSelectedItem(); Integer annee =
 * (Integer)recupAnnee.getSelectedItem(); Date dateNaissance = new
 * Date(jour,mois,annee); System.out.println(
 * "vous avez voulut enregistrer les infos de la personne dont le nom est : "
 * +nom+" "+prenom+" "+dateNaissance); }
 */

/*
 * public void paintComponent(Graphics g) { int nombreBoouton = 2; int positionX
 * = Math.round(((this.getWidth()/nombreBoouton)-130)/nombreBoouton); int
 * positionY = Math.round((this.getHeight()-33)/2); // if(position<0)position=0;
 * this.boutonQuitter.setBounds(positionX, positionY, 130, 33);
 * 
 * 
 * } /*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * // this.setLayout(new Box(BoxLayout.X_AXIS));
 * 
 * 
 * Box BoxHori = Box.createHorizontalBox();
 * 
 * 
 * 
 * this.boutonAnnuler = new JButton("Annuler");
 * this.boutonAnnuler.setPreferredSize(new Dimension(130,33));
 * this.boutonAnnuler.setMaximumSize(new Dimension(130,33));
 * System.out.println("size : "
 * +boutonAnnuler.getSize().toString()+" prefereed size : "
 * +boutonAnnuler.getPreferredSize().toString());
 * System.out.println("minimuin size : "
 * +boutonAnnuler.getMinimumSize().toString(
 * )+" max size : "+boutonAnnuler.getMaximumSize().toString()); // ((Box)
 * this.getLayout()).createGlue(); BoxHori.add(Box.createGlue());
 * BoxHori.add(boutonAnnuler);
 * 
 * 
 * 
 * 
 * // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); //BorderLayout());
 * 
 * // this.boutonAnnuler = new JButton("Annuler"); // this.setMaximumSize(new
 * Dimension(200, 70)); // this.setMinimumSize(new Dimension(100, 60)); //
 * this.setPreferredSize(new Dimension(200, 65));
 * 
 * 
 * // this.add(boutonAnnuler);//,BorderLayout.WEST);
 * 
 * if (podo) { BoxHori.add(Box.createGlue());
 * BoxHori.add(boutonValider);//,BorderLayout.CENTER);
 * 
 * boutonQuitter = new JButton("Quitter"); //BoxHori.add(Box.createGlue());
 * BoxHori.add(boutonQuitter);//, BorderLayout.EAST); } else {
 * BoxHori.add(Box.createHorizontalGlue());//createGlue());
 * this.add(boutonValider);//,BorderLayout.EAST); } //
 * BoxHori.add(Box.createGlue());
 * 
 * this.add(BoxHori);
 */

// }
