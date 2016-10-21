/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class HBoxConnector extends SwingBasisConnector {

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
	public HBoxConnector(JPanel panel) {
		super(GuiType.HBOX, panel);
		BoxLayout layout = new BoxLayout(panel,BoxLayout.LINE_AXIS);
		panel.setLayout(layout);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
	}

	public JPanel getJLabel() {
		return (JPanel) getComponent();
	}

	@Override
	protected void setGuiValue(String text) {
		
	}

	@Override
	public Serializable getGuiValue() {
		return "";
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

