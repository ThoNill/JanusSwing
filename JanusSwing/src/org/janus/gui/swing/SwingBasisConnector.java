package org.janus.gui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.janus.data.DataContext;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.NamedActionValue;
import org.janus.dict.helper.ID;
import org.janus.dict.interfaces.ActionListener;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;


public abstract class SwingBasisConnector implements PropertyChangeListener,
		GuiComponent, ActionListener {

	protected Component component;
	private TitledBorder titleBorder = null;
	private String style;
	private int id;
	private GuiType type;
	protected NamedActionValue value;
	protected DataContext context;
	private Vector<GuiComponent> childComponents = null;
	private EventActionBinderList eventActionList;

	public SwingBasisConnector(GuiType type, Component component) {
		super();
		if (component == null) {
			throw new IllegalArgumentException(" Component component = null ");
		}
		id = ID.getId();
		this.type = type;
		this.component = component;

		if (!(component != null && component instanceof JMenu)) {
			component.addPropertyChangeListener(this);
		}
	}

	public TitledBorder getTitleBorder() {
		return titleBorder;
	}

	public void setTitleBorder(TitledBorder titleBorder) {
		this.titleBorder = titleBorder;
	}

	public Component getComponent() {
		return component;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public void setField(GuiField field, Serializable value) {
		switch (field) {
		case LABEL:
			setComponentLabel((String) value);
			break;
		case BACKGROUND:
			component.setBackground((Color) value);
			break;
		case FOREGROUND:
			component.setForeground((Color) value);
			break;
		case FONT:
			component.setFont((Font) value);
			break;
		case ENABLED:
			component.setEnabled(((Boolean) value).booleanValue());
			break;
		case VISIBLE:
			component.setVisible(((Boolean) value).booleanValue());
			break;
		case FOCUS:
			if (value.equals(Boolean.TRUE)) {
				component.requestFocus();
			}
			break;
		case STYLE:
			setStyle((String) value);
			break;
		case TOOLTIP:
			if (component instanceof JComponent) {
				((JComponent) component).setToolTipText((String) value);
			}
			break;
		case WIDTH:
			component
					.setSize(new Dimension((int) value, component.getHeight()));
			break;
		case HEIGHT:
			component
					.setSize(new Dimension(component.getHeight(), (int) value));
			break;
		case X:
			component.setLocation((int) value, component.getY());
			break;
		case Y:
			component.setLocation(component.getX(), (int) value);
			break;
		}
	}

	private void setComponentLabel(String value) {
		if (value != null && component instanceof JComponent
				&& !(component instanceof JMenuItem)
				&& !(component instanceof JMenu)
				&& !(component instanceof JButton)
				&& !(component instanceof JLabel)) {
			if (titleBorder == null) {
				titleBorder = new TitledBorder(value);
				((JComponent) component).setBorder(titleBorder);
			} else {
				titleBorder.setTitle(value);
			}
		}

	}

	protected void setFieldInSwingThread(GuiField field, Serializable value) {
		if (SwingUtilities.isEventDispatchThread()) {
			setField(field, value);
		} else {

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					setField(field, value);
				}
			});
		}

	}

	@Override
	public void setGuiValue(Serializable v) {
		if (SwingUtilities.isEventDispatchThread()) {
			setGui(v);
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					setGui(v);
				}
			});
		}

	}

	protected void setGui(Serializable v) {
		setGuiValue((v == null) ? "" : v.toString());
	}

	protected void setGuiValue(String text) {

	}

	protected Component getUpdateComponent() {
		return component;
	}

	public void setSize(Dimension dim) {
		component.setPreferredSize(dim);
		component.setMaximumSize(component.getPreferredSize());

	}

	protected Dimension getXDimension() {
		FontMetrics fm = component.getFontMetrics(component.getFont());
		return new Dimension(fm.charWidth('X'), fm.getHeight());
	}

	protected Dimension getDefaultDimension() {
		return new Dimension(10, 10);
	}

	public Component decorate() {
		component.setSize(getDefaultDimension());
		return component;
	}

	@Override
	public Font getFont() {
		return component.getFont();
	}

	@Override
	public void setFont(Font font) {
		setFieldInSwingThread(GuiField.FONT, font);
	}

	@Override
	public Color getForeground() {
		return component.getForeground();
	}

	@Override
	public void setForeground(Color foreground) {
		setFieldInSwingThread(GuiField.FOREGROUND, foreground);
	}

	@Override
	public void setBackground(Color c) {
		setFieldInSwingThread(GuiField.BACKGROUND, c);

	}

	@Override
	public Color getBackground() {
		return component.getBackground();
	}

	@Override
	public void setEnabled(boolean b) {
		setFieldInSwingThread(GuiField.ENABLED, b);

	}

	@Override
	public boolean isEnabled() {
		return component.isEnabled();
	}

	@Override
	public void setVisible(boolean b) {
		setFieldInSwingThread(GuiField.VISIBLE, b);
	}

	@Override
	public boolean isVisible() {
		return component.isVisible();
	}

	@Override
	public void setFocus(boolean b) {
		setFieldInSwingThread(GuiField.FOCUS, b);
	}

	@Override
	public boolean hasFocus() {
		return component.hasFocus();
	}

	@Override
	public void setStyle(String t) {
		style = t;
	}

	@Override
	public String getStyle() {
		return style;
	}

	@Override
	public void setLabel(String t) {
		setFieldInSwingThread(GuiField.LABEL, t);
	}

	@Override
	public String getLabel() {
		if (titleBorder != null) {
			return titleBorder.getTitle();
		}
		return null;
	}

	@Override
	public void setTooltip(String t) {
		setFieldInSwingThread(GuiField.TOOLTIP, t);
	}

	@Override
	public String getTooltip() {
		if (component instanceof JComponent) {
			return ((JComponent) component).getToolTipText();
		}
		return null;
	}

	@Override
	public void setWidth(float w) {
		setFieldInSwingThread(GuiField.WIDTH, new Float(w));

	}

	@Override
	public float getWidth() {
		return component.getWidth();
	}

	@Override
	public void setHeight(float h) {
		setFieldInSwingThread(GuiField.HEIGHT, new Float(h));

	}

	@Override
	public float getHeight() {
		return component.getHeight();
	}

	@Override
	public float getX() {
		return component.getX();
	}

	@Override
	public void setX(float x) {
		setFieldInSwingThread(GuiField.X, new Float(x));
	}

	@Override
	public float getY() {
		return component.getY();
	}

	@Override
	public void setY(float y) {
		setFieldInSwingThread(GuiField.Y, new Float(y));

	}

	public void setModelValue(Serializable v) {
		value.setObject(context, v);

	}

	public Serializable getModelValue() {
		return value.getObject(context);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void validate() {
		component.invalidate();

	}

	@Override
	public GuiType getGuiType() {
		return type;
	}

	public DataContext getContext() {
		return context;
	}

	public void setContext(DataContext context) {
		this.context = context;
		if (eventActionList!=null) {
			eventActionList.setContext(context);
		}
		updateValue();
	}

	protected void updateValue() {
		if (value != null && context != null) {
			setGuiValue(getModelValue());
		}
	}

	public NamedActionValue getValue() {
		return value;
	}

	public void setValue(NamedActionValue value) {
		this.value = value;
		value.addActionListener(this);
		updateValue();
	}

	@Override
	abstract public Serializable getGuiValue();

	@Override
	public void addComponent(GuiComponent comp) {
		
		if (comp instanceof JPopupMenuConnector) {
			JPopupMenuConnector popup = (JPopupMenuConnector)comp;
			JPopupMenu popupMenu = popup.getMenu();
			component.addMouseListener(new PopupListener(popupMenu));
		}
		
		if (childComponents == null) {
			childComponents = new Vector<>();
		}
		childComponents.add(comp);

		if (comp instanceof SwingBasisConnector
				&& component instanceof JComponent) {
			((JComponent) component).add(((SwingBasisConnector) comp)
					.getComponent());
		}
	}

	@Override
	public List<GuiComponent> getChildComponents() {
		if (childComponents == null) {
			return Collections.emptyList();
		}
		return childComponents;
	}

	@Override
	public void actionPerformed(Object a, DataContext data) {

		context = data;
		setGuiValue(getModelValue());
	}

	public void performAllActions() {
		ActionDictionary dict = (ActionDictionary) context.getDataDescription();
		dict.perform(context);
	}

	public void setEventActionList(EventActionBinderList eventActionList) {
		if (component instanceof JComponent && eventActionList.size()>0) {
			this.eventActionList = eventActionList;
			eventActionList.register((JComponent)component);
		}
	}
}
