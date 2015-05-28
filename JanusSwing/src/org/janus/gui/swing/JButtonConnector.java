/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.janus.actions.Action;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;


/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JButtonConnector extends SwingBasisConnector implements
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
	public JButtonConnector(JButton box, Action action) {
		super(GuiType.BUTTON, box);
		this.action = action;
		box.addActionListener(this);
	}

	public JButton getButton() {
		return (JButton) getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SessionInterface.performAction(action,context);
	}

	@Override
	public void setField(GuiField field, Serializable value) {

		switch (field) {
		case LABEL:
			getButton().setText((String)value);
			break;
		default:
			super.setField(field, value);
			break;
		}
	}

	@Override
	protected Dimension getDefaultDimension() {
		FontMetrics fm = getButton().getFontMetrics(getButton().getFont());
		Border b = getButton().getBorder();
		int w = (int) (SwingUtilities.computeStringWidth(fm, getButton()
				.getText()) * 1.4);
		int h = (int) (fm.getHeight() * 1.3f);
		if (b != null) {
			;
			Insets i = b.getBorderInsets(getButton());
			return new Dimension(i.left + i.right + w, i.bottom + i.top + h);
		}
		return new Dimension(w, h);
	}

	@Override
	public void setGuiValue(String  text) {
		getButton().setText(text);
	}

	@Override
	public Serializable getGuiValue() {
		return null;
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

