/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGraphique;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ErrorWindow.java
 *
 * Created on 29 mars 2011, 14:37:34
 */


/**
 *
 * @author Gaetan BOISMAL
 */
public class Erreur extends AppSidasPanel{
    private JLabel title;
    private JLabel message;
    private JLabel icone;
    
    public Erreur(int largeur, int hauteur) {
        super(largeur, hauteur);
    }

    @Override
    public void etatInitial() {
        title = new JLabel("Une erreur est survenue: ");
        ajusterDansFenetre(title, 0.1, 0.05, 0.8, 0.12);
        icone = new JLabel();
        icone.setIcon(new ImageIcon("attention_pti.jpg"));
        ajusterDansFenetre(icone, 0.4, 0.05, 0.8, 0.12);
        message = new JLabel();
        ajusterDansFenetre(message, 0.4, 0.20, 0.8, 0.12);
        title.setVisible(true);
        icone.setVisible(true);
        message.setVisible(true);
    }
    
    public void setMessage(String msg) {
        message.setText(msg);
    }
  
}
