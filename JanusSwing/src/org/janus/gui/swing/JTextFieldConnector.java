package org.janus.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.janus.gui.enums.GuiType;
import org.janus.gui.enums.KeyEventType;

public class JTextFieldConnector extends SwingValueConnector implements
		java.awt.event.ActionListener, FocusListener {
    private static final Logger LOG = Logger.getLogger(JTextFieldConnector.class);
    
    
	private Color lastColor;
	private String lastValue;

	public JTextFieldConnector(JTextComponent textField) {
		super(GuiType.TEXTFIELD, textField);
		if (textField instanceof JTextField) {
			((JTextField) textField).addActionListener(this);
		}
		textField.addFocusListener(this);
		setGuiValue("");
	}
	
	public JTextComponent getTextfield() {
		return (JTextComponent)getComponent();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (KeyEventType.CTRL_D.name().equals(arg0.getActionCommand())) {
			resetToOldValue();
		}
		if (KeyEventType.COPY.name().equals(arg0.getActionCommand())) {
			setTextToClipboard(getTextfield().getText());
		}
		if (KeyEventType.PAST.name().equals(arg0.getActionCommand())) {
			getTextfield().setText(getTextFromClipboard());
		}
		setModelValue();
	}

	protected void setModelValue() {
		setModelValue(getTextfield().getText());
	}

	@Override
	protected void setGuiValue(String text) {
		if (text != null && getTextfield() != null) {
			getTextfield().setText(text);
		}
	}
	
	@Override
	protected void updateValue() {
		if (value != null && context != null) {
			Serializable s = getModelValue();
			setGuiValue(s);
		}
	}

	public void installKeys(JComponent c) {
		for (KeyEventType t : KeyEventType.values()) {
			c.registerKeyboardAction(this, t.name(), KeyStroke.getKeyStroke(t
					.getKeyEvent(), t.getMask(), false),
					JComponent.WHEN_FOCUSED);
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		lastColor = getTextfield().getBackground();
		getTextfield().setBackground(Color.yellow);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		getTextfield().setBackground(lastColor);
		safeOldValue();
		setModelValue();
	}

	public void safeOldValue() {
		lastValue = getTextfield().getText();
	}

	public void resetToOldValue() {
		getTextfield().setText(lastValue);
	}

	public static void setTextToClipboard(String text) {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection contents = new StringSelection(text);
		cb.setContents(contents, null);
	}

	public static String getTextFromClipboard() {
		String s = "";

		try {
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable content = cb.getContents(null);
			s = (String) content.getTransferData(DataFlavor.stringFlavor);
		} catch (Exception ex) {
		    LOG.error("Fehler beim uebertragen vom Clipboard",ex);
		}
		return s;
	}

	@Override
	protected Dimension getDefaultDimension() {
		FontMetrics fm = getTextfield().getFontMetrics(getTextfield().getFont());
		Border b = getTextfield().getBorder();
		float a = getWidth();
		if (a <= 0) {
			a = 5.0f;
		}
		int w = (int) (fm.charWidth('X') * a);
		int h = (int) (fm.getHeight() * 1.2f);
		if (b != null) {
			Insets i = b.getBorderInsets(getTextfield());
			return new Dimension(i.left + i.right + w, i.bottom + i.top + h);
		}
		return new Dimension(w, h);
	}

	@Override
	public Serializable getGuiValue() {
		return getTextfield().getText();
	}

}
