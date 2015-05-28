/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JMenuBar;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JMenuBarConnector extends SwingBasisConnector implements
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
	public JMenuBarConnector(JMenuBar bar) {
		super(GuiType.MENUBAR, bar);
	}
	
	public JMenuBar getBar() {
		return (JMenuBar)getComponent();
	};

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public Serializable getGuiValue() {
		return null;
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

