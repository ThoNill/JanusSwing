package org.janus.gui.swing;

import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.janus.gui.enums.GuiType;

public class JTabConnector extends SwingValueConnector implements
		ChangeListener {

	private JPanel  pane;
	private String name;

	public JTabConnector(JPanel pane,String name) {
		super(GuiType.TABS,pane);
		this.name = name;
		this.pane = pane;
		this.pane.setName(name);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}

	@Override
	public Serializable getGuiValue() {
		return null;
	}

	protected String getName() {
		return name;
	}
	
	


}
