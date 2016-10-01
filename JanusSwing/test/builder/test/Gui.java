package builder.test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.janus.binder.BindWalker;
import org.janus.binder.gui.GuiBuilderWalker;
import org.janus.builder.BuilderWalker;
import org.janus.data.DataContext;
import org.janus.dict.actions.ActionDictionary;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.JFrameConnector;
import org.janus.gui.swing.SwingBasisConnector;
import org.janus.gui.swing.builder.DefaultGuiElementBuilder;
import org.jdom2.Document;
import org.junit.Assert;
import org.junit.Test;

import toni.druck.xml.XMLDocumentLoader;

public class Gui {
    private static final Logger LOG = Logger.getLogger(Gui.class);

	@Test
	public void loadAndBind() {
		try {
			Document page = new XMLDocumentLoader()
					.createDocument("pages/GuiPage.xml");
			BuilderWalker walker = new TestBuilderWalker("GuiPage");
			ActionDictionary dict = new ActionDictionary("test");
			walker.setDict(dict);
			walker.walkAlong(page);
			
			BindWalker bindWalker = new TestBinderWalker();
			bindWalker.walkAlong(page);
			bindWalker.bind(dict);			
			
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			GuiBuilderWalker guiWalker = new GuiBuilderWalker(elementBuilder);
			guiWalker.setDict(dict);
			guiWalker.walkAlong(page);
			
			GuiComponent comp =guiWalker.getRoot();
	
			assertNotNull(comp);
			assertTrue(comp instanceof JFrameConnector);

		} catch (Exception e) {
			LOG.error("Fehler",e);;
			Assert.fail("Exception " + e.getMessage());
		}
	}	
	
	public static void main(String args[]) {
		try {
			Document page = new XMLDocumentLoader()
					.createDocument("pages/GuiPage.xml");
			BuilderWalker walker = new TestBuilderWalker("GuiPage");
			ActionDictionary dict = new ActionDictionary("test");
			walker.setDict(dict);
			walker.walkAlong(page);
			
			BindWalker bindWalker = new TestBinderWalker();
			bindWalker.walkAlong(page);
			bindWalker.bind(dict);			
			
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			GuiBuilderWalker guiWalker = new GuiBuilderWalker(elementBuilder);
			guiWalker.setDict(dict);
			guiWalker.walkAlong(page);
			
			GuiComponent comp =guiWalker.getRoot();
	
			assertNotNull(comp);
			assertTrue(comp instanceof JFrameConnector);
			
			DataContext context = dict.createDataContext();
			
			for(GuiComponent c : guiWalker.getComponents()) {
				((SwingBasisConnector)c).setContext(context);
			}
			
			JFrame frame = ((JFrameConnector)comp).getFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setSize(100,100);
			frame.setVisible(true);
			

		} catch (Exception e) {
			LOG.error("Fehler",e);;
			Assert.fail("Exception " + e.getMessage());
		}
	}	
	
	
}
