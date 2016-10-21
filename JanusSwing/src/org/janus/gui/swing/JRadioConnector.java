/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.event.ActionEvent;
import java.io.Serializable;

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
public class JRadioConnector extends SwingTableModelConnector implements
		java.awt.event.ActionListener {
	ExtendedTableModel tm;

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
	public JRadioConnector(JRadioGroup list) {
		super(GuiType.RADIO, list);
	}

	public JRadioGroup getRadioGroup() {
		return (JRadioGroup) getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setCurrentRowInTheModel(tm.find(arg0.getActionCommand(), 0));

	}

	@Override
	public void SelectionChanged(int pos) {
		getRadioGroup().setSelectedIndex(pos);
	}

	@Override
	public void setModel(ExtendedTableModel tm) {
		this.tm = tm;
		getRadioGroup().setModel(tm, this);
	}

	@Override
	public Serializable getGuiValue() {
		return getRadioGroup().getSelectedIndex();
	}



	@Override
	public void valueChanged(ListSelectionEvent ev) {
		

	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

