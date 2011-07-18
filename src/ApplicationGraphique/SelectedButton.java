/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGraphique;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Gatean Boismal
 */
class SelectedButton extends JButton{
    private Boolean isSelected;
    
    public SelectedButton(String s) {
        super(s);
        isSelected = false;
    }
    
    public void setSelectedSurChargee(Boolean b) {
        isSelected = b;
        if(b) {
            this.setBackground(Color.red);
        } else {
            this.setBackground(Color.WHITE);
        }
    }
    
    public Boolean getSelected() {
        return isSelected;
    }
}
