/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JMenu;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JMenuConnector extends SwingBasisConnector implements
		java.awt.event.ActionListener {
	

	/**
	 * Constructor declaration
	 * 
	 * 
	 * @param node
	 * @param name
	 * @param model
	 * 
	 * @see
	 */
	public JMenuConnector(JMenu menu) {
		super(GuiType.MENU, menu);
	}
	
	public JMenu getMenu() {
		return (JMenu)getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void setGuiValue(Serializable text) {
		if (text != null) {
			getMenu().setText((String)text);
		}
	}



	@Override
	public Serializable getGuiValue() {
		return getMenu().getText();
	};


}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

