/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ApplicationGraphique.ApplicationGraphique;
import Questionnaire.Date;

/**
 *
 * @author f100764
 */
public class Controller {
    /**
     * Principal controller de l'applicaiton pour fair ele lien entre l'affichage et
     * les données
     */
    private ApplicationGraphique vue;
    
    public Controller(ApplicationGraphique vue) {
        this.vue = vue;
    }

    public void lancerQuestionnaire() {
        vue.showIdentificationGraphique();
    }

    public void lanceridentification(String nom, String prenom, Date dateNaissance, Boolean dejaVenu) {
        vue.unshowIdentification();
        vue.showErreur("Personne inconnue! Merci de reessayer de vous identifier...");
    }
}
