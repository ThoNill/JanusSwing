/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.apache.log4j.Logger;
import org.janus.actions.DataValue;
import org.janus.data.DataContext;
import org.janus.dict.interfaces.ActionListener;
import org.janus.table.ExtendedTableModel;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */

public class SwingComboBoxModel extends AbstractListModel implements
		TableModelListener, ComboBoxModel, ActionListener {

	ExtendedTableModel model = null;
	int pos = 0;
	static Logger logger = Logger.getLogger("SwingComboBoxModel");

	/**
	 * Constructor declaration
	 * 
	 * 
	 * @param model
	 * 
	 * @see
	 */
	public SwingComboBoxModel(ExtendedTableModel model) {
		this.model = model;
		model.addTableModelListener(this);
	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @param i
	 * 
	 * @return
	 * 
	 * @see
	 */
	@Override
	public Object getElementAt(int i) {

		if (model.getColumnCount() < 2) {
			return model.getValueAt(i, 0);
		}

		return model.getValueAt(i, 1);
	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @return
	 * 
	 * @see
	 */
	@Override
	public int getSize() {

		return model.getRowCount();

	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @return
	 * 
	 * @see
	 */
	@Override
	public Object getSelectedItem() {

		Object erg = null;

		erg = model.getValueAt(pos, 1);

		return erg;

	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @param obj
	 * 
	 * @see
	 */
	public int searchTableEntry(int column, Object obj) {

		return model.find(obj, column);

	}

	@Override
	public void setSelectedItem(Object obj) {

		this.pos = searchTableEntry(1, obj);

		fireContentsChanged(this, 0, model.getRowCount());

	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @param e
	 * 
	 * @see
	 */
	@Override
	public void tableChanged(TableModelEvent e) {

		pos = model.getCurrentRow();
		fireContentsChanged(this, 0, model.getRowCount());

	}

	@Override
	public void actionPerformed(Object a, DataContext data)
			{
		if (a instanceof DataValue) {
			DataValue v = (DataValue) a;
			int pos = searchTableEntry(0, v.getObject(data));

			if (pos >= 0 && pos != model.getCurrentRow()) {
				model.setCurrentRow(pos);
				fireContentsChanged(this, 0, model.getRowCount());
			}
		}

	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/
