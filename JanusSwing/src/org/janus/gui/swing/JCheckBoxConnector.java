/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JCheckBoxConnector extends SwingBasisConnector implements
		ItemListener {

	public JCheckBoxConnector(JCheckBox box) {
		super(GuiType.CHECK, box);
		box.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		setModelValue("" + getCheckBox().isSelected());
	}

	@Override
	protected void setGuiValue(String text) {
		boolean b = Boolean.parseBoolean(text);
		if (getCheckBox() != null) {
			getCheckBox().setSelected(b);
		}
		;
	}

	@Override
	protected Dimension getDefaultDimension() {
		FontMetrics fm = getCheckBox().getFontMetrics(getCheckBox().getFont());
		Border b = getCheckBox().getBorder();
		int w = SwingUtilities.computeStringWidth(fm, "XXXXXXXXX");
		int h = (int) (fm.getHeight() * 1.1f);
		if (b != null) {
			Insets i = b.getBorderInsets(getCheckBox());
			return new Dimension(i.left + i.right + w, i.bottom + i.top + h);
		}
		return new Dimension(w, h);
	}


	JCheckBox getCheckBox() {
		return (JCheckBox)getComponent();
	}

	@Override
	public Serializable getGuiValue() {
		return getCheckBox().isSelected();
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

