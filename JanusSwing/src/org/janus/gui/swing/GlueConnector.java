/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.swing;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JComponent;
import org.janus.gui.enums.GuiType;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class GlueConnector extends SwingBasisConnector {

	/**
	 * Constructor declaration
	 * 
	 * 
	 * @param node
	 * @param name
	 * @param model
	 * 
	 * @see
	 */
	public GlueConnector(Component glue) {
		super(GuiType.GLUE, glue);
		((JComponent)glue).setAlignmentX(Component.LEFT_ALIGNMENT);
		
	}

	@Override
	protected void setGuiValue(String text) {
		
	}

	@Override
	public Serializable getGuiValue() {
		return "";
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

