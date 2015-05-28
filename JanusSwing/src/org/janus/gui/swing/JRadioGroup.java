package org.janus.gui.swing;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.janus.table.ExtendedTableModel;

public class JRadioGroup extends JPanel {
	ButtonGroup group;

	public JRadioGroup() {
		super();
		setAlignmentX(Component.LEFT_ALIGNMENT);
		init();
	}

	public JRadioGroup(LayoutManager arg0) {
		super(arg0);
		init();
		
	}

	public JRadioGroup(boolean arg0) {
		super(arg0);
		init();
		
	}

	public JRadioGroup(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		init();
		
	}

	public void init() {
		group = new ButtonGroup();
	}

	public void addEntry(String value, String text, ActionListener l,
			boolean selectIt) {
		JRadioButton b = new JRadioButton(text);
		b.setActionCommand(value);
		group.add(b);
		add(b);
		b.setSelected(selectIt);
		b.addActionListener(l);
	}

	public void setModel(ExtendedTableModel model, ActionListener l) {
		removeButtons(l);
		addButtons(model, l);
	}

	private void addButtons(ExtendedTableModel model, ActionListener l) {
		boolean isSelected = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			isSelected = (i == model.getCurrentRow());
			String value = model.getValueAt(i, 0).toString();
			String text = model.getValueAt(i, 1).toString();
			addEntry(value, text, l, isSelected);
		}

	}

	private void removeButtons(ActionListener l) {
		Enumeration<AbstractButton> e = group.getElements();
		while (e.hasMoreElements()) {
			AbstractButton b = e.nextElement();
			b.removeActionListener(l);
			group.remove(b);
			this.remove(b);
			e = group.getElements();
		}
	}

	public void setSelectedIndex(int pos) {
		if (pos >= getComponentCount())
			return;

		AbstractButton b = (AbstractButton) getComponent(pos);
		if (!b.isSelected()) {
			b.setSelected(true);
		}
	}
	
	public int getSelectedIndex() {
		int index = 0;
		Enumeration<AbstractButton> e = group.getElements();
		while (e.hasMoreElements()) {
			AbstractButton b = e.nextElement();
			if (b.isSelected())
				return index;
			index++;
		}
		return -1;
	}
}
