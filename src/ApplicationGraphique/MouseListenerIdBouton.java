package ApplicationGraphique;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MouseListenerIdBouton implements MouseListener {

	protected static MouseListenerIdBouton instance;
		
	public abstract void changerCouleur(Color c);
	
	
	@Override
	public abstract void mouseClicked(MouseEvent arg0);
	@Override
	public abstract void mouseEntered(MouseEvent arg0);
	@Override
	public abstract void mouseExited(MouseEvent arg0);
	@Override
	public abstract void mousePressed(MouseEvent arg0);
	@Override
	public abstract void mouseReleased(MouseEvent arg0);

}
