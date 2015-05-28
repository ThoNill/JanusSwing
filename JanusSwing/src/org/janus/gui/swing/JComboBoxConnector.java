/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;

import org.janus.gui.enums.GuiType;
import org.janus.table.ExtendedTableModel;


/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
// extends ExtendedTableModelConnector
public class JComboBoxConnector extends SwingTableModelConnector implements
		ItemListener {

	public JComboBoxConnector(JComboBox box) {
		super(GuiType.COMBO, box);
		box.addItemListener(this);
	}

	public JComboBox getComboBox() {
		return (JComboBox)getComponent();
	}

	@Override
	public void setModel(ExtendedTableModel tm) {
		SwingComboBoxModel m = new SwingComboBoxModel(tm);
		if (getComboBox() != null) {
			getComboBox().setModel(m);
		};
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		setCurrentRowInTheModel(getComboBox().getSelectedIndex());
		performAllActions();
	}

	@Override
	public void SelectionChanged(int pos) {
		if (getComboBox() != null && getComboBox().getModel().getSize() > pos && pos != getComboBox().getSelectedIndex())
			getComboBox().setSelectedIndex(pos);
		
	}
//	 01769-9048686

	@Override
	protected Dimension getDefaultDimension() {
		FontMetrics fm = getComboBox().getFontMetrics(getComboBox().getFont());
		Border b = getComboBox().getBorder();
		int w = SwingUtilities.computeStringWidth(fm, "XXXXXXXXX");
		int h = (int) (fm.getHeight() * 1.2f);
		if (b != null) {
			;
			Insets i = b.getBorderInsets(getComboBox());
			return new Dimension(i.left + i.right + w, i.bottom + i.top + h);
		}
		return new Dimension(w, h);
	}

	@Override
	public Serializable getGuiValue() {
		return (Serializable)getComboBox().getSelectedItem();
	}

	@Override
	public void valueChanged(ListSelectionEvent ev) {
		
	}

	




}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

