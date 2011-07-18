package ApplicationGraphique;

import javax.swing.*;
import java.util.*;

public class test {

	public static void main(String[] args) {
		Enumeration e = UIManager.getDefaults().keys();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			if (key.matches(".*RadioButton.*"))
				System.out.println(key + ": " + UIManager.get(key));
		}
	}
}
