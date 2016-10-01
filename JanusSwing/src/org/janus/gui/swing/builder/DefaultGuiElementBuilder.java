package org.janus.gui.swing.builder;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.List;
import java.util.List; import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.html.StyleSheet;

import org.janus.actions.Action;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.NamedActionValue;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.TableColumnDescription;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;
import org.janus.gui.swing.EventActionBinder;
import org.janus.gui.swing.EventActionBinderList;
import org.janus.gui.swing.GlueConnector;
import org.janus.gui.swing.HBoxConnector;
import org.janus.gui.swing.JButtonConnector;
import org.janus.gui.swing.JCheckBoxConnector;
import org.janus.gui.swing.JComboBoxConnector;
import org.janus.gui.swing.JFrameConnector;
import org.janus.gui.swing.JLabelConnector;
import org.janus.gui.swing.JListConnector;
import org.janus.gui.swing.JMenuBarConnector;
import org.janus.gui.swing.JMenuConnector;
import org.janus.gui.swing.JMenuItemConnector;
import org.janus.gui.swing.JPopupMenuConnector;
import org.janus.gui.swing.JRadioConnector;
import org.janus.gui.swing.JRadioGroup;
import org.janus.gui.swing.JTabConnector;
import org.janus.gui.swing.JTabbedPaneConnector;
import org.janus.gui.swing.JTableConnector;
import org.janus.gui.swing.JTextFieldConnector;
import org.janus.gui.swing.SwingBasisConnector;
import org.janus.gui.swing.VBoxConnector;
import org.janus.table.DefaultExtendedTableWrapper;
import org.janus.table.DefaultTableColumn;
import org.jdom2.Element;

public class DefaultGuiElementBuilder implements GuiElementBuilder {
	private static StyleSheet styleSheet = new StyleSheet();

	public DefaultGuiElementBuilder() {

	}
	
	@Override
	public GuiComponent createGuiElement(Element elem, Action a,ActionDictionary dict) {
		GuiComponent comp = createGuiElementIntern(elem,a);
		EventActionBinderList list = getEventActionBinderList(elem);
		if (comp instanceof SwingBasisConnector) {
			SwingBasisConnector connector = (SwingBasisConnector)comp;
			connector.setEventActionList(list);
			
		}
		return comp;
	}

	private GuiComponent createGuiElementIntern(Element elem, Action a) {
		GuiType type = GuiType.valueOf(elem.getName());
		switch (type) {
		case GUI:
			return new JFrameConnector(new JFrame());
		case GLUE:
			return createGlue(elem);
		case SHOWTABLE:
			return createTableConnector(elem, a);
		case VBOX:
			return createVBoxConnector(elem);
		case HBOX:
			return createHBoxConnector(elem);
		case TEXTFIELD:
			return createTextFieldConnector(elem, a);
		case MONEYFIELD:
			return createTextFieldConnector(elem, a);
		case DATEFIELD:
			return createTextFieldConnector(elem, a);
		case LABEL:
			return createLabelConnector(elem);
		case LIST:
			return createListConnector(elem, a);
		case COMBO:
			return createComboConnector(elem, a);
		case CHECK:
			return createCheckConnector(elem, a);
		case RADIO:
			return createRadioConnector(elem, a);
		case BUTTON:
			return createButtonConnector(elem, a);
		case TAB:
			return createTabConnector(elem, a);
		case TABS:
			return createTabsConnector(elem, a);
		case MENU:
			return new JMenuConnector(new JMenu(getTextFromElement(elem)));
		case MENUBAR:
			return new JMenuBarConnector(new JMenuBar());
		case MENUITEM:
			return new JMenuItemConnector(new JMenuItem(
					getTextFromElement(elem)), a);
		case POPUP:
			return new JPopupMenuConnector(new JPopupMenu(
					getTextFromElement(elem)));
		case FRAME:
			return null;

		}
		return null;
	}

	private GuiComponent createGlue(Element elem) {
		GuiType type = GuiType.valueOf(elem.getParentElement().getName());

		if (elem.getChildren().size() > 0) {
			JPanel panel = new JPanel(new GridLayout(1,1));
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			return new GlueConnector(panel);
				
			
		} else {

			if (type.equals(GuiType.VBOX)) {
				return new GlueConnector(Box.createVerticalGlue());
			}
			if (type.equals(GuiType.HBOX)) {
				return new GlueConnector(Box.createHorizontalGlue());
			}
		}
		return null;
	}

	private JButtonConnector createButtonConnector(Element elem, Action a) {
		JButton button = new JButton(getTextFromElement(elem));
		attributeSetzen(elem, button);
		return new JButtonConnector(button, a);
	}

	private HBoxConnector createHBoxConnector(Element elem) {
		JPanel panel = new JPanel();
		attributeSetzen(elem, panel);
		return new HBoxConnector(panel);
	}

	private VBoxConnector createVBoxConnector(Element elem) {
		JPanel panel = new JPanel();
		attributeSetzen(elem, panel);
		return new VBoxConnector(panel);
	}

	private JLabelConnector createLabelConnector(Element elem) {
		JLabel label = new JLabel(getTextFromElement(elem));
		attributeSetzen(elem, label);
		return new JLabelConnector(label);
	}

	private GuiComponent createTabConnector(Element elem, Action a) {
		JPanel panel = new JPanel();
		attributeSetzen(elem, panel);
		JTabConnector gui = new JTabConnector(panel,
				elem.getAttributeValue("name"));
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createTabsConnector(Element elem, Action a) {
		JTabbedPane pane = new JTabbedPane();
		attributeSetzen(elem, pane);
		JTabbedPaneConnector gui = new JTabbedPaneConnector(pane);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createTableConnector(Element elem, Action a) {
		List<TableColumnDescription> columnDescriptions = new ArrayList<>();
		boolean withColumns = false;
		for (Element e : elem.getChildren()) {
			if ("COLUMN".equals(e.getName())) {
				withColumns = true;
				columnDescriptions.add(new TableColumnDescription(e
						.getAttributeValue("renderer"), e
						.getAttributeValue("header"), e
						.getAttributeValue("name")));
			}
		}
		if (!withColumns && a instanceof DefaultExtendedTableWrapper) {
			DefaultExtendedTableWrapper wrapper = (DefaultExtendedTableWrapper) a;
			for (DefaultTableColumn c : wrapper.getColumns()) {
				columnDescriptions.add(new TableColumnDescription(c.getFormat()
						.getClass().getName(), c.getColumnName(), c
						.getColumnName()));
			}
		}
		JTable table = new JTable();
		attributeSetzen(elem, table);
		JTableConnector gui = new JTableConnector(table, columnDescriptions);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createRadioConnector(Element elem, Action a) {
		JRadioGroup group = new JRadioGroup();
		attributeSetzen(elem, group);
		JRadioConnector gui = new JRadioConnector(group);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createCheckConnector(Element elem, Action a) {
		JCheckBox box = new JCheckBox();
		attributeSetzen(elem, box);
		JCheckBoxConnector gui = new JCheckBoxConnector(box);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createComboConnector(Element elem, Action a) {
		JComboBox<String> combo = new JComboBox<String>();
		attributeSetzen(elem, combo);
		JComboBoxConnector gui = new JComboBoxConnector(combo);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createListConnector(Element elem, Action a) {
		JList<String> list = new JList<String>();
		attributeSetzen(elem, list);
		JListConnector gui = new JListConnector(list);
		setValue(gui, a);
		return gui;
	}

	private GuiComponent createTextFieldConnector(Element elem, Action a) {
		JTextField field = new JTextField(10);
		field.setAlignmentX(Component.LEFT_ALIGNMENT);

		Font f = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);
		field.setFont(f);
		FontMetrics fm = field.getFontMetrics(f);
		int h = (int) (fm.getHeight() * 1.4f);
		int w = fm.charWidth('X') * 10;
		Dimension dim = new Dimension(w, h);
		field.setSize(dim);
		field.setPreferredSize(dim);
		field.setMaximumSize(dim);
		field.setMinimumSize(dim);

		attributeSetzen(elem, field);
		JTextFieldConnector gui = new JTextFieldConnector(field);
		setValue(gui, a);
		return gui;
	}

	private void setValue(SwingBasisConnector gui, Action a) {
		if (a instanceof NamedActionValue) {
			gui.setValue(((NamedActionValue) a));
		}
	}

	private String getTextFromElement(Element elem) {
		String t = elem.getAttributeValue("text");
		if (t == null) {
			t = "";
		}
		return t;
	}

	private void attributeSetzen(Element elem, JComponent comp) {
		comp.setAlignmentX(Component.LEFT_ALIGNMENT);

		setAttribut(elem, GuiField.FOREGROUND,
				(String value) -> comp.setForeground(styleSheet
						.stringToColor(value)));
		setAttribut(elem, GuiField.BACKGROUND,
				(String value) -> comp.setBackground(styleSheet
						.stringToColor(value)));
		setAttribut(elem, GuiField.FONT,
				(String value) -> comp.setFont(Font.decode((value))));
	}

	private void setAttribut(Element elem, GuiField field,
			SwingAttributSetter setter) {
		String xmlValue = elem.getAttributeValue(field.name().toLowerCase());
		if (xmlValue != null) {
			setter.setAttribut(xmlValue);
		}
	}
	
	private EventActionBinderList getEventActionBinderList(Element elem) {
		EventActionBinderList list = new EventActionBinderList();
		for(Element e : elem.getChildren()) {
			if ("EVENT".equals(e.getName())) {
				EventActionBinder binder = new EventActionBinder(e.getAttributeValue("name"), e.getAttributeValue("action"));
				list.addElement(binder);
			}
		}
		return list;
	}

}
