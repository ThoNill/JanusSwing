/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JMenuItem;

import org.janus.actions.Action;
import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JMenuItemConnector extends SwingBasisConnector implements
		java.awt.event.ActionListener {
	Action action;


	/**
	 * Constructor declaration
	 * 
	 * 
	 * @param node
	 * @param name
	 * @param action
	 * 
	 * @see
	 */
	public JMenuItemConnector(JMenuItem item,Action action) {
		super(GuiType.MENUITEM, item);
		this.action = action;
		item.addActionListener(this);

	}
	public JMenuItem getMenuItem() {
		return (JMenuItem)getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SessionInterface.performAction(action,context);
	}

	@Override
	public void setGuiValue(Serializable text) {
		if (text != null) {
			getMenuItem().setText((String)text);
		}
	}
	@Override
	public Serializable getGuiValue() {
		return getMenuItem().getText();
	}



}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

