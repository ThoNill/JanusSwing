package org.janus.gui.swing;

import org.apache.log4j.Logger;
import org.janus.actions.Action;
import org.janus.actions.ActionPerformException;
import org.janus.data.DataContext;

public class ActionWithContext {
    private static final Logger LOG = Logger.getLogger(ActionWithContext.class);
	private DataContext context;
	private Action action;


	public ActionWithContext(DataContext session, 
			Action action) {
		super();
		this.context = session;
		this.action = action;
	}

	public void actionPerformed() {
		try {
			action.perform(context);
		} catch (Exception e) {
			LOG.error("Fehler in Action",e);;
			throw new ActionPerformException(e);
		}
	}


}
