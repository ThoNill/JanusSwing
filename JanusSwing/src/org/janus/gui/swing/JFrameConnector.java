package org.janus.gui.swing;

import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.janus.data.DataContext;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.RootGuiComponent;
import org.janus.gui.enums.GuiType;

public class JFrameConnector extends SwingBasisConnector implements RootGuiComponent{
	JFrame frame;
	private List<GuiComponent> components = null;
	

	public JFrameConnector(JFrame frame) {
		super(GuiType.FRAME,frame);
		this.frame = frame;
	//	frame.setLayout(new GridLayout(1,1));
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
	}
	
	public JFrame getFrame() {
		return (JFrame)getComponent();
	}

	@Override
	protected void setGuiValue(String text) {
		if (text != null) {
			getFrame().setTitle(text);

		}
	}


	@Override
	public Component getComponent() {
		return frame;
	}

	
	
	@Override
	protected Component getUpdateComponent() {
		return getFrame().getContentPane();
	}

	@Override
	public Serializable getGuiValue() {
		return getFrame().getTitle();
	}
	
	@Override
	public void addComponent(GuiComponent comp) {
		if (comp instanceof JMenuBarConnector) {
			setMenuBarConnector((JMenuBarConnector)comp);
			return;
		}
		if (comp instanceof SwingBasisConnector) {
			SwingBasisConnector childConnector = (SwingBasisConnector)comp;
			frame.getContentPane().add(childConnector.getComponent());
		}
	}

	private void setMenuBarConnector(JMenuBarConnector barConnector) {
		frame.setJMenuBar(barConnector.getBar());
	}

	public List<GuiComponent> getAllComponents() {
		return components;
	}

	public void setAllComponents(List<GuiComponent> components) {
		this.components = components;
	}
	
	public void setContext(DataContext context) {
		super.setContext(context);
		for(GuiComponent c : components) {
			if (c != this) {
			((SwingBasisConnector)c).setContext(context);
			}
		}
	}
	
}
