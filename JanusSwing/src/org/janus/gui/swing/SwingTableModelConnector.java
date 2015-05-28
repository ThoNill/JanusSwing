package org.janus.gui.swing;

import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;

import org.janus.dict.actions.NamedActionValue;
import org.janus.dict.actions.PageValue;
import org.janus.gui.enums.GuiType;
import org.janus.table.DefaultExtendedTableWrapper;
import org.janus.table.ExtendedTableModel;

public abstract class SwingTableModelConnector extends SwingBasisConnector {

	TableModel lastModel = null;
	int lastRow = -1;
	protected DefaultExtendedTableWrapper tableWrapper;

	public SwingTableModelConnector(GuiType type, JComponent component) {
		super(type, component);
	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @param ev
	 * 
	 * @see
	 */
	
	public abstract void SelectionChanged(int pos);
	public abstract void valueChanged(ListSelectionEvent ev);
	
	@Override
	public void setValue(NamedActionValue value) {
		super.setValue(value);
		if (value.getAction() instanceof DefaultExtendedTableWrapper) {
			tableWrapper = (DefaultExtendedTableWrapper)value.getAction();
			tableWrapper.getCurrentRow().addActionListener(this);
		}
	}

	private int getCurrentRowInTheModel() {
		return tableWrapper.getCurrentRow(context);
		
	}


	void setCurrentRowInTheModel(int selectedRow) {
		PageValue c = tableWrapper.getCurrentRow();
		c.setObject(context,new Integer(selectedRow));
		
	}

	@Override
	public void setGuiValue(Serializable tm) {

		if (tm instanceof ExtendedTableModel) {
			ExtendedTableModel aktuellesModel = (ExtendedTableModel)tm;
			if (lastModel == null || !lastModel.equals(aktuellesModel)) {
				lastModel = aktuellesModel;
				setModel(aktuellesModel);
				lastRow = -1;
			}
	
			int aktuelleRow = getCurrentRowInTheModel();
			if (lastRow != aktuelleRow) {
				lastRow = aktuelleRow;
				SelectionChanged(aktuelleRow);
			}
		} 
		if (tm instanceof Integer) {
			int aktuelleRow = ((Integer)tm).intValue();
			if (lastRow != aktuelleRow) {
				lastRow = aktuelleRow;
				SelectionChanged(aktuelleRow);
			}
		}
	}

	public abstract void setModel(ExtendedTableModel tm);
	

}