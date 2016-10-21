/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JLabel;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JLabelConnector extends SwingBasisConnector {

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
	public JLabelConnector(JLabel label) {
		super(GuiType.LABEL, label);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
	}

	public JLabel getJLabel() {
		return (JLabel) getComponent();
	}

	@Override
	protected void setGuiValue(String text) {
		if (text != null) {
			getJLabel().setText(text);
		}
	}

	@Override
	public Serializable getGuiValue() {
		return getJLabel().getText();
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

