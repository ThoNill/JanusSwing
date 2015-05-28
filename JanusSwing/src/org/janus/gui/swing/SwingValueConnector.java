package org.janus.gui.swing;

import javax.swing.JComponent;

import org.janus.dict.actions.NamedActionValue;
import org.janus.gui.enums.GuiType;

public abstract class SwingValueConnector extends SwingBasisConnector {

	public SwingValueConnector(GuiType type, JComponent component) {
		super(type, component);
	}


	@Override
	public void setValue(NamedActionValue value) {
		super.setValue(value);
	}
}