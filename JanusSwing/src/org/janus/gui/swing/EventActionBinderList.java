package org.janus.gui.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.janus.data.DataContext;

public class EventActionBinderList {
	List<EventActionBinder> list = new ArrayList<>();

	public EventActionBinderList() {
		
	}

	public void addElement(EventActionBinder obj) {
		list.add(obj);
	}

	public void register(JComponent component) {
		for( EventActionBinder b : list) {
			b.register(component);
		}
	}
	
	public void setContext(DataContext context) {
		for( EventActionBinder b : list) {
			b.setContext(context);
		}
	}

	public int size() {
		return list.size();
	}
	
}
