/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGraphique;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author f100764
 */
public abstract class AppSidasPanel extends JPanel{
    protected int largeur;
    protected int hauteur;
    
    public AppSidasPanel(int largeur, int hauteur) {
        super();
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    public abstract void etatInitial();
    
    /**
     * Factorise le code des setBounds
     * @param d 
     * @param e
     * @param f
     * @param g
     */
    protected void ajusterDansFenetre(Component c, double d, double e, double f, double g) {
            c.setBounds((int) Math.round(largeur * d),
                            (int) Math.round(hauteur * e),
                            (int) Math.round(largeur * f),
                            (int) Math.round(hauteur * g));

    }
    
}
