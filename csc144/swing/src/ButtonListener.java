import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * a class to listen to the button clicks on the window
 * 
 * @author open
 * 
 */

public class ButtonListener implements ActionListener {

	// the panel to repaint
	private JPanel panel;

	public ButtonListener(JPanel p) {
		panel = p;
	}

	
	/**
	 * repaints the panel whenever the button is clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		if (panel != null && e.getActionCommand().equals("Repaint")) {
			//for a button, getActionCommand () returns the string on the button
			panel.repaint();
		}
	}

}
