/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.io.Serializable;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.janus.gui.enums.GuiType;
import org.janus.table.ExtendedTableModel;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JListConnector extends SwingTableModelConnector implements
		ListSelectionListener {

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
	public JListConnector(JList list) {
		super(GuiType.LIST, list);
		// initSelectionModel();
	}

	public JList getList() {
		return (JList) getComponent();
	}

	private void initSelectionModel() {
		DefaultListSelectionModel sm = new DefaultListSelectionModel();
		sm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sm.setLeadAnchorNotificationEnabled(false);
		getList().setSelectionModel(sm);
		getList().addListSelectionListener(this);
		getList().setSelectedIndex(0);
	}

	@Override
	public void valueChanged(ListSelectionEvent ev) {
		if (!ev.getValueIsAdjusting()
				&& getList().getSelectedIndices().length > 0) {
			setCurrentRowInTheModel(getList().getSelectedIndex());

		}
	}

	@Override
	public void SelectionChanged(int pos) {
		if (getList().getModel().getSize() > pos
				&& pos != getList().getSelectedIndex()
				&& getList().getSelectedIndices().length > 0) {
			int oldPos = getList().getSelectedIndices()[0];
			if (oldPos != pos) {
				getList().setSelectedIndex(pos);
			}
		}

	}

	@Override
	public void setModel(ExtendedTableModel tm) {
		try {
			initSelectionModel();
			SwingComboBoxModel m = new SwingComboBoxModel(tm);
			if (getList() != null) {
				getList().setModel(m);
				//getList().setSelectedIndex(tm.getCurrentRow());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Serializable getGuiValue() {
		return getList().getSelectedIndex();
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

