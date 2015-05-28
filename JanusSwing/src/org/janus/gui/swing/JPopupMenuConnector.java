/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JPopupMenu;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JPopupMenuConnector extends SwingBasisConnector implements
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
	public JPopupMenuConnector(JPopupMenu menu) {
		super(GuiType.POPUP, menu);
	}
	
	public JPopupMenu getMenu() {
		return (JPopupMenu)getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void setGuiValue(Serializable text) {
		if (text != null) {
			getMenu().setLabel((String)text);
		}
	}



	@Override
	public Serializable getGuiValue() {
		return getMenu().getLabel();
	};


}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

