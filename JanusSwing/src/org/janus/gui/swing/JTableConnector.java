/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Component;

import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.janus.gui.basis.TableColumnDescription;
import org.janus.gui.enums.GuiType;
import org.janus.table.ExtendedTableModel;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class JTableConnector extends SwingTableModelConnector implements
		ListSelectionListener {
	
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
	public JTableConnector(JTable table,List<TableColumnDescription> columnDescription) {
		super(GuiType.SHOWTABLE, table);
		createColumnsFromDescriptions(table,columnDescription);
		initSelectionModel();

	}
	
	public JTable getJTable() {
		return (JTable)getComponent();
	}

	private void createColumnsFromDescriptions(JTable table,List<TableColumnDescription> columnDescription) {
		table.setAutoCreateColumnsFromModel(false);
		TableColumnModel cm = table.getColumnModel();
		int index = 0;
		for (TableColumnDescription desc : columnDescription) {
			createColumnFromDescription(cm, desc,index);
			index++;
		}
	}

	private void createColumnFromDescription(TableColumnModel cm,
			TableColumnDescription desc,int index) {
		TableColumn swingColumn = new TableColumn(index); //column.getIndex());
		swingColumn.setHeaderValue(desc.getHeader());
		cm.addColumn(swingColumn);
	}

	private void initSelectionModel() {
		DefaultListSelectionModel sm = new DefaultListSelectionModel();
		sm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sm.setLeadAnchorNotificationEnabled(false);
		getJTable().setSelectionModel(sm);
		sm.addListSelectionListener(this);
	}


	@Override
	public void SelectionChanged(int pos) {
		if (getJTable().getModel().getRowCount() > pos && pos != getJTable().getSelectedRow())
			getJTable().setRowSelectionInterval(pos, pos);

	}

	
	@Override
	public Serializable getGuiValue() {
		return (Serializable) getJTable().getModel();
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent ev) {
		if (!ev.getValueIsAdjusting()) {
			setCurrentRowInTheModel(getJTable().getSelectedRow());
		}
	}	

	@Override
	public void setModel(ExtendedTableModel tm) {
		if (tm != null) {
			getJTable().setModel(tm);
		};
	}

	@Override
	public Component decorate() {
		super.decorate();
		JScrollPane sp = new JScrollPane(getJTable());
		return sp;
	}
}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

