package org.janus.gui.swing;

import java.io.Serializable;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiType;

public class JTabbedPaneConnector extends SwingValueConnector implements
		ChangeListener {

	JTabbedPane pane;

	public JTabbedPaneConnector(JTabbedPane pane) {
		super(GuiType.TABS, pane);
		this.pane = pane;
		pane.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (context != null) {
			value.setObject(context, "" + getTabbedPane().getSelectedIndex());
		}
	}

	@Override
	protected void setGuiValue(String text) {
		int i = Integer.parseInt(text);
		if (getTabbedPane() != null && i >= 0
				&& i < getTabbedPane().getTabCount()) {
			getTabbedPane().setSelectedIndex(i);
		}

	}

	private JTabbedPane getTabbedPane() {
		return (JTabbedPane) getComponent();
	}

	@Override
	public Serializable getGuiValue() {
		if (getTabbedPane() != null) {
			return getTabbedPane().getSelectedIndex();
		}
		return -1;
	}

	@Override
	public void addComponent(GuiComponent comp) {
		if (comp instanceof JTabConnector) {
			JTabConnector childConnector = (JTabConnector) comp;
			pane.addTab(childConnector.getName(), childConnector.getComponent());
		}
	}
}
